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
package poisondog.vfs;

import java.net.URLConnection;
import java.nio.file.Files;
import poisondog.core.Mission;
import poisondog.string.GetFileName;

/**
 * @author Adam Huang
 * @since 2018-09-25
 */
public class GuessContentType implements Mission<String> {

	@Override
	public String execute(String input) {
		GetFileName task = new GetFileName();
		String filename = task.execute(input).toLowerCase();
		if(filename.endsWith(".bmp")) return "image/bmp";
		if(filename.endsWith(".mka")) return "audio/x-matroska";
		if(filename.endsWith(".avi")) return "video/avi";
		if(filename.endsWith(".3gpp")) return "video/3gpp";

		String result = guessWithURLConnection(input);
//		try {
//			result = Files.probeContentType(FileSystems.getDefault().getPath(filename));
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		return result == null ? "application/octet-stream" : result;
	}

	private String guessWithURLConnection(String input) {
		if (input.isEmpty()) return "inode/directory";
		GetFileName task = new GetFileName();
		String filename = task.execute(input).toLowerCase();
		filename = filename.replaceAll(".*\\.", "abc123\\.");
		if(filename.endsWith(".mp3")) return "audio/mpeg";
		if(filename.endsWith(".wma")) return "audio/x-ms-wma";
		if(filename.endsWith(".mka")) return "audio/x-matroska";
		if(filename.endsWith(".flac")) return "audio/flac";
		if(filename.endsWith(".avi")) return "video/avi";
		if(filename.endsWith(".mp4")) return "video/mp4";
		if(filename.endsWith(".wmv")) return "video/x-ms-wmv";
		if(filename.endsWith(".3gp")) return "video/3gpp";
		if(filename.endsWith(".3gpp")) return "video/3gpp";
		if(filename.endsWith(".flv")) return "video/x-flv";
		if(filename.endsWith(".mkv")) return "video/x-matroska";
		if(filename.endsWith(".rar")) return "application/rar";
		if(filename.endsWith(".7z")) return "application/x-7z-compressed";
		if(filename.endsWith(".ppt")) return "application/vnd.ms-powerpoint";
		if(filename.endsWith(".pptx")) return "application/vnd.openxmlformats-officedocument.presentationml.presentation";
		if(filename.endsWith(".pps")) return "application/vnd.ms-powerpoint";
		if(filename.endsWith(".doc")) return "application/msword";
		if(filename.endsWith(".docx")) return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		if(filename.endsWith(".xls")) return "application/vnd.ms-excel";
		if(filename.endsWith(".xlsx")) return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
		return URLConnection.guessContentTypeFromName(filename);
	}
}
