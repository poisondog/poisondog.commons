// 2013-11-13
/*******************************
 * MultipartEntityTest
 *******************************/
package poisondog.net;

import poisondog.commons.HashFunction;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Adam Huang
 */
public class MultipartEntityTest {
	private MultipartEntity mEntity;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mEntity = new MultipartEntity();
	}

	@Test
	public void testBoundary() throws Exception {
		Assert.assertEquals(mEntity.getBoundary(), mEntity.getBoundary());
		Assert.assertEquals(mEntity.getBoundary(), mEntity.getBoundary());
	}

	@Test
	public void testLineEnd() throws Exception {
		Assert.assertEquals("\r\n", mEntity.getLineEnd());
	}

	@Test
	public void testTwoHyphens() {
		Assert.assertEquals("--", mEntity.getTwoHyphens());
	}

	@Test
	public void testContentType() {
		String result = "multipart/form-data; boundary=" + mEntity.getBoundary();
		Assert.assertEquals(result, mEntity.getContentType());
	}

	@Test
	public void testContentDisposition() {
		try {
			mEntity.getContentDisposition();
			Assert.fail("not support thie method");
		} catch(Exception e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testAddNameValueEntity() throws Exception {
		NameValueEntity stringEntity = new NameValueEntity();
		stringEntity.addTextBody("a", "中文");

		File temp1 = File.createTempFile("sk2", ".jpg");
		IOUtils.write("test", new FileOutputStream(temp1), "utf8");
		FileEntity fileEntity = new FileEntity("imagek", temp1);

		mEntity.addEntity(stringEntity);
		mEntity.addEntity(fileEntity);

		StringBuilder exception = new StringBuilder();
		exception.append(mEntity.getTwoHyphens());
		exception.append(mEntity.getBoundary());
		exception.append(mEntity.getLineEnd());
		exception.append("Content-Type: application/x-www-form-urlencoded; charset=utf8");
		exception.append(mEntity.getLineEnd());
		exception.append(mEntity.getLineEnd());
		exception.append("a=%E4%B8%AD%E6%96%87");
		exception.append(mEntity.getLineEnd());

		exception.append(mEntity.getTwoHyphens());
		exception.append(mEntity.getBoundary());
		exception.append(mEntity.getLineEnd());
		exception.append("Content-Disposition: form-data; ");
		exception.append("name=\"imagek\"; ");
		exception.append("filename=\"");
		exception.append(temp1.getName());
		exception.append("\"");
		exception.append(mEntity.getLineEnd());
		exception.append("Content-Transfer-Encoding: binary");
		exception.append(mEntity.getLineEnd());
		exception.append("Content-Type: image/jpeg");
		exception.append(mEntity.getLineEnd());
		exception.append(mEntity.getLineEnd());
		exception.append("test");
		exception.append(mEntity.getLineEnd());
		exception.append(mEntity.getTwoHyphens());
		exception.append(mEntity.getBoundary());
		exception.append(mEntity.getTwoHyphens());
		exception.append(mEntity.getLineEnd());

		Assert.assertEquals(exception.toString(), new String(mEntity.getContent()));
		temp1.delete();
	}
}
