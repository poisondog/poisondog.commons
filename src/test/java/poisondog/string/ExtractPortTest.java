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
package poisondog.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 */
public class ExtractPortTest {
	private ExtractPort task;

	@Before
	public void setUp() throws Exception {
		task = new ExtractPort();
	}

	@Test
	public void testEmpty() throws Exception {
		Assert.assertEquals("", task.process(""));
	}

	@Test
	public void testNull() throws Exception {
		Assert.assertEquals("", task.process(null));
	}

	@Test
	public void testPort() throws Exception {
		Assert.assertEquals("8089", task.process("http://ant.apache.org:8089/manual/"));
	}

	@Test
	public void testAnotherPort() throws Exception {
		Assert.assertEquals("8080", task.process("http://ant.apache.org:8080/manual/"));
	}

	@Test
	public void testFtpFile() throws Exception {
		Assert.assertEquals("21", task.process("ftp://ant.apache.org/manual/Tasks/java.html"));
	}

	@Test
	public void testHttpFile() throws Exception {
		Assert.assertEquals("80", task.process("http://ant.apache.org/manual/Tasks/java.html"));
	}

	@Test
	public void testHttpFolder() throws Exception {
		Assert.assertEquals("80", task.process("http://ant.apache.org/manual/Tasks/java/"));
	}

	@Test
	public void testHttpsFile() throws Exception {
		Assert.assertEquals("443", task.process("https://ant.apache.org/manual/Tasks/java.html"));
	}

	@Test
	public void testHttpWithAuth() throws Exception {
		Assert.assertEquals("80", task.process("http://user:password@ant.apache.org/"));
	}

	@Test
	public void testFileScheme() throws Exception {
		Assert.assertEquals("", task.process("file:/reports/junit/html/index.html"));
	}

	@Test
	public void testPath() throws Exception {
		Assert.assertEquals("", task.process("/reports/junit/html/index.html"));
	}
}
