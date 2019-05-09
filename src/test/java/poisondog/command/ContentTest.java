
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
package poisondog.command;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.json.Json;

/**
 * @author Adam Huang
 * @since 2018-01-10
 */
public class ContentTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() throws Exception {
		Content parameter = new Content("str1", "para2", "arg3");
		Assert.assertEquals(3, parameter.size());
		Assert.assertEquals("str1", parameter.get(0));
		Assert.assertEquals("para2", parameter.get(1));
		Assert.assertEquals("arg3", parameter.get(2));

		Json result = new Json(parameter.toJson());
		Content another = new Content();
		another.loadJson(result.toString());
		Assert.assertEquals(3, another.size());
		Assert.assertEquals("str1", another.get(0));
		Assert.assertEquals("para2", another.get(1));
		Assert.assertEquals("arg3", another.get(2));
	}

	@Test
	public void testMap() throws Exception {
		Content parameter = new Content();
		parameter.add("from", "2001");
		parameter.add("to", "2009");
		Assert.assertEquals(2, parameter.size());
		Assert.assertEquals("2001", parameter.get("from"));
		Assert.assertEquals("2009", parameter.get("to"));

		Json result = new Json(parameter.toJson());
		Content another = new Content();
		another.loadJson(result.toString());
		Assert.assertEquals(2, another.size());
		Assert.assertEquals("2001", another.get("from"));
		Assert.assertEquals("2009", another.get("to"));
	}

}
