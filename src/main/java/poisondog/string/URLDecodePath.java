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
import java.net.URLEncoder;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 */
public class URLDecodePath implements StringProcessor, Mission<String> {
	private URLDecode mEncode;

	public URLDecodePath() {
		this("utf8");
	}

	public URLDecodePath(String charset) {
		mEncode = new URLDecode(charset);
	}

	@Override
	public String process(String input) throws UnsupportedEncodingException {
		return execute(input);
	}

	@Override
	public String execute(String input) throws UnsupportedEncodingException {
		ExtractPath task = new ExtractPath();
		String path = task.process(input);
		StringBuilder builder = new StringBuilder();
		builder.append(input.replace(path, ""));
		for (String str: path.split("/")) {
			builder.append(mEncode.process(str));
			builder.append("/");
		}
		if(!input.endsWith("/"))
			return builder.substring(0, builder.length() - 1);
		return builder.toString();
	}
}
