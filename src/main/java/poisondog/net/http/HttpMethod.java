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
import poisondog.core.Mission;
import poisondog.log.Log;
/**
 * @author Adam Huang
 */
public abstract class HttpMethod implements Mission<HttpParameter> {

	protected void setTimeout(HttpParameter parameter, HttpURLConnection connection) {
		if(parameter.getTimeout() > 0) {
			connection.setConnectTimeout(parameter.getTimeout());
			connection.setReadTimeout(parameter.getTimeout());
			Log.v("timeout: " + parameter.getTimeout());
		}
	}

	protected void setHeader(HttpParameter parameter, HttpURLConnection connection) {
		for (String key : parameter.headerKeys()) {
			connection.setRequestProperty(key, parameter.getHeader(key));
			Log.v("request property key: " + key);
			Log.v("request property value: " + parameter.getHeader(key));
		}
	}

	protected HttpResponse createResponse(HttpURLConnection connection) throws IOException {
		Log.v("createResponse input connection: " + connection);
		HttpResponse response = new HttpResponse(connection.getResponseCode());
		for (String key : connection.getHeaderFields().keySet()) {
			for (String line : connection.getHeaderFields().get(key)) {
				response.putHeader(key, line);
			}
		}
		response.setInputStream(connection.getInputStream());
		return response;
	}
}
