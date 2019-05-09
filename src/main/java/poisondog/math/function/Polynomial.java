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
import poisondog.math.Matrix;
import poisondog.math.Vector;

/**
 * @author Adam Huang
 * @since 2016-02-26
 */
public class Polynomial implements Function<Vector> {
	private Vector mArgument;

	public Polynomial(Vector argument) {
		mArgument = argument;
	}

	@Override
	public Matrix execute(Vector input) {
		return mArgument.transpose().dot(createParameter(input));
	}

	private Vector createParameter(Vector input) {
		if (mArgument.length() != input.length())
			throw new IllegalArgumentException("input vector length not match polynomial argument.");
		Vector result = new Vector(mArgument.length());
		for (int i = 0; i < mArgument.length(); i++) {
			result.set(i, Math.pow(input.get(i), i));
		}
		return result;
	}
}
