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

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2017-11-20
 */
public class CreateSSLContext implements Mission<InputStream> {
	private String mCertificate;
	private String mProtocol;

	/**
	 * Constructor
	 */
	public CreateSSLContext() {
		mCertificate = "X.509";
		mProtocol = "TLS";
	}

	@Override
	public SSLContext execute(InputStream input) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, KeyManagementException, IOException {
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		if(keyStore == null){
			return null;
		}

		CertificateFactory factory = CertificateFactory.getInstance(mCertificate);
		Certificate ca = factory.generateCertificate(new BufferedInputStream(input));
		keyStore.load(null, null);
		keyStore.setCertificateEntry("ca", ca);

		String algorithm = TrustManagerFactory.getDefaultAlgorithm();
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(algorithm);
		tmf.init(keyStore);

		SSLContext result = SSLContext.getInstance(mProtocol);
		result.init(null, tmf.getTrustManagers(), null);
		return result;
	}
}
