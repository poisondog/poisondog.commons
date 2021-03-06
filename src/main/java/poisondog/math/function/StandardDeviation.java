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
 * @since 2017-07-23
 */
public class StandardDeviation implements Function<Vector> {
	private Variance mVariance;

	/**
	 * Constructor
	 */
	public StandardDeviation() {
		mVariance = new Variance();
	}

	@Override
	public Value execute(Vector input) {
		return new Value(Math.sqrt(mVariance.execute(input).get()));
	}
}
