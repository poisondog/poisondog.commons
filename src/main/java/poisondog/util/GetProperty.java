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
package poisondog.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 */
public class GetProperty implements Mission<String> {
	private Properties mProperties;

	public GetProperty(String path) throws FileNotFoundException, IOException {
		mProperties = new Properties();
		mProperties.load(new FileInputStream(new File(path)));
	}

	public static GetProperty local() throws FileNotFoundException, IOException {
		return new GetProperty("local.properties");
	}

	@Override
	public String execute(String key) {
		return mProperties.getProperty(key);
	}
}
