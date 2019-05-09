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
public class RandomVariable {
	public static double calculateExpectedValue(double[] inputs) {
		double result=0;
		for (double d : inputs) {
			result+=d;
		}
		return result/inputs.length;
	}
	
	public static double calculateVariance(double[] inputs) {
		double expectedValue = calculateExpectedValue(inputs);
		double[] temp = new double[inputs.length];
		for (int i = 0; i < inputs.length; i++) {
			temp[i]=Math.pow(inputs[i]-expectedValue, 2);
		}
		return calculateExpectedValue(temp);
	}
	
	public static double calculateStandardDeviation(double[] inputs) {
		return Math.sqrt(calculateVariance(inputs));
	}
}
