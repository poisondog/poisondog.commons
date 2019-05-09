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
package poisondog.log;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 * @since 2018-05-14
 */
public class LogLevelTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testLevel() throws Exception {
		Assert.assertEquals(0, LogLevel.ERROR.ordinal());
		Assert.assertEquals(1, LogLevel.WARNING.ordinal());
		Assert.assertEquals(2, LogLevel.INFO.ordinal());
		Assert.assertEquals(3, LogLevel.DEBUG.ordinal());
		Assert.assertEquals(4, LogLevel.VERBOSE.ordinal());
	}
}
