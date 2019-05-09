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
import poisondog.log.Log;
/**
 * @author Adam Huang
 */
public class HttpDelete extends HttpMethod {

	@Override
	public HttpResponse execute(HttpParameter parameter) throws IOException {
		parameter.withAuthentication();
		String url = parameter.getUrl() + parameter.httpQuery();
		HttpURLConnection connection = (HttpURLConnection)(new URL(url).openConnection());
		connection.setRequestMethod("DELETE");

		Log.v("Request Method: DELETE");
		Log.v("Connect Url: " + url);

		connection.setRequestProperty("Accept-Charset", parameter.getCharset());
		setTimeout(parameter, connection);
		setHeader(parameter, connection);

		parameter.neverUseFile();
		return createResponse(connection);
	}
}
