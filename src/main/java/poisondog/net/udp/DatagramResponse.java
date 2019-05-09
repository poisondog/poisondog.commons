/*
 * Copyright (C) 2014 Adam Huang
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
package poisondog.net.udp;

import poisondog.net.SocketResponse;

/**
 * @author Adam Huang
 */
public class DatagramResponse extends SocketResponse {
	private int mPacketLength;

	public DatagramResponse(int length) {
		super();
		mContent = new byte[length];
	}

	public void setContent(byte[] bytes) {
		mContent = bytes;
	}

	public byte[] getContent() {
		if(mPacketLength <= 0)
			return mContent;
		byte[] result = new byte[mPacketLength];
		for (int i = 0; i < result.length; i++) {
			result[i] = mContent[i];
		}
		return result;
	}

	public void setPacketLength(int length) {
		mPacketLength = length;
	}
}
