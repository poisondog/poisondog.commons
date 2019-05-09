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

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import poisondog.net.http.HttpParameter;
/**
 * @author Adam Huang
 */
public class ApacheCommonsHttpParameter extends HttpParameter {
	private HttpMethod mMethod;

	public ApacheCommonsHttpParameter() {
		mMethod = new PostMethod();
	}

	public HttpMethod getMethod() {
		return mMethod;
	}

	public void setMethod(HttpMethod method) {
		mMethod = method;
	}
}
