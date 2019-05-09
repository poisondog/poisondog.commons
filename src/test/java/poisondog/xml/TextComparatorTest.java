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
package poisondog.xml;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
/**
 * @author poisondog <poisondog@gmail.com>
 */
public class TextComparatorTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSort() throws Exception {
		Node node1 = new Node("d");
		Node node2 = new Node("f");
		Node node3 = new Node("d");

		node1.setText("1");
		node2.setText("2");
		node3.setText("3");

		List<Node> list = new ArrayList<Node>();
		list.add(node2);
		list.add(node1);
		list.add(node3);

		Collections.sort(list, new TextComparator());
		Assert.assertEquals(node1, list.get(0));
		Assert.assertEquals(node2, list.get(1));
		Assert.assertEquals(node3, list.get(2));
	}
}
