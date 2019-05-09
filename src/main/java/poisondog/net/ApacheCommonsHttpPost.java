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
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.URI;
import poisondog.core.Mission;
import poisondog.net.http.HttpResponse;
/**
 * @author Adam Huang
 */
public class ApacheCommonsHttpPost implements Mission<ApacheCommonsHttpParameter> {
	public HttpResponse execute(ApacheCommonsHttpParameter parameter) throws IOException {
		HttpMethod method = parameter.getMethod();
		method.setURI(new URI(parameter.getUrl(), true));
		List<Part> parts = new ArrayList<Part>();
		for (String key : parameter.textKeys()) {
			parts.add(new StringPart(key, parameter.getText(key)));
		}
		for (String key : parameter.fileKeys()) {
			parts.add(new FilePart(key, parameter.getFile(key)));
		}

		HttpClient client = new HttpClient();
		int status = client.executeMethod(method);

		HttpResponse res = new HttpResponse(status);
		res.setInputStream(method.getResponseBodyAsStream());
		return res;
	}
}
