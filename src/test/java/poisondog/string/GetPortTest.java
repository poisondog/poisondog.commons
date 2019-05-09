/*
 * Copyright (C) 2017 Adam Huang <poisondog@gmail.com>
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
 * @since 2017-07-24
 */
public class GetPortTest {
	private GetPort task;

	@Before
	public void setUp() throws Exception {
		task = new GetPort();
	}

	@Test
	public void testPort() throws Exception {
		Assert.assertEquals("8089", task.execute("http://ant.apache.org:8089/manual/"));
	}

	@Test
	public void testAnotherPort() throws Exception {
		Assert.assertEquals("8080", task.execute("http://ant.apache.org:8080/manual/"));
	}

	@Test
	public void testEmpty() throws Exception {
		Assert.assertEquals("", task.execute(""));
	}

	@Test
	public void testNull() throws Exception {
		Assert.assertEquals("", task.execute(null));
	}

	@Test
	public void testFtpFile() throws Exception {
		Assert.assertEquals("", task.execute("ftp://ant.apache.org/manual/Tasks/java.html"));
	}

	@Test
	public void testHttpFile() throws Exception {
		Assert.assertEquals("", task.execute("http://ant.apache.org/manual/Tasks/java.html"));
	}

	@Test
	public void testHttpWithAuth() throws Exception {
		Assert.assertEquals("", task.execute("http://user:password@ant.apache.org/"));
	}

	@Test
	public void testFileScheme() throws Exception {
		Assert.assertEquals("", task.execute("file:/reports/junit/html/index.html"));
	}

	@Test
	public void testPath() throws Exception {
		Assert.assertEquals("", task.execute("/reports/junit/html/index.html"));
	}
}
