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

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;

/**
 * @author Adam Huang
 */
public class MemoryCache<S> implements Cache<S>{
	private Map<S, Object> mMap;
	private int mSize;

	/**
	 * Constructor
	 */
	public MemoryCache() {
		this(100);
	}

	public MemoryCache(int size) {
		mSize = size;
//		mMap = new HashMap<S, Object>();
		mMap = new LinkedHashMap<S, Object>(mSize) {
			@Override protected boolean removeEldestEntry(Map.Entry<S, Object> entry) {
				return size() > mSize;
			}
		};
	}

	@Override
	public void put(S key, Object value) {
		mMap.put(key, value);
	}

	@Override
	public Object get(S key) {
		return mMap.get(key);
	}
}
