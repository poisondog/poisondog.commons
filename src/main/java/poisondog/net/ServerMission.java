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
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import org.apache.commons.io.IOUtils;
import poisondog.concurrent.SleepMission;
import poisondog.core.Mission;
import poisondog.core.NoMission;
import poisondog.io.ReadLine;
import poisondog.io.ReadData;
import poisondog.log.Log;

/**
 * @author Adam Huang
 */
public class ServerMission implements Mission<SocketParameter> {
	private ServerSocket mServer;
	private Mission<Socket> mHandler;
	private boolean mFinished;

	public ServerMission() {
		mHandler = new NoMission<Socket>();
	}

	@Override
	public SocketParameter execute(SocketParameter parameter) throws UnknownHostException, IOException {
		mFinished = false;
		mServer = new ServerSocket();
		if (parameter.getPort() > 0)
			mServer = new ServerSocket(parameter.getPort());
		Log.i("Listen Port: " + parameter.getPort());
		while (!mFinished) {
			synchronized (mServer) {
				Socket socket = mServer.accept();
				new Thread(new RequestThread(socket)).start();
			}
		}
		return parameter;
	}

	public void close() throws IOException {
		mFinished = true;
		mServer.close();
	}

	public void setHandler(Mission<Socket> handler) {
		mHandler = handler;
	}

	private class RequestThread implements Runnable {
		private Socket mClient;

		public RequestThread(Socket client) {
			mClient = client;
		}

		@Override
		public void run() {
			Log.v("Get Connection From: " + mClient.getRemoteSocketAddress());
			try {
				mHandler.execute(mClient);
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(mClient != null && !mClient.isClosed())
						mClient.close();
				} catch (IOException e ) {
					e.printStackTrace();
				}
			}
		}
	}

}
