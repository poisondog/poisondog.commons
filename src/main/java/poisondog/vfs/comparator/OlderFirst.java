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
package poisondog.vfs.comparator;

import java.util.Comparator;
import poisondog.vfs.FileType;
import poisondog.vfs.IFile;
/**
 * @author Adam Huang
 */
public class OlderFirst implements Comparator<IFile> {
	public int compare(IFile obj1, IFile obj2) {
		try{
			if(obj1.getType() == FileType.FOLDER && obj2.getType() !=FileType.FOLDER)
				return -1;
			if(obj1.getType() != FileType.FOLDER && obj2.getType() ==FileType.FOLDER)
				return 1;
			if (obj1.getLastModifiedTime() > obj2.getLastModifiedTime())
				return 1;
			if (obj1.getLastModifiedTime() < obj2.getLastModifiedTime())
				return -1;
			return 0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
