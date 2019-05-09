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
package poisondog.net.tcp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import poisondog.core.Data;
import poisondog.core.Mission;
import poisondog.io.SendRecv;
import poisondog.net.SocketParameter;

/**
 * @author Adam Huang
 */
public class SendMission implements Mission<SocketParameter> {
	private Socket mSocket;
	private String mEndString;

	/**
	 * Constructor
	 */
	public SendMission() {
	}

	/**
	 * Constructor
	 */
	public SendMission(String endString) {
		mEndString = endString;
	}

	public Socket execute(SocketParameter parameter) throws IOException {
		mSocket = new Socket(InetAddress.getByName(parameter.getHost()), parameter.getPort());
		if(parameter.getTimeout() > 0)
			mSocket.setSoTimeout(parameter.getTimeout());
		if(parameter.getInputStream() != null) {
			SendRecv mSendRecv = new SendRecv(mSocket.getOutputStream(), mEndString);
			mSendRecv.push(new Data(parameter.getData()));
			mSocket.getOutputStream().flush();
		}
		return mSocket;
	}
}
