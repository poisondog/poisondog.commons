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
package poisondog.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 */
public class CreateRandomFile implements Mission<Long> {
	private Random mRandom;
	private int mBufferSize;

	/**
	 * Constructor
	 */
	public CreateRandomFile() {
		mRandom = new Random();
		mBufferSize = 1024;
	}

	@Override
	public File execute(Long size) throws IOException {
		Long current = size;
		File file = File.createTempFile("random", ".tmp");
		FileOutputStream output = new FileOutputStream(file);
		while (current > 0) {
			int buffer = mBufferSize;
			if (current < mBufferSize)
				buffer = Math.toIntExact(current);
			byte[] content = new byte[buffer];
			mRandom.nextBytes(content);
			output.write(content);
			current -= buffer;
		}
		return file;
	}
}
