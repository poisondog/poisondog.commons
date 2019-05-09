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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.FileRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.DateParseException;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.jackrabbit.webdav.client.methods.DavMethod;
import org.apache.jackrabbit.webdav.client.methods.DeleteMethod;
import org.apache.jackrabbit.webdav.client.methods.MkColMethod;
import org.apache.jackrabbit.webdav.client.methods.MoveMethod;
import org.apache.jackrabbit.webdav.client.methods.PropFindMethod;
import org.apache.jackrabbit.webdav.client.methods.PutMethod;
import org.apache.jackrabbit.webdav.DavConstants;
import org.apache.jackrabbit.webdav.DavException;
import org.apache.jackrabbit.webdav.MultiStatus;
import org.apache.jackrabbit.webdav.MultiStatusResponse;
import org.apache.jackrabbit.webdav.property.DavProperty;
import org.apache.jackrabbit.webdav.property.DavPropertyIterator;
import org.apache.jackrabbit.webdav.property.DavPropertyName;
import org.apache.jackrabbit.webdav.property.DavPropertyNameSet;
import org.apache.jackrabbit.webdav.property.DavPropertySet;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import poisondog.io.GetTempFolder;
import poisondog.io.StepListener;
import poisondog.log.Log;
import poisondog.net.http.HttpGet;
import poisondog.net.http.HttpParameter;
import poisondog.net.http.HttpPut;
import poisondog.net.http.HttpResponse;
import poisondog.net.http.RangeInputStream;
import poisondog.net.UploadMission;
import poisondog.net.URLUtils;
import poisondog.string.ReplacePath;
import poisondog.string.URLDecodePath;
import poisondog.vfs.FileType;
import poisondog.vfs.http.HttpInputStream;
import poisondog.vfs.http.HttpMission;
import poisondog.vfs.IFile;
import poisondog.vfs.IFileFactory;
import poisondog.net.http.HttpHead;

/**
 * @author Adam Huang
 */
public class WebdavFileFactory implements IFileFactory {
	protected HttpMission mMission;
	private WebdavUrlProcessor mTask;
	private URLDecodePath mDecode;
	private String mTempFolder;
	private StepListener mStepListener;

	/**
	 * Constructor
	 */
	public WebdavFileFactory() {
		this(new HttpMission(new HttpClient()));
	}

	/**
	 * Constructor
	 */
	public WebdavFileFactory(HttpMission mission) {
		this(mission, "http");
	}

	public WebdavFileFactory(HttpMission mission, String scheme) {
		mMission = mission;
		mTask = new WebdavUrlProcessor(scheme);
		mDecode = new URLDecodePath("utf8");
//		mTempFolder = "/tmp/";
		mTempFolder = new GetTempFolder().execute(null).getPath() + "/";
	}

	@Override
	public IFile getFile(String url) throws IOException, URISyntaxException {
		if(!URLUtils.scheme(url).equals("webdav"))
			throw new IllegalArgumentException("need to input url with 'webdav' scheme");
		if(url.endsWith("/")) {
			return new WebdavFolder(this, url);
		} else {
			return new WebdavData(this, url);
		}
	}

	public Integer execute(HttpMethod method) throws FileNotFoundException, IOException, DavException {
		Integer result = executeNotClose(method);
		method.releaseConnection();
		return result;
	}

	public Integer executeNotClose(HttpMethod method) throws FileNotFoundException, IOException, DavException {
		Integer result = mMission.execute(method);
		Log.v("Http Mission: " + mMission.getClass().getName());
		Log.v("Http Method: " + method);
		Log.v("Status Code: " + method.getStatusCode());
		return result;
	}

	public boolean delete(String url) throws FileNotFoundException, IOException, DavException {
		int response = execute(new DeleteMethod(mTask.process(url)));
		return response == HttpURLConnection.HTTP_OK || response == HttpURLConnection.HTTP_NO_CONTENT;
	}

	public boolean move(String from, String to, boolean overwrite) throws FileNotFoundException, IOException, DavException {
		return execute(new MoveMethod(mTask.process(from), mTask.process(to), overwrite)) == HttpURLConnection.HTTP_CREATED;
	}

	public boolean mkdir(String url) throws FileNotFoundException, IOException, DavException {
		int response = execute(new MkColMethod(mTask.process(url)));
		return response == HttpURLConnection.HTTP_OK || response == HttpURLConnection.HTTP_CREATED;
	}

	public boolean put(File from, String url) throws FileNotFoundException, IOException, DavException {
//		PutMethod method = new PutMethod(mTask.process(url));
//		method.setRequestEntity(new FileRequestEntity(from, URLUtils.guessContentType(from.getName())));
//		return execute(method) == HttpURLConnection.HTTP_OK;

		HttpParameter parameter = new HttpParameter();
		parameter.setUrl(mTask.process(url));
		parameter.addFile("file", from);
		parameter.setUsername(mMission.getUsername());
		parameter.setPassword(mMission.getPassword());
		if (mStepListener != null)
			parameter.setStepListener(mStepListener);

//		UploadMission mission = new UploadMission();
//		return mission.execute(parameter);

		HttpPut method = new HttpPut();
		HttpResponse response = method.execute(parameter);
		Log.v("response code: " + response.getResponseCode());
		if (response.getResponseCode() == HttpURLConnection.HTTP_CREATED)
			return true;
		if (response.getResponseCode() == HttpURLConnection.HTTP_NO_CONTENT)
			return true;
		return false;
	}

	public InputStream get(String url) throws FileNotFoundException, IOException, DavException {
//		final GetMethod getMethod = new GetMethod(mTask.process(url));
//		final int status = executeNotClose(getMethod);
//		if (status == HttpURLConnection.HTTP_NOT_FOUND) {
//			throw new FileNotFoundException(url);
//		}
//		if (status != HttpURLConnection.HTTP_OK) {
//			throw new IOException("can't get " + url + "\nbecause response code is " + status);
//		}
//		return new HttpInputStream(getMethod);

		HttpParameter para = new HttpParameter();
		para.setUrl(mTask.process(url));
		para.setUsername(mMission.getUsername());
		para.setPassword(mMission.getPassword());

//		HttpHead head = new HttpHead();
//		HttpResponse res = head.execute(para);
//		if (res.getHeader("Accept-Ranges").equals("bytes")) {
//			return new RangeInputStream(para, new HttpGet());
//		}

		HttpGet get = new HttpGet();
		HttpResponse response = get.execute(para);
		return response.getInputStream();

	}

	public boolean exists(String url) throws IOException, DavException {
		try {
			Log.v("exists: " + url);
			PropFindMethod method = new PropFindMethod(mTask.process(url), defaultPropertyNameSet(), depth0());
			int result = execute(method);
			if (result == HttpURLConnection.HTTP_UNAUTHORIZED) {
				return false;
			}
		}catch(FileNotFoundException e) {
			return false;
		}
		return true;
	}

//	public boolean isFolder(String url) throws IOException, DavException {
//		DavMethod method = new PropFindMethod(mTask.process(url), defaultPropertyNameSet(), depth0());
//		execute(method);
//		for (MultiStatusResponse response : getResponses(method)) {
//			System.out.println(getFileType(response));
//			return getFileType(response) == FileType.FOLDER;
//		}
//		return false;
//	}

	public boolean isHidden(String url) {
		if(URLUtils.file(url).startsWith("."))
			return true;
		return false;
	}

	public List<IFile> list(String url) throws FileNotFoundException, IOException, DavException {
		Log.v("list: " + url);
		DavMethod method = new PropFindMethod(mTask.process(url), defaultPropertyNameSet(), depth1());
		execute(method);
		List<IFile> result = new LinkedList<IFile>();
		for (MultiStatusResponse response : getResponses(method)) {
//			printMultiStatusResponse(response);
			String href = response.getHref();
			ReplacePath task = new ReplacePath(href);
			String childUrl = mDecode.process(task.process(url));
			if(childUrl.equals(url))
				continue;
			if(href.endsWith("/")) {
				WebdavFolder folder = new WebdavFolder(this, childUrl);
				folder.setLastModifiedTime(getLastModifiedTime(response));
				result.add(folder);
			} else {
				WebdavData data = new WebdavData(this, childUrl);
				data.setLastModifiedTime(getLastModifiedTime(response));
				data.setSize(getContentSize(response));
				result.add(data);
			}
		}
		return result;
	}

	public long getLastModifiedTime(String url) throws FileNotFoundException, IOException, DavException {
		Log.v("getLastModifiedTime: " + url);
		DavMethod method = new PropFindMethod(mTask.process(url), defaultPropertyNameSet(), depth0());
		execute(method);
		for (MultiStatusResponse response : getResponses(method)) {
			return getLastModifiedTime(response);
		}
		return 0;
	}

	public long getContentSize(String url) throws FileNotFoundException, IOException, DavException {
		Log.v("getContentSize: " + url);
		DavMethod method = new PropFindMethod(mTask.process(url), defaultPropertyNameSet(), depth0());
		execute(method);
		for (MultiStatusResponse response : getResponses(method)) {
			return getContentSize(response);
		}
		return 0;
	}

	private DavPropertyNameSet defaultPropertyNameSet() {
		DavPropertyNameSet nameSet = new DavPropertyNameSet();
		nameSet.add(DavPropertyName.create(DavConstants.PROPERTY_DISPLAYNAME));
		nameSet.add(DavPropertyName.create(DavConstants.PROPERTY_RESOURCETYPE));
		nameSet.add(DavPropertyName.create(DavConstants.PROPERTY_GETLASTMODIFIED));
		nameSet.add(DavPropertyName.create(DavConstants.PROPERTY_GETCONTENTLENGTH));
		return nameSet;
	}

	private int depth0() {
		return DavConstants.DEPTH_0;
	}

	private int depth1() {
		return DavConstants.DEPTH_1;
	}

	private MultiStatusResponse[] getResponses(DavMethod method) throws IOException, DavException {
		if(method.succeeded()) {
			MultiStatus multiStatus = method.getResponseBodyAsMultiStatus();
			return multiStatus.getResponses();
		}
		return new MultiStatusResponse[0];
	}

	private long getLastModifiedTime(MultiStatusResponse response) {
		DavPropertySet set = response.getProperties(HttpStatus.SC_OK);
		DavProperty lastModified = set.get(DavConstants.PROPERTY_GETLASTMODIFIED);
		try {
			if (lastModified != null)
				return DateUtil.parseDate((String)lastModified.getValue()).getTime();
		}catch(DateParseException e) {
		}
		return 0;
	}

	private long getContentSize(MultiStatusResponse response) {
		DavPropertySet set = response.getProperties(HttpStatus.SC_OK);
		DavProperty contentSize = set.get(DavConstants.PROPERTY_GETCONTENTLENGTH);
		if(contentSize != null)
			return Long.parseLong((String)contentSize.getValue());
		return 0;
	}

	private FileType getFileType(MultiStatusResponse response) {
		DavPropertySet set = response.getProperties(HttpStatus.SC_OK);
		DavProperty property = set.get(DavConstants.PROPERTY_RESOURCETYPE);
		Node node;
		if (property != null && (node = (Node) property.getValue()) != null) {
			if(node.getLocalName().equals(DavConstants.XML_COLLECTION))
				return FileType.FOLDER;
		}
		return FileType.DATA;
	}

	public String getTempFolder() {
		return mTempFolder;
	}

	public void setTempFolder(String tempFolder) {
		if (!tempFolder.endsWith("/"))
			throw new IllegalArgumentException("setTempFolder parameter string need ends with /");
		mTempFolder = tempFolder;
	}

	public void setHttpMission(HttpMission mission) {
		mMission = mission;
	}

	public void setStepListener(StepListener listener) {
		mStepListener = listener;
	}

	private void printMultiStatusResponse(MultiStatusResponse response) {
//		try {
//			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//			DocumentBuilder db = dbf.newDocumentBuilder();
//			Document doc = db.newDocument();
//			System.out.println("size response: " + response.toXml(doc).getTagName());
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		DavPropertySet set = response.getProperties(HttpStatus.SC_OK);
		DavPropertyIterator iterator = set.iterator();
		while (iterator.hasNext()) {
			DavProperty property = iterator.next();
//			System.out.println(property.getName() + " : " + property.getValue());
		}
//		for (DavProperty property : set) {
//			System.out.println(property.getName() + " : " + property.getValue());
//		}
//		System.out.println("content size: " + set.getContentSize());
	}

}
