/*
 * Copyright (C) 2018 Adam Huang <poisondog@gmail.com>
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2018-09-06
 */
public class NextSerialFilename implements Mission<String> {
	private NextSerialString mTask;

	/**
	 * Constructor
	 */
	public NextSerialFilename() {
		mTask = new NextSerialString();
	}

	@Override
	public String execute(String name) {
		GetExtension getter = new GetExtension();
		String extension = getter.execute(name);
		extension = extension.isEmpty() ? extension : "." + extension;
		String target = name.replaceAll(extension + "$", "");
		return mTask.execute(target) + extension;
	}

}
