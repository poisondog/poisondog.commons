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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author Adam Huang
 * @since 2016-04-17
 */
public class TimeUtils {
	public static final String EXTEND_DATE = "yyyy-MM-dd";
	public static final String EXTEND_TIME = "HH:mm:ss";
	public static final String EXTEND_TIME_ = "HH:mm:ss.SSS";
	public static final String EXTEND_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	public static final String BASIC_DATE = "yyyyMMdd";
	public static final String BASIC_TIME = "HHmmss";
	public static final String BASIC_FORMAT = "yyyyMMdd'T'HHmmss.SSSZ";
	private static SimpleDateFormat sFormat = new SimpleDateFormat();

	public static void setTimeZone(TimeZone zone) {
		sFormat.setTimeZone(zone);
	}

	public static long yesterday() {
		Calendar yesterday = Calendar.getInstance();
		yesterday.setTimeInMillis(today());
		yesterday.add(Calendar.DAY_OF_MONTH, -1);
		return yesterday.getTimeInMillis();
	}

	public static long today() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		return today.getTimeInMillis();
	}

	public static long tomorrow() {
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.setTimeInMillis(today());
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
		return tomorrow.getTimeInMillis();
	}

	public static long toDayStart(long time) {
		Calendar day = Calendar.getInstance();
		day.setTimeInMillis(time);
		day.set(Calendar.HOUR_OF_DAY, 0);
		day.set(Calendar.MINUTE, 0);
		day.set(Calendar.SECOND, 0);
		day.set(Calendar.MILLISECOND, 0);
		return day.getTimeInMillis();
	}

	/**
	 * @param time the first time to be recognize
	 * @param another the second time to be recognize
	 * @return <code>true</code> if first time and second time all in same day;
	 * <code>false</code> otherwise.
	 */
	public static boolean sameDay(long time, long another) {
		long startA = toDayStart(time);
		long startB = toDayStart(another);
		return startA == startB;
	}

	/**
	 * @param time the time to be recognize
	 * @return <code>true</code> if time between today start and tomorrow start;
	 * <code>false</code> otherwise.
	 */
	public static boolean isToday(long time) {
		long today = today();
		return time >= today && time < tomorrow();
	}

	public static long dayOfWeekOnMonth(int count, int dayOfWeek, long current) {
		Calendar day = Calendar.getInstance();
		day.setTimeInMillis(toDayStart(current));
		day.set(Calendar.DATE, 1);
		while (day.get(Calendar.DAY_OF_WEEK) != dayOfWeek) {
			day.add(Calendar.DAY_OF_MONTH, 1);
		}
		day.add(Calendar.WEEK_OF_MONTH, count - 1);
		return day.getTimeInMillis();
	}

	public static long toLong(String format, String time) throws ParseException {
		sFormat.applyPattern(format);
		return sFormat.parse(time).getTime();
	}

	public static long toLong(String time) {
		try {return extendFormatZone(time);} catch(ParseException e) {}
		try {return extendFormat(time);} catch(ParseException e) {}
		try {return extendSecondZone(time);} catch(ParseException e) {}
		try {return extendSecond(time);} catch(ParseException e) {}
		try {return extendMinuteZone(time);} catch(ParseException e) {}
		try {return extendMinute(time);} catch(ParseException e) {}
		try {return extendHourZone(time);} catch(ParseException e) {}
		try {return extendHour(time);} catch(ParseException e) {}
		try {return extendDateZone(time);} catch(ParseException e) {}
		try {return extendDate(time);} catch(ParseException e) {}
		try {return basicFormatZone(time);} catch(ParseException e) {}
		try {return basicFormat(time);} catch(ParseException e) {}
		try {return basicSecondZone(time);} catch(ParseException e) {}
		try {return basicSecond(time);} catch(ParseException e) {}
		try {return basicMinuteZone(time);} catch(ParseException e) {}
		try {return basicMinute(time);} catch(ParseException e) {}
		try {return basicHourZone(time);} catch(ParseException e) {}
		try {return basicHour(time);} catch(ParseException e) {}
		try {return basicDateZone(time);} catch(ParseException e) {}
		try {return basicDate(time);} catch(ParseException e) {}
		try {return extendTime(time);} catch(ParseException e) {}
		throw new IllegalArgumentException("can't parse this time format.");
	}

	private static long basicDate(String time) throws ParseException {
		return toLong("yyyyMMdd", time);
	}

	private static long basicDateZone(String time) throws ParseException {
		return toLong("yyyyMMddZ", time);
	}

	private static long basicHour(String time) throws ParseException {
		return toLong("yyyyMMdd'T'HH", time);
	}

	private static long basicHourZone(String time) throws ParseException {
		return toLong("yyyyMMdd'T'HHZ", time);
	}

	private static long basicMinute(String time) throws ParseException {
		return toLong("yyyyMMdd'T'HHmm", time);
	}

	private static long basicMinuteZone(String time) throws ParseException {
		return toLong("yyyyMMdd'T'HHmmZ", time);
	}

	private static long basicSecond(String time) throws ParseException {
		return toLong("yyyyMMdd'T'HHmmss", time);
	}

	private static long basicSecondZone(String time) throws ParseException {
		return toLong("yyyyMMdd'T'HHmmssZ", time);
	}

	private static long basicFormat(String time) throws ParseException {
		return toLong("yyyyMMdd'T'HHmmss.SSS", time);
	}

	private static long basicFormatZone(String time) throws ParseException {
		return toLong("yyyyMMdd'T'HHmmss.SSSZ", time);
	}

	private static long extendFormatZone(String time) throws ParseException {
		return toLong("yyyy-MM-dd'T'HH:mm:ss.SSSZ", time);
	}

	private static long extendFormat(String time) throws ParseException {
		return toLong("yyyy-MM-dd'T'HH:mm:ss.SSS", time);
	}

	private static long extendSecondZone(String time) throws ParseException {
		return toLong("yyyy-MM-dd'T'HH:mm:ssZ", time);
	}

	private static long extendSecond(String time) throws ParseException {
		return toLong("yyyy-MM-dd'T'HH:mm:ss", time);
	}

	private static long extendMinuteZone(String time) throws ParseException {
		return toLong("yyyy-MM-dd'T'HH:mmZ", time);
	}

	private static long extendMinute(String time) throws ParseException {
		return toLong("yyyy-MM-dd'T'HH:mm", time);
	}

	private static long extendHourZone(String time) throws ParseException {
		return toLong("yyyy-MM-dd'T'HHZ", time);
	}

	private static long extendHour(String time) throws ParseException {
		return toLong("yyyy-MM-dd'T'HH", time);
	}

	private static long extendDateZone(String time) throws ParseException {
		return toLong("yyyy-MM-ddZ", time);
	}

	private static long extendDate(String time) throws ParseException {
		return toLong("yyyy-MM-dd", time);
	}

	private static long extendTime(String time) throws ParseException {
		return adjustTime(today(), time);
	}

	/**
	 * @param target the target is time want to adjustTime
	 * @param time the time is time string for adjust target ex: 15:20:32
	 * @return long that date same with target and time same with time
	 */
	public static long adjustTime(long target, String time) throws ParseException {
		String date = toExtendDate(target);
		return toLong(date + "T" + time);
	}

	public static String toString(long time) {
		return toExtend(time);
	}

	/**
	 * @param time the time for transfer String
	 * @return extend format string. ex: 2015-08-19T22:17:29
	 */
	public static String toExtend(long time) {
		return toString(EXTEND_FORMAT, time);
	}

	/**
	 * @param time time for transfer String
	 * @return extend date string. ex:2015-08-19
	 */
	public static String toExtendDate(long time) {
		return toString(EXTEND_DATE, time);
	}

	/**
	 * @param time time for transfer String
	 * @return extend time string. ex:22:17:29
	 */
	public static String toExtendTime(long time) {
		return toString(EXTEND_TIME, time);
	}

	public static String toBasic(long time) {
		return toString(BASIC_FORMAT, time);
	}

	/**
	 * @param time time for transfer string
	 * @return basic date string. ex:20150819
	 */
	public static String toBasicDate(long time) {
		return toString(BASIC_DATE, time);
	}

	/**
	 * @param time time for transfer string
	 * @return basic time string. ex:221729
	 */
	public static String toBasicTime(long time) {
		return toString(BASIC_TIME, time);
	}

	public static String toString(String format, long time) {
		sFormat.applyPattern(format);
		return sFormat.format(time);
	}

	/**
	 * @param time the time for calculate next date
	 * @return next date with same time
	 */
	public static long nextDate(long time) {
		Calendar day = Calendar.getInstance();
		day.setTimeInMillis(time);
		day.add(Calendar.DATE, 1);
		return day.getTimeInMillis();
	}

	public static long between(String start, String end) {
		System.out.println(toLong(start));
		System.out.println(toLong(end));
		return Math.abs(toLong(end) - toLong(start));
	}

	public static long between(String another) {
		return Math.abs(toLong(another) - System.currentTimeMillis());
	}

}
