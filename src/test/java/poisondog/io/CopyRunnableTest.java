// 2014-04-28
/*******************************
 * CopyRunnableTest
 *******************************/
package poisondog.io;

import java.io.InputStream;
import java.io.OutputStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
/**
 * @author Adam Huang
 */
public class CopyRunnableTest {
	private CopyRunnable mRunnable;
	private InputStream mInput;
	private OutputStream mOutput;
	private byte[] mBuffer;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mBuffer = new byte[1024];
		mInput = Mockito.mock(InputStream.class);
		mOutput = Mockito.mock(OutputStream.class);

		mRunnable = new CopyRunnable(mInput, mOutput);
		Mockito.when(mInput.read(mBuffer)).thenReturn(1024).thenReturn(500).thenReturn(-1);
	}

	@Test
	public void testRun() throws Exception {
		mRunnable.run();
		Mockito.verify(mInput, Mockito.times(3)).read(mBuffer);
		Mockito.verify(mOutput).write(mBuffer, 0, 1024);
		Mockito.verify(mOutput).write(mBuffer, 0, 500);
		Mockito.verify(mInput).close();
		Mockito.verify(mOutput).close();
	}
}
