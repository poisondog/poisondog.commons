/*
 * Copyright (C) 2014 Adam Huang
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import poisondog.util.TimeUtils;
/**
 * @author Adam Huang
 */
public class TimeFormatUtils {
	public static final String APPEND_ALL = "yyyyMMdd";
	public static final String APPEND_ALL_WITH_TIME = "yyyyMMddHHmmss";
	public static final String APPEND_ALL_COMPLETE = "yyyyMMddHHmmssSSS";
	public static final String APPEND_ALL_TIME = "HHmmss";
	public static final String APPEND_ALL_TIME_ = "HHmmssSSS";
	public static final String SIMPLE = "yyyy/MM/dd";
	public static final String SIMPLE_COMPLETE = "yyyy/MM/dd HH:mm:ss.SSS";
	public static final String SIMPLE_WITH_TIME = "yyyy/MM/dd HH:mm:ss";
	public static final String SIMPLE_DATE_HOUR_MIN = "yyyy/MM/dd HH:mm";
	public static final String SIMPLE_TIME = "HH:mm:ss";

	public static String toString(long time) {
		return simpleComplete(time);
	}

	public static String toString(String format, long time) {
		return toString(TimeZone.getDefault(), format, time);
	}

	public static String toString(String timezone, String format, long time) {
		return toString(TimeZone.getTimeZone(timezone), format, time);
	}

	public static String toString(TimeZone timezone, String format, long time) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setTimeZone(timezone);
		return dateFormat.format(time);
	}

	public static long today() {
		return TimeUtils.today();
	}

	public static long yesterday() {
		return TimeUtils.yesterday();
	}

	public static long toLong(String time) {
		try {return toLong(SIMPLE_COMPLETE, time);} catch(ParseException e) {}
		try {return toLong(SIMPLE_WITH_TIME, time);} catch(ParseException e) {}
		try {return toLong(SIMPLE_DATE_HOUR_MIN, time);} catch(ParseException e) {}
		try {return toLong(SIMPLE, time);} catch(ParseException e) {}
		try {return toLong(SIMPLE_WITH_TIME, toString(SIMPLE, today()) + " " + time);} catch(ParseException e) {}
		try {return toLong(APPEND_ALL_COMPLETE, time);} catch(ParseException e) {}
		try {return toLong(APPEND_ALL_WITH_TIME, time);} catch(ParseException e) {}
		try {return toLong(APPEND_ALL, time);} catch(ParseException e) {}
		throw new IllegalArgumentException("can't parse this time format.");
	}

	public static long toLong(String format, String time) throws ParseException {
		return toLong(TimeZone.getDefault(), format, time);
	}

	public static long toLong(String timezone, String format, String time) throws ParseException {
		return toLong(TimeZone.getTimeZone(timezone), format, time);
	}

	public static long toLong(TimeZone timezone, String format, String time) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setTimeZone(timezone);
		return dateFormat.parse(time).getTime();
	}

	public static String simple(long time) {
		return toString(TimeZone.getDefault(), SIMPLE, time);
	}

	public static String simpleWithTime(long time) {
		return toString(TimeZone.getDefault(), SIMPLE_WITH_TIME, time);
	}

	public static String simpleComplete(long time) {
		return toString(TimeZone.getDefault(), SIMPLE_COMPLETE, time);
	}

	public static long simple(String time) throws ParseException {
		return toLong(SIMPLE, time);
	}

	public static long simpleWithTime(String time) throws ParseException {
		return toLong(SIMPLE_WITH_TIME, time);
	}
}
