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
package poisondog.log;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 * @since 2017-06-07
 */
public class LogFactoryTest {
	private LogFactory mFactory;

	@Before
	public void setUp() throws Exception {
		mFactory = new LogFactory();
	}

	@Test
	public void testCreate() throws Exception {
		LogItem item = mFactory.execute("tag", "message");
		Assert.assertEquals("tag", item.getTag());
		Assert.assertEquals("message", item.getMessage());
		Assert.assertEquals(LogLevel.VERBOSE, item.getLevel());
	}

	@Test
	public void testInputOne() throws Exception {
		try {
			mFactory.execute("message");
			Assert.fail("need Exception");
		} catch(Exception e) {
			Assert.assertTrue(true);
		}
	}
}
