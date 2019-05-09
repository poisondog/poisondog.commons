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
package poisondog.io;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import org.apache.commons.io.IOUtils;
import poisondog.core.Mission;
import poisondog.io.GetTempFolder;
import poisondog.util.GetDayString;
import poisondog.vfs.FileFactory;
import poisondog.vfs.IData;
import poisondog.vfs.IFolder;
/**
 * @author Adam Huang
 */
public class WriteToText implements Mission<Collection<?>> {
	private IData mTarget;

	public WriteToText() {
		try {
			File folder = new GetTempFolder().execute(null);
			GetDayString task = new GetDayString();
			String today = task.execute(System.currentTimeMillis()).replaceAll("\\D", "");
			mTarget = (IData)FileFactory.getFile(folder.getPath() + "/" + today + ".txt");
		} catch(Exception e) {
			System.err.println("can't find temp folder.");
			e.printStackTrace();
		}
	}

	public WriteToText(IFolder folder) throws Exception {
		GetDayString task = new GetDayString();
		String today = task.execute(System.currentTimeMillis()).replaceAll("\\D", "");
		mTarget = (IData)FileFactory.getFile(folder.getUrl() + today + ".txt");
	}

	public WriteToText(IData target) {
		mTarget = target;
	}

	@Override
	public Boolean execute(Collection<?> list) throws IOException, Exception {
		mTarget.create();
		IOUtils.writeLines(list, null, mTarget.getOutputStream(), "utf8");
		System.out.println("Save to: " + mTarget.getUrl());
		return true;
	}
}
