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
 * @since 2017-12-01
 */
public class JsonTest {
	private Json mJson;

	@Before
	public void setUp() throws Exception {
		GetResourceUrl task = new GetResourceUrl();
		IData data = (IData) FileFactory.getFile(task.execute("sample.json"));
		mJson = new Json(IOUtils.toString(data.getInputStream(), "utf8"));
	}

	@Test
	public void testGetString() throws Exception {
		Assert.assertEquals("on", mJson.get("debug").toString());
	}

	@Test
	public void testGetBoolean() throws Exception {
		Assert.assertTrue(mJson.get("text").get("useLog").toBoolean());
	}

	@Test
	public void testGetInt() throws Exception {
		Assert.assertEquals(250, mJson.get("image").get("hOffset").toInt());
	}

	@Test
	public void testGetLong() throws Exception {
		Assert.assertEquals(250L, mJson.get("image").get("vOffset").toLong());
	}

	@Test
	public void testGetDouble() throws Exception {
		Assert.assertEquals(50.9, mJson.get("window").get("width").toDouble(), Math.pow(10, -6));
	}

	@Test
	public void testHas() throws Exception {
		Assert.assertTrue(mJson.has("window"));
		Assert.assertTrue(!mJson.has("windows"));
	}

	@Test
	public void testGetOnValue() throws Exception {
		try {
			mJson.get("debug").get("width");
			Assert.fail("debug is just a string value, not Json object");
		} catch(Exception e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testEquals() throws Exception {
		Assert.assertEquals(new Json("250"), mJson.get("image").get("vOffset"));
		Assert.assertTrue(!new Json("250").equals("250"));
	}

	@Test
	public void testWhenObjectUseIndex() throws Exception {
		try {
			mJson.get(0);
			Assert.fail("need to throws Exception");
		} catch(Exception e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testSize() throws Exception {
		Assert.assertEquals(4, mJson.size());
	}

	@Test
	public void testGetSizeWhenContent() throws Exception {
		try {
			mJson.get("debug").size();
			Assert.fail("debug is content, no size");
		} catch(Exception e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testArray() throws Exception {
		Json array = mJson.get("text").get("offset");
		Assert.assertEquals(new Json("249.9"), array.get(0));
		Assert.assertEquals(new Json("233.1"), array.get(1));
		Assert.assertEquals(2, array.size());
		for (Json obj : array) {
			Assert.assertNotNull(obj);
		}
		Assert.assertEquals("[249.9,233.1]", array.toString().replaceAll("\\s", ""));
	}
}
