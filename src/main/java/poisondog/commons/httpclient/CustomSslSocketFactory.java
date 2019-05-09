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
package poisondog.commons.httpclient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import org.apache.commons.httpclient.protocol.SSLProtocolSocketFactory;

/**
 * @author Adam Huang
 * @since 2017-11-22
 */
public class CustomSslSocketFactory extends SSLProtocolSocketFactory {
	private SSLContext mContext;

	/**
	 * Constructor
	 */
	public CustomSslSocketFactory(SSLContext context) {
		mContext = context;
	}

	@Override
	public SSLSocket createSocket(String host, int port, InetAddress clientHost, int clientPort) throws IOException, UnknownHostException {
		return (SSLSocket) mContext.getSocketFactory().createSocket(host, port, clientHost, clientPort);
	}

}
