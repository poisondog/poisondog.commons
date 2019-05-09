/*
 * Copyright (C) 2019 Adam Huang <poisondog@gmail.com>
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
 * @since 2019-02-20
 */
public class CalculateAngle implements Mission<Double[]> {
	private double mOriginX;
	private double mOriginY;

	/**
	 * Constructor
	 */
	public CalculateAngle(double originX, double originY) {
		mOriginX = originX;
		mOriginY = originY;
	}

	@Override
	public Double execute(Double... input) {
		if (input.length != 2)
			throw new IllegalArgumentException("input length need only 2");
		double x = input[0];
		double y = input[1];
		double theta = Math.atan((y - mOriginY) / (x - mOriginX));
		if (x - mOriginX > 0 && y - mOriginY > 0) {
			theta = theta;
		} else if (x - mOriginX < 0 && y - mOriginY > 0) {
			theta = Math.PI + theta;
		} else if (x - mOriginX < 0 && y - mOriginY < 0) {
			theta = Math.PI + theta;
		} else if (x - mOriginX > 0 && y - mOriginY < 0) {
			theta = 2 * Math.PI + theta;
		}
		return theta;
	}
}
