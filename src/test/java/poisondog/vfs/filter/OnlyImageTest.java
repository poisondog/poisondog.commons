/*
 * Copyright (C) 2014 Adam Huang <poisondog@gmail.com>
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
import poisondog.vfs.LocalFileFactory;

/**
 * @author poisondog <poisondog@gmail.com>
 */
public class OnlyImageTest {
	private long mTime;
	private LocalFileFactory mFactory;
	private OnlyImage mRule;

	@Before
	public void setUp() throws Exception {
		mTime = System.currentTimeMillis();
		mFactory = new LocalFileFactory();
		mRule = new OnlyImage();
	}

	@Test
	public void testFolder() throws Exception {
		Assert.assertFalse(mRule.execute(mFactory.getFile("file:/tmp/" + mTime + "/foo/")));
	}

	@Test
	public void testData() throws Exception {
		Assert.assertFalse(mRule.execute(mFactory.getFile("file:/tmp/" + mTime + "/foo")));
	}

	@Test
	public void testJPG() throws Exception {
		Assert.assertTrue(mRule.execute(mFactory.getFile("file:/tmp/" + mTime + "/foo.jpg")));
		Assert.assertTrue(mRule.execute(mFactory.getFile("file:/tmp/" + mTime + "/foo.jpeg")));
	}

	@Test
	public void testPNG() throws Exception {
		Assert.assertTrue(mRule.execute(mFactory.getFile("file:/tmp/" + mTime + "/foo.png")));
	}

	@Test
	public void testBMP() throws Exception {
		Assert.assertTrue(mRule.execute(mFactory.getFile("file:/tmp/" + mTime + "/foo.bmp")));
	}
}
