/*
 * Copyright (C) 2015 Adam Huang <poisondog@gmail.com>
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

/**
 * @author Adam Huang
 */
public class Normalization {
	private double max;
	private double min;
	private double[] inputs;

	/**
	 * 建構子。
	 */
	public Normalization(double[] inputs) {
		findMinMax(inputs);
	}
	/**
	 * 建構子。
	 * @param doubles
	 */
	public Normalization(Double[] doubles) {
		double[] inputs = new double[doubles.length];
		for (int i = 0; i < inputs.length; i++) {
			inputs[i]=doubles[i];
		}
		findMinMax(inputs);		
	}


	private void findMinMax(double[] inputs) {
		this.inputs = inputs;
		max = Double.MIN_VALUE;
		min = Double.MAX_VALUE;
		for (double d : inputs) {
			max = Math.max(max, d);
			min = Math.min(min, d);
		}
	}
	
	public double calculate(double input) {
		return (input-min)/(max-min);
	}
	/**
	 * @return
	 */
	public double[] result() {
		double[] result = new double[inputs.length];
		for (int i = 0; i < inputs.length; i++) {
			result[i]=calculate(inputs[i]);
		}
		return result;
	}
}
