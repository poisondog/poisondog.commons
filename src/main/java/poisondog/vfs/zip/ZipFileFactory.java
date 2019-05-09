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
package poisondog.vfs.zip;

import poisondog.vfs.IFile;
import poisondog.vfs.IFileFactory;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;

/**
 * @author Adam Huang
 * @since 2016-10-15
 */
public class ZipFileFactory implements IFileFactory {

	@Override
	public IFile getFile(String url) throws FileSystemException {
		FileObject jarFile = VFS.getManager().resolveFile(url);
		if (url.endsWith("/"))
			return new ZipFolder(this, url, jarFile);
		else
			return new ZipData(url, jarFile);
	}
}
