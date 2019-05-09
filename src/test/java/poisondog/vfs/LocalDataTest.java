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

import java.util.List;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

/**
 * @author poisondog <poisondog@gmail.com>
 */
public class LocalDataTest {
	private IData mData;
	private long time;
	private LocalFileFactory mFactory;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		time = System.currentTimeMillis();
		mFactory = new LocalFileFactory();
		mData = (IData) mFactory.getFile("file:/tmp/" + time + ".tmp");
		mData.create();
	}

	@After
	public void tearDown() throws Exception {
		mData.delete();
	}

	@Test
	public void testGetUrl() throws Exception {
		Assert.assertEquals("file:/tmp/" + time + ".tmp", mData.getUrl());
	}

	@Test
	public void testFileType() throws Exception {
		Assert.assertEquals(FileType.DATA, mData.getType());
	}

	@Test
	public void testMove() throws Exception {
		IFile target = mFactory.getFile("file:/tmp/" + (time + 1) + ".tmp");
		Assert.assertTrue(mData.move(target));
		Assert.assertEquals(target.getUrl(), mData.getUrl());
	}

	@Test
	public void testDelete() throws Exception {
		Assert.assertTrue(mData.delete());
		Assert.assertFalse(mData.isExists());
	}

//	@Test
//	public void testDeleteCase2() throws Exception {
//		String path = "file:/tmp/kkkk" + (time + 1) + "/";
//		IFile target = mFactory.getFile(path + (time + 2) + ".tmp");
//		target.delete();
//	}

	@Test
	public void testAccess() throws Exception {
		IOUtils.write("test text", mData.getOutputStream(), "utf8");
		List<String> result = IOUtils.readLines(mData.getInputStream(), "utf8");
		Assert.assertEquals("test text", result.get(0));
	}

	@Test
	public void testSize() throws Exception {
		Assert.assertEquals(0, mData.getSize());
		IOUtils.write("test text", mData.getOutputStream(), "utf8");
		Assert.assertEquals(9, mData.getSize());
		IOUtils.write("test", mData.getOutputStream(), "utf8");
		Assert.assertEquals(4, mData.getSize());
	}

	@Test
	public void testLastModifiedTime() throws Exception {
		long current = System.currentTimeMillis();
		Assert.assertTrue(mData.getLastModifiedTime() > 0);
		Assert.assertTrue(current >= mData.getLastModifiedTime());
	}

	@Test
	public void testName() throws Exception {
		Assert.assertEquals(time + ".tmp", mData.getName());
	}

	@Test
	public void testCreate() throws Exception {
		String folderString = "file:/tmp/" + (time + 1) + "/";
		IFile target = mFactory.getFile(folderString + "1/2/3/4/5.tmp");
		target.create();
		mFactory.getFile(folderString).delete();
	}
}
