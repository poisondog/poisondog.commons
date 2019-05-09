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
package poisondog.json;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.io.GetResourceUrl;
import poisondog.vfs.FileFactory;
import poisondog.vfs.IData;

/**
 * @author Adam Huang
 * @since 2018-03-13
 */
public class JsonFactoryTest {
	private JsonFactory mFactory;
	private Json mJson;

	@Before
	public void setUp() throws Exception {
		mFactory = new JsonFactory();

		GetResourceUrl task = new GetResourceUrl();
		IData data = (IData) FileFactory.getFile(task.execute("sample.json"));
		mJson = new Json(IOUtils.toString(data.getInputStream(), "utf8"));
	}

	@Test
	public void testInteger() throws Exception {
		Integer i = new Integer(7);
		mFactory.execute(FileFactory.getFile("/"));
		Assert.assertTrue(true);
	}
}
