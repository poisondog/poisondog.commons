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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 基本的二元樹資料結構。
 * @author Adam Huang
 */
public class BinaryTree<E> implements Cloneable {
	private BinaryTree<E> parent;
	private BinaryTree<E> leftTree;
	private BinaryTree<E> rightTree;
	private E data;

	public static int LeftTree = 0;
	public static int RightTree = 1;

	protected BinaryTree() {
	}

	/**
	 * 建構子。
	 * @param object 為此二元樹節點所儲存的內容值
	 */
	public BinaryTree(E object) {
		this.data = object;
		this.parent = new EmptyTree<E>();
		this.leftTree = new EmptyTree<E>();
		this.rightTree = new EmptyTree<E>();
	}

	/**
	 * 設定二元樹的左子樹
	 * @param left
	 */
	public void setLeftTree(BinaryTree<E> left) {
		leftTree.parent = new EmptyTree<E>();

		if (left == null) {
			leftTree = new EmptyTree<E>();
		} else {
			leftTree = left;
			leftTree.parent = this;
		}
	}

	/**
	 * 設定二元樹的右子樹
	 * @param right
	 */
	public void setRightTree(BinaryTree<E> right) {
		rightTree.parent = new EmptyTree<E>();

		if (right == null) {
			rightTree = new EmptyTree<E>();
		} else {
			rightTree = right;
			rightTree.parent = this;
		}

	}

	/**
	 * 取得二元樹的左子樹
	 * @return
	 */
	public BinaryTree<E> getLeftTree() {
		return leftTree;
	}

	/**
	 * 取得二元樹的右子樹
	 * @return
	 */
	public BinaryTree<E> getRightTree() {
		return rightTree;
	}

	/**
	 * 取得二元樹的內容值
	 * @return
	 */
	public E getData() {
		return data;
	}

	/**
	 * 設定二元樹的內容值
	 * @param data
	 */
	public void setData(E data) {
		this.data = data;
	}

	public boolean isEmpty() {
		return false;
	}

	/**
	 * 依據前序走訪，搜尋內容值為e的二元樹，若未找到則回傳null。
	 * @param e
	 * @return
	 */
	public BinaryTree<E> find(E e) {
		for (BinaryTree<E> result : this.preorder()) {
			if (result.getData().equals(e))
				return result;
		}
		return null;
	}

	public BinaryTree<E> parent() {
		return parent;
	}

	/**
	 * @param temp
	 * @return
	 */
	public boolean remove(BinaryTree<E> temp) {
		BinaryTree<E> parent = temp.parent();
		if (parent.isEmpty())
			return false;

		if (parent.getLeftTree().equals(temp))
			parent.setLeftTree(new EmptyTree<E>());
		else if (parent.getRightTree().equals(temp))
			parent.setRightTree(new EmptyTree<E>());
		return true;
	}

	/**
	 * 取得目前二元樹的前序。
	 * @return 二元樹的前序走訪串列
	 */
	public List<BinaryTree<E>> preorder() {
		List<BinaryTree<E>> list = new ArrayList<BinaryTree<E>>();
		preorder(this, list);
		return list;
	}

	private void preorder(BinaryTree<E> tree, List<BinaryTree<E>> list) {
		list.add(tree);

		if (!tree.getLeftTree().isEmpty())
			preorder(tree.getLeftTree(), list);

		if (!tree.getRightTree().isEmpty())
			preorder(tree.getRightTree(), list);
	}

	/**
	 * 取得目前二元樹的中序。
	 * @return 二元樹的中序走訪串列
	 */
	public List<BinaryTree<E>> inorder() {
		List<BinaryTree<E>> list = new ArrayList<BinaryTree<E>>();
		inorder(this, list);
		return list;
	}

	private void inorder(BinaryTree<E> tree, List<BinaryTree<E>> list) {
		if (!tree.getLeftTree().isEmpty())
			inorder(tree.getLeftTree(), list);

		list.add(tree);

		if (!tree.getRightTree().isEmpty())
			inorder(tree.getRightTree(), list);
	}

	/**
	 * 取得目前二元樹的後序。
	 * @return 二元樹的後序走訪串列
	 */
	public List<BinaryTree<E>> postorder() {
		List<BinaryTree<E>> list = new ArrayList<BinaryTree<E>>();
		postorder(this, list);
		return list;
	}

	private void postorder(BinaryTree<E> tree, List<BinaryTree<E>> list) {
		if (!tree.getLeftTree().isEmpty())
			postorder(tree.getLeftTree(), list);

		if (!tree.getRightTree().isEmpty())
			postorder(tree.getRightTree(), list);

		list.add(tree);
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		BinaryTree<?> target = (BinaryTree<?>) obj;
		if (!this.getLeftTree().equals(target.getLeftTree()))
			return false;
		if (!this.getRightTree().equals(target.getRightTree()))
			return false;
		return this.getData().equals(target.getData());
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(this.getLeftTree());
		builder.append(this.getRightTree());
		builder.append(this.getData());
		return builder.toHashCode();
	}

	/*
	 * @see java.lang.Object#clone()
	 */
	@Override
	public BinaryTree<E> clone() {
		BinaryTree<E> clone = new BinaryTree<E>(data);
		if (!this.getLeftTree().isEmpty()) {
			clone.setLeftTree(this.getLeftTree().clone());
		}
		if (!this.getRightTree().isEmpty()) {
			clone.setRightTree(this.getRightTree().clone());
		}

		return clone;
	}

	/**
	 * @return
	 */
	public Collection<BinaryTree<E>> noLeftChild() {
		return noLeftChild(this);
	}

	private Collection<BinaryTree<E>> noLeftChild(BinaryTree<E> tree) {
		LinkedList<BinaryTree<E>> result = new LinkedList<BinaryTree<E>>();
		if (tree.getLeftTree().isEmpty()) {
			result.add(tree);
		} else {
			result.addAll(noLeftChild(tree.getLeftTree()));
		}
		if (!tree.getRightTree().isEmpty()) {
			result.addAll(noLeftChild(tree.getRightTree()));
		}
		return result;
	}

	/**
	 * @return
	 */
	public Collection<BinaryTree<E>> noRightChild() {
		return noRightChild(this);
	}

	private Collection<BinaryTree<E>> noRightChild(BinaryTree<E> tree) {
		LinkedList<BinaryTree<E>> result = new LinkedList<BinaryTree<E>>();
		if (tree.getRightTree().isEmpty()) {
			result.add(tree);
		} else {
			result.addAll(noRightChild(tree.getRightTree()));
		}
		if (!tree.getLeftTree().isEmpty()) {
			result.addAll(noRightChild(tree.getLeftTree()));
		}
		return result;
	}

	/**
	 * @return
	 */
	public Collection<BinaryTree<E>> notTwoChildParent() {
		HashSet<BinaryTree<E>> set = new HashSet<BinaryTree<E>>();
		set.addAll(noLeftChild());
		set.addAll(noRightChild());
		return set;
	}
}
