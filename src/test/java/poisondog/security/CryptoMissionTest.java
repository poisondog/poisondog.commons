/*
 * Copyright (C) 2016 Adam Huang <poisondog@gmail.com>
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
package poisondog.security;

import javax.crypto.BadPaddingException;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 */
public class CryptoMissionTest {
	private EncryptMission mEncrypt;
	private DecryptMission mDecrypt;

	@Before
	public void setUp() throws Exception {
		mEncrypt = new EncryptMission("password.....key");
		mDecrypt = new DecryptMission("password.....key");
	}

	@Test
	public void testEncrypt() throws Exception {
		Assert.assertEquals("Bk7b5U3BXCxWYQbZO/n1tw==", mEncrypt.execute("this is a dog."));
		Assert.assertEquals("RodbzB2PyUzccVJnJsdT8w==", mEncrypt.execute("this is a dag."));
	}

	@Test
	public void testDecrypt() throws Exception {
		Assert.assertEquals("this is a dog.", mDecrypt.execute("Bk7b5U3BXCxWYQbZO/n1tw=="));
		Assert.assertEquals("this is a dag.", mDecrypt.execute("RodbzB2PyUzccVJnJsdT8w=="));
	}

	@Test
	public void testWrongKey() throws Exception {
		mDecrypt = new DecryptMission("key");
		try {
			Assert.assertEquals("this is a dog.", mDecrypt.execute("Bk7b5U3BXCxWYQbZO/n1tw=="));
			Assert.fail("need to throws exception");
		} catch(BadPaddingException e) {
			Assert.assertTrue(true);
		}
		try {
			Assert.assertEquals("this is a dag.", mDecrypt.execute("RodbzB2PyUzccVJnJsdT8w=="));
			Assert.fail("need to throws exception");
		} catch(BadPaddingException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testWithInitialVector() throws Exception {
		Encrypt mEncrypt = new Encrypt("key!@", "initialVector");
		Assert.assertEquals("bf8e+vzN+IkNRrSPSLuaNw==", Base64.encodeBase64String(mEncrypt.execute("this is a dog.".getBytes())));
		Assert.assertEquals("+RtECF/PHywG8PqtP9/lPw==", Base64.encodeBase64String(mEncrypt.execute("this is a dag.".getBytes())));

		Decrypt mDecrypt = new Decrypt("key!@", "initialVector");
		Assert.assertEquals("this is a dog.", new String(mDecrypt.execute(Base64.decodeBase64("bf8e+vzN+IkNRrSPSLuaNw=="))));
		Assert.assertEquals("this is a dag.", new String(mDecrypt.execute(Base64.decodeBase64("+RtECF/PHywG8PqtP9/lPw=="))));

		mDecrypt = new Decrypt("key!@", "nitialVector");
		try {
			Assert.assertEquals("this is a dog.", new String(mDecrypt.execute(Base64.decodeBase64("bf8e+vzN+IkNRrSPSLuaNw=="))));
			Assert.fail("need to throws exception");
		} catch(BadPaddingException e) {
			Assert.assertTrue(true);
		}
	}

}
