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

import poisondog.core.Mission;

/**
 * @author Adam Huang
 */
public class GetExtension implements Mission<String> {

	/**
	 * @param input filename. ex: nice.mp3
	 * @return extension ex: mp3
	 */
	@Override
	public String execute(String input) {
		String filename = new GetFileName().execute(input);
		if(!filename.contains("."))
			return "";
		return filename.replaceAll("^(.*[\\.*])", "");
	}
}
