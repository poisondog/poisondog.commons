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
package poisondog.net.http;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
/**
 * @author Adam Huang
 */
public class HttpResponse implements Serializable {
	private int mResponseCode;
	private Map<String, String> mHeaders;
//	private byte[] mContent;
	private InputStream mInput;

	public HttpResponse(int code) {
		mHeaders = new HashMap<String, String>();
		mResponseCode = code;
	}

	public int getResponseCode() {
		return mResponseCode;
	}

	public void putHeader(String header, String value) {
		mHeaders.put(header, value);
	}

	public String getHeader(String header) {
		return mHeaders.get(header);
	}

	public void setInputStream(InputStream input) throws IOException {
//		mContent = IOUtils.toByteArray(input);
		mInput = input;
	}

	public InputStream getInputStream() {
//		return new ByteArrayInputStream(mContent);
		return mInput;
	}

	public String getInputStreamString() throws IOException {
//		return new String(mContent);
		return IOUtils.toString(mInput, "utf8");
	}

	public void setContent(byte[] content) {
//		mContent = content;
		mInput = new ByteArrayInputStream(content);
	}

	public byte[] getContent() {
//		return mContent;
		try {
			return IOUtils.toByteArray(mInput);
		} catch(IOException e) {
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Code : " + mResponseCode);
		builder.append("\n");
		for (String key : mHeaders.keySet()) {
			builder.append(key + " : " + mHeaders.get(key));
			builder.append("\n");
		}
		builder.append(getContent());
		builder.append("\n");
		return builder.toString();
	}
}
