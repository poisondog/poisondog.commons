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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import org.apache.commons.io.IOUtils;
/**
 * @author Adam Huang
 */
public class FileEntity implements Entity {
	private String mName;
	private File mFile;

	public FileEntity(String name, File file) {
		mName = name;
		mFile = file;
	}

	@Override
	public String getContentType() {
		return URLConnection.guessContentTypeFromName(mFile.getName());
	}

	@Override
	public String getContentDisposition() {
		StringBuilder result = new StringBuilder();
		result.append("form-data; ");
		result.append("name=\"");
		result.append(mName);
		result.append("\"; ");
		result.append("filename=\"");
		result.append(mFile.getName());
		result.append("\"");
		return result.toString();
	}

	@Override
	public String getContentTransferEncoding() {
		return "binary";
	}

	@Override
	public byte[] getContent() {
		try {
			return IOUtils.toByteArray(new FileInputStream(mFile));
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
