/*
 * Copyright (C) 2018 Adam Huang <poisondog@gmail.com>
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
 * @since 2018-09-26
 */
public class GetMatchTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSample1() throws Exception {
		GetMatch task = new GetMatch("(.)(.)(.).?\\3\\2\\1");
		String result = task.execute("xfoooofxxxxxxfoo");
		Assert.assertEquals("foooof", result);
		Assert.assertEquals("xxxxxx", task.next());
		Assert.assertNull(task.next());
	}

	@Test
	public void testSample2() throws Exception {
		GetMatch task = new GetMatch(".*foo");
		String result = task.execute("xfooxxxxxxfoo");
		Assert.assertEquals("xfooxxxxxxfoo", result);
	}

}
