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
package poisondog.math.function;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.math.Vector;

/**
 * @author Adam Huang
 * @since 2018-08-20
 */
public class BipolarSigmoidTest {
	private Vector input;

	@Before
	public void setUp() throws Exception {
		input = new Vector(3.0, -2.2);
	}

	@Test
	public void testDefault() throws Exception {
		BipolarSigmoid function = new BipolarSigmoid();
		Vector result = Vector.toVector(function.execute(input));
		Assert.assertEquals(0.9051482536448663, result.get(0), Math.pow(10, -3));
		Assert.assertEquals(-0.8004990217606297, result.get(1), Math.pow(10, -3));
	}

	@Test
	public void testWithLambda() throws Exception {
		BipolarSigmoid function = new BipolarSigmoid(5);
		Vector result = Vector.toVector(function.execute(input));
		Assert.assertEquals(0.999999388195546, result.get(0), Math.pow(10, -3));
		Assert.assertEquals(-0.9999665971563038, result.get(1), Math.pow(10, -3));
	}

}
