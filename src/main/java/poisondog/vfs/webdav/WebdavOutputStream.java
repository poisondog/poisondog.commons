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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.vfs2.util.MonitorOutputStream;
import org.apache.jackrabbit.webdav.DavException;

/**
 * @author Adam Huang
 */
public class WebdavOutputStream extends MonitorOutputStream {
	private WebdavFileFactory mFactory;
	private File mTemp;
	private String mUrl;

	public WebdavOutputStream(WebdavFileFactory factory, File temp, String url) throws IOException {
		super(new FileOutputStream(temp));
		mFactory = factory;
		mTemp = temp;
		mUrl = url;
	}

	/**
	 * Called after the stream has been closed.
	 */
	@Override
	protected void onClose() throws IOException {
		try{
			mFactory.put(mTemp, mUrl);
		}catch(DavException e) {
			throw new IOException(e.toString());
		}
	}
}
