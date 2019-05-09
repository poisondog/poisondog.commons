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
package poisondog.concurrent;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 * @since 2016-03-23
 */
public class SleepMissionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSleep() throws Exception {
		long start = System.currentTimeMillis();
		SleepMission task = new SleepMission();
		task.execute(10l);
		Assert.assertTrue((System.currentTimeMillis() - start) >= 10l);
	}

	@Test
	public void testSleepWithConstructor() throws Exception {
		long start = System.currentTimeMillis();
		new SleepMission(10l);
		Assert.assertTrue((System.currentTimeMillis() - start) >= 10l);
	}
}
