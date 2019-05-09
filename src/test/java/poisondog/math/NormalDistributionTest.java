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
package poisondog.math;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 * @since 2017-05-14
 */
public class NormalDistributionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testContent() throws Exception {
		NormalDistribution task = new NormalDistribution(170, 16);
		Assert.assertEquals(0.004382075123392136, task.execute(new Value(180)).get(), Math.pow(10, -6));
	}

	@Test
	public void testCase2() throws Exception {
		NormalDistribution task = new NormalDistribution();
		Assert.assertEquals(0.3989422804014327, task.execute(new Value(0)).get(), Math.pow(10, -6));
	}

	@Test
	public void testCase3() throws Exception {
		NormalDistribution task = new NormalDistribution(10, 1);
		Assert.assertEquals(0.3989422804014327, task.execute(new Value(10)).get(), Math.pow(10, -6));
	}

}
