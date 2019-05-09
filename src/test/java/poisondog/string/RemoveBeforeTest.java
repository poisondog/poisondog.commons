// 2014-02-05
/*******************************
 * RemoveBeforeTest
 *******************************/
package poisondog.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 */
public class RemoveBeforeTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRemoveBefore() throws Exception {
		RemoveBefore task1 = new RemoveBefore("java");
		Assert.assertEquals("java.org/java/Tasks/index.html", task1.process("http://ant.apache.java.org/java/Tasks/index.html"));
		RemoveBefore task2 = new RemoveBefore("ant");
		Assert.assertEquals("ant.apache.ant.org/ant/Tasks/index.html", task2.process("http://ant.apache.ant.org/ant/Tasks/index.html"));
	}

	@Test
	public void testRemoveBeforeNotKeep() throws Exception {
		RemoveBefore task = new RemoveBefore("ant", false);
		Assert.assertEquals(".apache.ant.org/ant/Tasks/index.html", task.process("http://ant.apache.ant.org/ant/Tasks/index.html"));
	}

	@Test
	public void testWhenNotFound() throws Exception {
		RemoveBefore task = new RemoveBefore("java");
		Assert.assertEquals("http://ant.apache.org/ant/Tasks/index.html", task.process("http://ant.apache.org/ant/Tasks/index.html"));
	}
}
