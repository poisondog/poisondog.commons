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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import org.junit.After;

/**
 * @author poisondog <poisondog@gmail.com>
 */
public class LocalFolderTest {
	private IFileFactory mFactory;
	private IFolder mFolder;
	private long time;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		time = System.currentTimeMillis();
		mFactory = new LocalFileFactory();
		mFolder = (IFolder) mFactory.getFile("file:/tmp/" + time + "/");
		mFolder.create();
	}

	@After
	public void tearDown() throws Exception {
		mFolder.delete();
	}

	@Test
	public void testGetUrl() throws Exception {
		Assert.assertEquals("file:/tmp/" + time + "/", mFolder.getUrl());
	}

	@Test
	public void testFileType() throws Exception {
		Assert.assertEquals(FileType.FOLDER, mFolder.getType());
	}

	@Test
	public void testMove() throws Exception {
		IFile target = mFactory.getFile("file:/tmp/" + (time + 1) + "/");
		Assert.assertTrue(mFolder.move(target));
		Assert.assertEquals(target.getUrl(), mFolder.getUrl());
	}

	@Test
	public void testMoveToFileUrl() throws Exception {
		try{
			mFolder.move(mFactory.getFile("file:/tmp/" + (time + 1) + ".jpg"));
			Assert.fail("need to throw a illegal argument exception");
		}catch(Exception e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testDelete() throws Exception {
		Assert.assertTrue(mFolder.delete());
		Assert.assertFalse(mFolder.isExists());
	}

	@Test
	public void testList() throws Exception {
		IFile f1 = mFactory.getFile("file:/tmp/" + time + "/1");
		IFile f2 = mFactory.getFile("file:/tmp/" + time + "/2中/");
		IFile f3 = mFactory.getFile("file:/tmp/" + time + "/3");
		f1.create();
		f2.create();
		f3.create();
		List<IFile> children = mFolder.getChildren();
		Assert.assertEquals(3, children.size());
		f1.delete();
		f2.delete();
		f3.delete();
	}

	@Test
	public void testListEmpty() throws Exception {
		IFolder f1 = (IFolder)mFactory.getFile("file:/tmp/" + time + "/1/");
		Assert.assertEquals(0, f1.getChildren().size());
	}

	@Test
	public void testLastModifiedTime() throws Exception {
		long current = System.currentTimeMillis();
		Assert.assertTrue(mFolder.getLastModifiedTime() > 0);
		Assert.assertTrue(current >= mFolder.getLastModifiedTime());
	}

	@Test
	public void testName() throws Exception {
		Assert.assertEquals(Long.toString(time), mFolder.getName());
	}
}
