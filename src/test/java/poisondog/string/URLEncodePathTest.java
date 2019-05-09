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
 * @author Adam Huang
 */
public class URLEncodePathTest {
	private URLEncodePath task;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		task = new URLEncodePath("utf8");
	}

	@Test
	public void testExtension() throws Exception {
		Assert.assertEquals("/%E4%B8%AD/%E6%96%87", task.process("/中/文"));
		Assert.assertEquals("/%E4%B8%AD/%E6%96%87/", task.process("/中/文/"));
		Assert.assertEquals("webdav://122.133.144.21:89/demo/%E4%B8%AD/%E6%96%87", task.process("webdav://122.133.144.21:89/demo/中/文"));
		Assert.assertEquals("webdav://122.133.144.21:89/demo/%E4%B8%AD/%E6%96%87/", task.process("webdav://122.133.144.21:89/demo/中/文/"));
		Assert.assertEquals("webdav://122.133.144.21:89/demo/%E4%B8%AD%2B%E6%96%87/", task.process("webdav://122.133.144.21:89/demo/中+文/"));
		Assert.assertEquals("webdav://122.133.144.21:89/", task.process("webdav://122.133.144.21:89/"));
	}
}
