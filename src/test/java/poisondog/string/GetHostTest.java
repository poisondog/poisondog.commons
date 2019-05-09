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
public class GetHostTest {
	private GetHost task;

	@Before
	public void setUp() throws Exception {
		task = new GetHost();
	}

	@Test
	public void testEmpty() throws Exception {
		Assert.assertEquals("", task.execute(""));
		Assert.assertEquals("", task.execute(null));
	}

	@Test
	public void testHttpFile() throws Exception {
		Assert.assertEquals("ant.apache.org", task.execute("http://ant.apache.org/manual/Tasks/java.html"));
	}

	@Test
	public void testHttpFolder() throws Exception {
		Assert.assertEquals("ant.apache.org", task.execute("http://ant.apache.org/manual/Tasks/java/"));
	}

	@Test
	public void testHttpWithPort() throws Exception {
		Assert.assertEquals("ant.apache.org", task.execute("http://ant.apache.org:8080/manual/"));
	}

	@Test
	public void testHttpWithUserinfo() throws Exception {
		Assert.assertEquals("ant.apache.org", task.execute("http://user:password@ant.apache.org/"));
	}

	@Test
	public void testFileUrl() throws Exception {
		Assert.assertEquals("", task.execute("file:/reports/junit/html/index.html"));
		Assert.assertEquals("", task.execute("file:/storage/emulated/0/"));
	}

	@Test
	public void testAbsolutePath() throws Exception {
		Assert.assertEquals("", task.execute("/reports/junit/html/index.html"));
	}

	@Test
	public void testComplex() throws Exception {
		Assert.assertEquals("ant.apache.org", task.execute("tar:gz:http://user:pass@ant.apache.org:8081/dir/mytar.tar.gz!/my.tar!/path/in/tar/a.tar.gz"));
	}

}
