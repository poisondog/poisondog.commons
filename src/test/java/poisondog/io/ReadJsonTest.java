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
package poisondog.io;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import poisondog.io.GetResourceUrl;
import poisondog.vfs.FileFactory;
import poisondog.vfs.IData;
import org.junit.Assert;

/**
 * @author Adam Huang
 * @since 2018-11-16
 */
public class ReadJsonTest {
	private GetResourceUrl mTask;
	private String mArray;
	private String mJson;

	@Before
	public void setUp() throws Exception {
		mTask = new GetResourceUrl();
		IData data = (IData) FileFactory.getFile(mTask.execute("array.json"));
		mArray = IOUtils.toString(data.getInputStream(), "utf8");
		data = (IData) FileFactory.getFile(mTask.execute("big.json"));
		mJson = IOUtils.toString(data.getInputStream(), "utf8");
	}

	// TODO 增加不完整Json輸入測試
	@Test
	public void testJson() throws Exception {
		InputStream input = new ByteArrayInputStream(mJson.getBytes());
		ReadJson reader = new ReadJson();
		Assert.assertEquals(reader.execute(input).toString(), mJson.replaceAll("\\s*$", ""));
		Assert.assertTrue(reader.isComplete());
	}

	// TODO 增加不完整JsonArray輸入測試
	@Test
	public void testArray() throws Exception {
		InputStream input = new ByteArrayInputStream(mArray.getBytes());
		ReadJson reader = new ReadJson();
		Assert.assertEquals(reader.execute(input).toString(), mArray.replaceAll("\\s*$", ""));
		Assert.assertTrue(reader.isComplete());
	}
}
