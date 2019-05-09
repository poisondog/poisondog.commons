/*
 * Copyright (C) 2014 Adam Huang <poisondog@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package poisondog.vfs;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.io.GetResourceUrl;
import poisondog.log.Log;

/**
 * @author poisondog <poisondog@gmail.com>
 */
public class LocalFileFactoryTest {
	private LocalFileFactory mFactory;
	private long time;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mFactory = new LocalFileFactory();
		time = System.currentTimeMillis();
	}

	@Test
	public void testResolveFileForData() throws Exception {
		IFile file = mFactory.getFile("file:/tmp/" + time + ".tmp");
		Assert.assertFalse(file.isExists());
		file.create();
		Assert.assertTrue(file.isExists());
		Assert.assertEquals(FileType.DATA, file.getType());
		Assert.assertEquals("file:/tmp/" + time + ".tmp", file.getUrl());
		file.delete();
	}

	@Test
	public void testIllegalFileName() throws Exception {
		IFile illegalData = mFactory.getFile("file:/tmp/t%e !+st.tmp");
		illegalData.create();
		Assert.assertEquals("file:/tmp/t%e !+st.tmp", illegalData.getUrl());
		Assert.assertEquals("t%e !+st.tmp", illegalData.getName());
		illegalData.delete();

		IFile illegalFolder = mFactory.getFile("file:/tmp/t%e !s+t/");
		illegalFolder.create();
		Assert.assertEquals("file:/tmp/t%e !s+t/", illegalFolder.getUrl());
		Assert.assertEquals("t%e !s+t", illegalFolder.getName());
		illegalFolder.delete();
	}

	@Test
	public void testResolveFileForFolder() throws Exception {
		IFile folder = mFactory.getFile("file:/tmp/" + time + "/");
		Assert.assertFalse(folder.isExists());
		folder.create();
		Assert.assertTrue(folder.isExists());
		Assert.assertEquals(FileType.FOLDER, folder.getType());
		Assert.assertEquals("file:/tmp/" + time + "/", folder.getUrl());
		folder.delete();
	}

	@Test
	public void testGetFileWithPath() throws Exception {
		IFile folder = mFactory.getFile("/tmp/" + time + "/");
		Assert.assertFalse(folder.isExists());
		folder.create();
		Assert.assertTrue(folder.isExists());
		Assert.assertEquals(FileType.FOLDER, folder.getType());
		Assert.assertEquals("file:/tmp/" + time + "/", folder.getUrl());
		folder.delete();
	}

	@Test
	public void testGetFileWithWrongScheme() throws Exception {
		try{
			mFactory.getFile("http://google.com/tmp/" + time + "/");
			Assert.fail("need to throws exception");
		}catch(Exception e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testHidden() throws Exception {
		IFile hiddenFile = mFactory.getFile("file:/tmp/." + time);
		IFile hiddenFolder = mFactory.getFile("file:/tmp/." + time + "/");
		IFile notHiddenFile = mFactory.getFile("file:/tmp/" + time);
		IFile notHiddenFolder = mFactory.getFile("file:/tmp/" + time + "/");
		Assert.assertTrue(hiddenFile.isHidden());
		Assert.assertTrue(hiddenFolder.isHidden());
		Assert.assertFalse(notHiddenFile.isHidden());
		Assert.assertFalse(notHiddenFolder.isHidden());
		hiddenFile.delete();
		hiddenFolder.delete();
		notHiddenFile.delete();
		notHiddenFolder.delete();
	}

	// TODO need to complete
	@Test
	public void testRelativePath() throws Exception {
		Log.i("Working Directory = " + System.getProperty("user.dir"));
//		GetResourceUrl getter = new GetResourceUrl();
//		Log.i(getter.execute("move.version.2"));
//		IData folder = (IData)FileFactory.getFile(getter.execute("move.version.2"));
//		IData folder = (IData)mFactory.getFile("move.version.2");
//		for (String file : IOUtils.readLines(folder.getInputStream())) {
//			Log.i(file);
//		}
	}

}
