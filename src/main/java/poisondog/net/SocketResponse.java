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
package poisondog.net;

/**
 * @author Adam Huang
 * @since 2018-10-31
 */
public class SocketResponse {
	private String mAddress;
	private int mPort;
	protected byte[] mContent;

	public void setAddress(String address) {
		mAddress = address;
	}

	public String getAddress() {
		return mAddress;
	}

	public void setPort(int port) {
		mPort = port;
	}

	public int getPort() {
		return mPort;
	}

	public void setContent(byte[] bytes) {
		mContent = bytes;
	}

	public byte[] getContent() {
		return mContent;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(mAddress);
		builder.append(":");
		builder.append(mPort);
		builder.append("\n");
		builder.append(new String(mContent));
		return builder.toString();
	}

}
