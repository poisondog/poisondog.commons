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
package poisondog.io;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 * @since 2014-02-26
 */
public class TransmissionRateTest {
	private TransmissionRate mRate;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mRate = new TransmissionRate();
	}

	@Test
	public void testOnStep() throws Exception {
		mRate.onStep(100);
		Assert.assertEquals(100, mRate.getRate(), Math.pow(10, -6));
		Thread.sleep(9);
		mRate.onStep(10);
		Thread.sleep(10);
//		Assert.assertEquals(110.0/9.0, mRate.getRate(), Math.pow(10, -4));
	}

	@Test
	public void testReset() throws Exception {
		mRate.onStep(100);
		Assert.assertEquals(100, mRate.getRate(), Math.pow(10, -6));
		mRate.reset();
		Assert.assertEquals(0, mRate.getRate(), Math.pow(10, -6));
	}

	@Test
	public void testOnCancel() throws Exception {
		mRate.onStep(100);
		Assert.assertEquals(100, mRate.getRate(), Math.pow(10, -6));
		mRate.onCancel();
		Assert.assertEquals(0, mRate.getRate(), Math.pow(10, -6));
	}
}
