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

import java.io.IOException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import poisondog.core.Mission;
import poisondog.net.http.HttpParameter;
import poisondog.net.http.HttpResponse;
/**
 * @author Adam Huang
 */
public class ApacheHttpPost implements Mission<HttpParameter> {
	private HttpClient mHttpClient;
	private MultipartEntityBuilder mBuilder;

	public ApacheHttpPost() {
		mHttpClient = HttpClientBuilder.create().build();
		mBuilder = MultipartEntityBuilder.create();
	}

	public HttpResponse execute(HttpParameter parameter) throws IOException {
		for (String key : parameter.textKeys()) {
			mBuilder.addTextBody(key, parameter.getText(key));
		}
		for (String key : parameter.fileKeys()) {
			mBuilder.addPart(key, new FileBody(parameter.getFile(key)));
		}
		return post(parameter);
	}

	public HttpResponse post(HttpParameter parameter) throws IOException {
		HttpPost method = new HttpPost(parameter.getUrl());
		for (String key : parameter.headerKeys()) {
			method.addHeader(key, parameter.getHeader(key));
		}
		method.setEntity(mBuilder.build());
		org.apache.http.HttpResponse response = mHttpClient.execute(method);

		HttpResponse res = new HttpResponse(response.getStatusLine().getStatusCode());
		for (int i = 0; i < response.getAllHeaders().length; i++) {
			res.putHeader(response.getAllHeaders()[i].getName(), response.getAllHeaders()[i].getValue());
		}
		res.setInputStream(response.getEntity().getContent());
		return res;
	}
}
