/*
 * Copyright (C) 2014 Adam Huang <poisondog@gmail.com>
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
package poisondog.vfs;

/**
 * @author Adam Huang
 */
public interface IFile {
	public FileType getType() throws Exception;
	public String getUrl() throws Exception;
	public String getName() throws Exception;
	public boolean move(IFile file) throws Exception;
	public boolean delete() throws Exception;
	public boolean isExists() throws Exception;
	public boolean isHidden() throws Exception;
	public boolean create() throws Exception;
	public long getLastModifiedTime() throws Exception;
}
