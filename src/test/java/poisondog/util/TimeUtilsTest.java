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

import java.util.Calendar;
import java.util.TimeZone;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.format.TimeFormatUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Adam Huang
 * @since 2016-04-17
 */
public class TimeUtilsTest {

	@Before
	public void setUp() throws Exception {
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
	}

	@Test
	public void testToDayStart() throws Exception {
		long result = TimeUtils.toDayStart(TimeUtils.toLong("2015-11-18T09:01:00.927"));
		Assert.assertEquals(TimeUtils.toLong("2015-11-18T00:00:00.000"), result);
		long result2 = TimeUtils.toDayStart(TimeUtils.toLong("2015-12-31T09:01:00.888"));
		Assert.assertEquals(TimeUtils.toLong("2015-12-31T00:00:00.000"), result2);
	}

	@Test
	public void testDayOfWeekOnMonth() throws Exception {
		Assert.assertEquals(TimeUtils.toLong("2015-11-18T00:00:00.000"), TimeUtils.dayOfWeekOnMonth(3, Calendar.WEDNESDAY, TimeUtils.toLong("2015-11-1")));
		Assert.assertEquals(TimeUtils.toLong("2015-11-18T00:00:00.000"), TimeUtils.dayOfWeekOnMonth(3, Calendar.WEDNESDAY, TimeUtils.toLong("2015-11-30")));
		Assert.assertEquals(TimeUtils.toLong("2016-04-01T00:00:00.000"), TimeUtils.dayOfWeekOnMonth(1, Calendar.FRIDAY, TimeUtils.toLong("2016-04-09")));
	}

	@Test
	public void testName() throws Exception {
		long time = 1427723278405L;
		System.out.println("===================" + TimeUtils.toString(time));
		TimeUtils.setTimeZone(TimeZone.getTimeZone("UTC"));
		System.out.println("===================" + TimeUtils.toString(time));
		TimeUtils.setTimeZone(TimeZone.getTimeZone("GMT+8"));
	}

	@Test
	public void testToLong() throws Exception {
		System.out.println(TimeFormatUtils.toLong("2015/08/19 00:00:00.000"));
		Assert.assertEquals(1439993849923L, TimeUtils.toLong("2015-08-19T22:17:29.923+0800"));
		Assert.assertEquals(1439993849923L, TimeUtils.toLong("2015-08-19T21:17:29.923+0700"));
		Assert.assertEquals(1439993849923L, TimeUtils.toLong("2015-08-19T22:17:29.923"));
		Assert.assertEquals(1439993849000L, TimeUtils.toLong("2015-08-19T22:17:29+0800"));
		Assert.assertEquals(1439993849000L, TimeUtils.toLong("2015-08-19T22:17:29+08:00"));
		Assert.assertEquals(1439993849000L, TimeUtils.toLong("2015-08-19T22:17:29"));
		Assert.assertEquals(1439993820000L, TimeUtils.toLong("2015-08-19T22:17+0800"));
		Assert.assertEquals(1439993820000L, TimeUtils.toLong("2015-08-19T22:17+08:00"));
		Assert.assertEquals(1439993820000L, TimeUtils.toLong("2015-08-19T22:17"));
		Assert.assertEquals(1439992800000L, TimeUtils.toLong("2015-08-19T22+0800"));
		Assert.assertEquals(1439992800000L, TimeUtils.toLong("2015-08-19T22+08:00"));
		Assert.assertEquals(1439992800000L, TimeUtils.toLong("2015-08-19T22"));
		Assert.assertEquals(1439913600000L, TimeUtils.toLong("2015-08-19+0800"));
		Assert.assertEquals(1439913600000L, TimeUtils.toLong("2015-08-19+08:00"));
		Assert.assertEquals(1439913600000L, TimeUtils.toLong("2015-08-19"));
		Assert.assertEquals(1439993849923L, TimeUtils.toLong("20150819T221729.923+0800"));
		Assert.assertEquals(1439993849923L, TimeUtils.toLong("20150819T221729.923"));
		Assert.assertEquals(1439993849000L, TimeUtils.toLong("20150819T221729+0800"));
		Assert.assertEquals(1439993849000L, TimeUtils.toLong("20150819T221729"));
		Assert.assertEquals(1439993820000L, TimeUtils.toLong("20150819T2217+0800"));
		Assert.assertEquals(1439993820000L, TimeUtils.toLong("20150819T2217"));
		Assert.assertEquals(1439992800000L, TimeUtils.toLong("20150819T22+0800"));
		Assert.assertEquals(1439992800000L, TimeUtils.toLong("20150819T22"));
		Assert.assertEquals(1439913600000L, TimeUtils.toLong("20150819+0800"));
		Assert.assertEquals(1439913600000L, TimeUtils.toLong("20150819"));
	}

	@Test
	public void testLongWithTodayTime() throws Exception {
		Assert.assertEquals(TimeFormatUtils.toLong("08:45:12"), TimeUtils.toLong("08:45:12"));
	}

	@Test
	public void testAdjustTime() throws Exception {
		long expected = TimeUtils.toLong("20150819T152032");
		Assert.assertEquals(expected, TimeUtils.adjustTime(TimeUtils.toLong("20150819T221729"), "15:20:32"));
	}

	@Test
	public void testToday() throws Exception {
		String expect = TimeFormatUtils.toString(TimeFormatUtils.SIMPLE, System.currentTimeMillis());
		Assert.assertEquals(expect + " 00:00:00.000", TimeFormatUtils.toString(TimeFormatUtils.SIMPLE_COMPLETE, TimeUtils.today()));
	}

	@Test
	public void testYesterday() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(TimeFormatUtils.today());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		String expect = TimeFormatUtils.toString(cal.getTimeInMillis());
		Assert.assertEquals(expect, TimeFormatUtils.toString(TimeUtils.yesterday()));
	}

	@Test
	public void testToLongBasic() throws Exception {
//		Assert.assertEquals(1439993849923L, TimeUtils.toLong("20150819T221729.923+0800"));
//		Assert.assertEquals(1439993849923L, TimeUtils.toLong("20150819T221729.923+08:00"));
//		Assert.assertEquals(1439993849923L, TimeUtils.toLong("20150819T221729.923"));
//		Assert.assertEquals(1439993849000L, TimeUtils.toLong("20150819T221729+0800"));
//		Assert.assertEquals(1439993849000L, TimeUtils.toLong("20150819T221729"));
//		Assert.assertEquals(1439993820000L, TimeUtils.toLong("20150819T2217+0800"));
//		Assert.assertEquals(1439993820000L, TimeUtils.toLong("20150819T2217"));
//		Assert.assertEquals(1439992800000L, TimeUtils.toLong("20150819T22+0800"));
//		Assert.assertEquals(1439992800000L, TimeUtils.toLong("20150819T22"));
//		Assert.assertEquals(1439913600000L, TimeUtils.toLong("20150819+0800"));
//		Assert.assertEquals(1439913600000L, TimeUtils.toLong("20150819"));
	}

	@Test
	public void testToString() throws Exception {
		Assert.assertEquals("2015-08-19T22:17:29.923+0800", TimeUtils.toString(1439993849923L));
		Assert.assertEquals("20150819T221729.923+0800", TimeUtils.toString(TimeUtils.BASIC_FORMAT, 1439993849923L));
	}

	@Test
	public void testToExtend() throws Exception {
		Assert.assertEquals("2015-08-19T22:17:29.923+0800", TimeUtils.toExtend(1439993849923L));
	}

	@Test
	public void testToBasic() throws Exception {
		Assert.assertEquals("20150819T221729.923+0800", TimeUtils.toBasic(1439993849923L));
	}

	@Test
	public void testToExtendDate() throws Exception {
		Assert.assertEquals("2015-08-19", TimeUtils.toExtendDate(1439993849923L));
	}

	@Test
	public void testToExtendTime() throws Exception {
		Assert.assertEquals("22:17:29", TimeUtils.toExtendTime(1439993849923L));
	}

	@Test
	public void testToBasicDate() throws Exception {
		Assert.assertEquals("20150819", TimeUtils.toBasicDate(1439993849923L));
	}

	@Test
	public void testToBasicTime() throws Exception {
		Assert.assertEquals("221729", TimeUtils.toBasicTime(1439993849923L));
	}

	@Test
	public void testIsToday() throws Exception {
		long current = System.currentTimeMillis();
		Assert.assertTrue(TimeUtils.isToday(current));
		Assert.assertFalse(TimeUtils.isToday(current - 86400000));
		Assert.assertFalse(TimeUtils.isToday(current + 86400000));
	}

	@Test
	public void testSameDay() throws Exception {
		long current = System.currentTimeMillis();
		Assert.assertTrue(TimeUtils.sameDay(1486093789341l, 1486093789551l));
		Assert.assertFalse(TimeUtils.sameDay(1486093789341l, 1486193789551l));
	}

	@Test
	public void testMonth() throws Exception {
		System.out.println("month: " + new SimpleDateFormat("yy-MM").format(System.currentTimeMillis()));
	}

	@Test
	public void testNextDate() throws Exception {
		Assert.assertEquals(TimeUtils.toLong("2015-08-20T22:17:29.923+0800"), TimeUtils.nextDate(1439993849923L));
	}

	@Test
	public void testBetween() throws Exception {
		Assert.assertEquals(19446150077l, TimeUtils.between("2015-08-19T22:17:29.923", "2016-04-01T00:00:00.000"));
	}

}
