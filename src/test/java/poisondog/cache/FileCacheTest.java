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
import poisondog.core.NoMission;

/**
 * @author Adam Huang
 */
public class FileCacheTest {
	private FileCache<String> mCache;

	@Before
	public void setUp() throws Exception {
		mCache = new FileCache<String>();
	}

	@Test
	public void testGetSimple() throws Exception {
//		mCache.put("cache1", "result1");
//		Assert.assertEquals("result1", mCache.get("cache1"));
	}

	@Test
	public void testGetNull() throws Exception {
		Assert.assertNull(mCache.get("never input"));
	}

	@Test
	public void testSetCache() throws Exception {
		MissionCache<String> mission = new MissionCache<String>(new NoMission<String>());
		mission.setCache(mCache);
	}
}
