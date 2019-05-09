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

import poisondog.format.TimeFormatUtils;

/**
 * @author Adam Huang
 */
public class LogItem {
	private long mTime;
	private LogLevel mLevel;
	private String mTag;
	private String mMessage;

	/**
	 * Constructor
	 */
	public LogItem() {
		mLevel = LogLevel.INFO;
	}

	public void setTime(long time) {
		mTime = time;
	}

	public long getTime() {
		return mTime;
	}

	public void setLevel(LogLevel level) {
		mLevel = level;
	}

	public LogLevel getLevel() {
		return mLevel;
	}

	public void setTag(String tag) {
		mTag = tag;
	}

	public String getTag() {
		return mTag;
	}

	public void setMessage(String message) {
		mMessage = message;
	}

	public String getMessage() {
		return mMessage;
	}

	public boolean isLog() {
		return getLevel().ordinal() <= Log.getLevel().ordinal();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof LogItem))
			return false;
		LogItem another = (LogItem)obj;
		if (mTime != another.getTime())
			return false;
		if (mLevel != another.getLevel())
			return false;
		if (!mTag.equals(another.getTag()))
			return false;
		if (!mMessage.equals(another.getMessage()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		if (!isLog())
			return "";
		StringBuilder builder = new StringBuilder();
		if (!Log.onlyMessage()) {
			builder.append(getLevel());
			builder.append("\t");
			builder.append(TimeFormatUtils.simpleComplete(getTime()));
			builder.append("\t");
			builder.append(getTag());
			builder.append("\t");
		}
		builder.append(getMessage());
		return builder.toString();
	}
}
