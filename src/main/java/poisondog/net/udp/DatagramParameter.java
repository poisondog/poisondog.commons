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

import poisondog.net.SocketParameter;
/**
 * @author Adam Huang
 */
public class DatagramParameter extends SocketParameter {
	private boolean mBroadcast;
	private boolean mWait;
	private String mLocal;
	private int mLength;

	public DatagramParameter() {
		mLocal = "localhost";
		mLength = 1024;
		setPort(-1);
	}

	public void setBroadcast(boolean broadcast) {
		mBroadcast = broadcast;
	}

	public boolean getBroadcast() {
		return mBroadcast;
	}
	
	public void waitResult(boolean wait) {
		mWait = wait;
	}
	
	public boolean waitResult() {
		return mWait;
	}

	public void setLocal(String ip) {
		mLocal = ip;
	}

	public String getLocal() {
		return mLocal;
	}

	public void setResponseLength(int length) {
		mLength = length;
	}

	public int getResponseLength() {
		return mLength;
	}
}
