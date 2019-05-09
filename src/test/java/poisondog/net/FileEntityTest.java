/*
 * Copyright (C) 2013 Adam Huang <poisondog@gmail.com>
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
package poisondog.net;

import java.io.File;
import java.io.FileOutputStream;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.commons.HashFunction;
/**
 * @author Adam Huang
 */
public class FileEntityTest {
	private File mTempJPG;
	private File mTempTXT;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mTempJPG = File.createTempFile("sk2", ".jpg");
		mTempTXT = File.createTempFile("sk2", ".txt");
	}

	@After
	public void tearDown() throws Exception {
		mTempJPG.delete();
		mTempTXT.delete();
	}

	@Test
	public void testContentType() throws Exception {
		FileEntity entity1 = new FileEntity("image", mTempJPG);
		Assert.assertEquals("image/jpeg", entity1.getContentType());

		FileEntity entity2 = new FileEntity("text", mTempTXT);
		Assert.assertEquals("text/plain", entity2.getContentType());
	}

	@Test
	public void testContentDisposition() throws Exception {
		FileEntity entity1 = new FileEntity("image", mTempJPG);
		StringBuilder result1 = new StringBuilder();
		result1.append("form-data; ");
		result1.append("name=\"image\"; ");
		result1.append("filename=\"");
		result1.append(mTempJPG.getName());
		result1.append("\"");
		Assert.assertEquals(result1.toString(), entity1.getContentDisposition());

		FileEntity entity2 = new FileEntity("text", mTempTXT);
		StringBuilder result2 = new StringBuilder();
		result2.append("form-data; ");
		result2.append("name=\"text\"; ");
		result2.append("filename=\"");
		result2.append(mTempTXT.getName());
		result2.append("\"");
		Assert.assertEquals(result2.toString(), entity2.getContentDisposition());
	}

	@Test
	public void testContentTransferEncoding() throws Exception {
		FileEntity entity1 = new FileEntity("image", mTempJPG);
		String result = "binary";
		Assert.assertEquals(result, entity1.getContentTransferEncoding());
	}

	@Test
	public void testContent() throws Exception {
		IOUtils.write("test", new FileOutputStream(mTempJPG), "utf8");
		FileEntity entity1 = new FileEntity("image", mTempJPG);
		String expected = HashFunction.md5("test");
		String result = HashFunction.md5(entity1.getContent());
		Assert.assertEquals(expected, result);
	}
}
