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
package poisondog.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author poisondog <poisondog@gmail.com>
 */
public class ReplacePathTest {
	private ReplacePath mTask;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mTask = new ReplacePath("/path/to/new/location");
	}

	@Test
	public void testWithBrackets() throws Exception {
		Assert.assertEquals("http://ant.apache.org/path/to/new/location", mTask.process("http://ant.apache.org/manual(4-1)/"));
	}

	@Test
	public void testSquareBrackets() throws Exception {
		Assert.assertEquals("http://ant.apache.org/path/to/new/location", mTask.process("http://ant.apache.org/manual[4-1]/"));
	}

	@Test
	public void testBraces() throws Exception {
		Assert.assertEquals("http://ant.apache.org/path/to/new/location", mTask.process("http://ant.apache.org/manual{4-1}/"));
	}

	@Test
	public void testPlus() throws Exception {
		Assert.assertEquals("http://ant.apache.org/path/to/new/location", mTask.process("http://ant.apache.org/manual4+1/"));
	}

	@Test
	public void testStar() throws Exception {
		Assert.assertEquals("http://ant.apache.org/path/to/new/location", mTask.process("http://ant.apache.org/manual4*1/"));
	}

	@Test
	public void testExclamation() throws Exception {
		Assert.assertEquals("http://ant.apache.org/path/to/new/location", mTask.process("http://ant.apache.org/manual4!1/"));
	}

	@Test
	public void testComma() throws Exception {
		Assert.assertEquals("http://ant.apache.org/path/to/new/location", mTask.process("http://ant.apache.org/manual4,1/"));
	}

	@Test
	public void testSingleQuotes() throws Exception {
		Assert.assertEquals("http://ant.apache.org/path/to/new/location", mTask.process("http://ant.apache.org/manual'4'1/"));
	}

	@Test
	public void testEqualSign() throws Exception {
		Assert.assertEquals("http://ant.apache.org/path/to/new/location", mTask.process("http://ant.apache.org/manual4=1/"));
//		Assert.assertEquals("http://ant.apache.org/path/to/new/location", mTask.process("http://ant.apache.org/[]{}#%^+=_ |*~<>€$¥,?!',/"));
	}

	@Test
	public void testDollar() throws Exception {
		Assert.assertEquals("webdav://ant.apache.org/path/to/new/location", mTask.process("webdav://ant.apache.org/MySkyBox/sata_1/@$,..JPG"));
	}

	@Test
	public void testApp() throws Exception {
		Assert.assertEquals("http://ant.apache.org/path/to/new/location", mTask.process("http://ant.apache.org/manual/"));
		Assert.assertEquals("http://ant.org/path/to/new/location", mTask.process("http://ant.org/manual/ant.org/"));
	}

	@Test
	public void testWrongLocal() throws Exception {
		Assert.assertEquals("file:///path/to/new/location", mTask.process("file:///reports/junit/html/index.html"));
	}

	@Test
	public void testLocal() throws Exception {
		Assert.assertEquals("file:/path/to/new/location", mTask.process("file:/reports/junit/html/index.html"));
	}

	@Test
	public void testAuthority() throws Exception {
		Assert.assertEquals("ftp://user:password@ant.apache.org/path/to/new/location", mTask.process("ftp://user:password@ant.apache.org/"));
		Assert.assertEquals("ftp://user:password@ant.apache.org/path/to/new/location", mTask.process("ftp://user:password@ant.apache.org"));
	}

	@Test
	public void testUser() throws Exception {
		Assert.assertEquals("ftp://user@ant.apache.org/path/to/new/location", mTask.process("ftp://user@ant.apache.org/"));
		Assert.assertEquals("ftp://user@ant.apache.org/path/to/new/location", mTask.process("ftp://user@ant.apache.org"));
	}

	@Test
	public void testHasDollar() throws Exception {
		mTask = new ReplacePath("/path/to/$RECYCLE.BIN/");
		Assert.assertEquals("http://ant.apache.org/path/to/$RECYCLE.BIN/", mTask.process("http://ant.apache.org/manual(4-1)/"));
	}
}
