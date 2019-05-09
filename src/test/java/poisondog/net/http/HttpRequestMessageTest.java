/*
 * Copyright (C) 2015 Adam Huang <poisondog@gmail.com>
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
package poisondog.net.http;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

/**
 * @author Adam Huang
 */
public class HttpRequestMessageTest {
	private HttpRequestMessage mMessage;
	private StringBuilder mExpected;

	private void givenMessage(String method, String path, String host) {
		mMessage = new HttpRequestMessage(method, path, host);
		givenExpected(mMessage);
	}

	private void givenExpected(HttpRequestMessage message) {
		mExpected = new StringBuilder();
		mExpected.append(message.getMethod());
		mExpected.append(" ");
		mExpected.append(message.getPath());
		mExpected.append(" ");
		mExpected.append("HTTP/1.1\n");
		mExpected.append("Host: ");
		mExpected.append(message.getHost());
		mExpected.append("\n");
	}

	private void thenSameContent() {
		Assert.assertTrue(Arrays.equals(mExpected.toString().getBytes(), mMessage.getContent()));
	}

	@Before
	public void setUp() throws Exception {
		givenMessage("GET", "/path/to/file", "www.google.com");
	}

	@Test
	public void testGetMethod() throws Exception {
		Assert.assertEquals("GET", mMessage.getMethod());
	}

	@Test
	public void testPostMethod() throws Exception {
		givenMessage("POST", "/path/to/file", "www.google.com");
		Assert.assertEquals("POST", mMessage.getMethod());
		mExpected.append("\n");
		thenSameContent();
	}

	@Test
	public void testPath() throws Exception {
		Assert.assertEquals("/path/to/file", mMessage.getPath());
	}

	@Test
	public void testAnotherPath() throws Exception {
		givenMessage("GET", "/path/to/another/file", "www.google.com");
		Assert.assertEquals("/path/to/another/file", mMessage.getPath());
		mExpected.append("\n");
		thenSameContent();
	}

	@Test
	public void testHost() throws Exception {
		Assert.assertEquals("www.google.com", mMessage.getHost());
	}

	@Test
	public void testAnotherHost() throws Exception {
		givenMessage("GET", "/path/to/file", "google.com");
		Assert.assertEquals("google.com", mMessage.getHost());
		mExpected.append("\n");
		thenSameContent();
	}

	@Test
	public void testContent() throws Exception {
		mExpected.append("\n");
		thenSameContent();
	}

	@Test
	public void testAuthorization1() throws Exception {
		mMessage.setAuthorization("username", "password");
		mExpected.append("Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQ=\n");
		mExpected.append("\n");
		thenSameContent();
	}

	@Test
	public void testAuthorization2() throws Exception {
		mMessage.setAuthorization("userame", "pasword");
		mExpected.append("Authorization: Basic dXNlcmFtZTpwYXN3b3Jk\n");
		mExpected.append("\n");
		thenSameContent();
	}

	@Test
	public void testContentType1() throws Exception {
		mMessage.setHeader("Content-Type", "image/jpg");
		mExpected.append("Content-Type: image/jpg\n");
		mExpected.append("\n");
		thenSameContent();
	}

	@Test
	public void testContentType2() throws Exception {
		mMessage.setHeader("Content-Type", "text/html");
		mExpected.append("Content-Type: text/html\n");
		mExpected.append("\n");
		thenSameContent();
	}

	@Test
	public void testContentLength1() throws Exception {
		mMessage.setHeader("Content-Length", "324");
		mExpected.append("Content-Length: 324\n");
		mExpected.append("\n");
		thenSameContent();
	}

	@Test
	public void testContentLength2() throws Exception {
		mMessage.setHeader("Content-Length", "2978");
		mExpected.append("Content-Length: 2978\n");
		mExpected.append("\n");
		thenSameContent();
	}

	@Test
	public void testConnection1() throws Exception {
		mMessage.setHeader("Connection", "Keep-Alive");
		mExpected.append("Connection: Keep-Alive\n");
		mExpected.append("\n");
		thenSameContent();
	}

	@Test
	public void testConnection2() throws Exception {
		mMessage.setHeader("Connection", "Close");
		mExpected.append("Connection: Close\n");
		mExpected.append("\n");
		thenSameContent();
	}

	@Test
	public void testBody() throws Exception {
		mMessage.setBody("Here is Body".getBytes());
		mExpected.append("\n");
		mExpected.append("Here is Body");
		thenSameContent();
	}
}
