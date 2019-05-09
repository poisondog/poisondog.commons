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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 * @since 2018-12-20
 */
public class VersionTest {
	private Version mVersion;

	@Before
	public void setUp() throws Exception {
		mVersion = new Version("3.2.14");
	}

	@Test
	public void testMajor() throws Exception {
		Assert.assertEquals(3, mVersion.getMajor());
	}

	@Test
	public void testMinor() throws Exception {
		Assert.assertEquals(2, mVersion.getMinor());
	}

	@Test
	public void testBuild() throws Exception {
		Assert.assertEquals(14, mVersion.getBuild());
	}

	@Test
	public void testCompare() throws Exception {
		Assert.assertEquals(1, mVersion.compareTo(new Version("2.2.17")));
		Assert.assertEquals(-1, mVersion.compareTo(new Version("3.3.0")));
		Assert.assertEquals(-5, mVersion.compareTo(new Version("3.2.19")));
		Assert.assertEquals(0, mVersion.compareTo(new Version("3.2.14")));
		Assert.assertEquals(14, mVersion.compareTo(new Version("3.2")));
	}
}
