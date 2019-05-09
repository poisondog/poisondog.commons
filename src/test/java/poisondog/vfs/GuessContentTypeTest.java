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
package poisondog.vfs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 * @since 2018-09-25
 */
public class GuessContentTypeTest {
	private GuessContentType mTask;

	@Before
	public void setUp() throws Exception {
		mTask = new GuessContentType();
	}

	@Test
	public void testImage() throws Exception {
		Assert.assertEquals("image/bmp", mTask.execute("hohoho.bmp"));
		Assert.assertEquals("image/png", mTask.execute("hohoho.png"));
		Assert.assertEquals("image/gif", mTask.execute("hohoho.gif"));
		Assert.assertEquals("image/jpeg", mTask.execute("hohoho.jpg"));
		Assert.assertEquals("image/jpeg", mTask.execute("hohoho.JPG"));
		Assert.assertEquals("image/jpeg", mTask.execute("hohoho.jpeg"));
		Assert.assertEquals("image/jpeg", mTask.execute("hohoho.jpe"));
		Assert.assertEquals("image/jpeg", mTask.execute("@$%#.jpe"));
		Assert.assertEquals("image/jpeg", mTask.execute("webdav://111.111.111.111:8080/box/sata/123#%.JPG"));
	}

	@Test
	public void testAudio() throws Exception {
		Assert.assertEquals("audio/mpeg", mTask.execute("hihihi.mp3"));
		Assert.assertEquals("audio/x-ms-wma", mTask.execute("hihihi.wma"));
		Assert.assertEquals("audio/flac", mTask.execute("mp4.rmvb.flac"));
		Assert.assertEquals("audio/x-matroska", mTask.execute("mp4.rmvb.mka"));
	}

	@Test
	public void testVideo() throws Exception {
		Assert.assertEquals("video/avi", mTask.execute("aaa.mp3.avi"));
		Assert.assertEquals("video/quicktime", mTask.execute("aaa.mp3.mov"));
		Assert.assertEquals("video/mpeg", mTask.execute("aaa.mp3.mpg"));
		Assert.assertEquals("video/mp4", mTask.execute("mp4.rmvb.mp4"));
		Assert.assertEquals("video/3gpp", mTask.execute("mp4.rmvb.3gp"));
		Assert.assertEquals("video/3gpp", mTask.execute("mp4.rmvb.3gpp"));
		Assert.assertEquals("video/x-ms-wmv", mTask.execute("mp4.rmvb.wmv"));
		Assert.assertEquals("video/x-flv", mTask.execute("mp4.rmvb.flv"));
		Assert.assertEquals("video/x-matroska", mTask.execute("mp4.rmvb.mkv"));
	}

	@Test
	public void testName() throws Exception {
		Assert.assertEquals("application/pdf", mTask.execute("kkk.jpeg.pdf"));
		Assert.assertEquals("application/zip", mTask.execute("kkk.jpeg.zip"));
		Assert.assertEquals("application/rar", mTask.execute("kkk.jpeg.rar"));
		Assert.assertEquals("application/vnd.ms-powerpoint", mTask.execute("mp4.rmvb.ppt"));
		Assert.assertEquals("application/vnd.openxmlformats-officedocument.presentationml.presentation", mTask.execute("mp4.rmvb.pptx"));
		Assert.assertEquals("application/vnd.ms-powerpoint", mTask.execute("mp4.rmvb.pps"));
		Assert.assertEquals("application/msword", mTask.execute("mp4.rmvb.doc"));
		Assert.assertEquals("application/vnd.openxmlformats-officedocument.wordprocessingml.document", mTask.execute("mp4.rmvb.docx"));
		Assert.assertEquals("application/vnd.ms-excel", mTask.execute("mp4.rmvb.xls"));
		Assert.assertEquals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", mTask.execute("mp4.rmvb.xlsx"));
		Assert.assertEquals("application/x-7z-compressed", mTask.execute("kkk.jpeg.7z"));
	}

	@Test
	public void testUnknownContentType() throws Exception {
		Assert.assertEquals("application/octet-stream", mTask.execute("hohoho.ot2k919fq98h3r1218f128"));
		Assert.assertEquals("application/octet-stream", mTask.execute("pdf"));
		Assert.assertEquals("application/octet-stream", mTask.execute("PDF"));
		Assert.assertEquals("application/octet-stream", mTask.execute("Movie"));
		Assert.assertEquals("application/octet-stream", mTask.execute("PPT"));
	}

	@Test
	public void testInode() throws Exception {
		Assert.assertEquals("inode/directory", mTask.execute(""));
	}

}
