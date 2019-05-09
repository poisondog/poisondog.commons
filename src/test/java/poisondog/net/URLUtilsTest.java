// 2013-09-12
/*******************************
 * URLUtilsTest
 *******************************/
package poisondog.net;

import java.util.Map;
import java.util.TreeMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 */
public class URLUtilsTest {

	@Test
	public void testHttpQueryString() {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("a", "111");
		map.put("b", "ajk");
		map.put("c", "中文");
		Assert.assertEquals("?a=111&b=ajk&c=%E4%B8%AD%E6%96%87", URLUtils.httpQuery(map));
	}

	@Test
	public void testLongToIP() {
		Assert.assertEquals("127.0.0.1", URLUtils.long2IP(2130706433));
		Assert.assertEquals("8.8.8.89", URLUtils.long2IP(134744153));
	}

}
