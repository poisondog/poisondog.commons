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

/**
 * @author Adam Huang
 * @since 2016-07-02
 */
public class Differential implements Function<Vector> {
	private Function<Vector> mFunction;
	private double mMin;

	/**
	 * Constructor
	 */
	public Differential(Function<Vector> function) {
		this(function, Math.pow(10, -10));
	}

	/**
	 * Constructor
	 */
	public Differential(Function<Vector> function, double min) {
		mFunction = function;
		mMin = min;
	}

	@Override
	public Vector execute(Vector input) throws Exception {
		double result = 0;
		for (int i = 0; i < input.length(); i++) {
			result += diff(input, i);
		}
		return new Vector(result);
	}

	private double diff(Vector input, int index) throws Exception {
		Vector temp = new Vector(input.length());
		for (int i = 0; i < input.length(); i++) {
			if (i == index)
				temp.set(i, input.get(i) + mMin);
			else
				temp.set(i, input.get(i));
		}
		double up = mFunction.execute(temp).get(0, 0) - mFunction.execute(input).get(0, 0);
		return up / mMin;
	}
}
