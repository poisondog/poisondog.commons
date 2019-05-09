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
package poisondog.log;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 * @since 2017-06-07
 */
public class LogTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testApp() throws Exception {
		Log.setLevel(LogLevel.VERBOSE);
		Log.v("verbose");
		Log.d("debug");
		Assert.assertTrue(true);
	}

	@Test
	public void testOnlyMessage() throws Exception {
		Log.setOnlyMessage(true);
		Log.i("only message");
	}

	@Test
	public void testSimple() throws Exception {
		Log.setSimpleClass(true);
		Log.w("warning message!");
		Log.e("error message!");
	}

}
