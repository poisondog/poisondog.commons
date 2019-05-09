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

import static org.junit.Assert.*;
import org.junit.Test;
import poisondog.math.function.RandomVariable;
/**
 * @author Adam Huang
 */
public class RandomVariableTest {
	@Test
	public void testCalculateExpectedValue() throws Exception {
		double[] inputs = new double[]{1,2,3,4,50,6,7,8,9,0};
		assertEquals(9,RandomVariable.calculateExpectedValue(inputs),Math.pow(10, -10));
	}

	@Test
	public void testCalculateVariance() throws Exception {
		double[] inputs = new double[]{1,2,3,4,5,6,7,8,9,0};
		assertEquals(8.25,RandomVariable.calculateVariance(inputs),Math.pow(10, -10));
	}
	
	@Test
	public void testCalculateStandardDeviation() throws Exception {
		double[] inputs = new double[]{1,2,3,4,5,6,7,8,9,0};
		assertEquals(Math.sqrt(8.25),RandomVariable.calculateStandardDeviation(inputs),Math.pow(10, -10));
		
	}
}
