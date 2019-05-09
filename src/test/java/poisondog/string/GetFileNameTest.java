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
public class GetFileNameTest {
	private GetFileName task;

	@Before
	public void setUp() throws Exception {
		task = new GetFileName();
	}

	@Test
	public void testContent() throws Exception {
		Assert.assertEquals("java.html.mp3", task.execute("/ant.apache.org/manual/Tasks/java.html.mp3"));
		Assert.assertEquals("java.html.mp3", task.execute("/java.html.mp3"));
		Assert.assertEquals("java.html.mp3", task.execute("java.html.mp3"));
		Assert.assertEquals(".setting", task.execute(".setting"));
		Assert.assertEquals("java.html.mp3", task.execute("ant.apache.org/manual/Tasks/java.html.mp3"));
		Assert.assertEquals("java.html#123.mp3", task.execute("ant.apache.org/manual/Tasks/java.html#123.mp3"));
		Assert.assertEquals("java.html", task.execute("http://ant.apache.org/manual/Tasks/java.html"));
		Assert.assertEquals("java", task.execute("http://ant.apache.org/manual/Tasks/java/"));
		Assert.assertEquals("java", task.execute("http://ant.apache.org/manual/Tasks/java"));
		Assert.assertEquals("java測試", task.execute("http://ant.apache.org/manual/Tasks/java測試"));
//		Assert.assertEquals("java", task.execute("java"));
//		Assert.assertEquals("java", task.execute("java/"));
	}
}
