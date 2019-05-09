/*
 * Copyright (C) 2014 Adam Huang <poisondog@gmail.com>
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author poisondog <poisondog@gmail.com>
 */
public class VectorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSingleValue() throws Exception {
		Vector v = new Vector(3.0);
		Assert.assertEquals(3, v.get(0), Math.pow(10, -6));
	}

	@Test
	public void testValue1() throws Exception {
		Vector v = new Vector(3);
		v.set(0, 9);
		Assert.assertEquals(9, v.get(0), Math.pow(10, -6));
		Assert.assertEquals(0, v.get(1), Math.pow(10, -6));
		Assert.assertEquals(0, v.get(2), Math.pow(10, -6));
	}
	
	@Test
	public void testValue2() throws Exception {
		double[] value = {3, 2, 1, 0};
		Vector v = new Vector(value);
		Assert.assertEquals(3, v.get(0), Math.pow(10, -6));
		Assert.assertEquals(2, v.get(1), Math.pow(10, -6));
		Assert.assertEquals(1, v.get(2), Math.pow(10, -6));
		Assert.assertEquals(0, v.get(3), Math.pow(10, -6));
	}

	@Test
	public void testConstructor() throws Exception {
		List<Double> input = new ArrayList<Double>();
		input.add(3.0);
		input.add(2.3);
		input.add(1.9);
		Vector v = new Vector(input);
		Assert.assertEquals(3, v.get(0), Math.pow(10, -6));
		Assert.assertEquals(2.3, v.get(1), Math.pow(10, -6));
		Assert.assertEquals(1.9, v.get(2), Math.pow(10, -6));
	}

	@Test
	public void testMatrixToVector() throws Exception {
		Matrix matrix = new Matrix(3, 1);
		matrix.set(0, 0, 3);
		matrix.set(1, 0, 2);
		matrix.set(2, 0, 1);
		Vector result = Vector.toVector(matrix);
		Assert.assertEquals(new Vector(new double[]{3, 2, 1}), result);
	}
}
