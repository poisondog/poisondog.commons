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
package poisondog.command;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import poisondog.json.Json;
import poisondog.json.Jsonable;

/**
 * @author Adam Huang
 * @since 2018-01-10
 */
public class Content implements Jsonable {
	private Map<String, String> mContent;

	/**
	 * Constructor
	 */
	public Content(String... content) {
		mContent = new HashMap<String, String>();
		for (int i = 0; i < content.length; i++) {
			mContent.put(Integer.toString(i), content[i]);
		}
	}

	public void add(String key, String value) {
		mContent.put(key, value);
	}

	public String get(int key) {
		return mContent.get(Integer.toString(key));
	}

	public String get(String key) {
		return mContent.get(key);
	}

	public int size() {
		return mContent.keySet().size();
	}

	@Override
	public void loadJson(String json) throws Exception {
		mContent.clear();
		Json data = new Json(json);
		for (Json key : data) {
			add(key.toString(), data.get(key.toString()).toString());
		}
	}

	@Override
	public String toJson() {
		JSONObject obj = new JSONObject(mContent);
		return obj.toString();
	}

	@Override
	public String toString() {
		return toJson();
	}
}
