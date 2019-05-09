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
package poisondog.filter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 * @since 2018-07-28
 */
public class FilterTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testObjectEqual() throws Exception {
		Filter on1 = Filter.eq("money", 10);
		Assert.assertTrue(!on1.equals(null));
		Assert.assertTrue(!on1.equals("string"));
		Assert.assertTrue(!on1.equals(Filter.eq("mone", 10)));
		Assert.assertTrue(!on1.equals(Filter.eq("money", 9)));
	}

	@Test
	public void testEqual() throws Exception {
		Filter on10 = Filter.eq("money", 10);
		Assert.assertEquals(Type.Equal, on10.getType());
		Assert.assertEquals("money", on10.getKey());
		Assert.assertEquals(10, on10.getValue());
		Assert.assertEquals(on10, Filter.eq("money", 10));
	}

	@Test
	public void testGreater() throws Exception {
		Filter over10 = Filter.gt("money", 10);
		Assert.assertEquals(Type.GreaterThan, over10.getType());
		Assert.assertEquals("money", over10.getKey());
		Assert.assertEquals(10, over10.getValue());
		Assert.assertEquals(over10, Filter.gt("money", 10));
	}

	@Test
	public void testLess() throws Exception {
		Filter less5 = Filter.lt("time", 5);
		Assert.assertEquals(Type.LessThan, less5.getType());
		Assert.assertEquals("time", less5.getKey());
		Assert.assertEquals(5, less5.getValue());
		Assert.assertEquals(less5, Filter.lt("time", 5));
	}

	@Test
	public void testAnd() throws Exception {
		Filter over10 = Filter.gt("money", 10);
		Filter less5 = Filter.lt("time", 5);
		Filter and = Filter.and(over10, less5);
		Assert.assertEquals(Type.AND, and.getType());
		Assert.assertEquals(2, and.getContent().size());
		Assert.assertTrue(and.getContent().contains(over10));
		Assert.assertTrue(and.getContent().contains(less5));
		Assert.assertEquals(and, Filter.and(less5, over10));
	}

	@Test
	public void testOr() throws Exception {
		Filter over10 = Filter.gt("money", 10);
		Filter less5 = Filter.lt("time", 5);
		Filter or = Filter.or(over10, less5);
		Assert.assertEquals(Type.OR, or.getType());
		Assert.assertEquals(2, or.getContent().size());
		Assert.assertTrue(or.getContent().contains(over10));
		Assert.assertTrue(or.getContent().contains(less5));
		Assert.assertEquals(or, Filter.or(less5, over10));
	}

	@Test
	public void testAndInOr() throws Exception {
		Filter over10 = Filter.gt("money", 10);
		Filter less5 = Filter.lt("time", 5);
		Filter less10 = Filter.lt("money", 10);
		Filter and = Filter.and(over10, less5);
		Filter or = Filter.or(and, less10);
		Assert.assertEquals(Type.AND, and.getType());
		Assert.assertEquals(Type.OR, or.getType());
		Assert.assertEquals(2, and.getContent().size());
		Assert.assertEquals(2, or.getContent().size());
		Assert.assertTrue(or.getContent().contains(and));
		Assert.assertTrue(or.getContent().contains(less10));
		Assert.assertTrue(and.getContent().contains(over10));
		Assert.assertTrue(and.getContent().contains(less5));
	}

	@Test
	public void testNotEqual() throws Exception {
		Filter on10 = Filter.not(Filter.eq("money", 10));
		Assert.assertEquals(Type.NotEqual, on10.getType());
		Assert.assertEquals("money", on10.getKey());
		Assert.assertEquals(10, on10.getValue());
	}

	@Test
	public void testNotGreater() throws Exception {
		Filter over10 = Filter.not(Filter.gt("money", 10));
		Assert.assertEquals(Type.LessEqual, over10.getType());
		Assert.assertEquals("money", over10.getKey());
		Assert.assertEquals(10, over10.getValue());
	}

	@Test
	public void testNotLess() throws Exception {
		Filter less5 = Filter.not(Filter.lt("time", 5));
		Assert.assertEquals(Type.GreaterEqual, less5.getType());
		Assert.assertEquals("time", less5.getKey());
		Assert.assertEquals(5, less5.getValue());
	}

	@Test
	public void testNotAnd() throws Exception {
		Filter over10 = Filter.gt("money", 10);
		Filter less5 = Filter.lt("time", 5);
		Filter and = Filter.not(Filter.and(over10, less5));
		Assert.assertEquals(Type.OR, and.getType());
		Assert.assertEquals(2, and.getContent().size());
		Assert.assertTrue(and.getContent().contains(Filter.not(over10)));
		Assert.assertTrue(and.getContent().contains(Filter.not(less5)));
	}

	@Test
	public void testNotOr() throws Exception {
		Filter over10 = Filter.gt("money", 10);
		Filter less5 = Filter.lt("time", 5);
		Filter or = Filter.not(Filter.or(over10, less5));
		Assert.assertEquals(Type.AND, or.getType());
		Assert.assertEquals(2, or.getContent().size());
		Assert.assertTrue(or.getContent().contains(Filter.not(over10)));
		Assert.assertTrue(or.getContent().contains(Filter.not(less5)));
	}

	@Test
	public void testEmptyAnd() throws Exception {
		Filter over10 = Filter.gt("money", 10);
		try {
			Filter.and();
			Assert.fail("you can't and with nothing");
		} catch(Exception e) {
			Assert.assertTrue(true);
		}
		try {
			Filter.and(over10);
			Assert.fail("you can't and with only one");
		} catch(Exception e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testEmptyOr() throws Exception {
		Filter over10 = Filter.gt("money", 10);
		try {
			Filter.or();
			Assert.fail("you can't or with nothing");
		} catch(Exception e) {
			Assert.assertTrue(true);
		}
		try {
			Filter.or(over10);
			Assert.fail("you can't or with only one");
		} catch(Exception e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testToString() throws Exception {
		Filter over10 = Filter.gt("money", 10);
		System.out.println(over10);
		Filter less5 = Filter.lt("time", 5);
//		Filter and = Filter.and(over10, less5);
		Filter and = Filter.not(Filter.and(over10, less5));
		System.out.println(and);
	}

}
