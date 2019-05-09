/*
 * Copyright (C) 2014 Adam Huang
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

import poisondog.core.Mission;
/**
 * @author Adam Huang
 */
public class MissionCache<T> implements Mission<T> {
	private Cache<T> mCache;
	private Mission<T> mMission;

	public MissionCache(Mission<T> mission, int size) {
		mCache = new MemoryCache<T>(size);
		mMission = mission;
	}

	public MissionCache(Mission<T> mission) {
		mCache = new MemoryCache<T>();
		mMission = mission;
	}

	@Override
	public Object execute(T input) throws Exception {
		Object cache = mCache.get(input);
		if (cache != null)
			return cache;
		Object result = mMission.execute(input);
		mCache.put(input, result);
		return result;
	}

	public void setCache(Cache<T> cache) {
		mCache = cache;
	}
}
