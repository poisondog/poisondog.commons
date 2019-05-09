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
package poisondog.util;

/**
 * @author Adam Huang
 * @since 2018-12-20
 */
public class Version implements Comparable<Version> {
	private String mContent;

	/**
	 * Constructor
	 */
	public Version(String content) {
		mContent = content;
	}

	public int getMajor() {
		return Integer.parseInt(mContent.split("\\.")[0]);
	}

	public int getMinor() {
		return Integer.parseInt(mContent.split("\\.")[1]);
	}

	public int getBuild() {
		String[] strs = mContent.split("\\.");
		if (strs.length < 3)
			return 0;
		return Integer.parseInt(strs[2]);
	}

	@Override
	public int compareTo(Version another) {
		if (getMajor() != another.getMajor())
			return getMajor() - another.getMajor();
		if (getMinor() != another.getMinor())
			return getMinor() - another.getMinor();
		if (getBuild() != another.getBuild())
			return getBuild() - another.getBuild();
		return 0;
	}

}
