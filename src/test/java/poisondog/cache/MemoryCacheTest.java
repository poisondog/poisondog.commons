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
package poisondog.cache;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;
import java.util.LinkedHashMap;

/**
 * @author Adam Huang
 */
public class MemoryCacheTest {
	private MemoryCache<String> mCache;

	@Before
	public void setUp() throws Exception {
		mCache = new MemoryCache<String>();
	}

	@Test
	public void testGetSimple() throws Exception {
		mCache.put("cache1", "result1");
		Assert.assertEquals("result1", mCache.get("cache1"));
	}

	@Test
	public void testGetNull() throws Exception {
		Assert.assertNull(mCache.get("never input"));
	}

	@Test
	public void testLinkedHashMapWithLimitSize() throws Exception {
		Map<Integer, Object> mMap = new LinkedHashMap<Integer, Object>(3) {
			@Override protected boolean removeEldestEntry(Map.Entry<Integer, Object> entry) {
				return size() > 3;
			}
		};
		mMap.put(1, "1");
		mMap.put(2, "2");
		mMap.put(3, "3");
		mMap.put(4, "4");
		mMap.put(5, "5");
		Assert.assertEquals(3, mMap.keySet().size());
		Assert.assertEquals(3, mMap.size());
		Assert.assertTrue(!mMap.keySet().contains(1));
		Assert.assertTrue(!mMap.keySet().contains(2));
		Assert.assertTrue(mMap.keySet().contains(3));
		Assert.assertTrue(mMap.keySet().contains(4));
		Assert.assertTrue(mMap.keySet().contains(5));
	}
}
