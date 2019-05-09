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
package poisondog.net;

import java.util.Map;
import java.util.HashMap;
import org.apache.commons.io.IOUtils;
/**
 * @author Adam Huang
 */
public class NameValueEntity implements Entity {
	private String mCharset;
	private Map<String, String> mTexts;
	private String mValue;

	public NameValueEntity(String charset) {
		mCharset = charset;
		mTexts = new HashMap<String, String>();
	}

	public NameValueEntity() {
		this("utf8");
	}

	public void addTextBody(String key, String value) {
		mTexts.put(key, value);
	}

	@Override
	public String getContentType() {
		return "application/x-www-form-urlencoded; charset=" + mCharset;
	}

	@Override
	public String getContentDisposition() {
		return null;
	}

	@Override
	public String getContentTransferEncoding() {
		return null;
	}

	@Override
	public byte[] getContent() {
		StringBuilder builder = new StringBuilder();
		for (String key : mTexts.keySet()) {
			builder.append(key);
			builder.append("=");
			builder.append(URLUtils.encode(mTexts.get(key)));
			builder.append("&");
		}
		return builder.toString().substring(0, builder.toString().length()-1).getBytes();
	}
}
