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
public class GetSchemeTest {
	private GetScheme mTask;

	@Before
	public void setUp() throws Exception {
		mTask = new GetScheme();
	}

	@Test
	public void testHttp() throws Exception {
		Assert.assertEquals("http", mTask.execute("http://ant.apache.org/manual/"));
	}

	@Test
	public void testHttpWithPort() throws Exception {
		Assert.assertEquals("http", mTask.execute("http://ant.apache.org:8080/manual/"));
	}

	@Test
	public void testFtpWithAuth() throws Exception {
		Assert.assertEquals("ftp", mTask.execute("ftp://user:password@ant.apache.org/"));
	}

	@Test
	public void testFileWithComplete() throws Exception {
		Assert.assertEquals("file", mTask.execute("file:/reports/junit/html/index.html"));
	}

	@Test
	public void testFileWithAbsPath() throws Exception {
		Assert.assertEquals("file", mTask.execute("/reports/junit/html/index.html"));
	}

	@Test
	public void testFileWithPath() throws Exception {
		Assert.assertEquals("file", mTask.execute("junit/html/index.html"));
	}

	@Test
	public void testEmptyString() throws Exception {
		Assert.assertEquals("", mTask.execute(""));
	}

	@Test
	public void testNull() throws Exception {
		Assert.assertEquals("", mTask.execute(null));
	}

	@Test
	public void testJar() throws Exception {
		Assert.assertEquals("jar", mTask.execute("jar:../lib/classes.jar!/META-INF/manifest.mf"));
	}

	@Test
	public void testJarZip() throws Exception {
		Assert.assertEquals("jar:zip", mTask.execute("jar:zip:outer.zip!/nested.jar!/somedir"));
	}

	@Test
	public void testTarFile() throws Exception {
		Assert.assertEquals("tgz:file", mTask.execute("tgz:file://anyhost/dir/mytar.tgz!/somepath/somefile"));
	}

	@Test
	public void testTarGzHttp() throws Exception {
		Assert.assertEquals("tar:gz:http", mTask.execute("tar:gz:http://anyhost/dir/mytar.tar.gz!/mytar.tar!/path/in/tar/README.txt"));
	}
}
