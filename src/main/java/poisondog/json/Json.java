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
package poisondog.json;

import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Adam Huang
 * @since 2017-12-01
 */
public class Json implements Iterable<Json> {
	private String mContent;
	private JSONObject mObject;
	private JSONArray mArray;

	/**
	 * Constructor
	 */
	public Json(String content) {
		mContent = content;
		try {
			mObject = new JSONObject(content);
		} catch(JSONException e) {
		}
		try {
			mArray = new JSONArray(content);
		} catch(JSONException e) {
		}
	}

	@Override
	public Iterator<Json> iterator() {
		ArrayList<Json> result = new ArrayList<Json>();
		if (mObject != null) {
			Iterator<String> iterator = mObject.keys();
			while (iterator.hasNext()) {
				result.add(new Json(iterator.next()));
			}
			return result.iterator();
		}
		if (mArray != null) {
			for (int i = 0; i < mArray.length(); i++) {
				result.add(get(i));
			}
			return result.iterator();
		}
		throw new IllegalArgumentException("this content not support iterator method");
	}

	public boolean has(String key) {
		if (mObject == null)
			throw new IllegalArgumentException("this content is not json object");
		return mObject.has(key);
	}

	public Json get(String key) {
		if (mObject == null)
			throw new IllegalArgumentException("this content is not json object");
		return new Json(mObject.get(key).toString());
	}

	public Json get(int index) {
		if (mArray == null)
			throw new IllegalArgumentException("this content is not json array");
		return new Json(mArray.get(index).toString());
	}

	public int size() {
		if (mObject != null)
			return mObject.length();
		if (mArray != null)
			return mArray.length();
		throw new IllegalArgumentException("this content not support iterator method");
	}

	public boolean toBoolean() {
		return Boolean.parseBoolean(mContent);
	}

	public int toInt() {
		return Integer.parseInt(mContent);
	}

	public long toLong() {
		return Long.parseLong(mContent);
	}

	public double toDouble() {
		return Double.parseDouble(mContent);
	}

	@Override
	public String toString() {
		if (mObject != null)
			return mObject.toString(8);
		if (mArray != null)
			return mArray.toString(8);
		return mContent;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Json))
			return false;
		Json another = (Json) obj;
		return mContent.equals(another.mContent);
	}
}
