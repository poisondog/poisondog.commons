/*
 * Copyright (C) 2017 Adam Huang
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
 * @since 2017-07-06
 */
public class GetFileName implements Mission<String> {

	@Override
	public String execute(String input) {
		Pattern p = Pattern.compile("/?([^/]+/?)$");
		Matcher m = p.matcher(input);
		String filename = m.find() ? m.group() : new String();
		return filename.replace("/", "");
	}
}
