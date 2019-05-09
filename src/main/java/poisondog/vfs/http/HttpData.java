/*
 * Copyright (C) 2016 Adam Huang <poisondog@gmail.com>
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
package poisondog.vfs.http;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import poisondog.net.URLUtils;
import poisondog.vfs.FileType;
import poisondog.vfs.IData;
import poisondog.vfs.IFile;

/**
 * @author Adam Huang
 * @since 2016-12-13
 */
public class HttpData implements IData {
	private HttpFileFactory mFactory;
	private String mUrl;
	private long mContentSize;
	private long mLastModifyTime;

	/**
	 * Constructor
	 */
	public HttpData(HttpFileFactory factory, String url) {
		mFactory = factory;
		mUrl = url;
	}

	@Override
	public FileType getType() {
		return FileType.DATA;
	}

	@Override
	public boolean move(IFile file) {
		throw new IllegalArgumentException("not support this method");
	}

	@Override
	public boolean delete() {
		throw new IllegalArgumentException("not support this method");
	}

	@Override
	public String getUrl() {
		return mUrl;
	}

	@Override
	public String getName() {
		return URLUtils.file(mUrl);
	}

	@Override
	public boolean isExists() throws IOException {
		return mFactory.exists(mUrl);
	}

	@Override
	public boolean isHidden() {
		return mFactory.isHidden(mUrl);
	}

	@Override
	public boolean create() {
		throw new IllegalArgumentException("not support this method");
	}

	@Override
	public OutputStream getOutputStream() {
		throw new IllegalArgumentException("not support this method");
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return mFactory.get(mUrl);
	}

	@Override
	public long getSize() throws FileNotFoundException, IOException {
		if (mContentSize <= 0)
			mContentSize = mFactory.getContentSize(mUrl);
		return mContentSize;
	}

	@Override
	public long getLastModifiedTime() throws FileNotFoundException, IOException {
		if (mLastModifyTime <= 0)
			mLastModifyTime = mFactory.getLastModifiedTime(mUrl);
		return mLastModifyTime;
	}
}
