// 2013-11-01
/*******************************
 * SizeFormatUtilsTest
 *******************************/
package poisondog.format;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.TimeZone;

/**
 * @author Adam Huang
 */
public class SizeFormatUtilsTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testB() throws Exception {
		Assert.assertEquals("20B", SizeFormatUtils.toString(20));
	}

	@Test
	public void testKB() throws Exception {
		Assert.assertEquals("2.00KB", SizeFormatUtils.toString(2048));
	}

	@Test
	public void testMB() throws Exception {
		Assert.assertEquals("7.00MB", SizeFormatUtils.toString((long)(7 * Math.pow(2, 20))));
	}

	@Test
	public void testGB() throws Exception {
		Assert.assertEquals("9.00GB", SizeFormatUtils.toString((long)(9 * Math.pow(2, 30))));
	}

	@Test
	public void testTB() throws Exception {
		Assert.assertEquals("5.00TB", SizeFormatUtils.toString((long)(5 * Math.pow(2, 40))));
	}

	@Test
	public void testPB() throws Exception {
		Assert.assertEquals("6.00PB", SizeFormatUtils.toString((long)(6 * Math.pow(2, 50))));
	}

	@Test
	public void testEB() throws Exception {
		Assert.assertEquals("3.00EB", SizeFormatUtils.toString((long)(3 * Math.pow(2, 60))));
	}
}
