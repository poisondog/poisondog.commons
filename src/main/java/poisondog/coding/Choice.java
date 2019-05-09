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
package poisondog.coding;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author Adam Huang
 */
public class Choice<T> implements Cloneable {
	private List<T> mAll;
	private int mIndex;
	private Random mRandom;

	public Choice() {
		mAll = new LinkedList<T>();
		mRandom = new Random();
	}

	public void add(T i) {
		if (indexOf(i) >= 0)
			return;
		mAll.add(i);
	}

	public T get() {
		return mAll.get(mIndex);
	}

	public int getIndex() {
		return mIndex;
	}

	public void setIndex(int index) {
		mIndex = index;
	}

	public void set(T obj) {
		if (indexOf(obj) < 0)
			add(obj);
		setIndex(indexOf(obj));
	}

	public List<T> getAll() {
		return mAll;
	}

	public void random() {
		mIndex = mRandom.nextInt(mAll.size());
	}

	public boolean contains(T i) {
		return mAll.contains(i);
	}

	public int indexOf(T d) {
		return mAll.indexOf(d);
	}

	public void clear() {
		mAll.clear();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Choice))
			return false;
		Choice another = (Choice)obj;
		if (!mAll.equals(another.mAll))
			return false;
		if (mIndex != another.mIndex)
			return false;
		return true;
	}

	@Override
	public Choice clone() {
		Choice result = new Choice();
		result.mAll = mAll;
		result.mIndex = mIndex;
		return result;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Index: ");
		builder.append(mIndex);
		builder.append("--> ");
		builder.append(get());
		builder.append("\t");
		return builder.toString();
	}
}
