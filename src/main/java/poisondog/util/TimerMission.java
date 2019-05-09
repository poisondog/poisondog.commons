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
package poisondog.util;

import java.util.TimerTask;
import poisondog.core.Mission;
import java.util.Timer;
import java.util.Date;
import poisondog.util.TimeUtils;

/**
 * @author Adam Huang
 * @since 2018-08-01
 */
public class TimerMission<T> implements Mission<T> {
	private Timer mTimer;
	private Mission<T> mMission;
	private String mStart;
	private long mDelay = -1;
	private long mPeriod = -1;

	/**
	 * Constructor
	 */
	public TimerMission(Mission<T> mission, String start, long period) {
		mMission = mission;
		mStart = start;
		mPeriod = period;
	}

	/**
	 * Constructor
	 */
	public TimerMission(Mission<T> mission, String start) {
		mMission = mission;
		mStart = start;
	}

	/**
	 * Constructor
	 */
	public TimerMission(Mission<T> mission, long delay, long period) {
		mMission = mission;
		mDelay = delay;
		mPeriod = period;
	}

	/**
	 * Constructor
	 */
	public TimerMission(Mission<T> mission, long delay) {
		mMission = mission;
		mDelay = delay;
	}

	@Override
	public Void execute(final T input) {
		mTimer = new Timer();
		if (mStart != null && mPeriod < 0)
			mTimer.schedule(new MissionTimerTask(input), new Date(TimeUtils.toLong(mStart)));
		if (mStart != null && mPeriod > 0)
			mTimer.scheduleAtFixedRate(new MissionTimerTask(input), new Date(TimeUtils.toLong(mStart)), mPeriod);
		if (mDelay >= 0 && mPeriod < 0)
			mTimer.schedule(new MissionTimerTask(input), mDelay);
		if (mDelay >= 0 && mPeriod > 0)
			mTimer.scheduleAtFixedRate(new MissionTimerTask(input), mDelay, mPeriod);
		return null;
	}

	class MissionTimerTask extends TimerTask {
		private T mInput;
		/**
		 * Constructor
		 */
		public MissionTimerTask(T input) {
			mInput = input;
		}
		@Override
		public void run() {
			Object result = null;
			try {
				result = mMission.execute(mInput);
			} catch(Exception e) {
				e.printStackTrace();
			}
			if (result != null)
				throw new IllegalArgumentException("Mission in Timer need return null");
		}
	}

	public void cancel() {
		mTimer.cancel();
	}

}
