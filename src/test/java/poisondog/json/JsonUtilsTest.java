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
package poisondog.json;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.HashSet;
import java.util.Collection;
import org.mockito.Mockito;

/**
 * @author Adam Huang
 * @since 2016-03-26
 */
public class JsonUtilsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testToJsonArray() throws Exception {
		Jsonable json1 = Mockito.mock(Jsonable.class);
		Jsonable json2 = Mockito.mock(Jsonable.class);
		Jsonable json3 = Mockito.mock(Jsonable.class);
		Mockito.when(json1.toJson()).thenReturn("{\"a\":3}");
		Mockito.when(json2.toJson()).thenReturn("{\"b\":3.1}");
		Mockito.when(json3.toJson()).thenReturn("{\"c\":\"apple\"}");
		Collection<Jsonable> input = new HashSet<Jsonable>();
		input.add(json1);
		input.add(json2);
		input.add(json3);
		System.out.println(JsonUtils.toJSONArray(input));
		Mockito.verify(json1).toJson();
		Mockito.verify(json2).toJson();
		Mockito.verify(json3).toJson();
	}
}
