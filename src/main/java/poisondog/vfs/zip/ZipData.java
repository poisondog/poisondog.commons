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
package poisondog.vfs.zip;

import java.io.InputStream;
import java.io.OutputStream;
import poisondog.vfs.FileType;
import poisondog.vfs.IData;
import poisondog.vfs.IFile;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import poisondog.net.URLUtils;

/**
 * @author Adam Huang
 * @since 2016-10-17
 */
public class ZipData implements IData {
	private String mUrl;
	private FileObject mObject;

	/**
	 * Constructor
	 */
	public ZipData(String url, FileObject object) {
		mUrl = url;
		mObject = object;
	}

	@Override
	public FileType getType() {
		return FileType.DATA;
	}

	@Override
	public boolean move(IFile data) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete() {
		throw new UnsupportedOperationException();
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
	public boolean isExists() throws FileSystemException {
		return mObject.exists();
	}

	@Override
	public boolean isHidden() throws FileSystemException {
		return mObject.isHidden();
	}

	@Override
	public OutputStream getOutputStream() {
		throw new UnsupportedOperationException();
	}

	@Override
	public InputStream getInputStream() throws FileSystemException {
		return mObject.getContent().getInputStream();
	}

	@Override
	public long getSize() throws FileSystemException {
		return mObject.getContent().getSize();
	}

	@Override
	public boolean create() {
		throw new UnsupportedOperationException();
	}

	@Override
	public long getLastModifiedTime() throws FileSystemException {
		return mObject.getContent().getLastModifiedTime();
	}
}
