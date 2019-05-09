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
import poisondog.math.Matrix;

/**
 * @author Adam Huang
 * @since 2017-06-09
 */
public class FeatureScalingTest {
	private FeatureScaling task;
	private Matrix mInput;
	private Matrix mOutput;

	@Before
	public void setUp() throws Exception {
		task = new FeatureScaling();

		mInput = new Matrix(2, 3);
		mInput.set(0, 0, 3);
		mInput.set(1, 1, 2);
		mInput.set(1, 2, -1);

		mOutput = new Matrix(2, 3);
		mOutput.set(0, 0, 4.0/4);
		mOutput.set(0, 1, 1.0/4);
		mOutput.set(0, 2, 1.0/4);
		mOutput.set(1, 0, 1.0/4);
		mOutput.set(1, 1, 3.0/4);
		mOutput.set(1, 2, 0/4);
	}

	@Test
	public void testSample() throws Exception {
		Assert.assertEquals(mOutput, task.execute(mInput));
	}

	@Test
	public void testInverse() throws Exception {
		task.setMinMax(mInput);
		Assert.assertEquals(mInput, task.inverse(mOutput));
	}
}
