/*
 * Copyright (C) 2014 Adam Huang <poisondog@gmail.com>
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

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import org.apache.jackrabbit.webdav.DavException;
import poisondog.net.URLUtils;
import poisondog.vfs.FileType;
import poisondog.vfs.IData;
import poisondog.vfs.IFile;
import poisondog.vfs.LocalData;
import poisondog.vfs.LocalFileFactory;
import poisondog.vfs.LocalFolder;

/**
 * @author Adam Huang
 */
public class WebdavData implements IData {
	private LocalFileFactory mLocal;
	private WebdavFileFactory mFactory;
	private String mUrl;
	private String mTempFolder;
	private long mContentSize;
	private long mLastModifyTime;

	public WebdavData(WebdavFileFactory factory, String url) {
		mLocal = new LocalFileFactory();
		mFactory = factory;
		mUrl = url;
		mTempFolder = mFactory.getTempFolder() + System.currentTimeMillis() + "/";
	}

	@Override
	public FileType getType() {
		return FileType.DATA;
	}

	@Override
	public boolean move(IFile file) throws FileNotFoundException, IOException, DavException {
		if(!(file instanceof WebdavData))
			throw new IllegalArgumentException("need to input WebdavData");
		WebdavData another = (WebdavData)file;
		boolean result = mFactory.move(mUrl, another.getUrl(), true);
		if(result)
			mUrl = another.getUrl();
		return result;
	}

	@Override
	public boolean delete() throws FileNotFoundException, IOException, DavException {
		return mFactory.delete(mUrl);
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
	public boolean isExists() throws IOException, DavException {
		return mFactory.exists(mUrl);
	}

	@Override
	public boolean isHidden() {
		return mFactory.isHidden(mUrl);
	}

	private void createTempFolder() throws IOException, URISyntaxException {
		LocalFolder folder = (LocalFolder)mLocal.getFile(mTempFolder);
		folder.create();
	}

	@Override
	public boolean create() throws FileNotFoundException, IOException, DavException, URISyntaxException {
		createTempFolder();
		LocalData data = (LocalData) mLocal.getFile(mTempFolder + getName());
		data.create();
		return mFactory.put(data.getFile(), mUrl);
	}

	@Override
	public OutputStream getOutputStream() throws FileNotFoundException, IOException, DavException, URISyntaxException {
		createTempFolder();
		LocalData data = (LocalData) mLocal.getFile(mTempFolder + getName());
		data.create();
		return new WebdavOutputStream(mFactory, data.getFile(), mUrl);
	}

	@Override
	public InputStream getInputStream() throws FileNotFoundException, IOException, DavException {
		return mFactory.get(mUrl);
	}

	@Override
	public long getSize() throws FileNotFoundException, IOException, DavException {
		if (mContentSize <= 0)
			mContentSize = mFactory.getContentSize(mUrl);
		return mContentSize;
	}

	@Override
	public long getLastModifiedTime() throws FileNotFoundException, IOException, DavException {
		if (mLastModifyTime <= 0)
			mLastModifyTime = mFactory.getLastModifiedTime(mUrl);
		return mLastModifyTime;
	}

	public void setLastModifiedTime(long time) {
		mLastModifyTime = time;
	}

	public void setSize(long size) {
		mContentSize = size;
	}
}
