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
package poisondog.log;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 */
public class LogItemTest {
	private LogItem mLog;

	@Before
	public void setUp() throws Exception {
		mLog = new LogItem();
	}

	@Test
	public void testTime() throws Exception {
		long time = System.currentTimeMillis();
		mLog.setTime(time);
		Assert.assertEquals(time, mLog.getTime());
	}

	@Test
	public void testTimeZone() throws Exception {
		// TODO another time zone
	}

	@Test
	public void testLevel() throws Exception {
		mLog.setLevel(LogLevel.VERBOSE);
		Assert.assertEquals(LogLevel.VERBOSE, mLog.getLevel());
		mLog.setLevel(LogLevel.DEBUG);
		Assert.assertEquals(LogLevel.DEBUG, mLog.getLevel());
		Log.setLevel(LogLevel.DEBUG);
		Assert.assertTrue(mLog.isLog());
		Log.setLevel(LogLevel.INFO);
		Assert.assertTrue(!mLog.isLog());
	}

	@Test
	public void testTag() throws Exception {
		mLog.setTag("Tag1");
		Assert.assertEquals("Tag1", mLog.getTag());
		mLog.setTag("Tag2");
		Assert.assertEquals("Tag2", mLog.getTag());
	}

	@Test
	public void testMessage() throws Exception {
		mLog.setMessage("message1");
		Assert.assertEquals("message1", mLog.getMessage());
		mLog.setMessage("message2");
		Assert.assertEquals("message2", mLog.getMessage());
	}

	@Test
	public void testEqualsNull() throws Exception {
		Assert.assertFalse(mLog.equals(null));
	}

	@Test
	public void testEqualsString() throws Exception {
		Assert.assertFalse(mLog.equals("str"));
	}

	private LogItem createLog() {
		LogItem log = new LogItem();
		log.setTime(System.currentTimeMillis());
		log.setLevel(LogLevel.VERBOSE);
		log.setTag("test");
		log.setMessage("test message");
		return log;
	}

	@Test
	public void testEquivalence() throws Exception {
		LogItem log1 = createLog();
		LogItem log2 = createLog();
		LogItem log3 = createLog();

		Assert.assertEquals(log1, log1);
		Assert.assertEquals(log1, log2);
		Assert.assertEquals(log2, log1);
		Assert.assertEquals(log2, log3);
		Assert.assertEquals(log1, log3);
	}

	@Test
	public void testEqualsTime() throws Exception {
		LogItem log1 = createLog();
		LogItem log2 = createLog();
		log2.setTime(System.currentTimeMillis() - 1);
		Assert.assertFalse(log1.equals(log2));
	}

	@Test
	public void testEqualsLevel() throws Exception {
		LogItem log1 = createLog();
		LogItem log2 = createLog();
		log2.setLevel(LogLevel.WARNING);
		Assert.assertFalse(log1.equals(log2));
	}

	@Test
	public void testEqualsTag() throws Exception {
		LogItem log1 = createLog();
		LogItem log2 = createLog();
		log2.setTag("tes");
		Assert.assertFalse(log1.equals(log2));
	}

	@Test
	public void testequalsMessage() throws Exception {
		LogItem log1 = createLog();
		LogItem log2 = createLog();
		log2.setMessage("testmessage");
		Assert.assertFalse(log1.equals(log2));
	}

	@Test
	public void testOnlyMessage() throws Exception {
		Log.setOnlyMessage(true);
		mLog.setMessage("message");
		Assert.assertEquals("message", mLog.toString());
		Log.setOnlyMessage(false);
	}
}
