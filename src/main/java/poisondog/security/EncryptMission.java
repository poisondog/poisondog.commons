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

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 */
public class EncryptMission implements Mission<String>{
	private Encrypt mEncrypt;

	public EncryptMission(String password) {
		mEncrypt = new Encrypt(password);
	}

	public EncryptMission(String password, String initialVector) {
		mEncrypt = new Encrypt(password, initialVector);
	}

	@Override
	public String execute(String input) throws NoSuchAlgorithmException, InvalidKeyException {
		return Base64.encodeBase64String(mEncrypt.execute(input.getBytes()));
	}

}
