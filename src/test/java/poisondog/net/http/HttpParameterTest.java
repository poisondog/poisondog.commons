/*
 * Copyright (C) 2013 Adam Huang <poisondog@gmail.com>
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.commons.HashFunction;
import poisondog.io.Accumulation;
import poisondog.io.StepListener;
import poisondog.log.SimpleLogger;
/**
 * @author Adam Huang
 */
public class HttpParameterTest {
	private HttpParameter mPara;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mPara = new HttpParameter();
	}

	@Test
	public void testDefaultHttpQuery() throws Exception {
		Assert.assertEquals("", mPara.httpQuery());
	}

	@Test
	public void testDefaultCharset() throws Exception {
		Assert.assertEquals("utf8", mPara.getCharset());
	}

	@Test
	public void testCharset() throws Exception {
		mPara.setCharset("big5");
		Assert.assertEquals("big5", mPara.getCharset());
	}

	@Test
	public void testText() throws Exception {
		mPara.addText("t1", "Test for 1");
		Assert.assertEquals("?t1=Test%20for%201", mPara.httpQuery());
		Assert.assertEquals(1, mPara.textKeys().size());

		mPara.addText("a2", "Test for 2");
		Assert.assertTrue(mPara.httpQuery().contains("t1=Test%20for%201"));
		Assert.assertTrue(mPara.httpQuery().contains("a2=Test%20for%202"));
		Assert.assertEquals(2, mPara.textKeys().size());

		Assert.assertEquals("Test for 1", mPara.getText("t1"));
		Assert.assertEquals("Test for 2", mPara.getText("a2"));
	}

	@Test
	public void testFile() throws Exception {
		File t1 = File.createTempFile("temp", ".jpg");
		FileUtils.write(t1, "77");
		File t2 = File.createTempFile("temp", ".txt");
		FileUtils.write(t2, "977");
		String expected1 = HashFunction.md5("77".getBytes());
		String expected2 = HashFunction.md5("977".getBytes());
		mPara.addFile("f1", t1);
		Assert.assertEquals(1, mPara.fileKeys().size());
		mPara.addFile("f2", t2);
		Assert.assertEquals(2, mPara.fileKeys().size());
		File f1 = mPara.getFile("f1");
		File f2 = mPara.getFile("f2");
		String result1 = HashFunction.md5(IOUtils.toByteArray(new FileInputStream(f1)));
		String result2 = HashFunction.md5(IOUtils.toByteArray(new FileInputStream(f2)));
		Assert.assertEquals(expected1, result1);
		Assert.assertEquals(expected2, result2);
		t1.delete();
		t2.delete();
	}

	@Test
	public void testUrl() throws Exception {
		mPara.setUrl("http://www.google.com/rsp_put.php");
		Assert.assertEquals("http://www.google.com/rsp_put.php", mPara.getUrl());
	}

	@Test
	public void testHeader() throws Exception {
		Assert.assertEquals(0, mPara.headerKeys().size());
		mPara.addHeader("Connection", "keep-alive");
		Assert.assertEquals(1, mPara.headerKeys().size());
		Assert.assertEquals("keep-alive", mPara.getHeader("Connection"));
	}

	@Test
	public void testTimeout() {
		mPara.setTimeout(1427);
		Assert.assertEquals(1427, mPara.getTimeout());
		mPara.setTimeout(882211);
		Assert.assertEquals(882211, mPara.getTimeout());
	}

	@Test
	public void testDefaultRequestMethod() throws Exception {
		Assert.assertEquals("GET", mPara.getRequestMethod());
	}

	@Test
	public void testRequestMethod() throws Exception {
		mPara.setRequestMethod("POST");
		Assert.assertEquals("POST", mPara.getRequestMethod());
	}

	@Test
	public void testNeverUseFile() throws Exception {
		mPara.neverUseFile();
		try {
		}catch(Exception e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testEquals() throws Exception {
		HttpParameter p1 = createSimpleHttpParameter();
		HttpParameter p2 = createSimpleHttpParameter();
		HttpParameter p3 = createSimpleHttpParameter();

		Assert.assertTrue(p1.equals(p1));
		Assert.assertTrue(p1.equals(p2));
		Assert.assertTrue(p1.equals(p3));
		Assert.assertTrue(p2.equals(p1));
		Assert.assertTrue(p2.equals(p3));
	}

	@Test
	public void testEqualsNull() throws Exception {
		HttpParameter p1 = createSimpleHttpParameter();
		Assert.assertFalse(p1.equals(null));
	}

	@Test
	public void testEqualsInteger() throws Exception {
		HttpParameter p1 = createSimpleHttpParameter();
		Assert.assertFalse(p1.equals(new Integer(0)));
	}

	private HttpParameter createSimpleHttpParameter() throws IOException {
		File temp = createTempFile();
		HttpParameter parameter = new HttpParameter();
		parameter.setUrl("http://www.google.com/rsp_get.php");
		parameter.addText("t", "Test for 1");
		parameter.addFile("f", temp);
		temp.delete();
		return parameter;
	}

	private File createTempFile() throws IOException {
		File file = File.createTempFile("temp", ".jpg");
		FileUtils.write(file, "977");
		return file;
	}

	@Test
	public void testNotEqualsUrl() throws Exception {
		HttpParameter p1 = new HttpParameter();
		HttpParameter p2 = new HttpParameter();
		p1.setUrl("http://www.google.com/rsp_put.php");
		p2.setUrl("http://www.google.com/rsp_get.php");
		Assert.assertFalse(p1.equals(p2));
	}

	@Test
	public void testNotEqualsText() throws Exception {
		HttpParameter p1 = new HttpParameter();
		HttpParameter p2 = new HttpParameter();
		HttpParameter p3 = new HttpParameter();
		p1.addText("t", "Test for 1");
		p2.addText("t", "Test for 2");
		p3.addText("s", "Test for 2");
		Assert.assertFalse(p1.equals(p2));
		Assert.assertFalse(p2.equals(p3));
	}

	@Test
	public void testUsername() throws Exception {
		HttpParameter p = new HttpParameter();
		p.setUsername("username");
		Assert.assertEquals("username", p.getUsername());
	}

	@Test
	public void testPassword() throws Exception {
		HttpParameter p = new HttpParameter();
		p.setPassword("pa5Sw0rd");
		Assert.assertEquals("pa5Sw0rd", p.getPassword());
	}

	@Test
	public void testStepListener() throws Exception {
		StepListener listener = new Accumulation();
		HttpParameter parameter = new HttpParameter();
		parameter.setStepListener(listener);
		Assert.assertEquals(listener, parameter.getStepListener());
	}
}
