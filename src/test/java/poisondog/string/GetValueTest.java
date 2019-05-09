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
package poisondog.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 * @since 2017-07-06
 */
public class GetValueTest {
	private GetValue task;

	@Before
	public void setUp() throws Exception {
		task = new GetValue();
	}

	@Test
	public void testValueFromEqualSign() throws Exception {
		Assert.assertEquals("A-1304-0079", task.execute("ID=A-1304-0079"));
		Assert.assertEquals("A-1304-0091", task.execute("ID=A-1304-0091"));
	}

	@Test
	public void testValueFromColon() throws Exception {
		Assert.assertEquals("A-1304-0079", task.execute("ID:A-1304-0079"));
		Assert.assertEquals("A-1304-0091", task.execute("ID:A-1304-0091"));
	}

}
