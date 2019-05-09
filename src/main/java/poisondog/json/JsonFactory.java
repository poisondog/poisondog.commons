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
package poisondog.json;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2018-03-13
 */
public class JsonFactory implements Mission<Object> {

	@Override
	public String execute(Object obj) throws IntrospectionException {
		PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(obj.getClass()).getPropertyDescriptors();
		List<String> propertyNames = new ArrayList<String>(propertyDescriptors.length);
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			propertyNames.add(propertyDescriptor.getName());
			System.out.println(propertyDescriptor.getPropertyType() + "\t" + propertyDescriptor.getName());
		}
		return null;
	}
}
