/*
 * Copyright (C) 2017 Adam Huang <poisondog@gmail.com>
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
package poisondog.net.http;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 * @since 2017-01-25
 */
public class CreateHeaderTest {
	private CreateHeader mTask;

	@Before
	public void setUp() throws Exception {
		mTask = new CreateHeader();
	}

	@Test
	public void testSample1() throws Exception {
		Assert.assertEquals("Content-Type: image/jpg\n", mTask.execute("Content-Type", "image/jpg"));
	}

	@Test
	public void testSample2() throws Exception {
		Assert.assertEquals("Connection: Keep-Alive\n", mTask.execute("Connection", "Keep-Alive"));
	}
}
