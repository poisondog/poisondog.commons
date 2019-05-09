/*
 * Copyright (C) 2016 Adam Huang <poisondog@gmail.com>
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
import java.io.ObjectOutputStream;
import java.io.Serializable;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2016-11-17
 */
public class ObjectToFile implements Mission<ObjectToFile.Parameter>{

	@Override
	public File execute(ObjectToFile.Parameter parameter) throws FileNotFoundException, IOException {
		File file = new File(parameter.mFilename);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
		oos.writeObject(parameter.mTarget);
		oos.close();
		return file;
	}

	public class Parameter {
		private String mFilename;
		private Serializable mTarget;

		/**
		 * Constructor
		 */
		public Parameter(String filename, Serializable target) {
			mFilename = filename;
			mTarget = target;
		}

	}

}
