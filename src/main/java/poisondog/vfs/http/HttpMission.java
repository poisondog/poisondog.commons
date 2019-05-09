/*
 * Copyright (C) 2014 Adam Huang <poisondog@gmail.com>
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
package poisondog.vfs.http;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import poisondog.commons.httpclient.HttpClientSwitch;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 */
public class HttpMission implements Mission<HttpMethod> {
	private HttpClientSwitch mClient;
	private String mUsername;
	private String mPassword;

	/**
	 * Constructor
	 */
	public HttpMission() {
		this(new HttpClient());
	}

	public HttpMission(HttpClient client) {
		mClient = new HttpClientSwitch(client);
		mUsername = "";
	}

	public void setHttpClient(HttpClient client) {
		mClient.setHttpClient(client);
	}

	public void setUsername(String username) {
		mUsername = username;
	}

	public void setPassword(String password) {
		mPassword = password;
	}

	private HttpClient create(String username, String password) {
		HttpClient client = new HttpClient();
		withAuthentication(client, username, password);
		return client;
	}

	private void withAuthentication(HttpClient client, String username, String password) {
		client.getParams().setAuthenticationPreemptive(true);
		client.getState().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
	}

	@Override
	public Integer execute(HttpMethod method) throws FileNotFoundException, IOException {
		if(!mUsername.isEmpty()) {
			mClient.setHttpClient(create(mUsername, mPassword));
		}
		mClient.getHttpConnectionManager().getParams().setSoTimeout(20000);

		int status = mClient.executeMethod(method);
		// TODO 這判斷於此是否適合？
		if (status == HttpURLConnection.HTTP_NOT_FOUND || status == HttpURLConnection.HTTP_GONE) {
			throw new FileNotFoundException();
		}
		return status;
	}

	public String getUsername() {
		return mUsername;
	}

	public String getPassword() {
		return mPassword;
	}
}
