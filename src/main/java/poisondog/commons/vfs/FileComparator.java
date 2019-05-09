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
package poisondog.commons.vfs;

import java.util.Comparator;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileType;
/**
 * @author Adam Huang
 */
public class FileComparator implements Comparator<FileObject> {
	
	public int compare(FileObject obj1, FileObject obj2) {
		try{
			if(obj1.getType() == FileType.FOLDER && obj2.getType() !=FileType.FOLDER)
				return -1;
			if(obj1.getType() != FileType.FOLDER && obj2.getType() ==FileType.FOLDER)
				return 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return obj1.getName().getBaseName().compareTo(obj2.getName().getBaseName());
	}
}
