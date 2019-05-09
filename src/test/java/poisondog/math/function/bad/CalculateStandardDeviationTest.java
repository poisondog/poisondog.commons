/*
 * Copyright (C)  Adam Huang <poisondog@gmail.com>
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
import java.util.List;
import java.util.LinkedList;

/**
 * @author Adam Huang
 */
public class CalculateStandardDeviationTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSimple() throws Exception {
		List<Double> inputs = new LinkedList<Double>();
		inputs.add(1.0);
		inputs.add(2.0);
		inputs.add(3.0);
		inputs.add(4.0);
		inputs.add(5.0);
		inputs.add(6.0);
		inputs.add(7.0);
		inputs.add(8.0);
		inputs.add(9.0);
		inputs.add(0.0);

		CalculateStandardDeviation task = new CalculateStandardDeviation();
		Assert.assertEquals(Math.sqrt(8.25), task.execute(inputs), Math.pow(10, -10));
	}
}
