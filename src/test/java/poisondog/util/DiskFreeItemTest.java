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
 * @since 2018-03-08
 */
public class DiskFreeItemTest {
	private DiskFreeItem mItem;

	@Before
	public void setUp() throws Exception {
		mItem = new DiskFreeItem("/dev/sda2  \t  37G  6.9G   29G  20% /home ");
	}

	@Test
	public void testSize() throws Exception {
		Assert.assertEquals("37G", mItem.getSize());
	}

	@Test
	public void testUsed() throws Exception {
		Assert.assertEquals("6.9G", mItem.getUsed());
	}

	@Test
	public void testAvailable() throws Exception {
		Assert.assertEquals("29G", mItem.getAvailable());
	}

	@Test
	public void testUsedPercent() throws Exception {
		Assert.assertEquals("20%", mItem.getUsedPercent());
	}

	@Test
	public void testMount() throws Exception {
		Assert.assertEquals("/home", mItem.getMount());
	}
}
