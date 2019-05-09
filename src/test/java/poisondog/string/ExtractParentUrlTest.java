// 2014-02-04
/*******************************
 * ExtractParentUrlTest
 *******************************/
package poisondog.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 */
public class ExtractParentUrlTest {
	private ExtractParentUrl task;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		task = new ExtractParentUrl();
	}

	@Test
	public void testHttpFile() throws Exception {
		Assert.assertEquals("http://ant.apache.org/manual/Tasks/", task.process("http://ant.apache.org/manual/Tasks/index.html"));
		Assert.assertEquals("http://ant.apache.org/manual/Tasks/", task.process("http://ant.apache.org/manual/Tasks/java"));
	}

	@Test
	public void testHttpFolder() throws Exception {
		Assert.assertEquals("http://ant.apache.org/manual/Tasks/", task.process("http://ant.apache.org/manual/Tasks/java/"));
	}

	@Test
	public void testHttpRoot() throws Exception {
		Assert.assertEquals("http://ant.apache.org/", task.process("http://ant.apache.org/"));
	}

	@Test
	public void testPathFile() throws Exception {
		Assert.assertEquals("/ant.apache.org/manual/Tasks/", task.process("/ant.apache.org/manual/Tasks/java"));
	}

	@Test
	public void testPathFolder() throws Exception {
		Assert.assertEquals("/ant.apache.org/manual/Tasks/", task.process("/ant.apache.org/manual/Tasks/java/"));
	}

	@Test
	public void testRoot() throws Exception {
		Assert.assertEquals("/", task.process("/"));
	}

	@Test
	public void testRelativeFile() throws Exception {
		Assert.assertEquals("./", task.process("./manual"));
	}

	@Test
	public void testRelativeFolder() throws Exception {
		Assert.assertEquals("./", task.process("./manual/"));
	}

	@Test
	public void testWithBrackets() throws Exception {
		Assert.assertEquals("http://ant.apache.org/manual/Tasks/", task.process("http://ant.apache.org/manual/Tasks/java(4-1)/"));
	}

	@Test
	public void testDollar() throws Exception {
		Assert.assertEquals("webdav://192.168.1.241:8080/MySkyBox/sata_1/", task.process("webdav://192.168.1.241:8080/MySkyBox/sata_1/@$,..JPG"));
	}
}
