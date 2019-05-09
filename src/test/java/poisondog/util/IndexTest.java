/*
 * Copyright (C) 2018 Adam Huang <poisondog@gmail.com>
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

/**
 * @author Adam Huang
 * @since 2018-08-01
 */
public class IndexTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testApp() throws Exception {
		Index<Integer, Integer> index = new Index<Integer, Integer>();
		index.put(1, 1);
		index.put(1, 2);
		index.put(2, 3);
		index.put(2, 4);
		index.put(2, 5);
		index.put(3, 6);
		index.put(3, 7);
		index.put(3, 8);
		index.put(3, 9);
		Assert.assertEquals(2, index.get(1).size());
		Assert.assertEquals(3, index.get(2).size());
		Assert.assertEquals(4, index.get(3).size());
		Assert.assertTrue(index.get(2).contains(3));
		Assert.assertTrue(index.get(2).contains(4));
		Assert.assertTrue(index.get(2).contains(5));
	}
}
