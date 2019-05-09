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
package poisondog.math.function;

import poisondog.math.Function;
import poisondog.math.Value;
import poisondog.math.Vector;

/**
 * @author Adam Huang
 * @since 2016-07-10
 */
public class Average implements Function<Vector> {

	@Override
	public Value execute(Vector input) {
		double result = 0;
		for (int i = 0; i < input.length(); i++) {
			result += input.get(i);
		}
		return new Value(result / input.length());
	}
}
