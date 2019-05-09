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

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import poisondog.format.TimeFormatUtils;
/**
 * @author Adam Huang
 */
public class LogRecorder implements Logger {
	private String mTag;
	private List<LogItem> mLogs;

	public LogRecorder(String tag) {
		mTag = tag;
		mLogs = new LinkedList<LogItem>();
	}

	private LogItem create(LogLevel level, String message) {
		LogItem log = new LogItem();
		log.setTime(System.currentTimeMillis());
		log.setLevel(level);
		log.setTag(mTag);
		log.setMessage(message);
		return log;
	}

	private void recordLog(LogLevel level, String message) {
		mLogs.add(create(level, message));
	}

	@Override
	public void log(LogLevel level, String message) {
		LogItem item = create(level, message);
		if (!item.isLog())
			return;
		mLogs.add(item);
//		if (!Log.show(level))
//			return;
//		recordLog(level, message);
	}

	@Override
	public void log(LogItem item) {
		mLogs.add(item);
	}

	public List<LogItem> getLogs() {
		return mLogs;
	}

}
