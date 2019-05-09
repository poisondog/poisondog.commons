/*
 * Copyright (C) 2015 Adam Huang <poisondog@gmail.com>
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
package poisondog.vfs.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.vfs.IFile;
import poisondog.vfs.LocalData;
import poisondog.vfs.LocalFileFactory;
import poisondog.vfs.LocalFolder;
import org.junit.After;
/**
 * @author poisondog <poisondog@gmail.com>
 */
public class LastModifiedSortTest {
	private long mTime;
	private LocalFileFactory mFactory;
	private LocalData mFile01;
	private LocalData mFile02;
	private LocalData mFile03;
	private LocalFolder mFile04;
	private LocalFolder mFile05;
	private LocalFolder mFile06;
	private List<IFile> testList;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mTime = System.currentTimeMillis();
		mFactory = new LocalFileFactory();
		mFile01 = (LocalData)mFactory.getFile("file:/tmp/" + mTime + "a.tmp");
		mFile02 = (LocalData)mFactory.getFile("file:/tmp/" + mTime + "1.tmp");
		mFile03 = (LocalData)mFactory.getFile("file:/tmp/" + mTime + ".tmp");
		mFile04 = (LocalFolder)mFactory.getFile("file:/tmp/" + mTime + "1.ftmp/");
		mFile05 = (LocalFolder)mFactory.getFile("file:/tmp/" + mTime + "a.ftmp/");
		mFile06 = (LocalFolder)mFactory.getFile("file:/tmp/" + mTime + ".ftmp/");

		mFile01.create();
		mFile02.create();
		mFile03.create();
		mFile04.create();
		mFile05.create();
		mFile06.create();

		mFile01.getFile().setLastModified(mTime + 200000);
		mFile02.getFile().setLastModified(mTime + 400000);
		mFile03.getFile().setLastModified(mTime + 300000);
		mFile04.getFile().setLastModified(mTime + 600000);
		mFile05.getFile().setLastModified(mTime + 100000);
		mFile06.getFile().setLastModified(mTime + 500000);

		testList = new ArrayList<IFile>();
		testList.add(mFile01);
		testList.add(mFile02);
		testList.add(mFile03);
		testList.add(mFile04);
		testList.add(mFile05);
		testList.add(mFile06);
	}

	@After
	public void tearDown() throws Exception {
		mFile01.delete();
		mFile02.delete();
		mFile03.delete();
		mFile04.delete();
		mFile05.delete();
		mFile06.delete();
	}

	@Test
	public void testNewerFirst() throws Exception {
		Collections.sort(testList, new NewerFirst());
		Assert.assertEquals(mFile04.getUrl(), testList.get(0).getUrl());
		Assert.assertEquals(mFile06.getUrl(), testList.get(1).getUrl());
		Assert.assertEquals(mFile05.getUrl(), testList.get(2).getUrl());
		Assert.assertEquals(mFile02.getUrl(), testList.get(3).getUrl());
		Assert.assertEquals(mFile03.getUrl(), testList.get(4).getUrl());
		Assert.assertEquals(mFile01.getUrl(), testList.get(5).getUrl());
	}

	@Test
	public void testOlderFirst() throws Exception {
		Collections.sort(testList, new OlderFirst());
		Assert.assertEquals(mFile05.getUrl(), testList.get(0).getUrl());
		Assert.assertEquals(mFile06.getUrl(), testList.get(1).getUrl());
		Assert.assertEquals(mFile04.getUrl(), testList.get(2).getUrl());
		Assert.assertEquals(mFile01.getUrl(), testList.get(3).getUrl());
		Assert.assertEquals(mFile03.getUrl(), testList.get(4).getUrl());
		Assert.assertEquals(mFile02.getUrl(), testList.get(5).getUrl());
	}
}
