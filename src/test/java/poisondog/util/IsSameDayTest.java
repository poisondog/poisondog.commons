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
package poisondog.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 */
public class IsSameDayTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSameDay() throws Exception {
		IsSameDay task = new IsSameDay(1445068396952L);
		Assert.assertTrue(task.execute(1445068440746L));
	}

	@Test
	public void testAnotherDay() throws Exception {
		IsSameDay task = new IsSameDay(1445068396952L);
		Assert.assertFalse(task.execute(1444068440746L));
	}
}
