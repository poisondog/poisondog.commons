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
package poisondog.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 * @since 2016-12-21
 */
public class HexStringToByteArrayTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSample() throws Exception {
		ByteArrayToHexString toHex = new ByteArrayToHexString();
		HexStringToByteArray toByte = new HexStringToByteArray();
		String hex = toHex.execute("test for demo1".getBytes());
		Assert.assertEquals("7465737420666f722064656d6f31", hex);
		byte[] result = toByte.execute(hex);
		Assert.assertEquals("test for demo1", new String(result));
	}
}
