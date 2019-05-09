/*
 * Copyright (C) 2016 Adam Huang <poisondog@gmail.com>
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
package poisondog.vfs.zip;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.io.GetResourceUrl;
import poisondog.vfs.IFile;
import org.apache.commons.io.IOUtils;

/**
 * @author Adam Huang
 * @since 2016-10-15
 */
public class ZipFileFactoryTest {
	private ZipFileFactory mFactory;

	@Before
	public void setUp() throws Exception {
		mFactory = new ZipFileFactory();
	}

	@Test
	public void testInputStream() throws Exception {
		GetResourceUrl task = new GetResourceUrl();
		System.out.println(task.execute("Daily_2016_09_08.zip"));
		ZipData file = (ZipData)mFactory.getFile("zip:" + task.execute("Daily_2016_09_08.zip") + "!Daily_2016_09_08.rpt");
		for (String line : IOUtils.readLines(file.getInputStream(), "big5")) {
			Assert.assertEquals("交易日期,商品代號,到期月份(週別),成交時間,成交價格,成交數量(B+S),近月價格,遠月價格,開盤集合競價 ", line);
			break;
		}
	}
}
