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

import java.util.ArrayList;
import java.util.Collection;
/**
 * @author Adam Huang
 */
public class UniqueList<E> extends ArrayList<E> {

	public UniqueList() {
		super();
	}

	public UniqueList(int initialCapacity) {
		super(initialCapacity);
	}

	public UniqueList(Collection<E> collection) {
		super(collection);
	}

	@Override
	public boolean add(E e) {
		if (this.contains(e)) {
			return false;
		}
		return super.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> collection) {
		Collection<E> copy = new ArrayList<E>(collection);
		copy.removeAll(this);
		return super.addAll(copy);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> collection) {
		Collection<E> copy = new ArrayList<E>(collection);
		copy.removeAll(this);
		return super.addAll(index, copy);
	}

	@Override
	public void add(int index, E element) {
		if (this.contains(element)) {
			return;
		}
		super.add(index, element);
	}
}
