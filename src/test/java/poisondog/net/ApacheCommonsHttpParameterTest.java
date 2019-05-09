// 2014-02-19
/*******************************
 * ApacheCommonsHttpParameterTest
 *******************************/
package poisondog.net;

import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Adam Huang
 */
public class ApacheCommonsHttpParameterTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMethod() throws Exception {
		ApacheCommonsHttpParameter parameter = new ApacheCommonsHttpParameter();
		Assert.assertNotNull(parameter.getMethod());

		GetMethod method = new GetMethod();
		parameter.setMethod(method);
		Assert.assertEquals(method, parameter.getMethod());
	}
}
