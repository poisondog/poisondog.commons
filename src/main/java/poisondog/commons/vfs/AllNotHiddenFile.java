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

import org.apache.commons.vfs2.AllFileSelector;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSelectInfo;
import org.apache.commons.vfs2.FileType;
/**
 * @author Adam Huang
 */
public class AllNotHiddenFile extends AllFileSelector {
	@Override
	public boolean includeFile(FileSelectInfo fileInfo) {
		FileObject file = fileInfo.getFile();
		try{
			if(file.getType() == FileType.FOLDER)
				return false;
			if(file.isHidden())
				return false;
		}catch(Exception e) {
		}
		return super.includeFile(fileInfo);
	}

	@Override
	public boolean traverseDescendents(FileSelectInfo fileInfo) {
		FileObject file = fileInfo.getFile();
		try{
			if(file.isHidden()) {
				return false;
			}
		}catch(Exception e) {
		}
		return super.traverseDescendents(fileInfo);
	}
}
