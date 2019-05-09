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

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.concurrent.SleepMission;
import poisondog.core.Mission;
import poisondog.io.HandleInputLine;
import poisondog.io.ReadLine;

/**
 * @author Adam Huang
 * @since 2018-06-01
 */
public class ServerClientTest {
	private SocketParameter parameter;

	@Before
	public void setUp() throws Exception {
		parameter = new SocketParameter();
		parameter.setPort(12345);
	}

	@Test
	public void testExecute() throws Exception {
		final ServerMission server = new ServerMission();
		server.setHandler(new Mission<Socket>() {
			@Override
			public Socket execute(final Socket client) throws IOException {
				ReadLine read = new ReadLine();
				Assert.assertEquals("client message", read.execute(client.getInputStream()));
				OutputStream output = client.getOutputStream();
				PrintStream writer = new PrintStream(output);
				writer.println("server message");
				writer.flush();
				return client;
			}
		});

		parameter.setInputStream(IOUtils.toInputStream("client message\n", "utf8"));

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					new SleepMission(1000l);
					SocketMission socker = new SocketMission();
					InputStream input = socker.execute(parameter).getInputStream();
					HandleInputLine handler = new HandleInputLine(new Mission<String>() {
						@Override
						public Void execute(String line) {
							Assert.assertEquals("server message", line);
							return null;
						}
					});
					handler.execute(input);
					socker.close();
					new SleepMission(1000l);
					server.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();

		try {
			server.execute(parameter);
		} catch(Exception e) {
		}
	}
}
