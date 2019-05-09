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
package poisondog.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.log.LogStackTrace;

/**
 * @author Adam Huang
 * @since 2017-06-07
 */
public class GetCallerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDefault() throws Exception {
		GetCallerInfo get = new GetCallerInfo();
		Assert.assertEquals("java.lang.Thread", get.execute(null).getClassName());
	}

	@Test
	public void testInput() throws Exception {
		GetCallerInfo get = new GetCallerInfo();
		Assert.assertEquals(get.getClass().getName(), get.execute("java.lang.Thread").getClassName());
	}
}
