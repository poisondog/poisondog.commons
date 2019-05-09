// 2014-02-07
/*******************************
 * ConfigTest
 *******************************/
package poisondog.commons;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 */
public class ConfigTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testValue() throws Exception {
		Config config = new Config();
		config.set("path", "/tmp/MainActivity/AndroidManifest.xml");
		Assert.assertEquals("/tmp/MainActivity/AndroidManifest.xml", config.get("path"));
		config.set("path", "/tmp/MainActivity/src/");
		Assert.assertEquals("/tmp/MainActivity/src/", config.get("path"));
		Assert.assertEquals(1, config.keySet().size());
		config.set("path2", "/tmp/MainActivity/bin/");
		Assert.assertEquals("/tmp/MainActivity/bin/", config.get("path2"));
		Assert.assertEquals(2, config.keySet().size());
	}
}
