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
import java.util.List;
import poisondog.core.Mission;
/**
 * @author Adam Huang
 */
public class CalculateConstant implements Mission<Collection<List<Double>>> {

	@Override
	public Double execute(Collection<List<Double>> input) {
		double xSum = 0;
		double ySum = 0;
		double x2Sum = 0;
		double y2Sum = 0;
		double xySum = 0;
		for (List<Double> v : input) {
			xSum += v.get(0);
			ySum += v.get(1);
			x2Sum += Math.pow(v.get(0), 2);
			y2Sum += Math.pow(v.get(1), 2);
			xySum += v.get(0) * v.get(1);
		}
		double ssx = x2Sum - (Math.pow(xSum, 2)/ input.size());
		double ssy = y2Sum - (Math.pow(ySum, 2)/ input.size());
		double ssxy = xySum - (xSum * ySum / input.size());
		return ySum / input.size() - ssxy / ssx * xSum / input.size();
	}
}
