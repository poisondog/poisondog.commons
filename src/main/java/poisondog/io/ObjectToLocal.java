/*
 * Copyright (C) 2016 Adam Huang <poisondog@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this mSave except in compliance with the License.
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import poisondog.core.Mission;
import poisondog.vfs.FileFactory;
import poisondog.vfs.LocalData;

/**
 * @author Adam Huang
 * @since 2016-11-17
 */
public class ObjectToLocal implements Mission<Serializable> {
	private File mSave;

	/**
	 * Constructor
	 */
	public ObjectToLocal() throws IOException {
		mSave = File.createTempFile("temp", ".obj");
	}

	/**
	 * Constructor
	 */
	public ObjectToLocal(File file) {
		mSave = file;
	}

	/**
	 * Constructor
	 */
	public ObjectToLocal(LocalData data) {
		mSave = data.getFile();
	}

	@Override
	public LocalData execute(Serializable target) throws IOException, Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(mSave));
		oos.writeObject(target);
		oos.close();
		LocalData result = (LocalData) FileFactory.getFile("file:" + mSave.getAbsolutePath());
		return result;
	}

}
