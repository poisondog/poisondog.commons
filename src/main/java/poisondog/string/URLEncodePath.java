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

import java.io.UnsupportedEncodingException;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 */
public class URLEncodePath implements StringProcessor, Mission<String> {
	private URLEncode mEncode;

	public URLEncodePath() {
		this("utf8");
	}

	public URLEncodePath(String charset) {
		mEncode = new URLEncode(charset);
	}

	@Override
	public String process(String input) throws UnsupportedEncodingException {
		return execute(input);
	}

	@Override
	public String execute(String input) throws UnsupportedEncodingException {
		ExtractPath task = new ExtractPath();
		String path = task.process(input);
		if (path.equals("/"))
			return input;
		String[] strings = path.split("/");
		StringBuilder builder = new StringBuilder();
		builder.append(input.replace(path, ""));
		for (String str: strings) {
			builder.append(mEncode.process(str));
			builder.append("/");
		}
		if(!input.endsWith("/"))
			return builder.substring(0, builder.length() - 1);
		return builder.toString();
	}
}
