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
import poisondog.util.Pair;

/**
 * @author Adam Huang
 */
public class CalculateSlopeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void TestSample1() throws Exception {
		List<Pair<Double, Double>> set = new ArrayList<Pair<Double, Double>>();
		set.add(createPair(1.0, 1.0));
		set.add(createPair(2.0, 1.0));
		set.add(createPair(3.0, 2.0));
		set.add(createPair(4.0, 2.0));
		set.add(createPair(5.0, 4.0));
		CalculateSlope mission = new CalculateSlope();
		Double result = mission.execute(set);
		Assert.assertEquals(0.7, result, Math.pow(10, -6));
	}

	@Test
	public void TestSample2() throws Exception {
		List<Pair<Double, Double>> set = new ArrayList<Pair<Double, Double>>();
		set.add(createPair(4.0, 3.0));
		set.add(createPair(6.0, 5.5));
		set.add(createPair(10.0, 6.5));
		set.add(createPair(12.0, 9.0));
		CalculateSlope mission = new CalculateSlope();
		Double result = mission.execute(set);
		Assert.assertEquals(0.65, result, Math.pow(10, -6));
	}

	@Test
	public void TestSample3() throws Exception {
		List<Pair<Double, Double>> set = new ArrayList<Pair<Double, Double>>();
		set.add(createPair(1.0, 6.0));
		set.add(createPair(2.0, 5.0));
		set.add(createPair(3.0, 7.0));
		set.add(createPair(4.0, 10.0));
		CalculateSlope mission = new CalculateSlope();
		Double result = mission.execute(set);
		Assert.assertEquals(1.4, result, Math.pow(10, -6));
	}

	@Test
	public void TestSample4() throws Exception {
		List<Pair<Double, Double>> set = new ArrayList<Pair<Double, Double>>();
		set.add(createPair(1.0, 6.0));
		set.add(createPair(2.0, 5.0));
		set.add(createPair(3.0, 4.0));
		set.add(createPair(4.0, 3.0));
		CalculateSlope mission = new CalculateSlope();
		Double result = mission.execute(set);
		Assert.assertEquals(-1, result, Math.pow(10, -6));
	}

	private Pair<Double, Double> createPair(double x, double y) {
		return new Pair<Double, Double>(x, y);
	}

}
