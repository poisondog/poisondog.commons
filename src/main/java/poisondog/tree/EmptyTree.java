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
package poisondog.tree;

import org.apache.commons.lang.builder.HashCodeBuilder;
/**
 * @author Adam Huang
 */
public class EmptyTree<E> extends BinaryTree<E> {
	/*
	 * @see poisondog.tree.BinaryTree#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return true;
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof EmptyTree)) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append("EmptyTree");
		return builder.toHashCode();
	}

	public void setData(E data) {
		throw new IllegalArgumentException("EmptyTree can't finish this method");
	}

	/*
	 * @see poisondog.tree.BinaryTree#setLeftTree(poisondog.common.BinaryTree)
	 */
	@Override
	public void setLeftTree(BinaryTree<E> leftTree) {
		throw new IllegalArgumentException("EmptyTree can't finish this method");
	}

	/*
	 * @see poisondog.tree.BinaryTree#setRightTree(poisondog.common.BinaryTree)
	 */
	@Override
	public void setRightTree(BinaryTree<E> rightTree) {
		throw new IllegalArgumentException("EmptyTree can't finish this method");
	}
}
