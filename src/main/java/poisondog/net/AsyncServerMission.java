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

import java.net.Socket;
import poisondog.core.Mission;
import poisondog.net.ServerMission;
import poisondog.net.SocketParameter;

/**
 * @author Adam Huang
 * @since 2018-04-11
 */
public class AsyncServerMission implements Mission<Integer> {
	private Mission<Socket> mHandler;

	/**
	 * Constructor
	 */
	public AsyncServerMission(Mission<Socket> handler) {
		mHandler = handler;
	}

	@Override
	public Void execute(final Integer port) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ServerMission server = new ServerMission();
					server.setHandler(mHandler);
					SocketParameter parameter = new SocketParameter();
					parameter.setPort(port);
					server.execute(parameter);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		return null;
	}

}
