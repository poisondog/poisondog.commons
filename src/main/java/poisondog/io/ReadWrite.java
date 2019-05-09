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
package poisondog.io;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.lang.ArrayUtils;
import poisondog.core.Data;

/**
 * @author Adam Huang
 * @since 2018-04-14
 */
public class ReadWrite {
	private String mEndString;

	/**
	 * Constructor
	 */
	public ReadWrite() {
		mEndString = "*~d<?\":*GGe5^}*=5,qY^ymxzhsQ&=)3d/t/WjCW}s]!=kr&UP";
	}

	/**
	 * Constructor
	 */
	public ReadWrite(String endString) {
		mEndString = endString;
	}

	public Data read(InputStream input) throws IOException {
		ReadData reader = new ReadData();
		return reader.execute(input);
	}

	public void write(Data data, OutputStream output) throws IOException {
//		byte[] result = ArrayUtils.addAll(data.getContent(), mEndString.getBytes());
		Data input = new Data(data.getContent());
		CopyTask task = new CopyTask(input.toInputStream(), output);
		task.setClose(false);
		task.transport();
	}

}
