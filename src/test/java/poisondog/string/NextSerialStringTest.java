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
package poisondog.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 * @since 2018-09-06
 */
public class NextSerialStringTest {
	private NextSerialString mTask;

	@Before
	public void setUp() throws Exception {
		mTask = new NextSerialString();
	}

	@Test
	public void testSample() throws Exception {
		Assert.assertEquals("name_1", mTask.execute("name"));
		Assert.assertEquals("cool_2", mTask.execute("cool_1"));
		Assert.assertEquals("cool_10", mTask.execute("cool_9"));
		Assert.assertEquals("cool_30", mTask.execute("cool_29"));
		Assert.assertEquals("co01_30", mTask.execute("co01_29"));
		Assert.assertEquals("12_30", mTask.execute("12_29"));
		Assert.assertEquals("12_1", mTask.execute("12"));
	}

}
