/*
 * Copyright (C) 2014 Adam Huang <poisondog@gmail.com>
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
package poisondog.vfs.webdav;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.apache.commons.httpclient.HttpClient;
import org.apache.jackrabbit.webdav.client.methods.MkColMethod;
import org.apache.jackrabbit.webdav.client.methods.DeleteMethod;
import org.apache.jackrabbit.webdav.client.methods.DavMethod;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import poisondog.net.URLUtils;

/**
 * @author poisondog <poisondog@gmail.com>
 */
public class WebdavUrlProcessorTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testExecute() throws Exception {
		WebdavUrlProcessor task = new WebdavUrlProcessor();
		Assert.assertEquals("http://122.133.144.21:89/demo/%E4%B8%AD%20%E6%96%87", task.process("webdav://122.133.144.21:89/demo/中 文"));
		Assert.assertEquals("http://122.133.144.21:89/demo/%E4%B8%AD%20%E6%96%87/", task.process("webdav://122.133.144.21:89/demo/中 文/"));
		Assert.assertEquals("http://122.133.144.21:89/demo/%40%2343.jpg", task.process("webdav://122.133.144.21:89/demo/@#43.jpg"));
//		Assert.assertEquals("http://122.133.144.21:89/demo/%40%2343.jpg", task.process("webdav://122.133.144.21:89/demo/@$,..jpg"));
	}
}
