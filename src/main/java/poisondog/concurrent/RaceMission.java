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

import java.util.ArrayList;
import java.util.List;
import poisondog.core.Mission;
import poisondog.core.Task;

/**
 * @author Adam Huang
 * @since 2016-02-27
 */
public class RaceMission implements Mission<Integer> {
	private int mCurrentPriority;
	private List<Task> mMissions;
	private ThreadPool mPool;

	/**
	 * Constructor
	 */
	public RaceMission() {
		mMissions = new ArrayList<Task>();
		mPool = new ThreadPool();
	}

	public void addMission(Mission mission, Object input, Mission handler) {
		addMission(1, mission, input, handler);
	}

	public void addMission(int priority, Mission mission, Object input, Mission handler) {
		mMissions.add(new Task(mission, input, new FlagHandler(priority, handler)));
	}

	@Override
	public Void execute(Integer priority) {
		mCurrentPriority = priority;
		for (Task task : mMissions) {
			mPool.execute(task);
//			new Thread(task).start();
		}
		return null;
	}

	private class FlagHandler implements Mission {
		private Mission mHandler;
		private int mPriority;

		/**
		 * Constructor
		 */
		public FlagHandler(int priority, Mission handler) {
			mPriority = priority;
			mHandler = handler;
		}

		@Override
		public Void execute(Object input) throws Exception {
			synchronized(RaceMission.this) {
				if (mPriority > mCurrentPriority) {
					mHandler.execute(input);
					mCurrentPriority = mPriority;
				}
//				mPool.close();
			}
			return null;
		}
	}
}
