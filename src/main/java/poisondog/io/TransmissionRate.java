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

import poisondog.log.Log;

/**
 * @author Adam Huang
 */
public class TransmissionRate implements StepListener, CancelListener {
	private Accumulation mAccumulation;
	private double mRate;
	private Duration mDuration;

	public TransmissionRate() {
		mAccumulation = new Accumulation();
		mDuration = new Duration();
	}

	@Override
	public void onStep(int length) {
		if (mAccumulation.getCount() <= 0)
			mDuration.reset();
		mAccumulation.onStep(length);
		mDuration.onStep(length);
		mRate = (double)mAccumulation.getCount() / mDuration.get();
		Log.v("current rate: " + mRate);
	}

	@Override
	public void onCancel() {
		reset();
	}

	public double getRate() {
		return mRate;
	}

	public void reset() {
		mAccumulation.reset();
		mRate = 0;
	}

}
