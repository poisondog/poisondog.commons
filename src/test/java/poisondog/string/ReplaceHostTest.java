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
public class ReplaceHostTest {
	private ReplaceHost mTask;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mTask = new ReplaceHost("localhost");
	}

	@Test
	public void testApp() throws Exception {
		Assert.assertEquals("http://localhost/manual/", mTask.process("http://ant.apache.org/manual/"));
		Assert.assertEquals("http://localhost/manual/ant.org", mTask.process("http://ant.org/manual/ant.org"));
		Assert.assertEquals("file:///reports/junit/html/index.html", mTask.process("file:///reports/junit/html/index.html"));
	}

	@Test
	public void testWithUserinfo() throws Exception {
		Assert.assertEquals("ftp://user:password@localhost/", mTask.process("ftp://user:password@ant.apache.org/"));
	}
}
