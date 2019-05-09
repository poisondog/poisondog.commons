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
public class NextSerialFilenameTest {
	private NextSerialFilename mTask;

	@Before
	public void setUp() throws Exception {
		mTask = new NextSerialFilename();
	}

	@Test
	public void testFilename() throws Exception {
		Assert.assertEquals("name_1.mp3", mTask.execute("name.mp3"));
		Assert.assertEquals("tool_2.mp4", mTask.execute("tool_1.mp4"));
		Assert.assertEquals("tool_2", mTask.execute("tool_1"));
	}

}
