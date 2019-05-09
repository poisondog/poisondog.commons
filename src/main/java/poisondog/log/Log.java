/*
 * Copyright (C) 2017 Adam Huang <poisondog@gmail.com>
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
package poisondog.log;

import java.util.Set;
import poisondog.util.GetCallerInfo;

/**
 * @author Adam Huang
 * @since 2017-06-07
 */
public class Log {
	private static LogLevel sLevel;
	private static Logger sLogger;
	private static GetCallerInfo sGetCaller;
	private static boolean sOnlyMessage;
	private static boolean sSimpleClass;

	static {
		sLogger = new SimpleLogger("");
		sGetCaller = new GetCallerInfo();
		sLevel = LogLevel.INFO;
	}

	public static void setLogger(Logger logger) {
		sLogger = logger;
	}

	public static void setLevel(LogLevel level) {
		sLevel = level;
	}

	public static void setOnlyMessage(boolean flag) {
		sOnlyMessage = flag;
	}

	public static void setSimpleClass(boolean flag) {
		sSimpleClass = flag;
	}

	public static boolean onlyMessage() {
		return sOnlyMessage;
	}

	public static LogLevel getLevel() {
		return sLevel;
	}

	private static LogItem create(LogLevel level, String tag, String message) {
		LogItem log = new LogItem();
		log.setTime(System.currentTimeMillis());
		log.setLevel(level);
		log.setTag(tag);
		log.setMessage(message);
		return log;
	}

	private static String getTag() {
		if (sSimpleClass)
			return sGetCaller.execute(Log.class.getName()).getClassName().replaceAll("\\S*\\.", "");
		return sGetCaller.execute(Log.class.getName()).getClassName();
	}

	public static void v(Object obj) {
		v(getTag(), obj.toString());
	}

	public static void d(Object obj) {
		d(getTag(), obj.toString());
	}

	public static void i(Object obj) {
		i(getTag(), obj.toString());
	}

	public static void w(Object obj) {
		w(getTag(), obj.toString());
	}

	public static void e(Object obj) {
		e(getTag(), obj.toString());
	}

	public static void v(String tag, Object obj) {
		sLogger.log(create(LogLevel.VERBOSE, tag, obj.toString()));
	}

	public static void d(String tag, Object obj) {
		sLogger.log(create(LogLevel.DEBUG, tag, obj.toString()));
	}

	public static void i(String tag, Object obj) {
		sLogger.log(create(LogLevel.INFO, tag, obj.toString()));
	}

	public static void w(String tag, Object obj) {
		sLogger.log(create(LogLevel.WARNING, tag, obj.toString()));
	}

	public static void e(String tag, Object obj) {
		sLogger.log(create(LogLevel.ERROR, tag, obj.toString()));
	}

}
