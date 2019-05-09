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
package poisondog.math.function;

import poisondog.core.Mission;
import poisondog.log.Log;
import poisondog.math.Matrix;

/**
 * @author Adam Huang
 * @since 2017-06-09
 */
public class FeatureScaling implements Mission<Matrix> {
	private double mMax;
	private double mMin;

	/**
	 * Constructor
	 */
	public FeatureScaling() {
		mMax = -Double.MAX_VALUE;
		mMin =  Double.MAX_VALUE;
	}

	public void setMinMax(Matrix input) {
		for (int i = 0; i < input.getRowSize(); i++) {
			for (int j = 0; j < input.getColumnSize(); j++) {
				mMax = Math.max(mMax, input.get(i, j));
				mMin = Math.min(mMin, input.get(i, j));
			}
		}
	}

	@Override
	public Matrix execute(Matrix input) {
		setMinMax(input);
		Matrix result = new Matrix(input.getRowSize(), input.getColumnSize());
		for (int i = 0; i < input.getRowSize(); i++) {
			for (int j = 0; j < input.getColumnSize(); j++) {
				result.set(i, j, (input.get(i, j) - mMin) / (mMax - mMin));
			}
		}
		return result;
	}

	public Matrix inverse(Matrix input) {
		Matrix result = new Matrix(input.getRowSize(), input.getColumnSize());
		for (int i = 0; i < input.getRowSize(); i++) {
			for (int j = 0; j < input.getColumnSize(); j++) {
				result.set(i, j, input.get(i, j) * (mMax - mMin) + mMin);
			}
		}
		return result;
	}

}
