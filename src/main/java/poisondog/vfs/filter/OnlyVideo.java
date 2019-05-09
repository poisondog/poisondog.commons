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
package poisondog.vfs.filter;

import poisondog.net.URLUtils;
import poisondog.rule.Rule;
import poisondog.vfs.IFile;

/**
 * @author Adam Huang
 * @since 2017-02-13
 */
public class OnlyVideo implements Rule<IFile>{

	@Override
	public Boolean execute(IFile file) throws Exception {
		if (URLUtils.guessContentType(file.getName()).startsWith("video/"))
			return true;
		return false;
	}
}
