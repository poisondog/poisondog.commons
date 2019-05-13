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

/**
 * @author Adam Huang
 * @since 2019-05-13
 */
public class PrintUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBlack() throws Exception {
		Assert.assertEquals("\u001B[30mHello\u001B[0m", PrintUtils.black("Hello"));
	}

	@Test
	public void testRed() throws Exception {
		Assert.assertEquals("\u001B[31mHello\u001B[0m", PrintUtils.red("Hello"));
	}

	@Test
	public void testGreen() throws Exception {
		Assert.assertEquals("\u001B[32mHello\u001B[0m", PrintUtils.green("Hello"));
	}

	@Test
	public void testYellow() throws Exception {
		Assert.assertEquals("\u001B[33mHello\u001B[0m", PrintUtils.yellow("Hello"));
	}

}
