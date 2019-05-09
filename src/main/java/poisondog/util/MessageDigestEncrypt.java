/*
 * Copyright (C) 2015 Adam Huang <poisondog@gmail.com>
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
package poisondog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import poisondog.core.Mission;
import poisondog.string.ByteArrayToHexString;

/**
 * @author Adam Huang
 */
public class MessageDigestEncrypt implements Mission<byte[]> {
	private String mEncType;

	public MessageDigestEncrypt(String encType) {
		mEncType = encType;
	}

	@Override
	public byte[] execute(byte[] bytes) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(mEncType);
		md.update(bytes);
		return md.digest();
	}

	private String toHexString(byte[] in) {
		return new ByteArrayToHexString().execute(in);
	}
}
