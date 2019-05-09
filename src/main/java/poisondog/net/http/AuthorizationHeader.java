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
package poisondog.net.http;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2017-01-25
 */
public class AuthorizationHeader implements Mission<String[]> {
	private String mCharset;

	/**
	 * Constructor
	 */
	public AuthorizationHeader(String charset) {
		mCharset = charset;
	}

	/**
	 * Constructor
	 */
	public AuthorizationHeader() {
		this("utf8");
	}

	@Override
	public Map<String, String> execute(String... input) throws UnsupportedEncodingException {
		if (input.length != 2)
			throw new IllegalArgumentException("need input username and password String.");
		String authorization = input[0] + ":" + input[1];
		String result = new String(Base64.encodeBase64(authorization.getBytes(), false), mCharset);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Authorization", "Basic " + result);
		return map;
	}

	public void setCharset(String charset) {
		mCharset = charset;
	}

}
