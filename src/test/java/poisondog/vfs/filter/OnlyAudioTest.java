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
package poisondog.vfs.filter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.vfs.FileFactory;

/**
 * @author Adam Huang
 * @since 2017-02-13
 */
public class OnlyAudioTest {
	private OnlyAudio mRule;

	@Before
	public void setUp() throws Exception {
		mRule = new OnlyAudio();
	}

	@Test
	public void testWithAudio() throws Exception {
		Assert.assertTrue(mRule.execute(FileFactory.getFile("file:/tmp/aka.mp3")));
	}

	@Test
	public void testWithVideo() throws Exception {
		Assert.assertFalse(mRule.execute(FileFactory.getFile("file:/tmp/aka.mp4")));
	}
}
