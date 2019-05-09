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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Adam Huang
 */
public class LocalData implements IData {
	private LocalFileFactory mFactory;
	private String mUrl;
	private File mFile;

	public LocalData(LocalFileFactory factory, String url) throws URISyntaxException {
		mFactory = factory;
		mUrl = url;
		mFile = new File(new URI(url));
	}

	@Override
	public FileType getType() {
		return FileType.DATA;
	}

	@Override
	public boolean move(IFile data) throws URISyntaxException, UnsupportedEncodingException {
		if(!(data instanceof LocalData))
			throw new IllegalArgumentException("need to input LocalData");
		LocalData another = (LocalData)data;
		if(mFile.renameTo(another.getFile())) {
			mUrl = another.getUrl();
			mFile = another.getFile();
			return true;
		}
		return false;
	}

	@Override
	public boolean delete() {
		return mFile.delete();
	}

	@Override
	public String getUrl() throws UnsupportedEncodingException {
		return mFactory.decode(mUrl);
	}

	@Override
	public String getName() {
		return mFile.getName();
	}

	@Override
	public boolean isExists() {
		return mFile.exists();
	}

	@Override
	public boolean isHidden() {
		return mFile.isHidden();
	}

	@Override
	public OutputStream getOutputStream() throws FileNotFoundException {
		return new FileOutputStream(mFile);
	}

	@Override
	public InputStream getInputStream() throws FileNotFoundException {
		return new FileInputStream(mFile);
	}

	@Override
	public long getSize() {
		return mFile.length();
	}

	@Override
	public boolean create() throws IOException {
		File parent = new File(mFile.getParent());
		parent.mkdirs();
		return mFile.createNewFile();
	}

	@Override
	public long getLastModifiedTime() {
		return mFile.lastModified();
	}

	public File getFile() {
		return mFile;
	}
}
