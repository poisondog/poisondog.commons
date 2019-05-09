// 2013-12-09
/*******************************
 * NodeTest
 *******************************/
package poisondog.xml;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 */
public class NodeTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testTag() throws Exception {
		Node node1 = new Node("file");
		Assert.assertEquals("file", node1.getName());

		Node node2 = new Node("folder");
		Assert.assertEquals("folder", node2.getName());
	}

	@Test
	public void testAttribute() {
		Node node1 = new Node("f");
		node1.setAttribute("version", "1.0");
		Assert.assertEquals("1.0", node1.getAttribute("version"));

		node1.setAttribute("version", "1.3");
		Assert.assertEquals("1.3", node1.getAttribute("version"));

		Assert.assertEquals(1, node1.getAttributes().size());
		node1.setAttribute("project name", "poisondog.xml");
		Assert.assertEquals(2, node1.getAttributes().size());
	}

	@Test
	public void testChildren() {
		Node node1 = new Node("d");
		Node node2 = new Node("d");
		Node node3 = new Node("d");
		Node node4 = new Node("f");

		node1.addChild(node2);
		node1.addChild(node3);
		Assert.assertEquals(2, node1.getChildren().size());
		Assert.assertEquals("d", node1.getChild(0).getName());
		Assert.assertEquals("d", node1.getChild(1).getName());

		node1.addChild(node4);
		Assert.assertEquals(3, node1.getChildren().size());
		Assert.assertEquals("f", node1.getChild(2).getName());
	}

	@Test
	public void testToString() {
		Node node1 = new Node("d");
		Node node2 = new Node("d");
		Node node3 = new Node("d");

		node1.setAttribute("version", "1.0");
		node2.setAttribute("hidden", "true");
		node2.setAttribute("read_only", "true");
		node3.setText("tst");

		node1.addChild(node2);
		node2.addChild(node3);

//		StringBuilder builder = new StringBuilder();
//		builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
//		builder.append("<d version=\"1.0\">");
//		builder.append("<d read_only=\"true\" hidden=\"true\">");
//		builder.append("<d>");
//		builder.append("tst");
//		builder.append("</d>");
//		builder.append("</d>");
//		builder.append("</d>");
//		Assert.assertEquals(builder.toString(), node1.toXML());
	}

	@Test
	public void testRemoveWithIndex() {
		Node node1 = new Node("d");
		Node node2 = new Node("d");
		Node node3 = new Node("d");

		node1.addChild(node2);
		node1.addChild(node3);
		Assert.assertEquals(node2, node1.getChild(0));
		Assert.assertEquals(node3, node1.getChild(1));

		node1.removeChild(0);
		Assert.assertEquals(node3, node1.getChild(0));
	}

	@Test
	public void testRemoveSimple() {
		Node node1 = new Node("d");
		Node node2 = new Node("d");
		Node node3 = new Node("d");

		node1.addChild(node2);
		node1.addChild(node3);
		Assert.assertEquals(node2, node1.getChild(0));
		Assert.assertEquals(node3, node1.getChild(1));

		node1.removeChild(node2);
		Assert.assertEquals(node3, node1.getChild(0));
	}

	@Test
	public void testRemoveRecursive() {
		Node node1 = new Node("d");
		Node node2 = new Node("d");
		Node node3 = new Node("d");

		node1.addChild(node2);
		node2.addChild(node3);
		Assert.assertEquals(node2, node1.getChild(0));
		Assert.assertEquals(node3, node2.getChild(0));

		node1.removeChild(0);
		Assert.assertEquals(0, node1.getChildren().size());
	}

	@Test
	public void testText() {
		Node node1 = new Node("f");
		node1.setText("test String");
		Assert.assertEquals("test String", node1.getText());
	}

	@Test
	public void testGetParent() {
		Node node1 = new Node("d");
		Node node2 = new Node("d");
		Node node3 = new Node("f");

		node1.addChild(node2);
		node2.addChild(node3);
		Assert.assertEquals(node1, node2.getParent());
		Assert.assertEquals(node2, node3.getParent());

		node2.removeChild(0);
		Assert.assertEquals(node1, node2.getParent());
		Assert.assertNull(node3.getParent());
	}
}
