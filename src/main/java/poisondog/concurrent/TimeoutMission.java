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

import poisondog.core.Mission;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Adam Huang
 * @since 2017-09-11
 */
public class TimeoutMission implements Mission<Runnable> {
	private long mTimeout;

	/**
	 * Constructor
	 */
	public TimeoutMission() {
		this(5000l);
	}

	/**
	 * Constructor
	 */
	public TimeoutMission(long timeout) {
		mTimeout = timeout;
	}

	@Override
	public Void execute(final Runnable mission) {
		final Thread task = new Thread(mission);

		final long start = System.currentTimeMillis();
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!task.isInterrupted()) {
					if (System.currentTimeMillis() - start > mTimeout) {
						task.interrupt();
					}
					try {
						Thread.sleep(100l);
					} catch(Exception e) {
					}
				}
			}
		});
		t.start();

		task.start();

		return null;
	}
}
