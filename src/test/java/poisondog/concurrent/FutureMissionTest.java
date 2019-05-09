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
package poisondog.concurrent;

import java.util.concurrent.FutureTask;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.core.Mission;
import poisondog.log.Log;

/**
 * @author Adam Huang
 * @since 2017-09-04
 */
public class FutureMissionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testExecute() throws Exception {
		Mission<String> handler = new Mission<String>() {
			@Override
			public String execute(String input) {
				for(int i = 1; i <= 10; i++){
					Log.i("[" +Thread.currentThread().getId() + "]" + (i * 10) + "%");
					new SleepMission(10l);
				}
				return "complete";
			}
		};
		FutureMission mission = new FutureMission(handler);
		FutureTask<String> task = mission.execute("");
		Log.i("[" +Thread.currentThread().getId() + "] start");
		Log.i("[" +Thread.currentThread().getId() + "] result: " + task.get());
		Assert.assertEquals("complete", task.get());
	}
}
