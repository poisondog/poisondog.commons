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
package poisondog.string;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Adam Huang
 */
public class ExtractPort implements StringProcessor {
	private GetPort mTask;

	/**
	 * Constructor
	 */
	public ExtractPort() {
		mTask = new GetPort();
	}

	public String process(String input) throws URISyntaxException, MalformedURLException {
		if (input == null)
			return "";
		String result = mTask.execute(input);
		if (result.isEmpty())
			return getDefaultPort(new URI(input));
		return result;
	}

	private String getDefaultPort(URI uri) throws MalformedURLException {
		if (uri.getHost() != null)
			return Integer.toString(uri.toURL().getDefaultPort());
		return "";
	}

}
