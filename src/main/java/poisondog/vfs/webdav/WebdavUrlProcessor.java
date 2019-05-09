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
package poisondog.vfs.webdav;

import java.io.UnsupportedEncodingException;
import poisondog.string.ReplaceScheme;
import poisondog.string.StringProcessor;
import poisondog.string.URLEncodePath;
/**
 * @author Adam Huang
 */
public class WebdavUrlProcessor implements StringProcessor {
	private ReplaceScheme mScheme;
	private URLEncodePath mEncode;

	public WebdavUrlProcessor() {
		this("http");
	}

	public WebdavUrlProcessor(String scheme) {
		mScheme = new ReplaceScheme(scheme);
		mEncode = new URLEncodePath("utf8");
	}

	@Override
	public String process(String input) {
		try {
			return mScheme.process(mEncode.process(input));
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return input;
	}
}
