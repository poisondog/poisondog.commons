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
package poisondog.vfs.http;

import java.io.IOException;
import java.util.List;
import poisondog.net.URLUtils;
import poisondog.vfs.FileType;
import poisondog.vfs.IFile;
import poisondog.vfs.IFolder;

/**
 * @author Adam Huang
 * @since 2017-01-10
 */
public class HttpFolder implements IFolder{
	private HttpFileFactory mFactory;
	private String mUrl;
	private long mLastModifyTime;

	/**
	 * Constructor
	 */
	public HttpFolder(HttpFileFactory factory, String url) {
		mFactory = factory;
		mUrl = url;
	}

	@Override
	public FileType getType() {
		return FileType.FOLDER;
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
	public boolean isExists() throws IOException {
		return mFactory.exists(mUrl);
	}

	@Override
	public boolean isHidden() {
		return mFactory.isHidden(mUrl);
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
	public List<IFile> getChildren() throws IOException {
		return mFactory.list(mUrl);
	}

	@Override
	public boolean create() {
		throw new IllegalArgumentException("not support this method");
	}

	@Override
	public long getLastModifiedTime() throws IOException {
		if (mLastModifyTime <= 0)
			mLastModifyTime = mFactory.getLastModifiedTime(mUrl);
		return mLastModifyTime;
	}
}
