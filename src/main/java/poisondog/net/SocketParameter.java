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
package poisondog.net;

import java.io.InputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import poisondog.core.Data;

/**
 * @author Adam Huang
 */
public class SocketParameter {
	private String mHost;
	private int mPort;
	private int mTimeout;
	private Data mData;

	public SocketParameter() {
		mHost = "localhost";
		mPort = 80;
		mTimeout = -1;
		mData = new Data();
	}

	public String getHost() {
		return mHost;
	}

	public void setHost(String host) {
		mHost = host;
	}

	public int getPort() {
		return mPort;
	}

	public void setPort(int port) {
		mPort = port;
	}

	public byte[] getData() {
		return mData.getContent();
	}

	public void setData(byte[] data) {
		mData = new Data(data);
	}

	public InputStream getInputStream() {
		if (mData.isEmpty())
			return null;
		return mData.toInputStream();
	}

	public void setInputStream(InputStream input) {
		try {
			mData = new Data(IOUtils.toByteArray(input));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public int getTimeout() {
		return mTimeout;
	}

	public void setTimeout(int timeout) {
		mTimeout = timeout;
	}
}
