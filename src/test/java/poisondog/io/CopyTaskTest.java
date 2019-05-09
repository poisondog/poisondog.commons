// 2014-02-25
/*******************************
 * CopyTaskTest
 *******************************/
package poisondog.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.apache.commons.io.IOUtils;
/**
 * @author Adam Huang
 */
public class CopyTaskTest {
	private InputStream mInput;
	private OutputStream mOutput;
	private CopyTask mTask;
	private byte[] mBuffer;
	private StepListener mStepListener;
	private CancelListener mCancelListener;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mBuffer = new byte[1024];
		mInput = Mockito.mock(InputStream.class);
		mOutput = Mockito.mock(OutputStream.class);
		mStepListener = Mockito.mock(StepListener.class);
		mCancelListener = Mockito.mock(CancelListener.class);
		mTask = new CopyTask(mInput, mOutput);
		mTask.setBuffer(mBuffer);
		Mockito.when(mInput.read(mBuffer)).thenReturn(1024).thenReturn(500).thenReturn(300).thenReturn(-1);
	}

	@Test
	public void testInputStream() throws Exception {
		Assert.assertEquals(mInput, mTask.getInputStream());
	}

	@Test
	public void testOutputStream() throws Exception {
		Assert.assertEquals(mOutput, mTask.getOutputStream());
	}

	@Test
	public void testTransport() throws Exception {
		Assert.assertFalse(mTask.isComplete());
		mTask.transport();
		Assert.assertTrue(mTask.isComplete());
		Mockito.verify(mInput, Mockito.times(4)).read(mBuffer);
		Mockito.verify(mOutput).write(mBuffer, 0, 1024);
		Mockito.verify(mOutput).write(mBuffer, 0, 500);
		Mockito.verify(mOutput).write(mBuffer, 0, 300);
		Mockito.verify(mInput).close();
		Mockito.verify(mOutput).close();
	}

	@Test
	public void testClose() throws Exception {
		mTask.close();
		Mockito.verify(mInput).close();
		Mockito.verify(mOutput).close();
	}

	@Test
	public void testTransportRealFile() throws Exception {
		File temp1 = File.createTempFile("for test", ".txt");
		File temp2 = File.createTempFile("for test", ".txt");
		IOUtils.write("w9Rph5nC3KYTgu2y3R3aqTEv", new FileOutputStream(temp1), "utf8");
		mTask = new CopyTask(new FileInputStream(temp1), new FileOutputStream(temp2));
		mTask.transport();
		Assert.assertEquals("w9Rph5nC3KYTgu2y3R3aqTEv", IOUtils.toString(new FileInputStream(temp2)));
		temp1.delete();
		temp2.delete();
	}

	@Test
	public void testInputNull() throws Exception {
		File temp2 = File.createTempFile("for test", ".txt");

		mTask = new CopyTask(null, new FileOutputStream(temp2));
		mTask.transport();
		Assert.assertFalse(mTask.isComplete());

		mTask = new CopyTask(null, new FileOutputStream(temp2));
		mTask.close();
		Assert.assertFalse(mTask.isComplete());
		temp2.delete();
	}

	@Test
	public void testOutputNull() throws Exception {
		File temp1 = File.createTempFile("for test", ".txt");

		mTask = new CopyTask(new FileInputStream(temp1), null);
		mTask.transport();
		Assert.assertFalse(mTask.isComplete());

		mTask = new CopyTask(new FileInputStream(temp1), null);
		mTask.close();
		Assert.assertFalse(mTask.isComplete());
		temp1.delete();
	}

	@Test
	public void testComplete() throws Exception {
		Assert.assertFalse(mTask.isComplete());
		mTask.stepWrite();
		Assert.assertFalse(mTask.isComplete());
		mTask.stepWrite();
		Assert.assertFalse(mTask.isComplete());
		mTask.stepWrite();
		Assert.assertFalse(mTask.isComplete());
		mTask.stepWrite();
		Assert.assertTrue(mTask.isComplete());
	}

	@Test
	public void testCompleteCantCancel() throws Exception {
		mTask.stepWrite();
		mTask.stepWrite();
		mTask.stepWrite();
		mTask.stepWrite();
		mTask.cancel();
		Assert.assertFalse(mTask.isCancelled());
	}

	@Test
	public void testCancel() throws Exception {
		mTask.stepWrite();
		Assert.assertFalse(mTask.isCancelled());
		mTask.cancel();
		Assert.assertTrue(mTask.isCancelled());
		mTask.stepWrite();
		Mockito.verify(mOutput).write(mBuffer, 0, 1024);
		Mockito.verify(mOutput, Mockito.never()).write(mBuffer, 0, 500);
		Mockito.verify(mInput, Mockito.times(1)).read(mBuffer);
		Mockito.verify(mInput).close();
		Mockito.verify(mOutput).close();
	}

	@Test
	public void testStepWrite() throws Exception {
		Assert.assertTrue(mTask.stepWrite());
		Assert.assertTrue(mTask.stepWrite());
		Assert.assertTrue(mTask.stepWrite());
		Assert.assertFalse(mTask.stepWrite());
	}

	@Test
	public void testStepListener() throws Exception {
		mTask.addStepListener(mStepListener);
		mTask.transport();
		Mockito.verify(mStepListener).onStep(1024);
		Mockito.verify(mStepListener).onStep(500);
		Mockito.verify(mStepListener).onStep(300);
	}

	@Test
	public void testCancelListener() throws Exception {
		mTask.addCancelListener(mCancelListener);
		mTask.cancel();
		Mockito.verify(mCancelListener).onCancel();
	}
}
