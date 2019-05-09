/*
 * Copyright (C) 2014 Adam Huang
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
package poisondog.net;

import java.io.UnsupportedEncodingException;
import java.net.URLConnection;
import java.util.Map;
import poisondog.string.ExtractFileName;
import poisondog.string.ExtractHost;
import poisondog.string.ExtractParent;
import poisondog.string.ExtractParentUrl;
import poisondog.string.ExtractPath;
import poisondog.string.ExtractPort;
import poisondog.string.ExtractScheme;
import poisondog.string.URLDecode;
import poisondog.string.URLEncode;
import poisondog.vfs.GuessContentType;
import poisondog.string.GetFileName;
import poisondog.string.GetPath;

/**
 * @author Adam Huang
 */
public class UrlUtils {
	
	public static String encode(String input) {
		try{
			URLEncode task = new URLEncode("utf-8");
			return task.process(input);
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return null;
	}

	public static String decode(String input) {
		try{
			URLDecode task = new URLDecode("utf-8");
			return task.process(input);
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return null;
	}

	public static String scheme(String url) {
		return new ExtractScheme().process(url);
	}

	public static String host(String url) {
		return new ExtractHost().process(url);
	}

	public static int port(String url) {
		try{
			return Integer.parseInt(new ExtractPort().process(url));
		}catch(Exception e) {
		}
		return -1;
	}

	public static String path(String url) {
		return new GetPath().execute(url);
	}

	public static String parent(String url) {
		return new ExtractParent().process(url);
	}

	public static String parentUrl(String url) {
		return new ExtractParentUrl().process(url);
	}

	public static String filename(String url) {
		return new GetFileName().execute(url);
	}

	public static String httpQuery(Map<String, String> map) {
		StringBuilder builder = new StringBuilder();
		builder.append("?");
		for (String key : map.keySet()) {
			builder.append(key);
			builder.append("=");
			builder.append(encode(map.get(key)));
			builder.append("&");
		}
		return builder.substring(0, builder.length()-1);
	}

	public static String long2IP(long proper_address) {
		StringBuilder builder = new StringBuilder();
		if (proper_address >= 0 ) {
			builder.append((int)(Math.floor(proper_address / Math.pow(256, 3))));
			builder.append(".");
			builder.append((int)(Math.floor((proper_address % Math.pow(256, 3)) / Math.pow(256, 2))));
			builder.append(".");
			builder.append((int)(Math.floor(((proper_address % Math.pow(256, 3)) % Math.pow(256, 2)) / Math.pow(256, 1))));
			builder.append(".");
			builder.append((int)(Math.floor((((proper_address % Math.pow(256, 3)) % Math.pow(256, 2)) % Math.pow(256, 1)) / Math.pow(256, 0))));
		}
		return builder.toString();
	}

	public static String guessContentType(String name) {
		GuessContentType task = new GuessContentType();
		return task.execute(name);
	}
}
