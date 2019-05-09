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
/**
 * @author Adam Huang
 */
public class CalculateStandardDeviation implements Mission<Collection<Double>> {

	@Override
	public Double execute(Collection<Double> inputs) {
		CalculateVariance task = new CalculateVariance();
		return Math.sqrt(task.execute(inputs));
	}
}
