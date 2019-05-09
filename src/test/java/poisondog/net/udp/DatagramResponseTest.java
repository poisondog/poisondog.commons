// 2014-01-28
/*******************************
 * DatagramResponseTest
 *******************************/
package poisondog.net.udp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 */
public class DatagramResponseTest {
	private DatagramResponse response;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		response = new DatagramResponse(40);
	}

	@Test
	public void testAddress() throws Exception {
		response.setAddress("192.168.1.99");
		Assert.assertEquals("192.168.1.99", response.getAddress());
		response.setAddress("192.168.0.100");
		Assert.assertEquals("192.168.0.100", response.getAddress());
	}

	@Test
	public void testPort() throws Exception {
		response.setPort(17213);
		Assert.assertEquals(17213, response.getPort());
		response.setPort(59324);
		Assert.assertEquals(59324, response.getPort());
	}

	@Test
	public void testContent() throws Exception {
		response.setContent("192168199".getBytes());
		Assert.assertEquals("192168199", new String(response.getContent()));
		response.setContent("1921680100".getBytes());
		Assert.assertEquals("1921680100", new String(response.getContent()));
	}

	@Test
	public void testContentLength() throws Exception {
		Assert.assertEquals(40, response.getContent().length);
	}

	@Test
	public void testPacketLength() throws Exception {
		response.setContent("192168199".getBytes());
		response.setPacketLength(4);
		Assert.assertEquals("1921", new String(response.getContent()));
	}
}
