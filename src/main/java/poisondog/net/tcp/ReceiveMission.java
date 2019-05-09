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
import java.net.ServerSocket;
import java.net.Socket;
import poisondog.core.Data;
import poisondog.core.Mission;
import poisondog.io.SendRecv;
import poisondog.net.SocketParameter;
import poisondog.net.SocketResponse;

/**
 * @author Adam Huang
 */
public class ReceiveMission implements Mission<SocketParameter> {
	private String mEndString;

	/**
	 * Constructor
	 */
	public ReceiveMission() {
	}

	/**
	 * Constructor
	 */
	public ReceiveMission(String endString) {
		mEndString = endString;
	}

	public SocketResponse execute(SocketParameter parameter) throws IOException {
		ServerSocket server = new ServerSocket();
		if (parameter.getPort() > 0)
			server = new ServerSocket(parameter.getPort());
		Socket socket = server.accept();
		if(parameter.getTimeout() > 0)
			socket.setSoTimeout(parameter.getTimeout());

		SendRecv receiver = new SendRecv(mEndString);
		Data result = receiver.pull(socket.getInputStream());

		SocketResponse response = new SocketResponse();
		response.setAddress(socket.getInetAddress().getHostAddress());
		response.setPort(socket.getPort());
		response.setContent(result.getContent());
		return response;
	}
}
