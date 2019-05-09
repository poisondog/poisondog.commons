// 2013-11-14
/*******************************
 * NameValueEntityTest
 *******************************/
package poisondog.net;

import poisondog.commons.HashFunction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.apache.commons.io.IOUtils;

/**
 * @author Adam Huang
 */
public class NameValueEntityTest {
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testContentType() throws Exception {
		NameValueEntity entity1 = new NameValueEntity("utf8");
		String result1 = "application/x-www-form-urlencoded; charset=utf8";
		Assert.assertEquals(result1, entity1.getContentType());

		NameValueEntity entity2 = new NameValueEntity("big5");
		String result2 = "application/x-www-form-urlencoded; charset=big5";
		Assert.assertEquals(result2, entity2.getContentType());

		NameValueEntity entity3 = new NameValueEntity();
		Assert.assertEquals(result1, entity3.getContentType());
	}

	@Test
	public void testContentDisposition() {
		NameValueEntity entity1 = new NameValueEntity();
		Assert.assertNull(entity1.getContentDisposition());
	}

	@Test
	public void testContentTransferEncoding() throws Exception {
		NameValueEntity entity1 = new NameValueEntity();
		Assert.assertNull(entity1.getContentTransferEncoding());
	}

	@Test
	public void testContent() {
		NameValueEntity entity1 = new NameValueEntity();
		entity1.addTextBody("a1", "b12&b3");
		entity1.addTextBody("b4", "中文");
		String expected = HashFunction.md5("a1=b12%26b3&b4=%E4%B8%AD%E6%96%87");
		String result = HashFunction.md5(entity1.getContent());
		Assert.assertEquals(expected, result);
	}
}
