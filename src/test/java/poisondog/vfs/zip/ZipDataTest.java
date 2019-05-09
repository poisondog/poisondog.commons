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
package poisondog.vfs.zip;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.io.GetResourceUrl;
import org.apache.commons.io.IOUtils;

/**
 * @author Adam Huang
 * @since 2018-05-16
 */
public class ZipDataTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testContent() throws Exception {
		GetResourceUrl task = new GetResourceUrl();
		ZipFileFactory factory = new ZipFileFactory();
		ZipData data = (ZipData) factory.getFile("zip:" + task.execute("Daily_2016_09_08.zip") + "!/Daily_2016_09_08.rpt");
		String content = IOUtils.toString(data.getInputStream());
		Assert.assertTrue(!content.isEmpty());
	}

	@Test
	public void testJarContent() throws Exception {
		GetResourceUrl task = new GetResourceUrl();
		ZipFileFactory factory = new ZipFileFactory();
		ZipData data = (ZipData) factory.getFile("jar:" + task.execute("tsp.jar") + "!/case/map/kroa100.map");
		String content = IOUtils.toString(data.getInputStream());
		Assert.assertTrue(!content.isEmpty());
	}

}
