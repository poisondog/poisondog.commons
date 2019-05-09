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

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author poisondog <poisondog@gmail.com>
 */
public class LogRecorderTest {
	private LogRecorder mLogger;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mLogger = new LogRecorder("LogRecorderTest");
	}

	private void logAllLevel() {
		mLogger.log(LogLevel.VERBOSE, "test trace");
		mLogger.log(LogLevel.DEBUG, "test debug");
		mLogger.log(LogLevel.INFO, "test info");
		mLogger.log(LogLevel.WARNING, "test warning");
		mLogger.log(LogLevel.ERROR, "test error");
	}

	@Test
	public void testLog() throws Exception {
		Log.setLevel(LogLevel.VERBOSE);
		logAllLevel();

		List<LogItem> logs = mLogger.getLogs();
		Assert.assertEquals(LogLevel.VERBOSE, logs.get(0).getLevel());
		Assert.assertEquals(LogLevel.DEBUG, logs.get(1).getLevel());
		Assert.assertEquals(LogLevel.INFO, logs.get(2).getLevel());
		Assert.assertEquals(LogLevel.WARNING, logs.get(3).getLevel());
		Assert.assertEquals(LogLevel.ERROR, logs.get(4).getLevel());
	}

	@Test
	public void testDebugLevel() throws Exception {
		Log.setLevel(LogLevel.DEBUG);
		logAllLevel();
		List<LogItem> logs = mLogger.getLogs();
		Assert.assertEquals(LogLevel.DEBUG, logs.get(0).getLevel());
		Assert.assertEquals(LogLevel.INFO, logs.get(1).getLevel());
		Assert.assertEquals(LogLevel.WARNING, logs.get(2).getLevel());
		Assert.assertEquals(LogLevel.ERROR, logs.get(3).getLevel());
	}
}
