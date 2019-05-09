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
import poisondog.math.Value;

/**
 * @author Adam Huang
 * @since 2018-08-08
 */
public class ProbabilityDensityFunctionTest {
	private ProbabilityDensityFunction mFunction;

	@Before
	public void setUp() throws Exception {
		mFunction = new ProbabilityDensityFunction();
	}

	@Test
	public void testApp() throws Exception {
		Assert.assertEquals(new Value(1), mFunction.execute(new Value(0)));
		Assert.assertEquals(new Value(0.36787944117144233), mFunction.execute(new Value(1)));
	}
}
