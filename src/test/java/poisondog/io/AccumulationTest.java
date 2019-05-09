/*
 * Copyright (C) 2014 Adam Huang <poisondog@gmail.com>
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
package poisondog.io;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 * @since 2014-02-26
 */
public class AccumulationTest {
	private Accumulation mAccumulation;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mAccumulation = new Accumulation();
	}

	@Test
	public void testOnStep() throws Exception {
		mAccumulation.onStep(333);
		Assert.assertEquals(333, mAccumulation.getCount());
		mAccumulation.onStep(331);
		Assert.assertEquals(664, mAccumulation.getCount());
	}

	@Test
	public void testReset() throws Exception {
		mAccumulation.onStep(333);
		Assert.assertEquals(333, mAccumulation.getCount());
		mAccumulation.reset();
		Assert.assertEquals(0, mAccumulation.getCount());
	}

	@Test
	public void testOnCancel() throws Exception {
		mAccumulation.onStep(333);
		Assert.assertEquals(333, mAccumulation.getCount());
		mAccumulation.onCancel();
		Assert.assertEquals(0, mAccumulation.getCount());
	}
}
