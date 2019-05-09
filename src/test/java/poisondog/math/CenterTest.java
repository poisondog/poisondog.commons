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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 * @since 2016-05-24
 */
public class CenterTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCase1() throws Exception {
		Center task = new Center();
		Vector result = task.execute(new Vector(new double[]{2, 1}), new Vector(new double[]{-3, 4}), new Vector(new double[]{8, 9}));
		Assert.assertEquals(new Vector(new double[]{7.0/3, 14.0/3}), result);
	}

	@Test
	public void testCase2() throws Exception {
		Center task = new Center();
		Vector result = task.execute(new Vector(new double[]{1, -5, -7}), new Vector(new double[]{-3, 4, 9}));
		Assert.assertEquals(new Vector(new double[]{-1, -0.5, 1}), result);
	}
}
