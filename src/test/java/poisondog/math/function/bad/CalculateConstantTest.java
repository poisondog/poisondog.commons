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

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 * @author Adam Huang
 */
public class CalculateConstantTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void TestSample1() throws Exception {
		List<List<Double>> set = new ArrayList<List<Double>>();
		set.add(createVector(1.0, 1.0));
		set.add(createVector(2.0, 1.0));
		set.add(createVector(3.0, 2.0));
		set.add(createVector(4.0, 2.0));
		set.add(createVector(5.0, 4.0));
		CalculateConstant mission = new CalculateConstant();
		Double result = mission.execute(set);
		Assert.assertEquals(-0.1, result, Math.pow(10, -6));
	}

	@Test
	public void TestSample2() throws Exception {
		List<List<Double>> set = new ArrayList<List<Double>>();
		set.add(createVector(4.0, 3.0));
		set.add(createVector(6.0, 5.5));
		set.add(createVector(10.0, 6.5));
		set.add(createVector(12.0, 9.0));
		CalculateConstant mission = new CalculateConstant();
		Double result = mission.execute(set);
		Assert.assertEquals(0.8, result, Math.pow(10, -6));
	}

	@Test
	public void TestSample3() throws Exception {
		List<List<Double>> set = new ArrayList<List<Double>>();
		set.add(createVector(1.0, 6.0));
		set.add(createVector(2.0, 5.0));
		set.add(createVector(3.0, 7.0));
		set.add(createVector(4.0, 10.0));
		CalculateConstant mission = new CalculateConstant();
		Double result = mission.execute(set);
		Assert.assertEquals(3.5, result, Math.pow(10, -6));
	}

	private List<Double> createVector(double x, double y) {
		List<Double> vector1 = new ArrayList<Double>();
		vector1.add(x);
		vector1.add(y);
		return vector1;
	}
}
