/*
 * Copyright (C) 2017 Adam Huang <poisondog@gmail.com>
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
package poisondog.net.http;

import java.io.InputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import poisondog.log.Log;

/**
 * @author Adam Huang
 * @since 2017-12-07
 */
public class RangeInputStream extends InputStream {
	private HttpParameter mParameter;
	private HttpMethod mMethod;
	private List<Byte> mContent;
	private long mRange;
	private long mStartByte;
	private long mLength;
	private boolean mEnd;

	/**
	 * Constructor
	 */
	public RangeInputStream(HttpParameter parameter, HttpMethod method) {
		mParameter = parameter;
		mMethod = method;
		mContent = new LinkedList<Byte>();
		mRange = (long)Math.pow(2, 20);
//		mRange = 100L;
		mStartByte = 0;
		mLength = -1;
		mEnd = false;
	}

	private void loadLength() throws IOException {
		if (mLength < 0) {
			HttpHead head = new HttpHead();
			HttpResponse res = head.execute(mParameter);
			mLength = Long.parseLong(res.getHeader("Content-Length"));
		}
	}

	private void loadContent() throws Exception {
		if (mStartByte > mLength) {
			return;
		}
		long endByte = mStartByte + (mRange - 1);
		Log.v("bytes=" + mStartByte + "-" + endByte);
		mParameter.addHeader("RANGE", "bytes=" + mStartByte + "-" + endByte);
		HttpResponse response = (HttpResponse)mMethod.execute(mParameter);
		for (byte b : response.getContent()) {
			mContent.add(b);
		}
//		response.getInputStream().close();
		mStartByte += mRange;
	}

	@Override
	public int read() throws IOException {
//		Log.v("mContent.size(): " + mContent.size());
		loadLength();
		if (mContent.isEmpty()) {
			if (mStartByte > mLength) {
				mEnd = true;
				return -1;
			}
			try {
				loadContent();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mContent.remove(0);
	}

	@Override
	public int read(byte[] b) throws IOException {
		loadLength();
		if (mEnd)
			return -1;
		for (int i = 0; i < b.length; i++) {
			int a = read();
			b[i] = (byte)a;
			if (mEnd)
				return i;
		}
		return b.length;
//		return read(b, 0, b.length);
	}

//	@Override
//	public int read(byte[] b, int off, int len) throws IOException {
//		loadLength();
//		if (mEnd)
//			return -1;
//		for (int i = 0; i < len; i++) {
//			int a = read();
////			if (a == -1 && i == 0 && mStartByte > mLength) {
////				return -1;
////			}
//			b[i + off] = (byte)a;
//			if (a == -1)
//				return i;
//		}
//		return len;
//	}

	@Override
	public void reset() throws IOException {
		super.reset();
		mStartByte = 0;
		mEnd = false;
	}

}
