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
 * @since 2016-07-02
 */
public class DifferentialTest {
	private Function<Vector> mFunction;
	private Function<Vector> mDiff;

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
		// 3*x^2 - y - x + 2*y
		mDiff = new Function<Vector>() {
			public Vector execute(Vector input) throws Exception {
				if (input.length() != 2)
					throw new IllegalArgumentException("this function need vector length equals 2");
				return new Vector(3 * Math.pow(input.get(0), 2) - input.get(1) - input.get(0) + 2 * input.get(1));
			}
		};
	}

	@Test
	public void testCalculate() throws Exception {
		Differential diff = new Differential(mFunction);
		Assert.assertEquals(mDiff.execute(new Vector(2, 3)).get(0, 0), diff.execute(new Vector(2, 3)).get(0, 0), Math.pow(10, -4));
		Assert.assertEquals(mDiff.execute(new Vector(-2, 7.2)).get(0, 0), diff.execute(new Vector(-2, 7.2)).get(0, 0), Math.pow(10, -4));
	}
}
