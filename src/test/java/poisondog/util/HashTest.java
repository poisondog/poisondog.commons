/*
 * Copyright (C) 2019 Adam Huang <poisondog@gmail.com>
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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.string.ByteArrayToHexString;

/**
 * @author Adam Huang
 * @since 2019-05-13
 */
public class HashTest {
	private ByteArrayToHexString toHex;

	@Before
	public void setUp() throws Exception {
		toHex = new ByteArrayToHexString();
	}

	@Test
	public void testSHA256() throws Exception {
		String result = toHex.execute(Hash.sha256("".getBytes()));
		Assert.assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855", result);
		result = toHex.execute(Hash.sha256("apple".getBytes()));
		Assert.assertEquals("3a7bd3e2360a3d29eea436fcfb7e44c735d117c42d1c1835420b6b9942dd4f1b", result);
		result = toHex.execute(Hash.sha256("appla".getBytes()));
		Assert.assertEquals("7b9f8b7f53cba6a93e23983a916991a0a81f4f12675899398403926e2b693314", result);
	}

	@Test
	public void testSHA384() throws Exception {
		String result = toHex.execute(Hash.sha384("".getBytes()));
		Assert.assertEquals("38b060a751ac96384cd9327eb1b1e36a21fdb71114be07434c0cc7bf63f6e1da274edebfe76f65fbd51ad2f14898b95b", result);
		result = toHex.execute(Hash.sha384("apple".getBytes()));
		Assert.assertEquals("3d8786fcb588c93348756c6429717dc6c374a14f7029362281a3b21dc10250ddf0d0578052749822eb08bc0dc1e68b0f", result);
		result = toHex.execute(Hash.sha384("appla".getBytes()));
		Assert.assertEquals("fdb7d58e9edbb5b32ea7a8fc16b26c6014b1420c8f2c0cb9e7b0c8c3d73e1f26b9cb03b23c076627a035e44d5dd4f970", result);
	}

	@Test
	public void testSHA512() throws Exception {
		String result = toHex.execute(Hash.sha512("".getBytes()));
		Assert.assertEquals("cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e", result);
		result = toHex.execute(Hash.sha512("apple".getBytes()));
		Assert.assertEquals("844d8779103b94c18f4aa4cc0c3b4474058580a991fba85d3ca698a0bc9e52c5940feb7a65a3a290e17e6b23ee943ecc4f73e7490327245b4fe5d5efb590feb2", result);
		result = toHex.execute(Hash.sha512("appla".getBytes()));
		Assert.assertEquals("d2822ec107237d49ea37e2a08d4cde853acfc00ba25d4d4fb1fcabd359342b88e3cbf8a24e995fffd8fc3c5753eaa72ca39607d64e6540d9679c82644eaf548a", result);
	}

	@Test
	public void testMD5() throws Exception {
		String result = toHex.execute(Hash.md5("".getBytes()));
		Assert.assertEquals("d41d8cd98f00b204e9800998ecf8427e", result);
		result = toHex.execute(Hash.md5("apple".getBytes()));
		Assert.assertEquals("1f3870be274f6c49b3e31a0c6728957f", result);
		result = toHex.execute(Hash.md5("appla".getBytes()));
		Assert.assertEquals("df4c9efc0c4baefb6488cbef9678f11b", result);
	}

}
