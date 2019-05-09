/*
 * Copyright (C) 2016 Adam Huang <poisondog@gmail.com>
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
 * @since 2016-12-21
 */
public class ByteArrayToHexString implements Mission<byte[]> {

	@Override
	public String execute(byte[] input) {
		StringBuilder hexString = new StringBuilder();
		for (int i = 0; i < input.length; i++){
			String hex = Integer.toHexString(0xFF & input[i]);
			if (hex.length() == 1){
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

}
