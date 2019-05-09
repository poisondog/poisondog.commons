/*
 * Copyright (C) 2014 Adam Huang <poisondog@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package poisondog.net;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author Adam Huang
 * @since 2014-01-03
 */
public class SocketParameterTest {
	private SocketParameter parameter;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		parameter = new SocketParameter();
	}

	@Test
	public void testHost() throws Exception {
		Assert.assertEquals("localhost", parameter.getHost());
		parameter.setHost("google.com");
		Assert.assertEquals("google.com", parameter.getHost());
		parameter.setHost("8.8.4.4");
		Assert.assertEquals("8.8.4.4", parameter.getHost());
	}

	@Test
	public void testPort() {
		Assert.assertEquals(80, parameter.getPort());
		parameter.setPort(124);
		Assert.assertEquals(124, parameter.getPort());
	}

//	@Test
//	public void testInputStream() throws Exception {
//		Assert.assertNull(parameter.getInputStream());
//		File temp = File.createTempFile("temp", ".tmp");
//		FileInputStream is = new FileInputStream(temp);
//		parameter.setInputStream(is);
//		Assert.assertEquals(is, parameter.getInputStream());
//		temp.delete();
//	}

	@Test
	public void testData() throws Exception {
		Assert.assertEquals(0, parameter.getData().length);
		parameter.setData("result".getBytes());
		Assert.assertEquals("result", new String(parameter.getData()));
	}

	@Test
	public void testTimeout() {
		Assert.assertTrue(parameter.getTimeout() < 0);
		parameter.setTimeout(100);
		Assert.assertEquals(100, parameter.getTimeout());
	}
}
