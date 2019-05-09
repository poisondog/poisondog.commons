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
package poisondog.log;

import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2016-10-11
 */
public class LogStackTrace implements Mission<Object> {

	@Override
	public String execute(Object none) {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		StringBuilder builder = new StringBuilder();
		for (StackTraceElement element : stackTraceElements) {
			builder.append(element.getClassName());
			builder.append(":");
			builder.append(element.getMethodName());
			builder.append(":");
			builder.append(element.getLineNumber());
			builder.append("\n");
		}
		return builder.toString();
	}

}
