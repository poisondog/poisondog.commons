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
package poisondog.vfs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 */
public class LegalFilenameTest {
	private LegalFilename mForbiddenFilename;

	@Before
	public void setUp() throws Exception {
		mForbiddenFilename = new LegalFilename();
	}

	@Test
	public void testNumber() throws Exception {
		Assert.assertTrue(mForbiddenFilename.execute("1234567890"));
	}

	@Test
	public void testLetter() throws Exception {
		Assert.assertTrue(mForbiddenFilename.execute("abcdefghijklmnopqrstuvwxyz"));
		Assert.assertTrue(mForbiddenFilename.execute("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
	}

	@Test
	public void testSlash() throws Exception {
		Assert.assertFalse(mForbiddenFilename.execute("/"));
		Assert.assertFalse(mForbiddenFilename.execute("a/"));
		Assert.assertFalse(mForbiddenFilename.execute("/2"));
	}

	@Test
	public void testBackslash() throws Exception {
		Assert.assertFalse(mForbiddenFilename.execute("\\"));
	}

	@Test
	public void testQuestionMark() throws Exception {
		Assert.assertFalse(mForbiddenFilename.execute("?"));
	}

	@Test
	public void testPercent() throws Exception {
		Assert.assertFalse(mForbiddenFilename.execute("%"));
	}

	@Test
	public void testAsterisk() throws Exception {
		Assert.assertFalse(mForbiddenFilename.execute("*"));
	}

	@Test
	public void testColon() throws Exception {
		Assert.assertFalse(mForbiddenFilename.execute(":"));
	}

	@Test
	public void testVerticalBar() throws Exception {
		Assert.assertFalse(mForbiddenFilename.execute("|"));
	}

	@Test
	public void testQuote() throws Exception {
		Assert.assertFalse(mForbiddenFilename.execute("\""));
	}

	@Test
	public void testLessThan() throws Exception {
		Assert.assertFalse(mForbiddenFilename.execute("<"));
	}

	@Test
	public void testGreaterThan() throws Exception {
		Assert.assertFalse(mForbiddenFilename.execute(">"));
	}

	@Test
	public void testDot() throws Exception {
		Assert.assertFalse(mForbiddenFilename.execute("."));
		Assert.assertFalse(mForbiddenFilename.execute(".."));
		Assert.assertTrue(mForbiddenFilename.execute("1."));
		Assert.assertTrue(mForbiddenFilename.execute(".b"));
	}
}
