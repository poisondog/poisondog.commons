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
package poisondog.json;

import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Adam Huang
 * @since 2016-03-26
 */
public class JsonUtils {

	public static JSONArray toJSONArray(Collection<? extends Jsonable> input) {
		JSONArray result = new JSONArray();
		for (Jsonable json : input) {
			result.put(new JSONObject(json.toJson()));
		}
		return result;
	}

	public static JSONObject toJSONObject(Object obj) {
		JSONObject result = new JSONObject();
		result.put("class", obj.getClass().getName());
		return result;
	}

	public static Object toObject(String json) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		JSONObject obj = new JSONObject(json);
		return toObject(obj);
	}

	public static Object toObject(JSONObject obj) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		return Class.forName(obj.getString("class")).newInstance();
	}
}
