// 2014-01-06
/*******************************
 * TimeUtilsTest
 *******************************/
package poisondog.commons;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 */
public class TimeUtilsTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIsToday() throws Exception {
		long current = System.currentTimeMillis();
		Assert.assertTrue(TimeUtils.isToday(current));
		Assert.assertFalse(TimeUtils.isToday(current - 86400000));
		Assert.assertFalse(TimeUtils.isToday(current + 86400000));
	}

}
