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
import java.util.LinkedList;
/**
 * @author Adam Huang
 */
public class LastListTest {
	private LastList<Integer> mList;

	@Before
	public void setUp() throws Exception {
		mList = new LastList<Integer>(3);
	}

	@Test
	public void testAdd() throws Exception {
		mList.add(1);
		mList.add(2);
		mList.add(3);
		mList.add(4);
		Assert.assertEquals(3, mList.size());
		Assert.assertEquals(2, (int)mList.get(0));
		Assert.assertEquals(3, (int)mList.get(1));
		Assert.assertEquals(4, (int)mList.get(2));
	}

	@Test
	public void testAddAll() throws Exception {
		LinkedList<Integer> input = new LinkedList<Integer>();
		input.add(1);
		input.add(2);
		input.add(3);
		input.add(4);
		mList.addAll(input);
		Assert.assertEquals(3, mList.size());
		Assert.assertEquals(2, (int)mList.get(0));
		Assert.assertEquals(3, (int)mList.get(1));
		Assert.assertEquals(4, (int)mList.get(2));
	}
}
