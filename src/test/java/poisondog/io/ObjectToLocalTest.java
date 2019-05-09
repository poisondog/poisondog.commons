/*
 * Copyright (C) 2019 Adam Huang <poisondog@gmail.com>
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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.vfs.IData;
import java.util.ArrayList;

/**
 * @author Adam Huang
 * @since 2019-03-12
 */
public class ObjectToLocalTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testApp() throws Exception {
		ArrayList<Integer> target = new ArrayList<Integer>();
		target.add(1);
		target.add(2);
		target.add(3);
		target.add(4);

		ObjectToLocal task = new ObjectToLocal();
		IData data = task.execute(target);

		IDataToObject reader = new IDataToObject();
		ArrayList result = (ArrayList) reader.execute(data);

		Assert.assertEquals(4, result.size());
		Assert.assertEquals(1, result.get(0));
		Assert.assertEquals(2, result.get(1));
		Assert.assertEquals(3, result.get(2));
		Assert.assertEquals(4, result.get(3));
		data.delete();
	}
}
