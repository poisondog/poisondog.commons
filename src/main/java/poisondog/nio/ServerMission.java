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
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
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
public class ServerMission implements Mission<SocketParameter> {
	private Selector mSelector;
	private Mission<SelectionKey> mHandler;

	/**
	 * Constructor
	 */
	public ServerMission() throws IOException {
		mSelector = Selector.open();
		mHandler = new NoMission<SelectionKey>();
	}

	public void setHandler(Mission<SelectionKey> handler) {
		mHandler = handler;
	}

	public Selector getSelector() {
		return mSelector;
	}

	public void registerWrite(SocketChannel channel) throws ClosedChannelException {
		channel.register(mSelector, SelectionKey.OP_WRITE);
	}

	public void registerRead(SocketChannel channel) throws ClosedChannelException {
		channel.register(mSelector, SelectionKey.OP_READ);
	}

	@Override
	public SocketParameter execute(SocketParameter parameter) throws Exception {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.register(mSelector, SelectionKey.OP_ACCEPT);

		ServerSocket serverSocket = serverSocketChannel.socket();
		serverSocket.bind(new InetSocketAddress(parameter.getPort()));

		Log.i("wait for client connect...");
		while(mSelector.select() > 0){
			Set<SelectionKey> selectionKeys = mSelector.selectedKeys();
			for (SelectionKey selectionKey : selectionKeys) {
				if(selectionKey.isAcceptable()){
					ServerSocketChannel server = (ServerSocketChannel)selectionKey.channel();
					SocketChannel socketChannel = server.accept();
					socketChannel.configureBlocking(false);
					socketChannel.register(mSelector, SelectionKey.OP_READ);
					Log.i("wait for " + socketChannel.getRemoteAddress() + " input");
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
		parameter.setPort(8888);

		final ServerMission server = new ServerMission();
		server.setHandler(new Mission<SelectionKey>() {
			private String mText;
			private ByteBuffer receiveBuffer = ByteBuffer.allocate(4096);
			private ByteBuffer sendBuffer = ByteBuffer.allocate(4096);
			@Override
			public SelectionKey execute(SelectionKey selectionKey) throws IOException {
				if(selectionKey.isReadable()){
					SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
					receiveBuffer.clear();
					int count = socketChannel.read(receiveBuffer);
					if(count>0){
						String receiveText = new String(receiveBuffer.array(),0,count);
						Log.i("receive data from server: " + receiveText);
						mText = receiveText;
						server.registerWrite(socketChannel);
					}
				}else if (selectionKey.isWritable()) {
					sendBuffer.clear();
					SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
					sendBuffer.put(mText.toUpperCase().getBytes());
					sendBuffer.flip();
					socketChannel.write(sendBuffer);
					Log.i("send message from server to client: " + mText.toUpperCase());
					server.registerRead(socketChannel);
				}
				return selectionKey;
			}
		});
		server.execute(parameter);
	}
}
