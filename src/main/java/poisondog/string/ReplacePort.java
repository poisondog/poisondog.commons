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
package poisondog.string;

import poisondog.core.Mission;

/**
 * @author Adam Huang
 */
public class ReplacePort implements StringProcessor, Mission<String> {
	private String mPort;

	public ReplacePort(String port) {
		mPort = port;
	}

	@Override
	public String process(String input) {
		return execute(input);
	}

	@Override
	public String execute(String input) {
		if (input == null)
			return "";
		String old = new GetPort().execute(input);
		if(!old.isEmpty())
			return input.replaceFirst(old, mPort);
		return input;
	}


}