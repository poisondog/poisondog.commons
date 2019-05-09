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
import java.net.URLDecoder;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 */
public class URLDecode implements StringProcessor, Mission<String> {
	private String mCharset;
	private URLDecoder mDecoder;

	public URLDecode() {
		this("utf8");
	}

	public URLDecode(String charset) {
		mCharset = charset;
		mDecoder = new URLDecoder();
	}

	@Override
	public String process(String input) throws UnsupportedEncodingException {
		return execute(input);
	}

	@Override
	public String execute(String input) throws UnsupportedEncodingException {
		String target = input.replace("+", "%2B");
		return mDecoder.decode(target, mCharset);
	}
}
