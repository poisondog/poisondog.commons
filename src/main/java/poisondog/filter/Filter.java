/*
 * Copyright (C) 2018 Adam Huang <poisondog@gmail.com>
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
package poisondog.filter;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Adam Huang
 * @since 2018-07-28
 */
public class Filter {
	private Type mType;
	private String mKey;
	private Object mValue;
	private List<Filter> mContent;

	/**
	 * Constructor
	 */
	private Filter(Type Type) {
		mType = Type;
		mContent = new ArrayList<Filter>();
	}

	/**
	 * Constructor
	 */
	private Filter(String key, Type Type, Object value) {
		mKey = key;
		mType = Type;
		mValue = value;
	}

	public void add(Filter filter) {
		if (mContent == null)
			throw new IllegalArgumentException("this filter is condition not group");
		mContent.add(filter);
	}

	public Type getType() {
		return mType;
	}

	public String getKey() {
		if (mKey == null)
			throw new IllegalArgumentException("this filter is group not condition");
		return mKey;
	}

	public Object getValue() {
		if (mValue == null)
			throw new IllegalArgumentException("this filter is group not condition");
		return mValue;
	}

	public List<Filter> getContent() {
		if (mContent == null)
			throw new IllegalArgumentException("this filter is condition not group");
		return mContent;
	}

	public static Filter eq(String key, Object value) {
		return new Filter(key, Type.Equal, value);
	}

	public static Filter gt(String key, Object value) {
		return new Filter(key, Type.GreaterThan, value);
	}

	public static Filter lt(String key, Object value) {
		return new Filter(key, Type.LessThan, value);
	}

	public static Filter not(Filter condition) {
		if (condition.getType() == Type.Equal) {
			return new Filter(condition.getKey(), Type.NotEqual, condition.getValue());
		}
		if (condition.getType() == Type.GreaterThan) {
			return new Filter(condition.getKey(), Type.LessEqual, condition.getValue());
		}
		if (condition.getType() == Type.LessThan) {
			return new Filter(condition.getKey(), Type.GreaterEqual, condition.getValue());
		}
		if (condition.getType() == Type.NotEqual) {
			return new Filter(condition.getKey(), Type.Equal, condition.getValue());
		}
		if (condition.getType() == Type.LessEqual) {
			return new Filter(condition.getKey(), Type.GreaterThan, condition.getValue());
		}
		if (condition.getType() == Type.GreaterEqual) {
			return new Filter(condition.getKey(), Type.LessThan, condition.getValue());
		}
		if (condition.getType() == Type.AND) {
			List<Filter> result = new ArrayList<Filter>();
			for (Filter f : condition.getContent()) {
				result.add(Filter.not(f));
			}
			return Filter.or(result.toArray(new Filter[0]));
		}
		if (condition.getType() == Type.OR) {
			List<Filter> result = new ArrayList<Filter>();
			for (Filter f : condition.getContent()) {
				result.add(Filter.not(f));
			}
			return Filter.and(result.toArray(new Filter[0]));
		}
		return null;
	}

	public static Filter and(Filter... conditions) {
		if (conditions.length < 2)
			throw new IllegalArgumentException("conditions size less then 2");
		Filter and = new Filter(Type.AND);
		for (Filter pair : conditions) {
			and.add(pair);
		}
		return and;
	}

	public static Filter or(Filter... conditions) {
		if (conditions.length < 2)
			throw new IllegalArgumentException("conditions size less then 2");
		Filter or = new Filter(Type.OR);
		for (Filter pair : conditions) {
			or.add(pair);
		}
		return or;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Filter)) {
			return false;
		}
		Filter another = (Filter) obj;
		if (!getType().equals(another.getType()))
			return false;
		if (mContent == null) {
			if (!getKey().equals(another.getKey()))
				return false;
			if (!getValue().equals(another.getValue()))
				return false;
		} else {
			if (!mContent.containsAll(another.getContent())) {
				return false;
			}
			if (!another.getContent().containsAll(mContent)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		JSONObject obj = new JSONObject();
		obj.put("class", getClass().getName());
		obj.put("type", mType);
		obj.put("key", mKey);
		obj.put("value", mValue);
		JSONArray array = new JSONArray();
		if (mContent != null) {
			for (Filter filter : mContent) {
				array.put(new JSONObject(filter.toString()));
			}
			obj.put("content", array);
		}
		return obj.toString();
	}

}

