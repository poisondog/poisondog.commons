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

import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2017-06-07
 */
public class LogFactory implements Mission<String[]> {
	private LogLevel mLevel;

	/**
	 * Constructor
	 */
	public LogFactory(LogLevel level) {
		mLevel = level;
	}

	/**
	 * Constructor
	 */
	public LogFactory() {
		this(LogLevel.VERBOSE);
	}

	@Override
	public LogItem execute(String... input) {
		if (input.length != 2)
			throw new IllegalArgumentException("input need has Tag and Message");
		LogItem log = new LogItem();
		log.setTime(System.currentTimeMillis());
		log.setLevel(mLevel);
		log.setTag(input[0]);
		log.setMessage(input[1]);
		return log;
	}
}
