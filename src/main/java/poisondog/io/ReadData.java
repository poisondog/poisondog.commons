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

/**
 * @author Adam Huang
 * @since 2018-04-14
 */
public class ReadData implements Mission<InputStream> {
	private String mEndString;

	/**
	 * Constructor
	 */
	public ReadData() {
	}

	/**
	 * Constructor
	 */
	public ReadData(String endString) {
		mEndString = endString;
	}

	@Override
	public Data execute(InputStream input) throws IOException {
		if (mEndString == null)
			return parseWithAvailable(input);
		return parseWithEndString(input);
	}

	public Data parseWithEndString(InputStream input) throws IOException {
		BufferedInputStream buffer = new BufferedInputStream(input);
		LinkedList<Byte> list = new LinkedList<Byte>();
		byte[] data = new byte[1];
		while(buffer.read(data) != -1) {
			list.add(data[0]);
			if (isEnd(list))
				break;
		}
		if (list.size() < mEndString.length())
			return null;
		return toData(list.subList(0, list.size() - mEndString.length()));
	}

	public Data parseWithAvailable(InputStream input) throws IOException {
		BufferedInputStream buffer = new BufferedInputStream(input);
		LinkedList<Byte> list = new LinkedList<Byte>();
		byte[] data = new byte[1];
		while(buffer.read(data) != -1) {
			list.add(data[0]);
			if (buffer.available() <= 0)
				break;
		}
		return toData(list.subList(0, list.size()));
	}

	private boolean isEnd(List<Byte> list) {
		if (list.size() < mEndString.length())
			return false;
		List<Byte> endList = list.subList(list.size() - mEndString.length(), list.size());
		return toString(endList).equals(mEndString);
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
