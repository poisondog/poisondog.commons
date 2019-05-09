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
package poisondog.math.function;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.math.Vector;
/**
 * @author Adam Huang
 * @since 2016-02-26
 */
public class PolynomialTest {
	private Polynomial mPolynomial;

	@Before
	public void setUp() throws Exception {
		mPolynomial = new Polynomial(new Vector(1.0, 2.0));
	}

	@Test
	public void testExecute() throws Exception {
		Assert.assertEquals(1.0, mPolynomial.execute(new Vector(1.0, 0.0)).get(0, 0), Math.pow(10, -6));
		Assert.assertEquals(5.0, mPolynomial.execute(new Vector(0.0, 2.0)).get(0, 0), Math.pow(10, -6));
	}

	@Test
	public void testQuadraticEquation() throws Exception {
		mPolynomial = new Polynomial(new Vector(1.0, 2.0, 3.0));
		Assert.assertEquals(13.0, mPolynomial.execute(new Vector(1.0, 0.0, 2.0)).get(0, 0), Math.pow(10, -6));
		Assert.assertEquals(15.0, mPolynomial.execute(new Vector(0.0, 1.0, 2.0)).get(0, 0), Math.pow(10, -6));
	}
}
