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
package poisondog.math;

import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2016-07-10
 */
public class FeatureScaling implements Mission<Double> {
	private Vector mAll;
	private double mMax = -Double.MAX_VALUE;
	private double mMin = Double.MAX_VALUE;

	/**
	 * Constructor
	 */
	public FeatureScaling(double... input) {
		mAll = new Vector(input);
		for (int i = 0; i < mAll.length(); i++) {
			if (mAll.get(i) > mMax)
				mMax = mAll.get(i);
			if (mAll.get(i) < mMin)
				mMin = mAll.get(i);
		}
	}

	@Override
	public Double execute(Double input) {
		return (input - mMin) / (mMax - mMin);
	}
}
