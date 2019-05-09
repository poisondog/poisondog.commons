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
package poisondog.vfs;

import java.io.File;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.io.GetResourceUrl;
import poisondog.log.Log;

/**
 * @author Adam Huang
 */
public class FileFactoryTest {
	private long time;

	@Before
	public void setUp() throws Exception {
		time = System.currentTimeMillis();
	}

	@Test
	public void testLocal() throws Exception {
		IFile file = FileFactory.getFile("file:/tmp/" + time + ".tmp");
		Assert.assertNotNull(file);
	}

	@Test
	public void testWebdav() throws Exception {
		IFile file = FileFactory.getFile("webdav://mydisk.com/demo/dila.mp4");
		Assert.assertNotNull(file);
	}

	@Test
	public void testJar() throws Exception {
		GetResourceUrl task = new GetResourceUrl();
		IFile file = FileFactory.getFile("jar:" + task.execute("tsp.jar") + "!/case/map/kroa100.map");
		Assert.assertNotNull(file);
	}

//	@Test
//	public void testCustom() throws Exception {
//		FileFactory.add("cool", new LocalFileFactory());
//		IFile file = FileFactory.getFile("cool://mydisk.com/demo/dila.mp4");
//		Assert.assertNotNull(file);
//	}

}
