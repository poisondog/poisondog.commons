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
package poisondog.net.http;

import java.net.HttpURLConnection;
import java.util.Arrays;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 */
public class HttpResponseTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testResponseCode() throws Exception {
		HttpResponse response = new HttpResponse(HttpURLConnection.HTTP_OK);
		Assert.assertEquals(200, response.getResponseCode());
		response = new HttpResponse(HttpURLConnection.HTTP_NOT_FOUND);
		Assert.assertEquals(404, response.getResponseCode());
	}

	@Test
	public void testHeader() throws Exception {
		HttpResponse response = new HttpResponse(HttpURLConnection.HTTP_OK);
		response.putHeader("ETag", "\"52cba784-77de\"");
		response.putHeader("Content-Type", "text/html; charset=utf-8");
		Assert.assertEquals("\"52cba784-77de\"", response.getHeader("ETag"));
		Assert.assertEquals("text/html; charset=utf-8", response.getHeader("Content-Type"));
	}

	@Test
	public void testInputStream() throws Exception {
		HttpResponse response = new HttpResponse(HttpURLConnection.HTTP_OK);
		response.setInputStream(IOUtils.toInputStream("test", "utf8"));
		Assert.assertEquals("test", IOUtils.toString(response.getInputStream(), "utf8"));
	}

	@Test
	public void testInputStreamString() throws Exception {
		HttpResponse response = new HttpResponse(HttpURLConnection.HTTP_OK);
		response.setInputStream(IOUtils.toInputStream("test", "utf8"));
		Assert.assertEquals("test", response.getInputStreamString());
	}

	@Test
	public void testContent() throws Exception {
		HttpResponse response = new HttpResponse(HttpURLConnection.HTTP_OK);
		response.setContent("test".getBytes());
		Assert.assertTrue(Arrays.equals("test".getBytes(), response.getContent()));
	}
}
