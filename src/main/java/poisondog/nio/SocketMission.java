/*
 * Copyright (C) 2017 Adam Huang <poisondog@gmail.com>
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
package poisondog.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;
import poisondog.core.Mission;
import poisondog.core.NoMission;
import poisondog.log.Log;
import poisondog.net.SocketParameter;

/**
 * @author Adam Huang
 * @since 2017-07-10
 */
public class SocketMission implements Mission<SocketParameter> {
	private Selector mSelector;
	private Mission<SelectionKey> mHandler;
	private long mTimeout;

	/**
	 * Constructor
	 */
	public SocketMission() throws IOException {
		mSelector = Selector.open();
		mHandler = new NoMission<SelectionKey>();
		mTimeout = 0l;
	}

	public void setTimeout(long timeout) {
		mTimeout = timeout;
	}

	public void setHandler(Mission<SelectionKey> handler) {
		mHandler = handler;
	}

	public Selector getSelector() {
		return mSelector;
	}

	public void registerRead(SocketChannel channel) throws ClosedChannelException {
		channel.register(mSelector, SelectionKey.OP_READ);
	}

	public void registerWrite(SocketChannel channel) throws ClosedChannelException {
		channel.register(mSelector, SelectionKey.OP_WRITE);
	}

	public void registerConnect(SocketChannel channel) throws ClosedChannelException {
		channel.register(mSelector, SelectionKey.OP_CONNECT);
	}

	@Override
	public SocketParameter execute(SocketParameter parameter) throws Exception {
		SocketChannel clientChannel = SocketChannel.open();
		clientChannel.configureBlocking(false);
		clientChannel.register(mSelector, SelectionKey.OP_CONNECT);
		clientChannel.connect(new InetSocketAddress(parameter.getHost(), parameter.getPort()));

		while (mSelector.select(mTimeout) > 0) {
			Set<SelectionKey> selectionKeys = mSelector.selectedKeys();
			for(SelectionKey selectionKey:selectionKeys){
				if (selectionKey.isConnectable()) {
					SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
					if (socketChannel.isConnectionPending()) {
						socketChannel.finishConnect();
						socketChannel.register(mSelector, SelectionKey.OP_WRITE);
					}
				} else {
					mHandler.execute(selectionKey);
				}
			}
			selectionKeys.clear();
		}
		return parameter;
	}

	public static void main(String[] args) throws Exception {
		SocketParameter parameter = new SocketParameter();
		parameter.setHost("localhost");
		parameter.setPort(8888);

		final SocketMission mission = new SocketMission();
		mission.setHandler(new Mission<SelectionKey>() {
			private ByteBuffer sendBuffer = ByteBuffer.allocate(4096);
			private ByteBuffer receiveBuffer = ByteBuffer.allocate(4096);
			@Override
			public SelectionKey execute(SelectionKey selectionKey) throws IOException {
				if (selectionKey.isReadable()) {
					SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
					receiveBuffer.clear();
					int count = socketChannel.read(receiveBuffer);
					if(count > 0){
						String receiveText = new String(receiveBuffer.array(), 0, count);
						Log.i("receive data from server: " + receiveText);
						mission.registerConnect(socketChannel);
					}

				} else if (selectionKey.isWritable()) {
					SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
					sendBuffer.clear();
					sendBuffer.put("Hello, Server".getBytes());
					sendBuffer.flip();
					socketChannel.write(sendBuffer);
					mission.registerRead(socketChannel);
				}
				return selectionKey;
			}
		});
		mission.execute(parameter);
	}

}
