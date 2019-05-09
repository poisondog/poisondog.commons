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

import java.io.File;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import poisondog.io.StepListener;
import poisondog.log.Log;
import poisondog.log.Logger;
import poisondog.log.NoLogger;
import poisondog.net.Entity;
import poisondog.net.FileEntity;
import poisondog.net.MultipartEntity;
import poisondog.net.NameValueEntity;
import poisondog.net.TextEntity;
import poisondog.net.URLUtils;
import poisondog.core.Data;

/**
 * @author Adam Huang
 */
public class HttpParameter {
	private String mCharset;
	private String mUrl;
	private String mUsername;
	private String mPassword;
	private String mRequestMethod;
	private int mTimeout;
	private Map<String, String> mHeaderMap;
	private Map<String, String> mTextMap;
	private Map<String, File> mFileMap;
	private StepListener mListener;
	private Data mData;

	public HttpParameter() {
		mCharset = "utf8";
		mTextMap = new HashMap<String, String>();
		mHeaderMap = new HashMap<String, String>();
		mFileMap = new HashMap<String, File>();
		mUrl = "";
		mRequestMethod = "GET";
	}

	public void addHeader(String key, String value) {
		mHeaderMap.put(key, value);
	}

	public String getHeader(String key) {
		return mHeaderMap.get(key);
	}

	public Set<String> headerKeys() {
		return mHeaderMap.keySet();
	}

	public void addText(String key, String value) {
		mTextMap.put(key, value);
	}

	public String getText(String key) {
		return mTextMap.get(key);
	}

	public Set<String> textKeys() {
		return mTextMap.keySet();
	}

	public String httpQuery() {
		return URLUtils.httpQuery(mTextMap);
	}

	public void addFile(String key, File value) {
		mFileMap.put(key, value);
	}

	public File getFile(String key) {
		return mFileMap.get(key);
	}

	public Set<String> fileKeys() {
		return mFileMap.keySet();
	}

	public void setUrl(String url) {
		mUrl = url;
	}

	public String getUrl() {
		return mUrl;
	}

	public void setTimeout(int timeout) {
		mTimeout = timeout;
	}

	public int getTimeout() {
		return mTimeout;
	}

	public void setUsername(String username) {
		mUsername = username;
	}

	public String getUsername() {
		return mUsername;
	}

	public void setPassword(String password) {
		mPassword = password;
	}

	public String getPassword() {
		return mPassword;
	}

	public void setData(Data data) {
		mData = data;
	}

	public Data getData() {
		return mData;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof HttpParameter))
			return false;
		HttpParameter another = (HttpParameter) obj;
		if(!mUrl.equals(another.getUrl()))
			return false;
		for (String key : textKeys()) {
			String value = another.getText(key);
			if(value == null)
				return false;
			if(!value.equals(getText(key)))
				return false;
		}
		return true;
	}

	public void setStepListener(StepListener listener) {
		mListener = listener;
	}

	public StepListener getStepListener() {
		return mListener;
	}

	public void setRequestMethod(String method) {
		mRequestMethod = method;
	}

	public String getRequestMethod() {
		return mRequestMethod;
	}

	public void neverUseFile() {
		if(!fileKeys().isEmpty()) {
			throw new IllegalArgumentException("this parameter has file, but this method never send files");
		}
	}

	public void withAuthentication() {
		if(mUsername != null && !mUsername.isEmpty()) {
			Authenticator.setDefault (new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(mUsername, mPassword.toCharArray());
				}
			});
			Log.v("with authentication");
		}
	}

	public void setCharset(String charset) {
		mCharset = charset;
	}

	public String getCharset() {
		return mCharset;
	}

	private NameValueEntity createTextEntity() {
		NameValueEntity textEntity = new NameValueEntity(mCharset);
		for (String key : textKeys()) {
			textEntity.addTextBody(key, getText(key));
		}
		return textEntity;
	}

	public Entity createMultiEntity() {
		if(!textKeys().isEmpty() && fileKeys().isEmpty()) {
			NameValueEntity textEntity = new NameValueEntity(mCharset);
			for (String key : textKeys()) {
				textEntity.addTextBody(key, getText(key));
			}
			return textEntity;
		}

		if (textKeys().isEmpty() && fileKeys().size() == 1) {
			for (String key : fileKeys()) {
				return new FileEntity(key, getFile(key));
			}
		}

		if(!fileKeys().isEmpty()) {
			MultipartEntity multiEntity = new MultipartEntity();
			for (String key : textKeys()) {
				multiEntity.addEntity(new TextEntity(key, getText(key), mCharset));
			}
			for (String key : fileKeys()) {
				multiEntity.addEntity(new FileEntity(key, getFile(key)));
			}
			return multiEntity;
		}
		return null;
	}
}
