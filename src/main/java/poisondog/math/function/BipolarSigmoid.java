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
package poisondog.math.function;

import poisondog.math.Function;
import poisondog.math.Matrix;

/**
 * @author Adam Huang
 */
public class BipolarSigmoid<T extends Matrix> implements Function<T> {
	private double lambda;

	/**
	 * Constructor
	 */
	public BipolarSigmoid() {
		lambda = 1;
	}

	public BipolarSigmoid(double lambda) {
		this.lambda = lambda;
	}

	@Override
	public Matrix execute(T input) {
		Matrix result = new Matrix(input.getRowSize(), input.getColumnSize());
		for (int i = 0; i < input.getRowSize(); i++) {
			for (int j = 0; j < input.getColumnSize(); j++) {
				result.set(i, j, calculate(input.get(i, j)));
			}
		}
		return result;
	}

	private double calculate(double ds) {
		return (2 / (1 + Math.pow(Math.E, -lambda * ds)))-1;
	}

}
