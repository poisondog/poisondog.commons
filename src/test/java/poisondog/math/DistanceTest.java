/*
 * Copyright (C) 2016 Adam Huang <poisondog@gmail.com>
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
package poisondog.math;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 * @since 2016-05-22
 */
public class DistanceTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCase1() throws Exception {
		Distance task = new Distance(new Vector(new double[]{-1, 0, -1}));
		Double result = task.execute(new Vector(new double[]{2, 1, 0}));
		Assert.assertEquals(Math.sqrt(11), result, Math.pow(10, -6));
	}

	@Test
	public void testCase2() throws Exception {
		Distance task = new Distance(new Vector(new double[]{6, 3, -1}));
		Double result = task.execute(new Vector(new double[]{4, 1, 2}));
		Assert.assertEquals(Math.sqrt(17), result, Math.pow(10, -6));
	}
}
