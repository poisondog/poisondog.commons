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
package poisondog.net;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import poisondog.core.Mission;
import poisondog.io.CopyTask;
import poisondog.net.http.HttpRequestMessage;
import poisondog.net.http.HttpParameter;

/**
 * @author Adam Huang
 */
public class UploadMission implements Mission<HttpParameter> {

	@Override
	public Boolean execute(HttpParameter parameter) throws UnknownHostException, IOException {
		String host = URLUtils.host(parameter.getUrl());
		int port = URLUtils.port(parameter.getUrl());
		String path = URLUtils.path(parameter.getUrl());

		Entity entity = getFileEntity(parameter);

		HttpRequestMessage message = new HttpRequestMessage("PUT", path, host);
		message.setAuthorization(parameter.getUsername(), parameter.getPassword());
		message.setHeader("Content-Type", entity.getContentType());
		message.setHeader("Content-Length", Long.toString(entity.getContent().length));
//		message.setHeader("Accept-Charset", "UTF-8");
//		message.setHeader("Connection", "Keep-Alive");
//		message.setHeader("Cache-Control", "no-cache");
		message.setBody(entity.getContent());

		Socket t = new Socket(InetAddress.getByName(host), port);
		CopyTask task = new CopyTask(new ByteArrayInputStream(message.getContent()), t.getOutputStream());
		task.addStepListener(parameter.getStepListener());
		task.transport();
		return task.isComplete();
	}

	private Entity getFileEntity(HttpParameter parameter) {
		if (parameter.fileKeys().size() != 1)
			throw new IllegalArgumentException("this mission not support multi file.");
		for (String key : parameter.fileKeys()) {
			return new FileEntity(key, parameter.getFile(key));
		}
		return null;
	}
}
