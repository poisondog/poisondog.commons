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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import poisondog.math.function.Normalization;
/**
 * @author Adam Huang
 */
public class NormalizationTest {

	private Normalization normalization;
	private double[] inputs;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		inputs=new double[]{3,5,1,6,8,3,9};
		normalization = new Normalization(inputs);
	}
	@Test
	public void testInit() throws Exception {
		Double[] ins = new Double[7];
		ins[0]=new Double(3);
		ins[1]=new Double(5);
		ins[2]=new Double(1);
		ins[3]=new Double(6);
		ins[4]=new Double(8);
		ins[5]=new Double(3);
		ins[6]=new Double(9);
		
		normalization=new Normalization(ins);
		for (double input : inputs) {
			assertEquals((input-1)/8,normalization.calculate(input),Math.pow(10, -2));
		}
	}

	@Test
	public void testCalculate() throws Exception {
		for (double input : inputs) {
			assertEquals((input-1)/8,normalization.calculate(input),Math.pow(10, -2));
		}
	}
	@Test
	public void testResult() throws Exception {
		double[] result = normalization.result();
		for (int i = 0; i < result.length; i++) {
			assertEquals((inputs[i]-1)/8,result[i],Math.pow(10, -2));
		}
	}
}
