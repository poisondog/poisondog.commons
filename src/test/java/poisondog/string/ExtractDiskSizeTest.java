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
package poisondog.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 */
public class ExtractDiskSizeTest {
	private ExtractDiskSize task;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		task = new ExtractDiskSize();
	}

	@Test
	public void testNormal() throws Exception {
		Assert.assertEquals("37G", task.process("/dev/sda2        37G  6.9G   29G  20% / "));
		Assert.assertEquals("1.9G", task.process("tmpfs  \t 1.9G  236K  1.9G   1% /dev/shm"));
	}

}
