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
package poisondog.vfs.filter;

import java.util.ArrayList;
import java.util.Collection;
import poisondog.core.Mission;
import poisondog.rule.Rule;
import poisondog.rule.TrueRule;
import poisondog.vfs.FileType;
import poisondog.vfs.IFile;
import poisondog.vfs.IFolder;

/**
 * @author Adam Huang
 */
public class FileFilter implements Mission<Collection<IFile>> {
	private Rule<IFile> mIncludeRule;
	private Rule<IFile> mTraverseRule;

	public FileFilter() {
		mIncludeRule = new TrueRule<IFile>();
		mTraverseRule = new TrueRule<IFile>();
	}

	public void setIncludeRule(Rule<IFile> rule) {
		mIncludeRule = rule;
	}

	public void setTraverseRule(Rule<IFile> rule) {
		mTraverseRule = rule;
	}

	public Collection<IFile> execute(Collection<IFile> files) throws Exception {
		Collection<IFile> result = new ArrayList<IFile>();
		for (IFile file : files) {
			if (mIncludeRule.execute(file))
				result.add(file);
			if (mTraverseRule.execute(file) && file.getType() == FileType.FOLDER) {
				IFolder folder = (IFolder) file;
				result.addAll(execute(folder.getChildren()));
			}
		}
		return result;
	}
}
