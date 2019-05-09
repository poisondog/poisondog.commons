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
package poisondog.net.http;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import poisondog.core.Mission;


/**
 * @author Adam Huang
 * @since 2017-11-30
 */
public class GetUnknownCertificate implements Mission<String> {

	@Override
	public InputStream execute(String sslurl) throws MalformedURLException, NoSuchAlgorithmException, KeyManagementException, IOException, CertificateEncodingException {
		URL url = new URL(sslurl);
		SSLContext sslCtx = SSLContext.getInstance("TLS");
		sslCtx.init(null, new TrustManager[]{ new X509TrustManager() {
			private X509Certificate[] accepted;
			@Override
			public void checkClientTrusted(X509Certificate[] xcs, String string) {
			}
			@Override
			public void checkServerTrusted(X509Certificate[] xcs, String string) {
				accepted = xcs;
			}
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return accepted;
			}
		}}, null);
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String string, SSLSession ssls) {
				return true;
			}
		});
		connection.setSSLSocketFactory(sslCtx.getSocketFactory());
		connection.connect();
		Certificate[] certificates = connection.getServerCertificates();
		byte[] result = null;
		for (int i = 0; i < certificates.length; i++) {
			Certificate certificate = certificates[i];
			result = certificate.getEncoded();
		}
		connection.disconnect();
		return new ByteArrayInputStream(result);
	}
}
