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
import poisondog.net.URLUtils;
import poisondog.string.URLDecodePath;
import poisondog.string.URLEncodePath;

/**
 * @author Adam Huang
 */
public class LocalFileFactory implements IFileFactory {
	private URLEncodePath mEncode;
	private URLDecodePath mDecode;

	public LocalFileFactory() {
		mEncode = new URLEncodePath("utf8");
		mDecode = new URLDecodePath("utf8");
	}

	@Override
	public IFile getFile(String url) throws IOException, URISyntaxException {
		if(!URLUtils.scheme(url).equals("file"))
			throw new IllegalArgumentException("the url isn't used file scheme.");
		StringBuilder builder = new StringBuilder();
		builder.append(URLUtils.scheme(url));
		builder.append(":");
		builder.append(mEncode.process(URLUtils.path(url)));

		if(url.endsWith("/")) {
			return new LocalFolder(this, builder.toString());
		} else {
			return new LocalData(this, builder.toString());
		}
	}

	public String decode(String url) throws UnsupportedEncodingException {
		return mDecode.process(url);
	}
}
