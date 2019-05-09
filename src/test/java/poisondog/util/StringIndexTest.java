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
package poisondog.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author poisondog <poisondog@gmail.com>
 */
public class StringIndexTest {
	private StringIndex<String> mIndex;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mIndex = new StringIndex<String>();
	}

	@Test
	public void testPutAndGet() throws Exception {
		mIndex.put("txt1", "text1.1");
		mIndex.put("txt1", "text1.2");
		mIndex.put("txt1", "text1.3");
		mIndex.put("txt2", "text2.1");
		mIndex.put("txt2", "text2.2");
		mIndex.put("txt3", "text3.1");
		Assert.assertEquals(3, mIndex.get("txt1").size());
		Assert.assertEquals(2, mIndex.get("txt2").size());
		Assert.assertEquals(1, mIndex.get("txt3").size());
		Assert.assertTrue(mIndex.get("txt1").contains("text1.1"));
		Assert.assertTrue(mIndex.get("txt1").contains("text1.2"));
		Assert.assertTrue(mIndex.get("txt1").contains("text1.3"));
		Assert.assertTrue(mIndex.get("txt2").contains("text2.1"));
		Assert.assertTrue(mIndex.get("txt2").contains("text2.2"));
		Assert.assertTrue(mIndex.get("txt3").contains("text3.1"));
	}
}
