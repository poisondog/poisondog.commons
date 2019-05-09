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
package poisondog.string;

import poisondog.core.Mission;

/**
 * @author Adam Huang
 */
public class ReplaceScheme implements StringProcessor, Mission<String> {
	private String mScheme;

	public ReplaceScheme(String scheme) {
		mScheme = scheme;
	}

	@Override
	public String execute(String input) {
		int index = input.indexOf(":");
		if(index < 0)
			return input;
		String line = input.substring(index);
		StringBuilder builder = new StringBuilder();
		builder.append(mScheme);
		builder.append(line);
		return builder.toString();
	}

	@Override
	public String process(String input) {
		return execute(input);
	}

}
