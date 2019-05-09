/*
 * Copyright (C) 2010 Adam Huang <poisondog@gmail.com>
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

/**
 * @author Adam Huang
 */
public class EqualsChecker {

	public boolean equivalence(Object o1, Object o2, Object o3) {
		if(!reflexive(o1))
			return false;
		if(!reflexive(o2))
			return false;
		if(!reflexive(o3))
			return false;

		if(!symmetric(o1, o2))
			return false;
		if(!symmetric(o2, o3))
			return false;
		if(!symmetric(o3, o1))
			return false;
		
		if(!transitive(o1, o2, o3))
			return false;

		if (o1.equals(null))
			return false;
		if (o2.equals(null))
			return false;
		if (o3.equals(null))
			return false;

		return true;
	}

	public boolean hashCode(Object o1, Object o2, Object o3) {
		if(!reflexive(o1.hashCode()))
			return false;
		if(!reflexive(o2.hashCode()))
			return false;
		if(!reflexive(o3.hashCode()))
			return false;
		
		if(!symmetric(o1.hashCode(), o2.hashCode()))
			return false;
		if(!symmetric(o2.hashCode(), o3.hashCode()))
			return false;
		if(!symmetric(o3.hashCode(), o1.hashCode()))
			return false;

		if(!transitive(o1.hashCode(), o2.hashCode(), o3.hashCode()))
			return false;

		return true;
	}

	/**
	 * @param o
	 * @return 
	 */
	public boolean reflexive(Object o) {
		if (!o.equals(o))
			return false;
		return true;
	}

	/**
	 * @param o1
	 * @param o2
	 * @return
	 */
	public boolean symmetric(Object o1, Object o2) {
		if (!o1.equals(o2))
			return false;
		if (!o2.equals(o1))
			return false;
		return true;
	}

	/**
	 * @param o1
	 * @param o2
	 * @param o3
	 * @return
	 */
	public boolean transitive(Object o1, Object o2, Object o3) {
		if (!o1.equals(o2))
			return false;
		if (!o2.equals(o3))
			return false;
		if (!o3.equals(o1))
			return false;
		return true;
	}
}
