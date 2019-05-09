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
 * @since 2016-05-01
 */
public class DurationTest {
	private Duration mDuration;

	@Before
	public void setUp() throws Exception {
		mDuration = new Duration();
	}

	@Test
	public void testOnStep() throws Exception {
		long time = System.currentTimeMillis();
		mDuration.setStart(time - 10);
		mDuration.onStep(1024);
		Assert.assertEquals(10, mDuration.get());
	}

	@Test
	public void testSetStart() throws Exception {
		long time = System.currentTimeMillis();
		mDuration.setStart(time - 10);
		Assert.assertEquals(time - 10, mDuration.getStart());
	}

	@Test
	public void testStartWhenZero() throws Exception {
		long time = System.currentTimeMillis();
		mDuration.setStart(time);
		mDuration.onStep(1024);
		Assert.assertEquals(1, mDuration.get());
	}
}
