// 2014-01-07
/*******************************
 * StringDecreasingTest
 *******************************/
package poisondog.commons;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 */
public class StringDecreasingTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCompare() throws Exception {
		StringDecreasing compartor = new StringDecreasing();
		Assert.assertTrue(compartor.compare("12", "13") > 0);
		Assert.assertTrue(compartor.compare("12", "12") == 0);
		Assert.assertTrue(compartor.compare("13", "12") < 0);
		Assert.assertTrue(compartor.compare("a3", "12") < 0);
		Assert.assertTrue(compartor.compare("13", "a2") > 0);
	}
}
