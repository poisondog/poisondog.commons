/*
 * Copyright (C) 2018 Adam Huang <poisondog@gmail.com>
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

import java.io.InputStream;
import java.io.OutputStream;
import poisondog.core.Mission;
import poisondog.util.Pair;

/**
 * @author Adam Huang
 * @since 2018-04-13
 */
public class CopyFactory implements Mission<Pair<InputStream, OutputStream>> {

	@Override
	public CopyTask execute(Pair<InputStream, OutputStream> pair) throws Exception {
		return new CopyTask(pair.getValue1(), pair.getValue2());
	}
}
