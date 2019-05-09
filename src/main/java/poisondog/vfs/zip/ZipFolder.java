/*
 * Copyright (C) 2018 Adam Huang <poisondog@gmail.com>
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

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import poisondog.net.URLUtils;
import poisondog.vfs.FileType;
import poisondog.vfs.IFile;
import poisondog.vfs.IFolder;

/**
 * @author Adam Huang
 * @since 2018-05-17
 */
public class ZipFolder implements IFolder {
	private ZipFileFactory mFactory;
	private String mUrl;
	private FileObject mObject;

	/**
	 * Constructor
	 */
	public ZipFolder(ZipFileFactory factory, String url, FileObject object) {
		mFactory = factory;
		mUrl = url;
		mObject = object;
	}

	@Override
	public FileType getType() {
		return FileType.FOLDER;
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
	public boolean isExists() throws FileSystemException {
		return mObject.exists();
	}

	@Override
	public boolean isHidden() throws FileSystemException {
		return mObject.isHidden();
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
	public List<IFile> getChildren() throws FileSystemException {
		ArrayList<IFile> result = new ArrayList<IFile>();
		FileObject[] children = mObject.getChildren();
		for (FileObject obj : children) {
			result.add(mFactory.getFile(obj.getURL().toString()));
		}
		return result;
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
