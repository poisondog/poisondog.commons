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
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.jackrabbit.webdav.DavConstants;
import org.apache.jackrabbit.webdav.DavException;
import org.apache.jackrabbit.webdav.MultiStatusResponse;
import org.apache.jackrabbit.webdav.property.DavProperty;
import org.apache.jackrabbit.webdav.property.DavPropertySet;
import poisondog.net.URLUtils;
import poisondog.vfs.FileType;
import poisondog.vfs.IFile;
import poisondog.vfs.IFolder;

/**
 * @author Adam Huang
 */
public class WebdavFolder implements IFolder {
	private WebdavFileFactory mFactory;
	private String mUrl;
	private long mLastModifyTime;

	public WebdavFolder(WebdavFileFactory factory, String url) {
		mFactory = factory;
		mUrl = url;
	}

	@Override
	public FileType getType() {
		return FileType.FOLDER;
	}

	@Override
	public boolean move(IFile file) throws FileNotFoundException, IOException, DavException {
		if(!(file instanceof WebdavFolder))
			throw new IllegalArgumentException("need to input WebdavFolder");
		WebdavFolder another = (WebdavFolder)file;
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
	public boolean isExists() throws IOException, DavException {
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
	public List<IFile> getChildren() throws IOException, URISyntaxException, DavException {
		return mFactory.list(mUrl);
	}

	@Override
	public boolean create() throws FileNotFoundException, IOException, DavException {
		return mFactory.mkdir(mUrl);
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
}
