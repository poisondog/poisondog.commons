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

import java.util.HashSet;

/**
 * @author Adam Huang
 */
public class LowerThen extends HashSet<LogLevel> {
	public LowerThen(LogLevel level) {
		super();
		if (level == LogLevel.VERBOSE) {
			add(LogLevel.VERBOSE);
			add(LogLevel.DEBUG);
			add(LogLevel.INFO);
			add(LogLevel.WARNING);
			add(LogLevel.ERROR);
		} else if (level == LogLevel.DEBUG) {
			add(LogLevel.DEBUG);
			add(LogLevel.INFO);
			add(LogLevel.WARNING);
			add(LogLevel.ERROR);
		} else if (level == LogLevel.INFO) {
			add(LogLevel.INFO);
			add(LogLevel.WARNING);
			add(LogLevel.ERROR);
		} else if (level == LogLevel.WARNING) {
			add(LogLevel.WARNING);
			add(LogLevel.ERROR);
		} else if (level == LogLevel.ERROR) {
			add(LogLevel.ERROR);
		} else {
			add(LogLevel.INFO);
			add(LogLevel.WARNING);
			add(LogLevel.ERROR);
		}
	}
}
