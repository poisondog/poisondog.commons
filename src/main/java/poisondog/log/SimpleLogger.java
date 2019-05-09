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
package poisondog.log;


/**
 * @author Adam Huang
 */
public class SimpleLogger implements Logger {
	private String mTag;

	public SimpleLogger(String tag) {
		mTag = tag;
	}

	private LogItem create(LogLevel level, String message) {
		LogItem log = new LogItem();
		log.setTime(System.currentTimeMillis());
		log.setLevel(level);
		log.setTag(mTag);
		log.setMessage(message);
		return log;
	}

	@Override
	public void log(LogLevel level, String message) {
		log(create(level, message));
	}

	@Override
	public void log(LogItem item) {
		if (item.isLog())
			printLog(item);
	}

	private void printLog(LogItem log) {
		System.out.println(log);
	}

	public static void setDefaultLogLevel(LogLevel level) {
		Log.setLevel(level);
	}

}
