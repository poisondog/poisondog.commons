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
package poisondog.net.http;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import poisondog.io.CopyTask;
import poisondog.log.Log;
import poisondog.net.Entity;
/**
 * @author Adam Huang
 */
public class HttpPut extends HttpMethod {

	@Override
	public HttpResponse execute(HttpParameter parameter) throws IOException {
		parameter.withAuthentication();
		HttpURLConnection connection = (HttpURLConnection)(new URL(parameter.getUrl()).openConnection());
		connection.setRequestMethod("PUT");

		Log.v("Request Method: PUT");
		Log.v("Connect Url: " + parameter.getUrl());

		connection.setDoOutput(true);

		connection.setRequestProperty("Accept-Charset", parameter.getCharset());
		setTimeout(parameter, connection);
		setHeader(parameter, connection);

		Entity entity = parameter.createMultiEntity();
		connection.setRequestProperty("Content-Type", entity.getContentType());
//		IOUtils.write(entity.getContent(), connection.getOutputStream());

		CopyTask task = new CopyTask(new ByteArrayInputStream(entity.getContent()), connection.getOutputStream());
		if (parameter.getStepListener() != null)
			task.addStepListener(parameter.getStepListener());
		task.transport();

		return createResponse(connection);
	}
}
