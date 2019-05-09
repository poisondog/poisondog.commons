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
import poisondog.math.Matrix;

/**
 * @author Adam Huang
 */
public class SigmoidTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCalculate() throws Exception {
		Vector input = new Vector(3.0, -2.2);
		Sigmoid function = new Sigmoid();
		Vector result = Vector.toVector(function.execute(input));
		Assert.assertEquals(0.9525741268224331, result.get(0), Math.pow(10, -3));
		Assert.assertEquals(0.09975048911968513, result.get(1), Math.pow(10, -3));

		Sigmoid function2 = new Sigmoid(5);
		result = Vector.toVector(function2.execute(input));
		Assert.assertEquals(0.999999694097773, result.get(0), Math.pow(10, -3));
		Assert.assertEquals(1.670142184809519E-5, result.get(1), Math.pow(10, -3));
	}

	@Test
	public void testInverse() throws Exception {
		Sigmoid sigmoid = new Sigmoid(.01);
		Vector input = new Vector(-300.0,300.0);
		Vector output = Vector.toVector(sigmoid.execute(input));
		Assert.assertEquals(input, sigmoid.inverse(output));
		System.out.println(sigmoid.execute(new Vector(-9.0, -10.0)));
	}
}
