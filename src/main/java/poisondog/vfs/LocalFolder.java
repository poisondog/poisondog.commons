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
package poisondog.vfs;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adam Huang
 */
public class LocalFolder implements IFolder {
	private LocalFileFactory mFactory;
	private File mFolder;
	private String mUrl;

	public LocalFolder(LocalFileFactory factory, String url) throws URISyntaxException {
		mFactory = factory;
		mUrl = url;
		mFolder = new File(new URI(url));
	}

	@Override
	public FileType getType() {
		return FileType.FOLDER;
	}

	@Override
	public boolean move(IFile folder) throws URISyntaxException, UnsupportedEncodingException {
//		if(!url.endsWith("/"))
//			throw new IllegalArgumentException("need to input a url end with '/'");
		if(!(folder instanceof LocalFolder))
			throw new IllegalArgumentException("need to input LocalFolder");
		LocalFolder another = (LocalFolder)folder;
		if(mFolder.renameTo(another.getFile())) {
			mUrl = another.getUrl();
			mFolder = another.getFile();
			return true;
		}
		return false;
	}

	@Override
	public boolean delete() throws IOException, Exception {
		for (IFile data : getChildren()) {
			if(!data.delete()) {
				System.out.println(data.getUrl());
				return false;
			}
		}
		return mFolder.delete();
	}

//	private boolean deleteRecurive(IFile file) throws Exception {
//		boolean result = true;
//		if(file.getType() == FileType.DATA) {
//			result = result && file.delete();
//		} else if (file.getType() == FileType.FOLDER) {
//			LocalFolder folder = (LocalFolder)file;
//			for (IFile data : folder.getChildren()) {
//				System.out.println(data.getUrl());
//				result = result && deleteRecurive(data);
//			}
//			result = result && folder.mFolder.delete();
//		}
//		return result;
//	}

	@Override
	public boolean isExists() {
		return mFolder.exists();
	}

	@Override
	public boolean isHidden() {
		return mFolder.isHidden();
	}

	@Override
	public String getUrl() throws UnsupportedEncodingException {
		return mFactory.decode(mUrl);
	}

	@Override
	public String getName() {
		return mFolder.getName();
	}

	@Override
	public List<IFile> getChildren() throws IOException, URISyntaxException {
		File[] children = mFolder.listFiles();
		if (children == null) {
			return new ArrayList<IFile>();
		}
		List<IFile> result = new ArrayList<IFile>(children.length);
		for (File file : children) {
			result.add(mFactory.getFile(mFactory.decode(file.toURI().toString())));
		}
		return result;
	}

	@Override
	public boolean create() {
		return mFolder.mkdirs();
	}

	@Override
	public long getLastModifiedTime() {
		return mFolder.lastModified();
	}

	public File getFile() {
		return mFolder;
	}
}
