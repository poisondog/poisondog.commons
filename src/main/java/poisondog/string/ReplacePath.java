/*
 * Copyright (C) 2014 Adam Huang <poisondog@gmail.com>
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

import java.util.regex.Pattern;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 */
public class ReplacePath implements StringProcessor, Mission<String> {
	private String mPath;

	public ReplacePath(String path) {
		mPath = replaceDollar(path);
	}

	@Override
	public String execute(String input) {
		String old = toNormalString(new GetPath().execute(input));
		if(!old.equals("/"))
			return input.replaceFirst(old, mPath);
		if(input.endsWith("/"))
			return input.substring(0, input.length() - 1) + mPath;
		return input + mPath;
	}

	@Override
	public String process(String input) {
		return execute(input);
	}

	private String toNormalString(String regex) {
		String result = regex.replace("(", "\\(").replace(")", "\\)");
		result = result.replace("[", "\\[").replace("]", "\\]");
		result = result.replace("{", "\\{").replace("}", "\\}");
		result = result.replace("+", "\\+").replace("*", "\\*");
		result = replaceDollar(result);
//		result = result.replace("<", "\\<").replace(">", "\\>");
//		result = result.replace("^", "\\^").replace("|", "\\|");
		return result;
	}

	private String replaceDollar(String input) {
		return input.replace("$", "\\$");
	}
}
