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

import java.util.HashSet;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * @author Adam Huang
 */
public class EqualsCheckerTest {
	@Test
	public void testReflexive() throws Exception {
		EqualsChecker checker = new EqualsChecker();
		assertFalse(checker.reflexive(new NonReflexive()));
		assertTrue(checker.reflexive(new Integer(7)));
	}
	
	@Test
	public void testSymmetric() throws Exception {
		EqualsChecker checker = new EqualsChecker();
		
		NonSymmetric ns1 = new NonSymmetric();
		NonSymmetric ns2 = new NonSymmetric();
		
		ns1.add(new Integer(0));
		ns1.add(new Integer(1));
		
		ns2.add(new Integer(0));
		assertFalse(checker.symmetric(ns1,ns2));
		ns2.add(new Integer(1));
		assertTrue(checker.symmetric(ns1,ns2));
	}
	@Test
	public void testTransitive() throws Exception {
		EqualsChecker checker = new EqualsChecker();
		
		NonTransitive nt1 = new NonTransitive();
		NonTransitive nt2 = new NonTransitive();
		NonTransitive nt3 = new NonTransitive();
		
		nt1.add(new Integer(0));
		nt1.add(new Integer(1));
		
		nt2.add(new Integer(1));
		nt2.add(new Integer(2));
		
		nt3.add(new Integer(2));
		nt3.add(new Integer(3));
		
		assertFalse(checker.transitive(nt1,nt2,nt3));
		
		nt1.add(new Integer(2));
		nt1.add(new Integer(3));
		
		assertTrue(checker.transitive(nt1, nt2, nt3));
	}
}

class NonReflexive{
	/* 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return false;
	}
}
class NonSymmetric{
	protected HashSet<Integer> set;
	/**
	 * Constructors
	 */
	public NonSymmetric() {
		set = new HashSet<Integer>();
	}
	public void add(Integer i) {
		set.add(i);
	}
	/* 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		NonSymmetric ns=(NonSymmetric) obj;
		return this.set.containsAll(ns.set);
	}
}
class NonTransitive extends NonSymmetric{
	/* 
	 * @see poisondog.common.NonSymmetric#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		NonTransitive nt=(NonTransitive) obj;
		for (Integer i : nt.set) {
			if(this.set.contains(i))
				return true;
		}
		return false;
	}
}

