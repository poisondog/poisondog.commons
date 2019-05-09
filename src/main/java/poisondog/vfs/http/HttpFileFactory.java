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
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import poisondog.net.http.HttpGet;
import poisondog.net.http.HttpHead;
import poisondog.net.http.HttpParameter;
import poisondog.net.URLUtils;
import poisondog.string.ReplacePath;
import poisondog.vfs.IFile;
import poisondog.vfs.IFileFactory;

/**
 * @author Adam Huang
 * @since 2016-12-13
 */
public class HttpFileFactory implements IFileFactory {
	private HttpParameter mParameter;
	private HttpGet mGet;
	private HttpHead mHead;

	/**
	 * Constructor
	 */
	public HttpFileFactory() {
		this(null, null);
	}

	/**
	 * Constructor
	 */
	public HttpFileFactory(String username, String password) {
		mGet = new HttpGet();
		mHead = new HttpHead();
		mParameter = new HttpParameter();
		mParameter.setUsername(username);
		mParameter.setPassword(password);
	}

	public void setUsername(String username) {
		mParameter.setUsername(username);
	}

	public void setPassword(String password) {
		mParameter.setPassword(password);
	}

	@Override
	public IFile getFile(String url) {
		if (url.endsWith("/"))
			return new HttpFolder(this, url);
		else
			return new HttpData(this, url);
	}

	public void setGet(HttpGet get) {
		mGet = get;
	}

	public void setHead(HttpHead head) {
		mHead = head;
	}

	public boolean exists(String url) throws IOException {
		mParameter.setUrl(url);
		try {
			mHead.execute(mParameter);
		} catch(FileNotFoundException e) {
			return false;
		}
		return true;
	}

	public boolean isHidden(String url) {
		if(URLUtils.file(url).startsWith("."))
			return true;
		return false;
	}

	public InputStream get(String url) throws IOException {
		mParameter.setUrl(url);
		return mGet.execute(mParameter).getInputStream();
	}

	public long getContentSize(String url) throws IOException {
		mParameter.setUrl(url);
		return Long.parseLong(mHead.execute(mParameter).getHeader("Content-Length"));
	}

	public long getLastModifiedTime(String url) throws IOException {
		mParameter.setUrl(url);
		String last = mHead.execute(mParameter).getHeader("Last-Modified");
		if (last == null)
			return 0l;
		return Long.parseLong(last);
	}

	public List<IFile> list(String url) throws IOException {
		Document doc = Jsoup.parse(IOUtils.toString(get(url), "utf8"));
		Elements newsHeadlines = doc.select("a");
		List<IFile> result = new ArrayList<IFile>();
		for (Element e : newsHeadlines) {
			String path = URLUtils.path(e.attr("href"));
			if (path.startsWith("/")) {
				ReplacePath task = new ReplacePath(path);
				result.add(getFile(task.process(url)));
			} else {
				result.add(getFile(url + path));
			}
		}
		return result;
	}

}
