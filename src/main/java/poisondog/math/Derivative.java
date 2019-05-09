/*
 * Copyright (C) 2018 Adam Huang <poisondog@gmail.com>
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
 * @since 2018-09-10
 */
public class Derivative<T extends Matrix> implements Function<T> {
	private Function<T> mFunction;
	private double mMin;

	/**
	 * Constructor
	 */
	public Derivative(Function<T> function) {
		this(function, Math.pow(10, -10));
	}

	/**
	 * Constructor
	 */
	public Derivative(Function<T> function, double min) {
		mFunction = function;
		mMin = min;
	}

	@Override
	public Matrix execute(T input) throws Exception {
		Matrix result = input.zeros();
		for (int i = 0; i < input.getRowSize(); i++) {
			for (int j = 0; j < input.getColumnSize(); j++) {
				double temp = input.get(i, j);
				input.set(i, j, temp + mMin);
				Matrix fx1 = mFunction.execute(input);
				input.set(i, j, temp - mMin);
				Matrix fx2 = mFunction.execute(input);
				input.set(i, j, temp);
				Matrix delta = fx1.plus(fx2.scalar(-1)).scalar(1.0 / (2 * mMin));
				assert delta.getRowSize() == 1 && delta.getColumnSize() == 1 : "function result need Value";
				result.set(i, j, delta.get(0, 0));
			}
		}
		return result;
	}

}
