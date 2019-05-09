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
package poisondog.commons.httpclient;

import java.io.IOException;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.params.HttpClientParams;
/**
 * @author Adam Huang
 */
public class HttpClientSwitch extends HttpClient {
	private HttpClient mClient;

	public HttpClientSwitch(HttpClient client) {
		setHttpClient(client);
	}

	public void setHttpClient(HttpClient client) {
		mClient = client;
	}

	@Override
	public int executeMethod(HttpMethod method) throws IOException {
		return mClient.executeMethod(method);
	}

	@Override
	public int executeMethod(HostConfiguration configuration, HttpMethod method) throws IOException {
		return mClient.executeMethod(configuration, method);
	}

	@Override
	public int executeMethod(HostConfiguration configuration, HttpMethod method, HttpState state) throws IOException {
		return mClient.executeMethod(configuration, method, state);
	}

	@Override
	public HostConfiguration getHostConfiguration() {
		return mClient.getHostConfiguration();
	}

	@Override
	public HttpClientParams getParams() {
		return mClient.getParams();
	}

	@Override
	public HttpState getState() {
		return mClient.getState();
	}

	@Override
	public void setHostConfiguration(HostConfiguration configuration) {
		mClient.setHostConfiguration(configuration);
	}

	@Override
	public void setHttpConnectionManager(HttpConnectionManager manager) {
		mClient.setHttpConnectionManager(manager);
	}

	@Override
	public void setParams(HttpClientParams params) {
		mClient.setParams(params);
	}

	@Override
	public void setState(HttpState state) {
		mClient.setState(state);
	}
}
