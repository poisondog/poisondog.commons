/*
 * Copyright (C)  Adam Huang <poisondog@gmail.com>
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
import java.util.List;
import java.util.ArrayList;
/**
 * @author Adam Huang
 */
public class CalculateVariance implements Mission<Collection<Double>> {

	@Override
	public Double execute(Collection<Double> inputs) {
		CalculateExpectedValue task = new CalculateExpectedValue();
		double expectedValue = task.execute(inputs);
		List<Double> temp = new ArrayList<Double>(inputs.size());
		for (Double input : inputs) {
			temp.add(Math.pow(input - expectedValue, 2));
		}
		return task.execute(temp);
	}
}
