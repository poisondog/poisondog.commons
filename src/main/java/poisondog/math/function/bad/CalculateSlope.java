/*
 * Copyright (C) 2015 Adam Huang <poisondog@gmail.com>
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

import java.util.Collection;
import poisondog.core.Mission;
import poisondog.util.Pair;

/**
 * @author Adam Huang
 */
public class CalculateSlope implements Mission<Collection<Pair<Double, Double>>> {

	@Override
	public Double execute(Collection<Pair<Double, Double>> input) {
		double xSum = 0;
		double ySum = 0;
		double x2Sum = 0;
		double y2Sum = 0;
		double xySum = 0;
		for (Pair<Double, Double> v : input) {
			xSum += v.getValue1();
			ySum += v.getValue2();
			x2Sum += Math.pow(v.getValue1(), 2);
			y2Sum += Math.pow(v.getValue2(), 2);
			xySum += v.getValue1() * v.getValue2();
		}
		double ssx = x2Sum - (Math.pow(xSum, 2)/ input.size());
		double ssy = y2Sum - (Math.pow(ySum, 2)/ input.size());
		double ssxy = xySum - (xSum * ySum / input.size());
		return ssxy / ssx;
	}
}
