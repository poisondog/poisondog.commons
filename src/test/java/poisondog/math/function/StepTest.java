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
package poisondog.math.function;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.math.Vector;

/**
 * @author Adam Huang
 */
public class StepTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCalculate() throws Exception {
		Vector input = new Vector(1.0, .1, .01, .0, -.01, -.1, -1.0);
		Step function = new Step();
		Vector result = Vector.toVector(function.execute(input));
		Assert.assertEquals(1, result.get(0), Math.pow(10, -6));
		Assert.assertEquals(1, result.get(1), Math.pow(10, -6));
		Assert.assertEquals(1, result.get(2), Math.pow(10, -6));
		Assert.assertEquals(1, result.get(3), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(4), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(5), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(6), Math.pow(10, -6));
	}
}
