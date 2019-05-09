/*
 * Copyright (C) 2019 Adam Huang <poisondog@gmail.com>
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
package poisondog.io;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import poisondog.core.Data;
import poisondog.core.PullPush;
import org.apache.commons.lang.ArrayUtils;

/**
 * @author Adam Huang
 * @since 2019-01-15
 */
public class SendRecv implements PullPush<Data> {
	private OutputStream mOutput;
	private String mEndString;

	/**
	 * Constructor
	 */
	public SendRecv(OutputStream output, String endString) {
		mOutput = output;
		mEndString = endString;
	}

	/**
	 * Constructor
	 */
	public SendRecv(OutputStream output) {
		this(output, null);
	}

	/**
	 * Constructor
	 */
	public SendRecv(String endString) {
		this(null, endString);
	}

	/**
	 * Constructor
	 */
	public SendRecv() {
		this(null, null);
	}

	@Override
	public Data pull(Object input) throws IOException {
		if (mEndString == null) {
			ReadData reader = new ReadData();
			return reader.execute((InputStream)input);
		} else {
			ReadData reader = new ReadData(mEndString);
			return reader.execute((InputStream)input);
		}
	}

	@Override
	public OutputStream push(Data data) throws IOException {
		byte[] temp = data.getContent();
		if (mEndString != null)
			temp = ArrayUtils.addAll(data.getContent(), mEndString.getBytes());
		Data input = new Data(temp);
		CopyTask task = new CopyTask(input.toInputStream(), mOutput);
		task.setClose(false);
		task.run();
		return mOutput;
	}
}
