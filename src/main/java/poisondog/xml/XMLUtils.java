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

import java.io.InputStream;
import java.util.Iterator;
import org.dom4j.Element;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
/**
 * @author Adam Huang
 */
public class XMLUtils {
	public static Node parse(InputStream context) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(context);
		Element root = doc.getRootElement();
		return createNode(root);
	}
	
	private static Node createNode(Element element) {
		Node node = new Node(element.getQName().getName());
		for (int i = 0; i < element.attributeCount(); i++) {
			Attribute attribute = element.attribute(i);
			node.setAttribute(attribute.getQName().getName(), attribute.getValue());
		}

		for (Iterator i = element.elementIterator(); i.hasNext(); ) {
			Element e = (Element) i.next();
			node.addChild(createNode(e));
		}

		node.setText(element.getText());
		return node;
	}
}
