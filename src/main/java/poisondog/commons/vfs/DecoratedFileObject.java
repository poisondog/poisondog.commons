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

import java.net.URL;
import java.util.List;
import org.apache.commons.vfs2.FileContent;
import org.apache.commons.vfs2.FileName;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSelector;
import org.apache.commons.vfs2.FileSystem;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.NameScope;
import org.apache.commons.vfs2.operations.FileOperations;
/**
 * @author Adam Huang
 */
public class DecoratedFileObject implements FileObject {
	private FileObject decoratedFileObject;

	public DecoratedFileObject(FileObject decoratedFileObject) {
		this.decoratedFileObject = decoratedFileObject;
	}

	@Override
	public boolean canRenameTo(FileObject newfile) {
		return decoratedFileObject.canRenameTo(newfile);
	}

	@Override
	public void close() throws FileSystemException {
		decoratedFileObject.close();
	}

	@Override
	public void copyFrom(FileObject srcFile, FileSelector selector) throws FileSystemException {
		decoratedFileObject.copyFrom(srcFile, selector);
	}

	@Override
	public void createFile() throws FileSystemException {
		decoratedFileObject.createFile();
	}

	@Override
	public void createFolder() throws FileSystemException {
		decoratedFileObject.createFolder();
	}

	@Override
	public boolean delete() throws FileSystemException {
		return decoratedFileObject.delete();
	}

	@Override
	public int delete(FileSelector selector) throws FileSystemException {
		return decoratedFileObject.delete(selector);
	}

	@Override
	public boolean exists() throws FileSystemException {
		return decoratedFileObject.exists();
	}

	@Override
	public void findFiles(FileSelector selector, boolean depthwise, List<FileObject> selected) throws FileSystemException {
		decoratedFileObject.findFiles(selector, depthwise, selected);
	}

	@Override
	public FileObject[] findFiles(FileSelector selector) throws FileSystemException {
		return decoratedFileObject.findFiles(selector);
	}

	@Override
	public FileObject getChild(String name) throws FileSystemException {
		return decoratedFileObject.getChild(name);
	}

	@Override
	public FileObject[] getChildren() throws FileSystemException {
		return decoratedFileObject.getChildren();
	}

	@Override
	public FileContent getContent() throws FileSystemException {
		return decoratedFileObject.getContent();
	}

	@Override
	public FileSystem getFileSystem() {
		return decoratedFileObject.getFileSystem();
	}

	@Override
	public FileName getName() {
		return decoratedFileObject.getName();
	}

	@Override
	public FileObject getParent() throws FileSystemException {
		return decoratedFileObject.getParent();
	}

	@Override
	public FileType getType() throws FileSystemException {
		return decoratedFileObject.getType();
	}

	@Override
	public URL getURL() throws FileSystemException {
		return decoratedFileObject.getURL();
	}

	@Override
	public boolean isHidden() throws FileSystemException {
		return decoratedFileObject.isHidden();
	}

	@Override
	public boolean isReadable() throws FileSystemException {
		return decoratedFileObject.isReadable();
	}

	@Override
	public boolean isWriteable() throws FileSystemException {
		return decoratedFileObject.isWriteable();
	}

	@Override
	public void moveTo(FileObject destFile) throws FileSystemException {
		decoratedFileObject.moveTo(destFile);
	}

	@Override
	public FileObject resolveFile(String name, NameScope scope) throws FileSystemException {
		return decoratedFileObject.resolveFile(name, scope);
	}

	@Override
	public FileObject resolveFile(String path) throws FileSystemException {
		return decoratedFileObject.resolveFile(path);
	}

	@Override
	public void refresh() throws FileSystemException {
		decoratedFileObject.refresh();
	}

	@Override
	public boolean isAttached() {
		return decoratedFileObject.isAttached();
	}

	@Override
	public boolean isContentOpen() {
		return decoratedFileObject.isContentOpen();
	}

	@Override
	public String toString() {
		return decoratedFileObject.toString();
	}

	@Override
	public FileOperations getFileOperations() throws FileSystemException {
		return decoratedFileObject.getFileOperations();
	}

	public FileObject getDecoratedFileObject() {
		return decoratedFileObject;
	}

	public void setDecoratedFileObject(FileObject decorated) {
		decoratedFileObject = decorated;
	}
}
