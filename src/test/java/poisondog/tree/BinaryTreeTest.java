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

import java.util.Collection;
import java.util.List;
import java.util.Random;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import poisondog.util.EqualsChecker;
/**
 * @author Adam Huang
 */
public class BinaryTreeTest extends TestCase {
	private BinaryTree<String> aTree;
	private BinaryTree<String> cTree;
	private BinaryTree<String> bTree;
	private BinaryTree<String> dTree;
	private BinaryTree<String> eTree;
	private BinaryTree<String> fTree;
	private BinaryTree<String> gTree;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		aTree = new BinaryTree<String>("A");
		bTree = new BinaryTree<String>("B");
		cTree = new BinaryTree<String>("C");
		dTree = new BinaryTree<String>("D");
		eTree = new BinaryTree<String>("E");
		fTree = new BinaryTree<String>("F");
		gTree = new BinaryTree<String>("G");

		assertEquals("A", aTree.getData());
		assertTrue(aTree.getLeftTree().isEmpty());
		assertTrue(aTree.getRightTree().isEmpty());

		aTree.setLeftTree(bTree);
		aTree.setRightTree(cTree);

		bTree.setLeftTree(dTree);
		bTree.setRightTree(eTree);

		cTree.setLeftTree(fTree);
		cTree.setRightTree(gTree);
	}

	@Test
	public void testLeftNode() throws Exception {
		assertEquals(bTree, aTree.getLeftTree());
	}

	@Test
	public void testRightNode() throws Exception {
		assertEquals(cTree, aTree.getRightTree());
	}

	@Test
	public void testPreorder1() throws Exception {
		BinaryTree<String> t1 = new BinaryTree<String>("1");
		BinaryTree<String> t2 = new BinaryTree<String>("2");
		BinaryTree<String> t3 = new BinaryTree<String>("3");
		BinaryTree<String> t4 = new BinaryTree<String>("4");

		t1.setLeftTree(t2);
		t1.setRightTree(t3);
		t3.setLeftTree(t4);

		List<BinaryTree<String>> preorder = t1.preorder();
		Assert.assertEquals(t1, preorder.get(0));
		Assert.assertEquals(t2, preorder.get(1));
		Assert.assertEquals(t3, preorder.get(2));
		Assert.assertEquals(t4, preorder.get(3));
	}

	@Test
	public void testPreorder2() throws Exception {
		List<BinaryTree<String>> preorder = aTree.preorder();
		assertEquals(aTree, preorder.get(0));
		assertEquals(bTree, preorder.get(1));
		assertEquals(dTree, preorder.get(2));
		assertEquals(eTree, preorder.get(3));
		assertEquals(cTree, preorder.get(4));
		assertEquals(fTree, preorder.get(5));
		assertEquals(gTree, preorder.get(6));
	}

	@Test
	public void testInorder1() throws Exception {
		BinaryTree<String> t1 = new BinaryTree<String>("1");
		BinaryTree<String> t2 = new BinaryTree<String>("2");
		BinaryTree<String> t3 = new BinaryTree<String>("3");
		BinaryTree<String> t4 = new BinaryTree<String>("4");

		t2.setLeftTree(t4);
		t4.setRightTree(t3);
		t4.setLeftTree(t1);

		List<BinaryTree<String>> inorder = t2.inorder();
		assertEquals(t1, inorder.get(0));
		assertEquals(t4, inorder.get(1));
		assertEquals(t3, inorder.get(2));
		assertEquals(t2, inorder.get(3));
	}

	@Test
	public void testInorder2() throws Exception {
		List<BinaryTree<String>> inorder = aTree.inorder();
		assertEquals(dTree, inorder.get(0));
		assertEquals(bTree, inorder.get(1));
		assertEquals(eTree, inorder.get(2));
		assertEquals(aTree, inorder.get(3));
		assertEquals(fTree, inorder.get(4));
		assertEquals(cTree, inorder.get(5));
		assertEquals(gTree, inorder.get(6));
	}

	@Test
	public void testPostorder1() throws Exception {
		BinaryTree<String> t1 = new BinaryTree<String>("1");
		BinaryTree<String> t2 = new BinaryTree<String>("2");
		BinaryTree<String> t3 = new BinaryTree<String>("3");
		BinaryTree<String> t4 = new BinaryTree<String>("4");

		t4.setRightTree(t2);
		t2.setRightTree(t1);
		t1.setRightTree(t3);

		List<BinaryTree<String>> postorder = t4.postorder();
		assertEquals(t3, postorder.get(0));
		assertEquals(t1, postorder.get(1));
		assertEquals(t2, postorder.get(2));
		assertEquals(t4, postorder.get(3));
	}

	@Test
	public void testPostorder2() throws Exception {
		List<BinaryTree<String>> postorder = aTree.postorder();
		assertEquals(dTree, postorder.get(0));
		assertEquals(eTree, postorder.get(1));
		assertEquals(bTree, postorder.get(2));
		assertEquals(fTree, postorder.get(3));
		assertEquals(gTree, postorder.get(4));
		assertEquals(cTree, postorder.get(5));
		assertEquals(aTree, postorder.get(6));
	}

	@Test
	public void testClone() throws Exception {
		BinaryTree<String> clone = aTree.clone();
		assertTrue(aTree != clone);
		assertTrue(aTree.getClass() == clone.getClass());
		assertTrue(aTree.equals(clone));
	}

	@Test
	public void testFind() throws Exception {
		BinaryTree<String> a = new BinaryTree<String>("A");
		BinaryTree<String> b = new BinaryTree<String>("B");
		BinaryTree<String> c = new BinaryTree<String>("C");
		BinaryTree<String> d = new BinaryTree<String>("D");
		BinaryTree<String> e = new BinaryTree<String>("E");

		a.setLeftTree(b);
		a.setRightTree(d);
		b.setRightTree(c);
		d.setRightTree(e);

		BinaryTree<String> result = a.find("D");

		BinaryTreeDataFilter<String> onlyData = new BinaryTreeDataFilter<String>();
		assertTrue(onlyData.execute(result.inorder()).equals(onlyData.execute(d.inorder())));
		assertTrue(onlyData.execute(result.preorder()).equals(onlyData.execute(d.preorder())));
	}

	@Test
	public void testRemove() throws Exception {
		BinaryTree<String> a = new BinaryTree<String>("A");
		BinaryTree<String> b = new BinaryTree<String>("B");
		BinaryTree<String> c = new BinaryTree<String>("C");
		BinaryTree<String> d = new BinaryTree<String>("D");
		BinaryTree<String> e = new BinaryTree<String>("E");

		a.setLeftTree(b);
		b.setRightTree(c);

		d.setRightTree(e);

		BinaryTreeCombine<String> mission = new BinaryTreeCombine<String>(a);
		List<BinaryTree<String>> combine = mission.execute(d);

		Random random = new Random();
		BinaryTree<String> target = combine.get(random.nextInt(combine.size()));

		BinaryTreeDataFilter<String> onlyData = new BinaryTreeDataFilter<String>();
		if (target.remove(target.find("D"))) {
			assertTrue(onlyData.execute(target.inorder()).equals(onlyData.execute(a.inorder())));
			assertTrue(onlyData.execute(target.preorder()).equals(onlyData.execute(a.preorder())));
		}
	}

	@Test
	public void testParent() throws Exception {
		BinaryTree<String> a = new BinaryTree<String>("A");
		BinaryTree<String> b = new BinaryTree<String>("B");
		BinaryTree<String> c = new BinaryTree<String>("C");

		a.setLeftTree(b);
		a.setRightTree(c);

		assertFalse(b.parent().isEmpty());
		assertFalse(c.parent().isEmpty());

		assertEquals(a, b.parent());
		assertEquals(a, c.parent());

		assertEquals("A", b.parent().getData());
		assertEquals("A", c.parent().getData());
	}

	@Test
	public void testEquals() throws Exception {
		BinaryTree<String> tree1 = new BinaryTree<String>("A");
		BinaryTree<String> tree2 = new BinaryTree<String>("B");
		BinaryTree<String> tree3 = new BinaryTree<String>("C");
		BinaryTree<String> tree4 = new BinaryTree<String>("D");
		BinaryTree<String> tree5 = new BinaryTree<String>("E");
		BinaryTree<String> tree6 = new BinaryTree<String>("F");
		BinaryTree<String> tree7 = new BinaryTree<String>("G");

		tree1.setLeftTree(tree2);
		tree1.setRightTree(tree3);

		tree2.setLeftTree(tree4);
		tree2.setRightTree(tree5);

		tree3.setLeftTree(tree6);
		tree3.setRightTree(tree7);

		BinaryTree<String> t1 = new BinaryTree<String>("A");
		BinaryTree<String> t2 = new BinaryTree<String>("B");
		BinaryTree<String> t3 = new BinaryTree<String>("C");
		BinaryTree<String> t4 = new BinaryTree<String>("D");
		BinaryTree<String> t5 = new BinaryTree<String>("E");
		BinaryTree<String> t6 = new BinaryTree<String>("F");
		BinaryTree<String> t7 = new BinaryTree<String>("G");

		t1.setLeftTree(t2);
		t1.setRightTree(t3);

		t2.setLeftTree(t4);
		t2.setRightTree(t5);

		t3.setLeftTree(t6);
		t3.setRightTree(t7);

		EqualsChecker checker = new EqualsChecker();
		assertTrue(checker.equivalence(aTree, tree1, t1));
		assertTrue(checker.hashCode(aTree, tree1, t1));

		BinaryTree<String> n1 = new BinaryTree<String>("A");
		BinaryTree<String> n2 = new BinaryTree<String>("B");
		BinaryTree<String> n3 = new BinaryTree<String>("C");
		BinaryTree<String> n4 = new BinaryTree<String>("D");
		BinaryTree<String> n5 = new BinaryTree<String>("K");
		BinaryTree<String> n6 = new BinaryTree<String>("F");
		BinaryTree<String> n7 = new BinaryTree<String>("G");

		n1.setLeftTree(n2);
		n1.setRightTree(n3);

		n2.setLeftTree(n4);
		n2.setRightTree(n5);

		n3.setLeftTree(n6);
		n3.setRightTree(n7);

		assertFalse(checker.equivalence(aTree, tree1, n1));
		assertFalse(checker.hashCode(aTree, tree1, n1));

		n1.setLeftTree(n2);
		n1.setRightTree(n3);

		n2.setLeftTree(n4);
		n2.setRightTree(null);

		n3.setLeftTree(n6);
		n3.setRightTree(n7);

		assertFalse(checker.equivalence(aTree, tree1, n1));
		assertFalse(checker.hashCode(aTree, tree1, n1));
	}

	@Test
	public void testNoLeftChild() throws Exception {
		BinaryTree<String> t1 = new BinaryTree<String>("A");
		BinaryTree<String> t2 = new BinaryTree<String>("B");
		BinaryTree<String> t3 = new BinaryTree<String>("C");
		BinaryTree<String> t4 = new BinaryTree<String>("D");
		BinaryTree<String> t5 = new BinaryTree<String>("E");

		t1.setLeftTree(t2);
		t1.setRightTree(t3);

		t2.setLeftTree(t4);
		t3.setRightTree(t5);
		
		Collection<BinaryTree<String>> parents=t1.noLeftChild();
		assertEquals(3, parents.size());
		assertTrue(parents.contains(t3));
		assertTrue(parents.contains(t4));
		assertTrue(parents.contains(t5));
	}
	
	@Test
	public void testNoRightChild() throws Exception {
		BinaryTree<String> t1 = new BinaryTree<String>("A");
		BinaryTree<String> t2 = new BinaryTree<String>("B");
		BinaryTree<String> t3 = new BinaryTree<String>("C");
		BinaryTree<String> t4 = new BinaryTree<String>("D");
		BinaryTree<String> t5 = new BinaryTree<String>("E");

		t1.setLeftTree(t2);
		t1.setRightTree(t3);

		t2.setLeftTree(t4);
		t3.setRightTree(t5);
		
		Collection<BinaryTree<String>> parents=t1.noRightChild();
		assertEquals(3, parents.size());
		assertTrue(parents.contains(t2));
		assertTrue(parents.contains(t4));
		assertTrue(parents.contains(t5));
	}

	@Test
	public void testNo2ChildParent() throws Exception {
		BinaryTree<String> t1 = new BinaryTree<String>("A");
		BinaryTree<String> t2 = new BinaryTree<String>("B");
		BinaryTree<String> t3 = new BinaryTree<String>("C");
		BinaryTree<String> t4 = new BinaryTree<String>("D");
		BinaryTree<String> t5 = new BinaryTree<String>("E");

		t1.setLeftTree(t2);
		t1.setRightTree(t3);

		t2.setLeftTree(t4);
		t3.setRightTree(t5);
		
		Collection<BinaryTree<String>> parents=t1.notTwoChildParent();
		assertEquals(4, parents.size());
		assertTrue(parents.contains(t2));
		assertTrue(parents.contains(t3));
		assertTrue(parents.contains(t4));
		assertTrue(parents.contains(t5));
	}
}
