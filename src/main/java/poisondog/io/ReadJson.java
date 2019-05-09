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
package poisondog.io;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import poisondog.core.Data;
import poisondog.core.Mission;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.Arrays;
import org.json.JSONArray;

/**
 * @author Adam Huang
 * @since 2018-04-14
 */
public class ReadJson implements Mission<InputStream> {
	private int mBufferSize;
	private boolean mComplete;

	/**
	 * Constructor
	 */
	public ReadJson(int size) {
		mBufferSize = size;
	}

	/**
	 * Constructor
	 */
	public ReadJson() {
		this(2048);
	}

	@Override
	public Data execute(InputStream input) throws IOException {
		mComplete = false;
		BufferedInputStream buffer = new BufferedInputStream(input, mBufferSize);
		LinkedList<Byte> list = new LinkedList<Byte>();

		int c;
		while ((c = buffer.read()) != -1) {
			list.add((byte) c);
			if (c == '}' && isJson(list))
				break;
			if (c == ']' && isJsonArray(list))
				break;
		}
		mComplete = true;

		return toData(list);
	}

	public boolean isComplete() {
		return mComplete;
	}

	private boolean isJson(List<Byte> list) {
		try {
			new JSONObject(toString(list));
			return true;
		} catch(JSONException e) {
		}
		return false;
	}

	private boolean isJsonArray(List<Byte> list) {
		try {
			new JSONArray(toString(list));
			return true;
		} catch(JSONException e) {
		}
		return false;
	}

	private String toString(List<Byte> list) {
		return new String(toData(list).getContent());
	}

	private Data toData(List<Byte> list) {
		byte[] result = new byte[list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = list.get(i);
		}
		return new Data(result);
	}
}
