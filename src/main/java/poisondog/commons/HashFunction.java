/*
 * Copyright (C) 2014 Adam Huang
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
package poisondog.commons;

import java.security.NoSuchAlgorithmException;
import poisondog.core.Mission;
import poisondog.string.ByteArrayToHexString;
import poisondog.util.MessageDigestEncrypt;
import poisondog.util.Hash;

/**
 * @author Adam Huang
 */
public class HashFunction implements Mission<byte[]>{
	private Hash mHash;
	private String mType;

	public HashFunction(String type) {
		mType = type;
		mHash = new Hash(type);
	}

	public String execute(byte[] bytes) throws NoSuchAlgorithmException {
		return new ByteArrayToHexString().execute(mHash.execute(bytes));
	}

	public static String sha256(String input) {
		return encrypt(input.getBytes(), "SHA-256");
	}

	public static String sha384(String input) {
		return encrypt(input.getBytes(), "SHA-384");
	}

	public static String sha512(String input) {
		return encrypt(input.getBytes(), "SHA-512");
	}

	public static String md5(String input) {
		return md5(input.getBytes());
	}

	public static String md5(byte[] bytes) {
		return encrypt(bytes, "md5");
	}

	private static String encrypt(byte[] bytes, String encType) {
		HashFunction hash = new HashFunction(encType);
		try {
			return hash.execute(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
