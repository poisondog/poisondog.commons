/*
 * Copyright (C) 2017 Adam Huang <poisondog@gmail.com>
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
 * @since 2017-05-08
 */
public class VarianceTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSimple() throws Exception {
		Variance var = new Variance();
		Assert.assertEquals(200.0/3, var.execute(new Vector(160.0, 170.0, 180.0)).get(), Math.pow(10, -6));
	}

	@Test
	public void testSimple2() throws Exception {
		Variance var = new Variance();
		Assert.assertEquals(0.25, var.execute(new Vector(0.0, 1.0)).get(), Math.pow(10, -6));
	}

}
