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

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.math.Vector;

/**
 * @author Adam Huang
 * @since 2017-07-23
 */
public class StandardDeviationTest {
	private StandardDeviation mTask;

	@Before
	public void setUp() throws Exception {
		mTask = new StandardDeviation();
	}

	@Test
	public void testSimple() throws Exception {
		ArrayList<Double> input = new ArrayList<Double>();
		input.add(5.0);
		input.add(6.0);
		input.add(8.0);
		input.add(9.0);
		Assert.assertEquals(1.5811388300841898, mTask.execute(new Vector(input)).get(), Math.pow(10, -6));
	}

	@Test
	public void testSimple2() throws Exception {
		Assert.assertEquals(Math.sqrt(0.25), mTask.execute(new Vector(0.0, 1.0)).get(), Math.pow(10, -6));
	}

}
