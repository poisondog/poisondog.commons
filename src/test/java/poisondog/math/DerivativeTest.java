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
package poisondog.math;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 * @since 2018-09-10
 */
public class DerivativeTest {
	private Derivative mTask;
	private Function<Vector> mFunction;

	@Before
	public void setUp() throws Exception {
		// x^3 - x*y + y^2
		mFunction = new Function<Vector>() {
			public Vector execute(Vector input) throws Exception {
				if (input.length() != 2)
					throw new IllegalArgumentException("this function need vector length equals 2");
				return new Vector(Math.pow(input.get(0), 3) - input.get(0) * input.get(1) + Math.pow(input.get(1), 2));
			}
		};
		mTask = new Derivative(mFunction);
	}

	@Test
	public void testSample1() throws Exception {
		Vector input = new Vector(2, 3);
		input.setTolerance(Math.pow(10, -10));
		Vector result = new Vector(9, 4);
		result.setTolerance(Math.pow(10, -4));
		Assert.assertEquals(result, mTask.execute(input));
		Assert.assertEquals(input, new Vector(2, 3));
	}

	@Test
	public void testSample2() throws Exception {
		Vector input = new Vector(-2, 7);
		input.setTolerance(Math.pow(10, -10));
		Vector result = new Vector(5, 16);
		result.setTolerance(Math.pow(10, -4));
		Assert.assertEquals(result, mTask.execute(input));
		Assert.assertEquals(input, new Vector(-2, 7));
	}

}
