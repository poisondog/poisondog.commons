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

import poisondog.log.Logger;
import poisondog.log.NoLogger;
import poisondog.log.LogLevel;

/**
 * @author Adam Huang
 * @since 2016-05-01
 */
public class Duration implements StepListener, CancelListener {
	private Logger mLogger;
	private long mStart;
	private long mDuration;

	/**
	 * Constructor
	 */
	public Duration() {
		mLogger = new NoLogger();
		reset();
	}

	@Override
	public void onStep(int length) {
		mDuration = System.currentTimeMillis() - mStart;
		mLogger.log(LogLevel.VERBOSE, "onStep duration : " + mDuration);
	}

	@Override
	public void onCancel() {
		reset();
	}

	public long get() {
		return Math.max(1, mDuration);
	}

	public void reset() {
		mStart = System.currentTimeMillis();
	}

	public void setLogger(Logger logger) {
		mLogger = logger;
	}

	public void setStart(long time) {
		mStart = time;
	}

	public long getStart() {
		return mStart;
	}
}
