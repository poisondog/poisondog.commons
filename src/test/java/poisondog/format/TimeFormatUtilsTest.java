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
package poisondog.format;

import java.util.Calendar;
import java.util.TimeZone;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 */
public class TimeFormatUtilsTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
	}

	@Test
	public void testToString() throws Exception {
		Assert.assertEquals("1970/01/01", TimeFormatUtils.toString(TimeFormatUtils.SIMPLE, 0));
		Assert.assertEquals("1970/01/01", TimeFormatUtils.simple(0));
		Assert.assertEquals("1970/01/01 08:00:02", TimeFormatUtils.simpleWithTime(2000));

		//because here is GTM+8
		Assert.assertEquals("197001010800", TimeFormatUtils.toString("yyyyMMddHHmm", 0));
		Assert.assertEquals("197001010801", TimeFormatUtils.toString("yyyyMMddHHmm", 60000));
	}

	@Test
	public void testStringWithTimeZone() throws Exception {
		Assert.assertEquals("197001010000", TimeFormatUtils.toString(TimeZone.getTimeZone("GMT"), "yyyyMMddHHmm", 0));
		Assert.assertEquals("197001010001", TimeFormatUtils.toString(TimeZone.getTimeZone("GMT"), "yyyyMMddHHmm", 60000));
		Assert.assertEquals("197001010002", TimeFormatUtils.toString(TimeZone.getTimeZone("GMT"), "yyyyMMddHHmm", 120000));
	}

	@Test
	public void testStringWithTimeZoneString() throws Exception {
		Assert.assertEquals("197001010000", TimeFormatUtils.toString("GMT", "yyyyMMddHHmm", 0));
		Assert.assertEquals("197001011400", TimeFormatUtils.toString("GMT", "yyyyMMddHHmm", 50400000));
		Assert.assertEquals("197001010001", TimeFormatUtils.toString("GMT", "yyyyMMddHHmm", 60000));
		Assert.assertEquals("197001010002", TimeFormatUtils.toString("GMT", "yyyyMMddHHmm", 120000));
	}

	@Test
	public void testToLong() throws Exception {
		Assert.assertEquals(-28800000, TimeFormatUtils.toLong("1970/01/01"));
		Assert.assertEquals(0, TimeFormatUtils.toLong("1970/01/01 08:00:00"));
		Assert.assertEquals(124, TimeFormatUtils.toLong("1970/01/01 08:00:00.124"));
		Assert.assertEquals(0, TimeFormatUtils.toLong("1970/01/01 08:00"));
		Assert.assertEquals(0, TimeFormatUtils.toLong("1970/1/1 8:00"));
	}

	@Test
	public void testToLongWithFormat() throws Exception {
		Assert.assertEquals(0, TimeFormatUtils.toLong(TimeFormatUtils.SIMPLE_WITH_TIME, "1970/01/01 08:00:00"));
		Assert.assertEquals(57600000, TimeFormatUtils.simple("1970/01/02"));
		Assert.assertEquals(1000, TimeFormatUtils.simpleWithTime("1970/01/01 08:00:01"));

		try{
			Assert.assertEquals(0, TimeFormatUtils.toLong("yyyyMMddHHmm", "197001010800"));
			Assert.assertEquals(60000, TimeFormatUtils.toLong("yyyyMMddHHmm", "197001010801"));
		}catch(Exception e) {
			Assert.fail();
		}
	}

	@Test
	public void testToday() throws Exception {
		String expect = TimeFormatUtils.toString(TimeFormatUtils.SIMPLE, System.currentTimeMillis());
		Assert.assertEquals(expect + " 00:00:00.000", TimeFormatUtils.toString(TimeFormatUtils.SIMPLE_COMPLETE, TimeFormatUtils.today()));
	}

	@Test
	public void testYesterday() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(TimeFormatUtils.today());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		String expect = TimeFormatUtils.toString(cal.getTimeInMillis());
		Assert.assertEquals(expect, TimeFormatUtils.toString(TimeFormatUtils.yesterday()));
	}

	@Test
	public void testLongWithTimeZone() throws Exception {
		try{
			Assert.assertEquals(0, TimeFormatUtils.toLong(TimeZone.getTimeZone("GMT"), "yyyyMMddHHmm", "197001010000"));
			Assert.assertEquals(50400000, TimeFormatUtils.toLong(TimeZone.getTimeZone("GMT"), "yyyyMMddHHmm", "197001011400"));
			Assert.assertEquals(60000, TimeFormatUtils.toLong(TimeZone.getTimeZone("GMT"), "yyyyMMddHHmm", "197001010001"));
			Assert.assertEquals(120000, TimeFormatUtils.toLong(TimeZone.getTimeZone("GMT"), "yyyyMMddHHmm", "197001010002"));
		}catch(Exception e) {
			Assert.fail();
		}
	}

	@Test
	public void testLongWithTimeZoneString() throws Exception {
		try{
			Assert.assertEquals(0, TimeFormatUtils.toLong("GMT", "yyyyMMddHHmm", "197001010000"));
			Assert.assertEquals(60000, TimeFormatUtils.toLong("GMT", "yyyyMMddHHmm", "197001010001"));
			Assert.assertEquals(120000, TimeFormatUtils.toLong("GMT", "yyyyMMddHHmm", "197001010002"));
		}catch(Exception e) {
			Assert.fail();
		}
	}

	@Test
	public void testAppendAllComplete() throws Exception {
		TimeZone gmt = TimeZone.getTimeZone("GMT");
		Assert.assertEquals("19700101000000003", TimeFormatUtils.toString(gmt, TimeFormatUtils.APPEND_ALL_COMPLETE, 3));
		Assert.assertEquals("19700101000100789", TimeFormatUtils.toString(gmt, TimeFormatUtils.APPEND_ALL_COMPLETE, 60789));
		Assert.assertEquals("19700101000203456", TimeFormatUtils.toString(gmt, TimeFormatUtils.APPEND_ALL_COMPLETE, 123456));
		System.out.println(TimeFormatUtils.toString(TimeFormatUtils.APPEND_ALL_COMPLETE, 3));
	}

	@Test
	public void testLongWithSimpleTime() throws Exception {
		long time = TimeFormatUtils.toLong("08:45:12");
		String date = TimeFormatUtils.toString(TimeFormatUtils.SIMPLE, TimeFormatUtils.today());
		Assert.assertEquals(date + " 08:45:12.000", TimeFormatUtils.toString(time));
	}

}
