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
public class ExtractAuthorityTest {
	private ExtractAuthority task;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		task = new ExtractAuthority();
	}

	@Test
	public void testNormal() throws Exception {
		Assert.assertEquals("ant.apache.org", task.process("http://ant.apache.org/manual/Tasks/java.html"));
		Assert.assertEquals("ant.org", task.process("http://ant.org/manual/Tasks/java.html"));
	}

	@Test
	public void testAuthority() throws Exception {
		Assert.assertEquals("user:p*s_-!.~'()*,;:$&+=@ant.apache.org", task.process("ftp://user:p*s_-!.~'()*,;:$&+=@ant.apache.org/"));
		Assert.assertEquals("user:pa55w?rd@h.ost:80", task.process("ftp://user:pa55w%3Frd@h.ost:80"));
	}

	@Test
	public void testLocal() throws Exception {
		Assert.assertEquals("", task.process("file:///reports/junit/html/index.html"));
		Assert.assertEquals("", task.process("file:/reports/junit/html/index.html"));
	}

	@Test
	public void testPath() throws Exception {
		Assert.assertEquals("", task.process("/reports/junit/html/index.html"));
		Assert.assertEquals("", task.process("/reports/junit//html/index.html"));
	}
}
