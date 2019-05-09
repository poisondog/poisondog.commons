/*
 * Copyright (C) 2019 Adam Huang <poisondog@gmail.com>
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

import java.util.LinkedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 * @since 2019-03-13
 */
public class UniqueLastTest {
	private UniqueLast<Integer> mList;

	@Before
	public void setUp() throws Exception {
		mList = new UniqueLast<Integer>(5);
	}

	@Test
	public void testAdd() throws Exception {
		mList.add(1);
		mList.add(2);
		mList.add(3);
		mList.add(4);
		mList.add(5);
		mList.add(2);
		mList.add(6);
		Assert.assertEquals(5, mList.size());
		Assert.assertEquals(3, (int)mList.get(0));
		Assert.assertEquals(4, (int)mList.get(1));
		Assert.assertEquals(5, (int)mList.get(2));
		Assert.assertEquals(2, (int)mList.get(3));
		Assert.assertEquals(6, (int)mList.get(4));
	}

	@Test
	public void testAddAll() throws Exception {
		LinkedList<Integer> input = new LinkedList<Integer>();
		input.add(1);
		input.add(2);
		input.add(3);
		input.add(4);
		input.add(5);
		input.add(2);
		input.add(6);
		mList.addAll(input);
		Assert.assertEquals(5, mList.size());
		Assert.assertEquals(3, (int)mList.get(0));
		Assert.assertEquals(4, (int)mList.get(1));
		Assert.assertEquals(5, (int)mList.get(2));
		Assert.assertEquals(2, (int)mList.get(3));
		Assert.assertEquals(6, (int)mList.get(4));
	}
}
