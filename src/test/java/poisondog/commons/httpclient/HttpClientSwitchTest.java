// 2014-03-11
/*******************************
 * HttpClientSwitchTest
 *******************************/
package poisondog.commons.httpclient;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Adam Huang
 */
public class HttpClientSwitchTest {
	private HttpClient mClient1;
	private HttpClient mClient2;
	private HttpClientSwitch mSwitch;
	private HttpMethod mMethod;
	private HttpConnectionManager mManager;
	private HostConfiguration mHostConfiguration1;
	private HostConfiguration mHostConfiguration2;
	private HttpState mHttpState1;
	private HttpState mHttpState2;
	private HttpClientParams mParams;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mClient1 = Mockito.mock(HttpClient.class);
		mClient2 = Mockito.mock(HttpClient.class);
		mSwitch = new HttpClientSwitch(mClient1);
		mMethod = Mockito.mock(HttpMethod.class);
		mHostConfiguration1 = Mockito.mock(HostConfiguration.class);
		mHostConfiguration2 = Mockito.mock(HostConfiguration.class);
		mHttpState1 = Mockito.mock(HttpState.class);
		mHttpState2 = Mockito.mock(HttpState.class);
		mManager = Mockito.mock(HttpConnectionManager.class);
		mParams = Mockito.mock(HttpClientParams.class);
	}

	@Test
	public void testExecuteMethod1() throws Exception {
		mSwitch.executeMethod(mMethod);
		Mockito.verify(mClient1).executeMethod(mMethod);

		mSwitch.setHttpClient(mClient2);
		mSwitch.executeMethod(mMethod);
		Mockito.verify(mClient2).executeMethod(mMethod);
	}

	@Test
	public void testExecuteMethod2() throws Exception {
		mSwitch.executeMethod(mHostConfiguration1, mMethod);
		Mockito.verify(mClient1).executeMethod(mHostConfiguration1, mMethod);

		mSwitch.setHttpClient(mClient2);
		mSwitch.executeMethod(mHostConfiguration1, mMethod);
		Mockito.verify(mClient2).executeMethod(mHostConfiguration1, mMethod);
	}

	@Test
	public void testExecuteMethod3() throws Exception {
		mSwitch.executeMethod(mHostConfiguration1, mMethod, mHttpState1);
		Mockito.verify(mClient1).executeMethod(mHostConfiguration1, mMethod, mHttpState1);

		mSwitch.setHttpClient(mClient2);
		mSwitch.executeMethod(mHostConfiguration1, mMethod, mHttpState1);
		Mockito.verify(mClient2).executeMethod(mHostConfiguration1, mMethod, mHttpState1);
	}

	@Test
	public void testHostConfiguration() throws Exception {
		mSwitch.getHostConfiguration();
		Mockito.verify(mClient1).getHostConfiguration();

		mSwitch.setHttpClient(mClient2);
		mSwitch.getHostConfiguration();
		Mockito.verify(mClient2).getHostConfiguration();
	}

	@Test
	public void testGetParams() throws Exception {
		mSwitch.getParams();
		Mockito.verify(mClient1).getParams();

		mSwitch.setHttpClient(mClient2);
		mSwitch.getParams();
		Mockito.verify(mClient2).getParams();
	}

	@Test
	public void testGetState() throws Exception {
		mSwitch.getState();
		Mockito.verify(mClient1).getState();

		mSwitch.setHttpClient(mClient2);
		mSwitch.getState();
		Mockito.verify(mClient2).getState();
	}

	@Test
	public void testSetHostConfiguration() throws Exception {
		mSwitch.setHostConfiguration(mHostConfiguration2);
		Mockito.verify(mClient1).setHostConfiguration(mHostConfiguration2);

		mSwitch.setHttpClient(mClient2);
		mSwitch.setHostConfiguration(mHostConfiguration2);
		Mockito.verify(mClient2).setHostConfiguration(mHostConfiguration2);
	}

	@Test
	public void testHttpConnectionManager() throws Exception {
		mSwitch.setHttpConnectionManager(mManager);
		Mockito.verify(mClient1).setHttpConnectionManager(mManager);

		mSwitch.setHttpClient(mClient2);
		mSwitch.setHttpConnectionManager(mManager);
		Mockito.verify(mClient2).setHttpConnectionManager(mManager);
	}

	@Test
	public void testHttpClientParams() throws Exception {
		mSwitch.setParams(mParams);
		Mockito.verify(mClient1).setParams(mParams);
		
		mSwitch.setHttpClient(mClient2);
		mSwitch.setParams(mParams);
		Mockito.verify(mClient2).setParams(mParams);
	}

	@Test
	public void testHttpState() throws Exception {
		mSwitch.setState(mHttpState2);
		Mockito.verify(mClient1).setState(mHttpState2);

		mSwitch.setHttpClient(mClient2);
		mSwitch.setState(mHttpState2);
		Mockito.verify(mClient2).setState(mHttpState2);
	}
}
