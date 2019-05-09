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

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import poisondog.core.Data;
import poisondog.core.Mission;
import poisondog.io.SendRecv;

/**
 * @author Adam Huang
 */
public class SocketMission implements Mission<SocketParameter> {
	private String mEndString;
	private Socket mSocket;

	/**
	 * Constructor
	 */
	public SocketMission(String endString) {
		mEndString = endString;
	}

	/**
	 * Constructor
	 */
	public SocketMission() {
		this(null);
	}

	public Socket execute(SocketParameter parameter) throws UnknownHostException, IOException {
		mSocket = new Socket(InetAddress.getByName(parameter.getHost()), parameter.getPort());
		if(parameter.getTimeout() > 0)
			mSocket.setSoTimeout(parameter.getTimeout());
		if(parameter.getInputStream() != null) {
			SendRecv sr = new SendRecv(mSocket.getOutputStream(), mEndString);
			sr.push(new Data(parameter.getData()));
		}
		return mSocket;
	}

	public void close() throws IOException {
		mSocket.close();
	}

}
