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
import javax.crypto.BadPaddingException;
import poisondog.core.Mission;
import org.apache.commons.codec.binary.Base64;

/**
 * @author Adam Huang
 */
public class DecryptMission implements Mission<String>{
	private Decrypt mDecrypt;

	public DecryptMission(String key) {
		mDecrypt = new Decrypt(key);
	}

	public DecryptMission(String key, String initialVector) {
		mDecrypt = new Decrypt(key, initialVector);
	}

	@Override
	public String execute(String base64Text) throws NoSuchAlgorithmException, InvalidKeyException, BadPaddingException {
		return new String(mDecrypt.execute(Base64.decodeBase64(base64Text)));
	}
}
