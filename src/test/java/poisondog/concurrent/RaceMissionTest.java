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
import org.mockito.Mockito;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2016-03-28
 */
public class RaceMissionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testEmpty() throws Exception {
		RaceMission race = new RaceMission();
		race.execute(0);
		Assert.assertTrue(true);
	}

	@Test
	public void testExecute() throws Exception {
		Mission m1 = Mockito.mock(Mission.class);
		Mission m2 = createDelayMission(10);
		Mission h1 = createDelayMission(30);
		Mission h2 = Mockito.mock(Mission.class);
		RaceMission race = new RaceMission();
		race.addMission(m1, null,h1);
		race.addMission(m2, null,h2);
		race.execute(0);
		Thread.sleep(100);
		Mockito.verify(m1).execute(null);
		Mockito.verify(m2).execute(null);
		Mockito.verify(h1).execute(null);
		Mockito.verify(h2, Mockito.never()).execute(null);
	}

	@Test
	public void testPriority() throws Exception {
		Mission m1 = Mockito.mock(Mission.class);
		Mission m2 = createDelayMission(10);
		Mission h1 = Mockito.mock(Mission.class);
		Mission h2 = Mockito.mock(Mission.class);
		RaceMission race = new RaceMission();
		race.addMission(1, m1, null,h1);
		race.addMission(2, m2, null,h2);
		race.execute(0);
		Thread.sleep(100);
		Mockito.verify(m1).execute(null);
		Mockito.verify(m2).execute(null);
		Mockito.verify(h1).execute(null);
		Mockito.verify(h2).execute(null);
	}

	private Mission createDelayMission(final long delay) {
		return Mockito.spy(new Mission() {
			public Void execute(Object none) {
				new SleepMission(delay);
				return null;
			}
		});
	}
}
