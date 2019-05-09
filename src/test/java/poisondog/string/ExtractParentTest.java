// 2014-02-03
/*******************************
 * ExtractParentTest
 *******************************/
package poisondog.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 */
public class ExtractParentTest {
	private ExtractParent task;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		task = new ExtractParent();
	}

	@Test
	public void testHttpFile() throws Exception {
		Assert.assertEquals("Tachikoma.asks", task.process("http://ant.apache.org/manual/Tachikoma.asks/index.html"));
		Assert.assertEquals("Tasks", task.process("http://ant.apache.org/manual/Tasks/java"));
	}

	@Test
	public void testHttpFolder() throws Exception {
		Assert.assertEquals("Tasks", task.process("http://ant.apache.org/manual/Tasks/java/"));
	}
}
