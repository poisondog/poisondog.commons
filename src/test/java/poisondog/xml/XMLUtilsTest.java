// 2013-12-09
/*******************************
 * XMLUtilsTest
 *******************************/
package poisondog.xml;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.apache.commons.io.IOUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
/**
 * @author Adam Huang
 */
public class XMLUtilsTest {
	private String mXML;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		builder.append("<filesystem test=\"jdk\">");
		builder.append("<file hidden =\"true\" classpath=\"lib/commons-logging-1.1.3.jar\">./lib/commons-io-2.4.jar</file>");
		builder.append("<folder read_only=\"true\">./target/classes/main/</folder>");
		builder.append("</filesystem>");
		mXML = builder.toString();
	}

	@Test
	public void testParser() throws Exception {
		File temp = File.createTempFile("test", ".xml");
		IOUtils.write(mXML, new FileOutputStream(temp), "utf8");

		Node root = XMLUtils.parse(new FileInputStream(temp));
		Assert.assertEquals("filesystem", root.getName());
		Assert.assertEquals("jdk", root.getAttribute("test"));
		Assert.assertEquals(2, root.getChildren().size());

		Node n1 = root.getChild(0);
		Assert.assertEquals("file", n1.getName());
		Assert.assertEquals("true", n1.getAttribute("hidden"));
		Assert.assertEquals("lib/commons-logging-1.1.3.jar", n1.getAttribute("classpath"));
		Assert.assertEquals("./lib/commons-io-2.4.jar", n1.getText());

		Node n2 = root.getChild(1);
		Assert.assertEquals("folder", n2.getName());
		Assert.assertEquals("true", n2.getAttribute("read_only"));
		Assert.assertEquals("./target/classes/main/", n2.getText());
		temp.delete();
	}
}
