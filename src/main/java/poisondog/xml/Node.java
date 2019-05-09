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
package poisondog.xml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * @author Adam Huang
 */
public class Node {
	private Node mParent;
	private List<Node> mChlidren;
	private Map<String, String> mAttribute;
	private String mName;
	private String mText;
	private Comparator<Node> mComparator;

	public Node(final String name) {
		mChlidren = new ArrayList<Node>();
		mAttribute = new HashMap<String, String>();
		mName = name;
		mText = "";
	}

	public String getName() {
		return mName;
	}

	public void setAttribute(String key, String value) {
		mAttribute.put(key, value);
	}

	public String getAttribute(String key) {
		return mAttribute.get(key);
	}

	public Set<String> getAttributes() {
		return mAttribute.keySet();
	}

	public Node getParent() {
		return mParent;
	}

	public void setParent(Node parent) {
		mParent = parent;
	}

	public void addChild(Node node) {
		node.setParent(this);
		mChlidren.add(node);
	}

	public void removeChild(int index) {
		getChild(index).setParent(null);
		mChlidren.remove(index);
	}

	public void removeChild(Node n) {
		mChlidren.remove(n);
	}

	public Node getChild(int index) {
		return mChlidren.get(index);
	}

	public List<Node> getChildren() {
		return mChlidren;
	}

	public void setText(String text) {
		mText = text;
	}

	public String getText() {
		return mText;
	}

	public String toXML() {
		StringBuilder builder = new StringBuilder();
		builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		builder.append(toString());
		return builder.toString();
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("<");
		builder.append(mName);
		for (String attribute : getAttributes()) {
			builder.append(" ");
			builder.append(attribute);
			builder.append("=\"");
			builder.append(getAttribute(attribute));
			builder.append("\"");
		}
		builder.append(">");
		for (Node n : mChlidren) {
			builder.append(n.toString());
		}
		builder.append(mText);
		builder.append("</");
		builder.append(mName);
		builder.append(">");
		return builder.toString();
	}
}
