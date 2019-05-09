/*
 * Copyright (C) 2019 Adam Huang <poisondog@gmail.com>
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
 * @since 2019-02-20
 */
public class CalculateAngleTest {
	private CalculateAngle mTask;

	@Before
	public void setUp() throws Exception {
		mTask = new CalculateAngle(0, 0);
	}

	@Test
	public void testOne() throws Exception {
		Assert.assertEquals(Math.PI / 4, mTask.execute(1.0, 1.0), Math.pow(10, -6));
		Assert.assertEquals(Math.PI / 3, mTask.execute(1.0, Math.sqrt(3)), Math.pow(10, -6));
	}

	@Test
	public void testTwo() throws Exception {
		Assert.assertEquals(Math.PI * 3 / 4, mTask.execute(-1.0, 1.0), Math.pow(10, -6));
		Assert.assertEquals(Math.PI * 2 / 3, mTask.execute(-1.0, Math.sqrt(3)), Math.pow(10, -6));
	}

	@Test
	public void testThree() throws Exception {
		Assert.assertEquals(Math.PI * 5 / 4, mTask.execute(-1.0, -1.0), Math.pow(10, -6));
		Assert.assertEquals(Math.PI * 4 / 3, mTask.execute(-1.0, -Math.sqrt(3)), Math.pow(10, -6));
	}

	@Test
	public void testFour() throws Exception {
		Assert.assertEquals(Math.PI * 7 / 4, mTask.execute(1.0, -1.0), Math.pow(10, -6));
		Assert.assertEquals(Math.PI * 5 / 3, mTask.execute(1.0, -Math.sqrt(3)), Math.pow(10, -6));
	}
}
