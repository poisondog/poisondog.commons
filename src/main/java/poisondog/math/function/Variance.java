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

import poisondog.math.Function;
import poisondog.math.Value;
import poisondog.math.Vector;

/**
 * @author Adam Huang
 * @since 2017-05-08
 */
public class Variance implements Function<Vector> {
	@Override
	public Value execute(Vector input) {
		Average average = new Average();
		double mean = average.execute(input).get();
		double sum = .0;
		for (int i = 0; i < input.length(); i++) {
			sum += Math.pow(input.get(i) - mean, 2);
		}
		return new Value(sum / input.length());
	}
}
