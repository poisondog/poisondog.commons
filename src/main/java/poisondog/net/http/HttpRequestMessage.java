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
package poisondog.net.http;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.ArrayUtils;
/**
 * @author Adam Huang
 */
public class HttpRequestMessage {
	private String mCharset;
	private String mMethod;
	private String mPath;
	private String mHost;
	private String mAuthorization;
	private byte[] mBody;
	private Map<String, String> mHeaders;

	public HttpRequestMessage(String method, String path, String host) {
		mHeaders = new HashMap<String, String>();
		mMethod = method;
		mPath = path;
		mHost = host;
		mCharset = "UTF-8";
	}

	public String getMethod() {
		return mMethod;
	}

	public String getPath() {
		return mPath;
	}

	public String getHost() {
		return mHost;
	}

	public byte[] getContent() {
		StringBuilder builder = new StringBuilder();
		builder.append(mMethod);
		builder.append(" ");
		builder.append(mPath);
		builder.append(" HTTP/1.1\n");
		builder.append(createHeader("Host", mHost));
		if (mAuthorization != null) {
			builder.append(mAuthorization);
		}
		for (String key : mHeaders.keySet()) {
			builder.append(createHeader(key, mHeaders.get(key)));
		}
		builder.append("\n");
		byte[] result = builder.toString().getBytes();
		if (mBody != null)
			result = ArrayUtils.addAll(result, mBody);
		return result;
	}

	private String createHeader(String title, String value) {
		CreateHeader task = new CreateHeader();
		return task.execute(title, value);
	}

	public void setAuthorization(String username, String password) throws UnsupportedEncodingException {
		AuthorizationHeader task = new AuthorizationHeader(mCharset);
		Map<String, String> result = task.execute(username, password);
		mAuthorization = createHeader("Authorization", result.get("Authorization"));
	}

	public void setHeader(String title, String value) {
		mHeaders.put(title, value);
	}

	public void setCharset(String charset) {
		mCharset = charset;
	}

	public void setBody(String body) {
		mBody = body.getBytes();
	}

	public void setBody(byte[] body) {
		mBody = body;
	}
}
