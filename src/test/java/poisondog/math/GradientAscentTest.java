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
public class GradientAscentTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testExecute() throws Exception {
		Function<Vector> function = new Function<Vector>() {
			public Vector execute(Vector input) throws Exception {
				if (input.length() != 1)
					throw new IllegalArgumentException("this function need vector length equals 1");
				return new Vector(-(Math.pow(input.get(0), 2) + 3 * input.get(0) + 5));
			}
		};
		GradientAscent task = new GradientAscent(function);
		Assert.assertEquals(0.7000050560272939, task.execute(new Vector(4.0)).get(0), Math.pow(10, -3));
		Assert.assertEquals(-4.899951616389444, task.execute(new Vector(-10.0)).get(0), Math.pow(10, -3));
	}
}
