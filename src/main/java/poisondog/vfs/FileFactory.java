/*
 * Copyright (C) 2015 Adam Huang <poisondog@gmail.com>
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

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.httpclient.HttpClient;
import poisondog.net.URLUtils;
import poisondog.vfs.http.HttpFileFactory;
import poisondog.vfs.http.HttpMission;
import poisondog.vfs.webdav.WebdavFileFactory;
import poisondog.vfs.zip.ZipFileFactory;

/**
 * @author Adam Huang
 */
public class FileFactory {
	private static Map<String, IFileFactory> sMap;
	private static LocalFileFactory sLocalFileFactory;
	private static HttpFileFactory sHttpFileFactory;
	private static WebdavFileFactory sWebdavFileFactory;
	private static ZipFileFactory sZipFileFactory;

	static {
		sMap = new HashMap<String, IFileFactory>();
		sLocalFileFactory = new LocalFileFactory();
		sHttpFileFactory = new HttpFileFactory();
		sZipFileFactory = new ZipFileFactory();
		sWebdavFileFactory = new WebdavFileFactory(new HttpMission(new HttpClient()));
		sMap.put("file", sLocalFileFactory);
		sMap.put("http", sHttpFileFactory);
		sMap.put("https", sHttpFileFactory);
		sMap.put("webdav", sWebdavFileFactory);
		sMap.put("zip", sZipFileFactory);
		sMap.put("jar", sZipFileFactory);
	}

	public static IFile getFile(String url) throws Exception {
		IFileFactory factory = sMap.get(URLUtils.scheme(url));
		for (String key : sMap.keySet()) {
			if (URLUtils.scheme(url).startsWith(key + ":")) {
				factory = sMap.get(key);
			}
		}
		if (factory != null)
			return factory.getFile(url);
		return null;
	}

	public static void setHttpMission(HttpMission mission) {
		sWebdavFileFactory.setHttpMission(mission);
	}

	public static void setUsername(String username) {
		sHttpFileFactory.setUsername(username);
	}

	public static void setPassword(String password) {
		sHttpFileFactory.setPassword(password);
	}

	public static void add(String scheme, IFileFactory factory) {
		sMap.put(scheme, factory);
	}
}
