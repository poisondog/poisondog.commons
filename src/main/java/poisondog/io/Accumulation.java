/*
 * Copyright (C) 2014 Adam Huang
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
 */
public class Accumulation implements StepListener, CancelListener {
	private Logger mLogger;
	private long mCount;

	public Accumulation() {
		mLogger = new NoLogger();
	}

	@Override
	public void onStep(int length) {
		mCount += length;
		mLogger.log(LogLevel.VERBOSE, "onStep complete: " + mCount);
	}

	@Override
	public void onCancel() {
		reset();
	}

	public long getCount() {
		return mCount;
	}

	public void reset() {
		mCount = 0;
	}

	public void setLogger(Logger logger) {
		mLogger = logger;
	}
}
