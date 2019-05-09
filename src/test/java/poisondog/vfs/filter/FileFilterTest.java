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
package poisondog.vfs.filter;

import java.util.Collection;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.rule.AndRule;
import poisondog.rule.NotRule;
import poisondog.vfs.IFile;
import poisondog.vfs.LocalFileFactory;
import poisondog.vfs.LocalFolder;
import org.junit.After;

/**
 * @author poisondog <poisondog@gmail.com>
 */
public class FileFilterTest {
	private long mTime;
	private LocalFileFactory mFactory;
	private LocalFolder mFolder;
	private IFile mNormalData;
	private IFile mHiddenData;
	private IFile mNormalFolder;
	private IFile mHiddenFolder;
	private IFile mNormalFolderData;
	private IFile mHiddenFolderData;
	private IFile mHiddenFolderFolder;

	@Before
	public void setUp() throws Exception {
		mTime = System.currentTimeMillis();
		mFactory = new LocalFileFactory();
		mFolder = (LocalFolder) mFactory.getFile("file:/tmp/" + mTime + "/");
		mFolder.create();
		mNormalData = mFactory.getFile("file:/tmp/" + mTime + "/data");
		mHiddenData = mFactory.getFile("file:/tmp/" + mTime + "/.hidden.data");
		mNormalFolder = mFactory.getFile("file:/tmp/" + mTime + "/folder/");
		mHiddenFolder = mFactory.getFile("file:/tmp/" + mTime + "/.hidden.folder/");
		mNormalFolderData = mFactory.getFile("file:/tmp/" + mTime + "/folder/data");
		mHiddenFolderData = mFactory.getFile("file:/tmp/" + mTime + "/.hidden.folder/data");
		mHiddenFolderFolder = mFactory.getFile("file:/tmp/" + mTime + "/.hidden.folder/folder/");
		mNormalData.create();
		mHiddenData.create();
		mNormalFolder.create();
		mHiddenFolder.create();
		mNormalFolderData.create();
		mHiddenFolderData.create();
		mHiddenFolderFolder.create();
	}

	@After
	public void tearDown() throws Exception {
		mHiddenFolderFolder.delete();
		mNormalFolderData.delete();
		mHiddenFolderData.delete();
		mNormalFolder.delete();
		mHiddenFolder.delete();
		mNormalData.delete();
		mHiddenData.delete();
		mFolder.delete();
	}

	@Test
	public void testDefault() throws Exception {
		FileFilter filter = new FileFilter();
		Collection<IFile> result = filter.execute(mFolder.getChildren());
		Assert.assertEquals(7, result.size());
	}

	@Test
	public void testOnlyFolder() throws Exception {
		FileFilter filter = new FileFilter();
		filter.setIncludeRule(new OnlyFolder());
		Collection<IFile> result = filter.execute(mFolder.getChildren());
		Assert.assertEquals(3, result.size());
	}

	@Test
	public void testOnlyHidden() throws Exception {
		FileFilter filter = new FileFilter();
		filter.setIncludeRule(new OnlyHidden());
		Collection<IFile> result = filter.execute(mFolder.getChildren());
		Assert.assertEquals(2, result.size());
	}

	@Test
	public void testTraverseHidden() throws Exception {
		FileFilter filter = new FileFilter();
		filter.setTraverseRule(new OnlyHidden());
		Collection<IFile> result = filter.execute(mFolder.getChildren());
		Assert.assertEquals(6, result.size());
	}

	@Test
	public void testOnlyNotHidden() throws Exception {
		FileFilter filter = new FileFilter();
		filter.setIncludeRule(new NotRule(new OnlyHidden()));
		Collection<IFile> result = filter.execute(mFolder.getChildren());
		Assert.assertEquals(5, result.size());
	}

	@Test
	public void testOnlyNotHiddenTraverseNotHidden() throws Exception {
		FileFilter filter = new FileFilter();
		filter.setIncludeRule(new NotRule(new OnlyHidden()));
		filter.setTraverseRule(new NotRule(new OnlyHidden()));
		Collection<IFile> result = filter.execute(mFolder.getChildren());
		Assert.assertEquals(3, result.size());
	}

	@Test
	public void testOnlyNotHiddenDataTraverseNotHidden() throws Exception {
		AndRule and = new AndRule();
		and.add(new OnlyData());
		and.add(new NotRule(new OnlyHidden()));

		FileFilter filter = new FileFilter();
		filter.setIncludeRule(and);
		filter.setTraverseRule(new NotRule(new OnlyHidden()));
		Collection<IFile> result = filter.execute(mFolder.getChildren());
		Assert.assertEquals(2, result.size());
	}
}
