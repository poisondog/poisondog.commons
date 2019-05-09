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

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import org.apache.commons.io.IOUtils;
import poisondog.core.Mission;
/**
 * @author Adam Huang
 */
public class DatagramMission implements Mission<DatagramParameter> {

	public DatagramResponse execute(DatagramParameter parameter) throws IOException {
		DatagramSocket socket = new DatagramSocket(null);
		socket.setReuseAddress(true);

		socket.setBroadcast(parameter.getBroadcast());
		if(parameter.getTimeout() > 0)
			socket.setSoTimeout(parameter.getTimeout());
		String data = IOUtils.toString(parameter.getInputStream());
		DatagramPacket packet = new DatagramPacket(data.getBytes(), data.length());
		packet.setAddress(InetAddress.getByName(parameter.getHost()));
		packet.setPort(parameter.getPort());
		socket.send(packet);

		DatagramResponse response = new DatagramResponse(parameter.getResponseLength());
		DatagramPacket responsePacket = new DatagramPacket(response.getContent(), response.getContent().length);
		socket.receive(responsePacket);
		response.setAddress(responsePacket.getAddress().getHostAddress());
		response.setPacketLength(responsePacket.getLength());
		socket.close();
		return response;
	}
}
