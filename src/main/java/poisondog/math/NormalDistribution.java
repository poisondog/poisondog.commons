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
package poisondog.math;

/**
 * @author Adam Huang
 * @since 2017-05-14
 */
public class NormalDistribution implements Function<Value> {
	private double mMean;
	private double mVariance;

	/**
	 * Constructor
	 */
	public NormalDistribution(double mean, double variance) {
		mMean = mean;
		mVariance = variance;
	}

	/**
	 * Constructor
	 */
	public NormalDistribution() {
		this(0, 1);
	}

	@Override
	public Value execute(Value input) {
		return new Value(Math.pow(Math.E, -Math.pow(input.get() - mMean, 2) / (2 * mVariance)) / Math.sqrt(mVariance * 2 * Math.PI));
	}
}
