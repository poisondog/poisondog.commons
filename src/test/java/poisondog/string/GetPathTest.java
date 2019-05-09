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
 * @since 2017-07-06
 */
public class GetPathTest {
	private GetPath task;

	@Before
	public void setUp() throws Exception {
		task = new GetPath();
	}

	@Test
	public void testPath() throws Exception {
		Assert.assertEquals("/reports/junit/html/index.html", task.execute("/reports/junit/html/index.html"));
	}

	@Test
	public void testWrongPath() throws Exception {
		Assert.assertEquals("/reports/junit/html/index.html", task.execute("/reports/junit//html/index.html"));
	}

	@Test
	public void testHttpWrongPath() throws Exception {
		Assert.assertEquals("/manual/Tasks/java.html", task.execute("http://ant.apache.org/manual//Tasks/java.html"));
	}

	@Test
	public void testLocal() throws Exception {
		Assert.assertEquals("/reports/junit/html/index.html", task.execute("file:///reports/junit/html/index.html"));
		Assert.assertEquals("/reports/junit/html/index.html", task.execute("file:/reports/junit/html/index.html"));
	}

	@Test
	public void testChinese() throws Exception {
		Assert.assertEquals("/reports/junit/html/中文.jpg", task.execute("/reports/junit/html/中文.jpg"));
	}

	@Test
	public void testHttpFile() throws Exception {
		Assert.assertEquals("/manual/Tasks/java.html", task.execute("http://ant.apache.org/manual/Tasks/java.html"));
	}

	@Test
	public void testHttpFolder() throws Exception {
		Assert.assertEquals("/manual/Tasks/", task.execute("http://ant.apache.org/manual/Tasks/"));
		Assert.assertEquals("/manual/Tasks/", task.execute("http://ant.org/manual/Tasks/"));
		Assert.assertEquals("/manual/", task.execute("http://ant.apache.org/manual/"));
	}

	@Test
	public void testWithQuery() throws Exception {
		Assert.assertEquals("/manual/Tasks.jsp", task.execute("http://ant.apache.org/manual/Tasks.jsp?id=124"));
	}

	@Test
	public void testHttpWithChinese() throws Exception {
		Assert.assertEquals("/manual/Tasks/中文.png", task.execute("http://ant.apache.org/manual/Tasks/中文.png"));
	}

	@Test
	public void testFtpAuthority() throws Exception {
		Assert.assertEquals("/", task.execute("ftp://user:password@ant.apache.org/"));
		Assert.assertEquals("/", task.execute("ftp://user:password@ant.apache.org"));
		Assert.assertEquals("/html/index.html", task.execute("ftp://user:password@ant.apache.org/html/index.html"));
	}

	@Test
	public void testRoot() throws Exception {
		Assert.assertEquals("/", task.execute("http://ant.apache.org/"));
	}

	@Test
	public void testDollar() throws Exception {
		Assert.assertEquals("/Box/sata_1/@$,..JPG", task.execute("webdav://192.168.1.1:8080/Box/sata_1/@$,..JPG"));
	}
}
