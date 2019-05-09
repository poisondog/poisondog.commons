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
import poisondog.vfs.LocalFileFactory;
/**
 * @author poisondog <poisondog@gmail.com>
 */
public class NameOrderTest {
	private long mTime;
	private LocalFileFactory mFactory;
	private IFile mFile01;
	private IFile mFile02;
	private IFile mFile03;
	private IFile mFile04;
	private IFile mFile05;
	private IFile mFile06;
	private List<IFile> testList;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mTime = System.currentTimeMillis();
		mFactory = new LocalFileFactory();
		mFile01 = mFactory.getFile("file:/tmp/" + mTime + "a.tmp");
		mFile02 = mFactory.getFile("file:/tmp/" + mTime + "1.tmp");
		mFile03 = mFactory.getFile("file:/tmp/" + mTime + ".tmp");
		mFile04 = mFactory.getFile("file:/tmp/" + mTime + "1.tmp/");
		mFile05 = mFactory.getFile("file:/tmp/" + mTime + "a.tmp/");
		mFile06 = mFactory.getFile("file:/tmp/" + mTime + ".tmp/");

		testList = new ArrayList<IFile>();
		testList.add(mFile01);
		testList.add(mFile02);
		testList.add(mFile03);
		testList.add(mFile04);
		testList.add(mFile05);
		testList.add(mFile06);
	}

	@Test
	public void testSort() throws Exception {
		Collections.sort(testList, new NameOrder());
		Assert.assertEquals(mFile06.getUrl(), testList.get(0).getUrl());
		Assert.assertEquals(mFile04.getUrl(), testList.get(1).getUrl());
		Assert.assertEquals(mFile05.getUrl(), testList.get(2).getUrl());
		Assert.assertEquals(mFile03.getUrl(), testList.get(3).getUrl());
		Assert.assertEquals(mFile02.getUrl(), testList.get(4).getUrl());
		Assert.assertEquals(mFile01.getUrl(), testList.get(5).getUrl());
		Assert.assertTrue(true);
	}
}
