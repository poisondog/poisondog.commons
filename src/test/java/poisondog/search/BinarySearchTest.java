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
package poisondog.search;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Adam Huang
 * @since 2019-01-03
 */
public class BinarySearchTest {
	private ArrayList<String> input;

	@Before
	public void setUp() throws Exception {
		input = new ArrayList<String>();
		input.add("hi");
		input.add("hello");
		input.add("world");
		input.add("and");
		input.add("java");
		Collections.sort(input);
		// [and, hello, hi, java, world]
	}

	@Test
	public void testEqualFirst() throws Exception {
		BinarySearch search = BinarySearch.equal("and");
		Integer result = search.execute(input);
		Assert.assertEquals("and", input.get(result));
		Assert.assertEquals(1, search.getGreater());
		Assert.assertEquals(-1, search.getLess());
		Assert.assertEquals(0, search.getNearest());
	}

	@Test
	public void testEqualLast() throws Exception {
		BinarySearch search = BinarySearch.equal("world");
		Integer result = search.execute(input);
		Assert.assertEquals("world", input.get(result));
		Assert.assertEquals(-1, search.getGreater());
		Assert.assertEquals(3, search.getLess());
		Assert.assertEquals(4, search.getNearest());
	}

	@Test
	public void testEqualBeforeFirst() throws Exception {
		BinarySearch search = BinarySearch.equal("an");
		Integer result = search.execute(input);
		Assert.assertEquals(-1, result.intValue());
		Assert.assertEquals(0, search.getGreater());
		Assert.assertEquals(-1, search.getLess());
		Assert.assertEquals(0, search.getNearest());
	}

	@Test
	public void testEqualAfterFirst() throws Exception {
		BinarySearch search = BinarySearch.equal("ands");
		Integer result = search.execute(input);
		Assert.assertEquals(-1, result.intValue());
		Assert.assertEquals(1, search.getGreater());
		Assert.assertEquals(0, search.getLess());
		Assert.assertEquals(0, search.getNearest());
	}

	@Test
	public void testEqualBeforeLast() throws Exception {
		BinarySearch search = BinarySearch.equal("worl");
		Integer result = search.execute(input);
		Assert.assertEquals(-1, result.intValue());
		Assert.assertEquals(4, search.getGreater());
		Assert.assertEquals(3, search.getLess());
		Assert.assertEquals(4, search.getNearest());
	}

	@Test
	public void testEqualAfterLast() throws Exception {
		BinarySearch search = BinarySearch.equal("worlds");
		Integer result = search.execute(input);
		Assert.assertEquals(-1, result.intValue());
		Assert.assertEquals(-1, search.getGreater());
		Assert.assertEquals(4, search.getLess());
		Assert.assertEquals(4, search.getNearest());
	}

	@Test
	public void testEqualJava() throws Exception {
		BinarySearch search = BinarySearch.equal("java");
		Integer result = search.execute(input);
		Assert.assertEquals(3, result.intValue());
	}

	@Test
	public void testGreaterJava() throws Exception {
		BinarySearch search = BinarySearch.greater("java");
		Integer result = search.execute(input);
		Assert.assertEquals(4, result.intValue());
	}

	@Test
	public void testLessJava() throws Exception {
		BinarySearch search = BinarySearch.less("java");
		Integer result = search.execute(input);
		Assert.assertEquals(2, result.intValue());
	}

	@Test
	public void testEqualJav() throws Exception {
		BinarySearch search = BinarySearch.equal("jav");
		Integer result = search.execute(input);
		Assert.assertEquals(-1, result.intValue());
	}

	@Test
	public void testGreaterJav() throws Exception {
		BinarySearch search = BinarySearch.greater("jav");
		Integer result = search.execute(input);
		Assert.assertEquals(3, result.intValue());
	}

	@Test
	public void testLessJav() throws Exception {
		BinarySearch search = BinarySearch.less("jav");
		Integer result = search.execute(input);
		Assert.assertEquals(2, result.intValue());
	}

	@Test
	public void testNearestJav() throws Exception {
		BinarySearch search = BinarySearch.nearest("jav");
		Integer result = search.execute(input);
		Assert.assertEquals(3, result.intValue());
	}

	@Test
	public void testEqualJavac() throws Exception {
		BinarySearch search = BinarySearch.equal("javac");
		Integer result = search.execute(input);
		Assert.assertEquals(-1, result.intValue());
	}

	@Test
	public void testGreaterJavac() throws Exception {
		BinarySearch search = BinarySearch.greater("javac");
		Integer result = search.execute(input);
		Assert.assertEquals(4, result.intValue());
	}

	@Test
	public void testLessJavac() throws Exception {
		BinarySearch search = BinarySearch.less("javac");
		Integer result = search.execute(input);
		Assert.assertEquals(3, result.intValue());
	}

	@Test
	public void testNearestJavac() throws Exception {
		BinarySearch search = BinarySearch.nearest("javac");
		Integer result = search.execute(input);
		Assert.assertEquals(3, result.intValue());
	}

}
