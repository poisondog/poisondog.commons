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
package poisondog.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ClassNotFoundException;
import java.security.NoSuchAlgorithmException;
import poisondog.commons.HashFunction;
import poisondog.io.GetTempFolder;
/**
 * @author Adam Huang
 */
public class FileCache<S extends Object> implements Cache<S>{
	private File mFolder;
	private HashFunction mHash;

	public FileCache() {
		mFolder = new GetTempFolder().execute(null);
//		mHash = new HashFunction("MD5");
		mHash = new HashFunction("SHA-256");
	}

	public void setFolder(File folder) {
		mFolder = folder;
	}

	@Override
	public void put(S obj, Object value) throws FileNotFoundException, IOException {
		String key = obj.toString();
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(mFolder + "/" + mHash.execute(key.getBytes())));
			output.writeObject(value);
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object get(S obj) throws IOException, ClassNotFoundException {
		String key = obj.toString();
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(mFolder + "/" + mHash.execute(key.getBytes())));
			return input.readObject();
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch(FileNotFoundException e) {
		}
		return null;
	}
}
