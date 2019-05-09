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

import poisondog.core.Mission;
import java.io.InputStream;
import java.io.IOException;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.net.URL;
import java.net.HttpURLConnection;
/**
 * @author Adam Huang
 */
public class HttpHead extends HttpMethod {

	@Override
	public HttpResponse execute(HttpParameter parameter) throws IOException {
		if (parameter == null)
			return null;
		parameter.withAuthentication();
		HttpURLConnection connection = (HttpURLConnection)(new URL(parameter.getUrl()).openConnection());
		connection.setRequestMethod("HEAD");

		connection.setRequestProperty("Accept-Charset", parameter.getCharset());
		setTimeout(parameter, connection);
		setHeader(parameter, connection);

		parameter.neverUseFile();
		return createResponse(connection);
	}
}
