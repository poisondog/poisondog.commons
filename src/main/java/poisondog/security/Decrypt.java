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

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import poisondog.core.Mission;
import poisondog.util.Hash;

/**
 * @author Adam Huang
 * @since 2018-05-29
 */
public class Decrypt implements Mission<byte[]>{
	private String mPassword;
	private String mAlgorithm;
	private String mInitialVector;

	public Decrypt(String password) {
		this(password, null);
	}

	public Decrypt(String password, String initialVector) {
		mAlgorithm = "AES/CBC/PKCS5Padding";
		mPassword = password;
		mInitialVector = initialVector;
	}

	@Override
	public byte[] execute(byte[] cryptograph) throws NoSuchAlgorithmException, InvalidKeyException, BadPaddingException {
		try {
			SecretKeySpec key = createKey();
			Cipher cipher = Cipher.getInstance(mAlgorithm);
			cipher.init(Cipher.DECRYPT_MODE, key, createInitialVector());
			byte[] content = cipher.doFinal(cryptograph);
			return content;
		} catch(IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch(NoSuchPaddingException e) {
			e.printStackTrace();
		} catch(InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return null;
	}

	private SecretKeySpec createKey() throws NoSuchAlgorithmException {
		return new SecretKeySpec(Hash.md5(mPassword.getBytes()), mAlgorithm.replaceAll("/.*$", ""));
	}

	private IvParameterSpec createInitialVector() {
		if (mInitialVector == null)
			return new IvParameterSpec(new byte[128 / 8]);
		return new IvParameterSpec(Hash.md5(mInitialVector.getBytes()));
	}

}
