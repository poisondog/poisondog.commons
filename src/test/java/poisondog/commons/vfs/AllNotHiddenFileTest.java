// 2014-02-21
/*******************************
 * AllNotHiddenFileTest
 *******************************/
package poisondog.commons.vfs;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
/**
 * @author Adam Huang
 */
public class AllNotHiddenFileTest {
	private AllNotHiddenFile mSelector;
	private FileObject notHiddenFolder;
	private FileObject notHiddenFile;
	private FileObject hiddenFolder;
	private FileObject hiddenFile;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mSelector = new AllNotHiddenFile();

		notHiddenFolder = Mockito.mock(FileObject.class);
		Mockito.when(notHiddenFolder.getType()).thenReturn(FileType.FOLDER);
		Mockito.when(notHiddenFolder.isHidden()).thenReturn(false);

		hiddenFolder = Mockito.mock(FileObject.class);
		Mockito.when(hiddenFolder.getType()).thenReturn(FileType.FOLDER);
		Mockito.when(hiddenFolder.isHidden()).thenReturn(true);

		notHiddenFile = Mockito.mock(FileObject.class);
		Mockito.when(notHiddenFile.getType()).thenReturn(FileType.FILE);
		Mockito.when(notHiddenFile.isHidden()).thenReturn(false);

		hiddenFile = Mockito.mock(FileObject.class);
		Mockito.when(hiddenFile.getType()).thenReturn(FileType.FILE);
		Mockito.when(hiddenFile.isHidden()).thenReturn(true);
	}

	@Test
	public void testNotHiddenFolder() throws Exception {
		FileSelectInfo info = Mockito.mock(FileSelectInfo.class);
		Mockito.when(info.getFile()).thenReturn(notHiddenFolder);
		Assert.assertFalse(mSelector.includeFile(info));
		Assert.assertTrue(mSelector.traverseDescendents(info));
	}

	@Test
	public void testHiddenFolder() throws Exception {
		FileSelectInfo info = Mockito.mock(FileSelectInfo.class);
		Mockito.when(info.getFile()).thenReturn(hiddenFolder);
		Assert.assertFalse(mSelector.includeFile(info));
		Assert.assertFalse(mSelector.traverseDescendents(info));
	}

	@Test
	public void testNotHiddenFile() throws Exception {
		FileSelectInfo info = Mockito.mock(FileSelectInfo.class);
		Mockito.when(info.getFile()).thenReturn(notHiddenFile);
		Assert.assertTrue(mSelector.includeFile(info));
		Assert.assertTrue(mSelector.traverseDescendents(info));
	}

	@Test
	public void testHiddenFile() throws Exception {
		FileSelectInfo info = Mockito.mock(FileSelectInfo.class);
		Mockito.when(info.getFile()).thenReturn(hiddenFile);
		Assert.assertFalse(mSelector.includeFile(info));
		Assert.assertFalse(mSelector.traverseDescendents(info));
	}
}
