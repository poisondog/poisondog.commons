/*
 * Copyright (C) 2017 Adam Huang <poisondog@gmail.com>
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
package poisondog.net.http;

import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2017-01-25
 */
public class CreateHeader implements Mission<String[]> {

	@Override
	public String execute(String... input) {
		if (input.length != 2)
			throw new IllegalArgumentException("need to input key and value String");
		StringBuilder builder = new StringBuilder();
		builder.append(input[0]);
		builder.append(": ");
		builder.append(input[1]);
		builder.append("\n");
		return builder.toString();
	}
}
