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
package poisondog.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2019-01-03
 */
public class BinarySearch implements Mission<List>{
	private Object mKey;
	private Comparator mComparator;
	private Type mType = Type.Equal;
	private Integer mGreater = -1;
	private Integer mLess = -1;
	private Integer mMiddle;
	private List mContent;

	/**
	 * Constructor
	 */
	private BinarySearch(Type type, Object key) {
		mType = type;
		mKey = key;
		mComparator = new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2) {
				return o1.toString().compareTo(o2.toString());
			}
		};
	}

	public static BinarySearch equal(Object key) {
		return new BinarySearch(Type.Equal, key);
	}

	public static BinarySearch greater(Object key) {
		return new BinarySearch(Type.Greater, key);
	}

	public static BinarySearch less(Object key) {
		return new BinarySearch(Type.Less, key);
	}

	public static BinarySearch nearest(Object key) {
		return new BinarySearch(Type.Nearest, key);
	}

	public void setComparator(Comparator comparator) {
		mComparator = comparator;
	}

	@Override
	public Integer execute(List list) {
		mContent = list;
		search(0, list.size() - 1);

		if (mType == Type.Greater)
			return mGreater;
		else if (mType == Type.Less)
			return mLess;
		else if (mType == Type.Nearest)
			return getNearest();
		return mMiddle;
	}

	public int getGreater() {
		return mGreater;
	}

	public int getLess() {
		return mLess;
	}

	public int getNearest() {
		if (mMiddle >= 0 && mMiddle < mContent.size())
			return mMiddle;
		ArrayList<Integer> temp = new ArrayList<Integer>();
		if (mGreater >= 0 && mGreater < mContent.size())
			temp.add(mGreater);
		if (mLess >= 0 && mLess < mContent.size())
			temp.add(mLess);
		int nearest = -1;
		int distance = Integer.MAX_VALUE;
		for (Integer index : temp) {
			int dd = Math.abs(mComparator.compare(mContent.get(index), mKey));
			if (dd < distance) {
				nearest = index;
				distance = dd;
			}
		}
		return nearest;
	}

	private void search(int start, int end) {
		if (start > end) {
			mGreater = start >= mContent.size() ? -1 : start;
			mLess = end;
			mMiddle = -1;
			return;
		}
		mMiddle = start + (end - start) / 2;
		if (mComparator.compare(mContent.get(mMiddle), mKey) < 0) {
			search(mMiddle + 1, end);
			return;
		}
		if (mComparator.compare(mContent.get(mMiddle), mKey) > 0) {
			search(start, mMiddle - 1);
			return;
		}
		mGreater = mMiddle + 1 >= mContent.size() ? -1 : mMiddle + 1;
		mLess = mMiddle - 1;
		return;
	}
}

enum Type {
	Equal, Greater, Less, Nearest
}
