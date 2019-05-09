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
package poisondog.util;

import org.apache.commons.lang.builder.HashCodeBuilder;
/**
 * @author Adam Huang
 */
public class Pair<S, T> {
	public S mValue1;
	public T mValue2;

	public Pair(S value1, T value2) {
		mValue1 = value1;
		mValue2 = value2;
	}

	public S getValue1() {
		return mValue1;
	}

	public T getValue2() {
		return mValue2;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Pair))
			return false;
		Pair another = (Pair)obj;
		if (!mValue1.equals(another.mValue1))
			return false;
		if (!mValue2.equals(another.mValue2))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(mValue1);
		builder.append(mValue2);
		return builder.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		builder.append(mValue1.toString());
		builder.append(",");
		builder.append(mValue2.toString());
		builder.append("]");
		return builder.toString();
	}

}
