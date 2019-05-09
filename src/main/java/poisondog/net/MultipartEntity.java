/*
 * Copyright (C) 2014 Adam Huang
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

import java.util.List;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
/**
 * @author Adam Huang
 */
public class MultipartEntity implements Entity{

	private List<Entity> mEntityList;
	private String mTwoHyphens = "--";
	private String mLineEnd = "\r\n";
	private String mBoundary = Long.toHexString(System.currentTimeMillis());

	public MultipartEntity() {
		mEntityList = new ArrayList<Entity>();
	}

	public String getBoundary() {
		return mBoundary;
	}

	public String getLineEnd() {
		return mLineEnd;
	}

	public String getTwoHyphens() {
		return mTwoHyphens;
	}

	@Override
	public String getContentType() {
		return "multipart/form-data; boundary=" + mBoundary;
	}

	@Override
	public String getContentDisposition() {
		throw null;
	}

	@Override
	public String getContentTransferEncoding() {
		return null;
	}

	@Override
	public byte[] getContent() {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			for (Entity entity : mEntityList) {
				outputStream.write(headerToString(entity).getBytes());
				outputStream.write(entity.getContent());
				outputStream.write(getLineEnd().getBytes());
			}
			outputStream.write(getTwoHyphens().getBytes());
			outputStream.write(getBoundary().getBytes());
			outputStream.write(getTwoHyphens().getBytes());
			outputStream.write(getLineEnd().getBytes());
		} catch(IOException e) {
			System.out.println(e);
		}
		return outputStream.toByteArray();
	}

	private String headerToString(Entity entity) {
		StringBuilder builder = new StringBuilder();
		builder.append(getTwoHyphens());
		builder.append(getBoundary());
		builder.append(getLineEnd());
		if(entity.getContentDisposition() != null) {
			builder.append("Content-Disposition: ");
			builder.append(entity.getContentDisposition());
			builder.append(getLineEnd());
		}
		if(entity.getContentTransferEncoding() != null) {
			builder.append("Content-Transfer-Encoding: ");
			builder.append(entity.getContentTransferEncoding());
			builder.append(getLineEnd());
		}
		builder.append("Content-Type: ");
		builder.append(entity.getContentType());
		builder.append(getLineEnd());
		builder.append(getLineEnd());
		return builder.toString();
	}

	public void addEntity(Entity entity) {
		mEntityList.add(entity);
	}
}
