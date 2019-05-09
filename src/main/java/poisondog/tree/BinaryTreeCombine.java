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
package poisondog.tree;

import java.util.ArrayList;
import java.util.List;
import poisondog.core.Mission;
/**
 * 以目前的二元樹為基礎，將參數中的二元樹組合在一起，且回傳所有可能的組合。
 * @author Adam Huang
 */
public class BinaryTreeCombine<E> implements Mission<BinaryTree<E>> {
	private BinaryTree<E> root;

	public BinaryTreeCombine(BinaryTree<E> root) {
		this.root = root;
	}

	@Override
	public List<BinaryTree<E>> execute(BinaryTree<E> target) {
		return combines(root, root, target);
	}

	private List<BinaryTree<E>> combines(BinaryTree<E> root, BinaryTree<E> now, BinaryTree<E> target) {
		List<BinaryTree<E>> results = new ArrayList<BinaryTree<E>>();
		if (!now.getLeftTree().isEmpty()) {
			results.addAll(combines(root, now.getLeftTree(), target));
		} else {
			now.setLeftTree(target);
			results.add(root.clone());
			now.setLeftTree(new EmptyTree<E>());
		}
		if (!now.getRightTree().isEmpty()) {
			results.addAll(combines(root, now.getRightTree(), target));
		} else {
			now.setRightTree(target);
			results.add(root.clone());
			now.setRightTree(new EmptyTree<E>());
		}
		return results;
	}
}
