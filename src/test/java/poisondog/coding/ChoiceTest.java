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
package poisondog.coding;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 */
public class ChoiceTest {
	private Choice<Double> mChoice;

	@Before
	public void setUp() throws Exception {
		mChoice = new Choice<Double>();
		mChoice.add(3.0);
		mChoice.add(4.0);
		mChoice.add(5.0);
		mChoice.add(6.0);
	}

	@Test
	public void testRandom() throws Exception {
		Assert.assertEquals(3.0, mChoice.get(), Math.pow(10, -6));
		mChoice.random();
		Assert.assertTrue(mChoice.contains(mChoice.get()));
	}

	@Test
	public void testSetIndex() throws Exception {
		mChoice.setIndex(0);
		Assert.assertEquals(3.0, mChoice.get(), Math.pow(10, -6));
		mChoice.setIndex(2);
		Assert.assertEquals(5.0, mChoice.get(), Math.pow(10, -6));
	}

	@Test
	public void testIndex() throws Exception {
		Assert.assertEquals(-1, mChoice.indexOf(2.0));
		Assert.assertEquals(0, mChoice.indexOf(3.0));
		Assert.assertEquals(1, mChoice.indexOf(4.0));
		Assert.assertEquals(2, mChoice.indexOf(5.0));
		Assert.assertEquals(3, mChoice.indexOf(6.0));
		Assert.assertEquals(-1, mChoice.indexOf(7.0));
	}

	@Test
	public void testEquals() throws Exception {
		Choice<Double> another = new Choice<Double>();
		another.add(3.0);
		another.add(4.0);
		another.add(5.0);
		another.add(6.0);
		another.setIndex(1);
		mChoice.setIndex(1);
		Assert.assertTrue(mChoice.equals(another));
	}

	@Test
	public void testNotEqualsNull() throws Exception {
		Assert.assertFalse(mChoice.equals(null));
	}

	@Test
	public void testNotEqualsString() throws Exception {
		Assert.assertFalse(mChoice.equals("test"));
	}

	@Test
	public void testNotEqualsWrongOrder() throws Exception {
		Choice<Double> another = new Choice<Double>();
		another.add(3.0);
		another.add(4.0);
		another.add(6.0);
		another.add(5.0);
		another.setIndex(1);
		mChoice.setIndex(1);
		Assert.assertFalse(mChoice.equals(another));
	}

	@Test
	public void testNotEqualsWrongTarget() throws Exception {
		Choice<Double> another = new Choice<Double>();
		another.add(3.0);
		another.add(4.0);
		another.add(5.0);
		another.add(6.0);
		another.setIndex(1);
		mChoice.setIndex(0);
		Assert.assertFalse(mChoice.equals(another));
	}

	@Test
	public void testClone() throws Exception {
		mChoice.setIndex(1);
		Choice<Double> clone = mChoice.clone();
		clone.setIndex(2);
		Assert.assertEquals(1, mChoice.getIndex());
		Assert.assertEquals(2, clone.getIndex());
	}

	@Test
	public void testSet() throws Exception {
		mChoice.set(2.0);
		Assert.assertEquals(4, mChoice.getIndex());
	}

	@Test
	public void testSize() throws Exception {
		Assert.assertEquals(4, mChoice.getAll().size());
	}

	@Test
	public void testClear() throws Exception {
		mChoice.clear();
		Assert.assertEquals(0, mChoice.getAll().size());
	}

	@Test
	public void testSetAdd() throws Exception {
		mChoice.set(2.0);
		mChoice.add(1.0);
		mChoice.add(2.0);
		Assert.assertEquals(6, mChoice.getAll().size());
	}
}
