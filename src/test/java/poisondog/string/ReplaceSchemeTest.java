// 2014-03-20
/*******************************
 * ReplaceSchemeTest
 *******************************/
package poisondog.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 */
public class ReplaceSchemeTest {
	private ReplaceScheme mTask;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mTask = new ReplaceScheme("http");
	}

	@Test
	public void testProcess() throws Exception {
		Assert.assertEquals("http://ant.apache.org/manual/", mTask.process("http://ant.apache.org/manual/"));
		Assert.assertEquals("http://user:password@ant.apache.org/", mTask.process("ftp://user:password@ant.apache.org/"));
		Assert.assertEquals("http:///reports/junit/html/index.html", mTask.process("file:///reports/junit/html/index.html"));
		Assert.assertEquals("http:/reports/junit/html/index.html", mTask.process("file:/reports/junit/html/index.html"));
		Assert.assertEquals("/reports/junit/html/index.html", mTask.process("/reports/junit/html/index.html"));
	}
}
