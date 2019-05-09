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
package poisondog.util;

import java.util.LinkedList;
import java.util.Collection;

/**
 * keep last item in list
 * @author Adam Huang
 */
public class LastList<E> extends LinkedList<E> {
	private int mSize;

	public LastList(int size) {
		mSize = size;
	}

	@Override
	public boolean add(E e) {
		boolean result = super.add(e);
		if (size() > mSize)
			removeFirst();
		return result;
	}

	@Override
	public boolean addAll(Collection<? extends E> collection) {
		boolean result = super.addAll(collection);
		while(size() > mSize)
			removeFirst();
		return result;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> collection) {
		boolean result = super.addAll(index, collection);
		while(size() > mSize)
			removeFirst();
		return result;
	}

	@Override
	public void add(int index, E element) {
		super.add(index, element);
		if (size() > mSize)
			removeFirst();
	}
}
