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
package poisondog.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
/**
 * @author Adam Huang
 */
public class UniqueListTest {
	private UniqueList<Integer> mList;

	@Before
	public void setUp() throws Exception {
		mList = new UniqueList<Integer>();
	}

	@Test
	public void testConstructorWithCapacity() throws Exception {
		mList = new UniqueList<Integer>(3);
		Assert.assertNotNull(mList);
	}

	@Test
	public void testConstructorWithCollection() throws Exception {
		ArrayList<Integer> input = new ArrayList<Integer>();
		input.add(3);
		input.add(4);
		input.add(5);
		mList = new UniqueList<Integer>(input);
		Assert.assertEquals(3, mList.size());
	}

	@Test
	public void testAdd() throws Exception {
		Assert.assertTrue(mList.add(1));
		Assert.assertFalse(mList.add(1));
		Assert.assertEquals(1, mList.size());
	}

	@Test
	public void testAddAll() throws Exception {
		ArrayList<Integer> input = new ArrayList<Integer>();
		input.add(3);
		input.add(4);
		input.add(5);

		mList.add(1);
		mList.add(2);
		mList.add(3);
		mList.add(4);

		mList.addAll(input);
		Assert.assertEquals(5, mList.size());
		Assert.assertTrue(mList.contains(1));
		Assert.assertTrue(mList.contains(2));
		Assert.assertTrue(mList.contains(3));
		Assert.assertTrue(mList.contains(4));
		Assert.assertTrue(mList.contains(5));
	}

	@Test
	public void testAddAllWithIndex() throws Exception {
		ArrayList<Integer> input = new ArrayList<Integer>();
		input.add(1);
		input.add(3);
		input.add(4);
		input.add(5);

		mList.add(1);
		mList.add(2);
		mList.add(3);
		mList.add(4);

		mList.addAll(1, input);
		Assert.assertEquals(5, mList.size());
		Assert.assertEquals(1, mList.get(0).intValue());
		Assert.assertEquals(5, mList.get(1).intValue());
		Assert.assertEquals(2, mList.get(2).intValue());
		Assert.assertEquals(3, mList.get(3).intValue());
		Assert.assertEquals(4, mList.get(4).intValue());
	}
}
