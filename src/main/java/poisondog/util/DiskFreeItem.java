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
 * @since 2018-03-08
 */
public class DiskFreeItem {
	private String mContent;

	/**
	 * Constructor
	 */
	public DiskFreeItem(String line) {
		mContent = line;
		if (mContent != null && line.split("\\s+").length != 6) {
			mContent = null;
		}
	}

	public String getSize() {
		if (mContent == null)
			return "0";
		return mContent.split("\\s+")[1];
	}

	public String getUsed() {
		if (mContent == null)
			return "0";
		return mContent.split("\\s+")[2];
	}

	public String getAvailable() {
		if (mContent == null)
			return "0";
		return mContent.split("\\s+")[3];
	}

	public String getUsedPercent() {
		if (mContent == null)
			return "0";
		return mContent.split("\\s+")[4];
	}

	public String getMount() {
		if (mContent == null)
			return "0";
		return mContent.split("\\s+")[5];
	}

	@Override
	public String toString() {
		return mContent;
	}
}
