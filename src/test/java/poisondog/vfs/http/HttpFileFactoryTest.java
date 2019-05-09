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
package poisondog.vfs.http;

import java.io.FileNotFoundException;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import poisondog.net.http.HttpGet;
import poisondog.net.http.HttpHead;
import poisondog.net.http.HttpParameter;
import poisondog.net.http.HttpResponse;
import poisondog.vfs.IFile;

/**
 * @author Adam Huang
 * @since 2016-12-13
 */
public class HttpFileFactoryTest {
	private HttpFileFactory mFactory;
	private HttpGet mGet;
	private HttpHead mHead;
	private HttpResponse mGood;
	private HttpResponse mNotFound;

	@Before
	public void setUp() throws Exception {
		mFactory = new HttpFileFactory();
		mGet = Mockito.spy(new HttpGet());
		mHead = Mockito.spy(new HttpHead());
		HttpResponse response = new HttpResponse(200);
		response.setContent("Hello".getBytes());
		mGood = Mockito.spy(response);
		mNotFound = Mockito.spy(new HttpResponse(404));
		mFactory.setGet(mGet);
		mFactory.setHead(mHead);
	}

	@Test
	public void testExists() throws Exception {
		Mockito.when(mHead.execute(Mockito.any(HttpParameter.class))).thenReturn(mGood);
		Assert.assertTrue(mFactory.exists("http://google.com"));
		Mockito.when(mHead.execute(Mockito.any(HttpParameter.class))).thenThrow(new FileNotFoundException());
		Assert.assertTrue(!mFactory.exists("http://google.com/234.mp4"));
	}

	@Test
	public void testHidden() throws Exception {
		Assert.assertTrue(!mFactory.isHidden("http://google.com/folder/"));
		Assert.assertTrue(!mFactory.isHidden("http://google.com/234.mp4"));
		Assert.assertTrue(mFactory.isHidden("http://google.com/.folder/"));
		Assert.assertTrue(mFactory.isHidden("http://google.com/.234.mp4"));
	}

	@Test
	public void testGet() throws Exception {
		Mockito.when(mGet.execute(Mockito.any(HttpParameter.class))).thenReturn(mGood);
		List<String> result = IOUtils.readLines(mFactory.get("http://google.com"), "utf8");
		Assert.assertNotNull(result);
		Assert.assertEquals(1, result.size());
		Assert.assertEquals("Hello", result.get(0));
//		System.out.println("size: " + mFactory.getContentSize("https://dl.dropboxusercontent.com/u/9172363/tmp/demo1.mp4"));
//		System.out.println("last: " + mFactory.getLastModifiedTime("https://dl.dropboxusercontent.com/u/9172363/tmp/demo1.mp4"));
	}

	@Test
	public void testInstance() throws Exception {
		Mockito.when(mGet.execute(Mockito.any(HttpParameter.class))).thenReturn(mGood);
		HttpData result = (HttpData)mFactory.getFile("http://google.com");
		Assert.assertEquals("Hello", IOUtils.toString(result.getInputStream(), "utf8"));
	}

//	@Test
//	public void testList() throws Exception {
////		System.out.println(mFactory.list("http://hypem.com/download/"));
//		for (IFile file : mFactory.list("http://walton.poisondog.com:8081/nexus/content/repositories/releases/com/walton/cob/")) {
//			System.out.println(file.getUrl());
//		}
//	}

}
