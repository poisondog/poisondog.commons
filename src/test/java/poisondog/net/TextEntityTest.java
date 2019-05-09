// 2013-12-12
/*******************************
 * TextEntityTest
 *******************************/
package poisondog.net;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 */
public class TextEntityTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testContentType() throws Exception {
		TextEntity entity1 = new TextEntity("a", "中文", "utf8");
		Assert.assertEquals("text/plain; charset=utf8", entity1.getContentType());
	}

	@Test
	public void testContentDisposition() {
		TextEntity entity1 = new TextEntity("a", "中文", "utf8");
		Assert.assertEquals("form-data; name=\"a\"", entity1.getContentDisposition());
		TextEntity entity2 = new TextEntity("b", "abc123", "utf8");
		Assert.assertEquals("form-data; name=\"b\"", entity2.getContentDisposition());
	}

	@Test
	public void testContent() {
		TextEntity entity1 = new TextEntity("a", "中文", "utf8");
		TextEntity entity2 = new TextEntity("b", "abc123", "utf8");

		Assert.assertEquals("中文", new String(entity1.getContent()));
		Assert.assertEquals("abc123", new String(entity2.getContent()));
	}
}
