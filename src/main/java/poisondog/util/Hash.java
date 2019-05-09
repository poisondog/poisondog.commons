/*
 * Copyright (C) 2018 Adam Huang <poisondog@gmail.com>
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

import java.security.NoSuchAlgorithmException;
import poisondog.core.Mission;
import poisondog.string.ByteArrayToHexString;

/**
 * @author Adam Huang
 * @since 2018-05-29
 */
public class Hash implements Mission<byte[]>{
	private String mType;

	public Hash(String type) {
		mType = type;
	}

	public byte[] execute(byte[] bytes) throws NoSuchAlgorithmException {
		MessageDigestEncrypt task = new MessageDigestEncrypt(mType);
		return task.execute(bytes);
	}

	public static byte[] sha256(byte[] input) {
		return encrypt(input, "SHA-256");
	}

	public static byte[] sha384(byte[] input) {
		return encrypt(input, "SHA-384");
	}

	public static byte[] sha512(byte[] input) {
		return encrypt(input, "SHA-512");
	}

	public static byte[] md5(byte[] bytes) {
		return encrypt(bytes, "md5");
	}

	private static byte[] encrypt(byte[] bytes, String encType) {
		Hash hash = new Hash(encType);
		try {
			return hash.execute(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
