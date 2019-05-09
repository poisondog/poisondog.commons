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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import poisondog.log.Log;
import poisondog.net.Entity;
/**
 * @author Adam Huang
 */
public class HttpPost extends HttpMethod {

	@Override
	public HttpResponse execute(HttpParameter parameter) throws IOException {
		parameter.withAuthentication();
		HttpURLConnection connection = (HttpURLConnection)(new URL(parameter.getUrl()).openConnection());
		connection.setRequestMethod("POST");

		Log.v("Request Method: POST");
		Log.v("Connect Url: " + parameter.getUrl());

		connection.setDoOutput(true);
		connection.setRequestProperty("Accept-Charset", parameter.getCharset());

		setTimeout(parameter, connection);
		setHeader(parameter, connection);

		if (parameter.getData() != null) {
			IOUtils.write(parameter.getData().getContent(), connection.getOutputStream());
		} else {
			Entity entity = parameter.createMultiEntity();
			connection.setRequestProperty("Content-Type", entity.getContentType());
			IOUtils.write(entity.getContent(), connection.getOutputStream());
		}

		return createResponse(connection);
	}
}
