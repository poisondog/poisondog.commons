/*
 * Copyright (C) 2017 Adam Huang <poisondog@gmail.com>
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

import poisondog.net.URLUtils;
import poisondog.core.Mission;
import java.util.List;

/**
 * @author Adam Huang
 * @since 2017-01-19
 */
public class GetSibling implements Mission<IFile> {
	private IFileFactory mFactory;

	/**
	 * Constructor
	 */
	public GetSibling(IFileFactory factory) {
		mFactory = factory;
	}

	@Override
	public List<IFile> execute(IFile current) throws Exception {
		String parentUrl = URLUtils.parentUrl(current.getUrl());
		return ((IFolder)mFactory.getFile(parentUrl)).getChildren();
	}
}
