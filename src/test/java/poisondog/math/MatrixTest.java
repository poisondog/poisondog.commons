/*
 * Copyright (C) 2014 Adam Huang <poisondog@gmail.com>
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
package poisondog.math;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.io.FileToObject;
import poisondog.io.ObjectToFile;
import java.io.File;

/**
 * @author Adam Huang
 */
public class MatrixTest {
	private Matrix mMatrix;

	@Before
	public void setUp() throws Exception {
		Matrix.setSeed(10);
		// mMatrix
		// [[3, 0, 0],
		//  [0, 2, 1]]
		mMatrix = new Matrix(2, 3);
		mMatrix.set(0, 0, 3);
		mMatrix.set(1, 1, 2);
		mMatrix.set(1, 2, 1);
	}

	@Test
	public void testRowSize() throws Exception {
		Assert.assertEquals(2, mMatrix.getRowSize());
	}

	@Test
	public void testColumnSize() throws Exception {
		Assert.assertEquals(3, mMatrix.getColumnSize());
	}

	@Test
	public void testSingleValue() throws Exception {
		mMatrix = new Matrix(3.0);
		Assert.assertEquals(3, mMatrix.get(0, 0), Math.pow(10, -6));
	}

	@Test
	public void testValue1() throws Exception {
		Assert.assertEquals(3, mMatrix.get(0, 0), Math.pow(10, -6));
		Assert.assertEquals(0, mMatrix.get(0, 1), Math.pow(10, -6));
		Assert.assertEquals(0, mMatrix.get(0, 2), Math.pow(10, -6));
		Assert.assertEquals(0, mMatrix.get(1, 0), Math.pow(10, -6));
		Assert.assertEquals(2, mMatrix.get(1, 1), Math.pow(10, -6));
		Assert.assertEquals(1, mMatrix.get(1, 2), Math.pow(10, -6));
	}

	@Test
	public void testValue2() throws Exception {
		Matrix m = new Matrix(2, 2);
		m.set(1, 0, 7);
		Assert.assertEquals(0, m.get(0, 0), Math.pow(10, -6));
		Assert.assertEquals(0, m.get(0, 1), Math.pow(10, -6));
		Assert.assertEquals(7, m.get(1, 0), Math.pow(10, -6));
		Assert.assertEquals(0, m.get(1, 1), Math.pow(10, -6));
	}

	@Test
	public void testSetGreaterThanColumn() throws Exception {
		try {
			mMatrix.set(1, 3, 1);
			Assert.fail("out of column");
		} catch(Exception e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testSetLessThanZero() throws Exception {
		try {
			mMatrix.set(1, -1, 1);
			Assert.fail("out of column");
		} catch(Exception e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testSetGreaterThanRow() throws Exception {
		try {
			mMatrix.set(2, 2, 1);
			Assert.fail("out of row");
		} catch(Exception e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testSetLessThanRow() throws Exception {
		try {
			mMatrix.set(-1, 2, 1);
			Assert.fail("out of row");
		} catch(Exception e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testGetGreaterThanColumn() throws Exception {
		try {
			mMatrix.get(1, 3);
			Assert.fail("out of column");
		} catch(Exception e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testLessThanZero() throws Exception {
		try {
			mMatrix.get(-1, 2);
			Assert.fail("out of row");
		} catch(Exception e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testEquals() throws Exception {
		Matrix m2 = new Matrix(2, 3);
		m2.set(0, 0, 3);
		m2.set(1, 1, 2);
		m2.set(1, 2, 1);
		Assert.assertEquals(mMatrix, m2);
		Assert.assertEquals(mMatrix.hashCode(), m2.hashCode());
	}

	@Test
	public void testTolerance() throws Exception {
		mMatrix.setTolerance(Math.pow(10, -1));
		Matrix m2 = new Matrix(2, 3);
		m2.set(0, 0, 3);
		m2.set(1, 1, 2);
		m2.set(1, 2, 1.09);
		Assert.assertEquals(mMatrix, m2);
	}

	@Test
	public void testEqualsNull() throws Exception {
		Assert.assertFalse(mMatrix.equals(null));
	}

	@Test
	public void testPlus() throws Exception {
		Matrix m2 = new Matrix(2, 3);
		m2.set(0, 1.0, 2.0, -2.0);
		m2.set(1, 3.1, -2.0, 1.0);
		Matrix result = mMatrix.plus(m2);
		Assert.assertEquals(4, result.get(0, 0), Math.pow(10, -6));
		Assert.assertEquals(2, result.get(0, 1), Math.pow(10, -6));
		Assert.assertEquals(-2, result.get(0, 2), Math.pow(10, -6));
		Assert.assertEquals(3.1, result.get(1, 0), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(1, 1), Math.pow(10, -6));
		Assert.assertEquals(2, result.get(1, 2), Math.pow(10, -6));
	}

	@Test
	public void testPlusRow() throws Exception {
		Matrix m = new Matrix(1, 3);
		m.set(0, -1.0, 2.0, 0.0);
		Matrix result = mMatrix.plus(m);
		Assert.assertEquals(2, result.get(0, 0), Math.pow(10, -6));
		Assert.assertEquals(2, result.get(0, 1), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(0, 2), Math.pow(10, -6));
		Assert.assertEquals(-1, result.get(1, 0), Math.pow(10, -6));
		Assert.assertEquals(4, result.get(1, 1), Math.pow(10, -6));
		Assert.assertEquals(1, result.get(1, 2), Math.pow(10, -6));
	}

	@Test
	public void testPower() throws Exception {
		Matrix m = new Matrix(1, 3);
		m.set(0, 3.0, 2.0, 0.0);
		Matrix result = mMatrix.pow(m);
		Assert.assertEquals(27, result.get(0, 0), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(0, 1), Math.pow(10, -6));
		Assert.assertEquals(1, result.get(0, 2), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(1, 0), Math.pow(10, -6));
		Assert.assertEquals(4, result.get(1, 1), Math.pow(10, -6));
		Assert.assertEquals(1, result.get(1, 2), Math.pow(10, -6));
	}

	@Test
	public void testPowerPure() throws Exception {
		Matrix result = mMatrix.pow(2);
		Assert.assertEquals(9, result.get(0, 0), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(0, 1), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(0, 2), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(1, 0), Math.pow(10, -6));
		Assert.assertEquals(4, result.get(1, 1), Math.pow(10, -6));
		Assert.assertEquals(1, result.get(1, 2), Math.pow(10, -6));
	}

	@Test
	public void testIdentity() throws Exception {
		Matrix result = Matrix.identity(3);
		Assert.assertEquals(1, result.get(0, 0), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(0, 1), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(0, 2), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(1, 0), Math.pow(10, -6));
		Assert.assertEquals(1, result.get(1, 1), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(1, 2), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(2, 0), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(2, 1), Math.pow(10, -6));
		Assert.assertEquals(1, result.get(2, 2), Math.pow(10, -6));
	}

	@Test
	public void testOnes() throws Exception {
		// [[1, 1, 1],
		//  [1, 1, 1]]
		Matrix result = mMatrix.ones();
		Assert.assertEquals(1, result.get(0, 0), Math.pow(10, -6));
		Assert.assertEquals(1, result.get(0, 1), Math.pow(10, -6));
		Assert.assertEquals(1, result.get(0, 2), Math.pow(10, -6));
		Assert.assertEquals(1, result.get(1, 0), Math.pow(10, -6));
		Assert.assertEquals(1, result.get(1, 1), Math.pow(10, -6));
		Assert.assertEquals(1, result.get(1, 2), Math.pow(10, -6));
	}

	@Test
	public void testZeros() throws Exception {
		// [[0, 0],
		//  [0, 0],
		//  [0, 0]]
		Matrix result = mMatrix.transpose().zeros();
		Assert.assertEquals(0, result.get(0, 0), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(0, 1), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(1, 0), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(1, 1), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(2, 0), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(2, 1), Math.pow(10, -6));
	}

	@Test
	public void testPlusPure() throws Exception {
		Matrix m2 = new Matrix(2, 3);
		m2.set(0, 1.0, 2.0, -2.0);
		m2.set(1, 3.1, -2.0, 1.0);
		Matrix result = m2.plus(1);
		Assert.assertEquals(2, result.get(0, 0), Math.pow(10, -6));
		Assert.assertEquals(3, result.get(0, 1), Math.pow(10, -6));
		Assert.assertEquals(-1, result.get(0, 2), Math.pow(10, -6));
		Assert.assertEquals(4.1, result.get(1, 0), Math.pow(10, -6));
		Assert.assertEquals(-1, result.get(1, 1), Math.pow(10, -6));
		Assert.assertEquals(2, result.get(1, 2), Math.pow(10, -6));
	}

	@Test
	public void testScalarPure() throws Exception {
		Matrix result = mMatrix.scalar(2);
		Assert.assertEquals(6, result.get(0, 0), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(0, 1), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(0, 2), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(1, 0), Math.pow(10, -6));
		Assert.assertEquals(4, result.get(1, 1), Math.pow(10, -6));
		Assert.assertEquals(2, result.get(1, 2), Math.pow(10, -6));
	}

	@Test
	public void testScalar() throws Exception {
		Matrix m2 = new Matrix(2, 3);
		m2.set(0, 1.0, 2.0, -2.0);
		m2.set(1, 3.1, -2.0, 1.0);
		Matrix result = mMatrix.scalar(m2);
		Assert.assertEquals(3, result.get(0, 0), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(0, 1), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(0, 2), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(1, 0), Math.pow(10, -6));
		Assert.assertEquals(-4, result.get(1, 1), Math.pow(10, -6));
		Assert.assertEquals(1, result.get(1, 2), Math.pow(10, -6));
	}

//	@Test
//	public void testInverse() throws Exception {
//		Matrix m = new Matrix(2, 2);
//		m.set(0, 0, 3);
//		m.set(1, 0, 2);
//		m.set(0, 1, 4);
//		m.set(1, 1, 16);
//		Matrix result = m.inverse();
//		Assert.assertEquals(0.4, result.get(0, 0), Math.pow(10, -6));
//		Assert.assertEquals(-0.1, result.get(0, 1), Math.pow(10, -6));
//		Assert.assertEquals(-0.05, result.get(1, 0), Math.pow(10, -6));
//		Assert.assertEquals(0.075, result.get(1, 1), Math.pow(10, -6));
//	}

	@Test
	public void testMultiply() throws Exception {
		Matrix m1 = new Matrix(2, 3);
		m1.set(0, 0, 1);
		m1.set(0, 1, 2);
		m1.set(0, 2, 3);
		m1.set(1, 0, -1);
		m1.set(1, 1, 0);
		m1.set(1, 2, 4);
		Matrix m2 = new Matrix(3, 4);
		m2.set(0, 0, 1);
		m2.set(0, 1, 2);
		m2.set(0, 2, 3);
		m2.set(0, 3, -3);
		m2.set(1, 0, 0);
		m2.set(1, 1, -1);
		m2.set(1, 2, 4);
		m2.set(1, 3, 0);
		m2.set(2, 0, -1);
		m2.set(2, 1, 0);
		m2.set(2, 2, -2);
		m2.set(2, 3, 1);
		Matrix result = m1.dot(m2);
		Assert.assertEquals(-2, result.get(0, 0), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(0, 1), Math.pow(10, -6));
		Assert.assertEquals(5, result.get(0, 2), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(0, 3), Math.pow(10, -6));
		Assert.assertEquals(-5, result.get(1, 0), Math.pow(10, -6));
		Assert.assertEquals(-2, result.get(1, 1), Math.pow(10, -6));
		Assert.assertEquals(-11, result.get(1, 2), Math.pow(10, -6));
		Assert.assertEquals(7, result.get(1, 3), Math.pow(10, -6));
	}

	@Test
	public void testTranspose() throws Exception {
		Matrix result = mMatrix.transpose();
		Assert.assertEquals(3, result.get(0, 0), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(0, 1), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(1, 0), Math.pow(10, -6));
		Assert.assertEquals(2, result.get(1, 1), Math.pow(10, -6));
		Assert.assertEquals(0, result.get(2, 0), Math.pow(10, -6));
		Assert.assertEquals(1, result.get(2, 1), Math.pow(10, -6));
	}

	@Test
	public void testGetRow() throws Exception {
		Matrix row = mMatrix.getRow(1);
		Assert.assertEquals(0, row.get(0, 0), Math.pow(10, -6));
		Assert.assertEquals(2, row.get(0, 1), Math.pow(10, -6));
		Assert.assertEquals(1, row.get(0, 2), Math.pow(10, -6));
	}

	@Test
	public void testGetCol() throws Exception {
		Matrix col = mMatrix.getCol(1);
		Assert.assertEquals(0, col.get(0, 0), Math.pow(10, -6));
		Assert.assertEquals(2, col.get(1, 0), Math.pow(10, -6));
	}

	@Test
	public void testSum() throws Exception {
		Assert.assertEquals(6, mMatrix.sum(), Math.pow(10, -6));
		Matrix result = mMatrix.transpose().dot(mMatrix.ones());
		Assert.assertEquals(3, result.get(0, 0), Math.pow(10, -6));
		Assert.assertEquals(2, result.get(1, 0), Math.pow(10, -6));
		Assert.assertEquals(1, result.get(2, 0), Math.pow(10, -6));
	}

	@Test
	public void testRandom() throws Exception {
		mMatrix.random();
		Assert.assertTrue(mMatrix.get(0, 0) > 0 && mMatrix.get(0, 0) < 1);
		Assert.assertTrue(mMatrix.get(0, 1) > 0 && mMatrix.get(0, 1) < 1);
		Assert.assertTrue(mMatrix.get(0, 2) > 0 && mMatrix.get(0, 2) < 1);
		Assert.assertTrue(mMatrix.get(1, 0) > 0 && mMatrix.get(1, 0) < 1);
		Assert.assertTrue(mMatrix.get(1, 1) > 0 && mMatrix.get(1, 1) < 1);
		Assert.assertTrue(mMatrix.get(1, 2) > 0 && mMatrix.get(1, 2) < 1);
	}

	@Test
	public void testRand() throws Exception {
		Matrix m = Matrix.rand(3, 2);
		Assert.assertEquals(0.73043, m.get(0, 0), Math.pow(10, -5));
		Assert.assertEquals(0.25780, m.get(0, 1), Math.pow(10, -5));
		Assert.assertEquals(0.05920, m.get(1, 0), Math.pow(10, -5));
		Assert.assertEquals(0.24412, m.get(1, 1), Math.pow(10, -5));
		Assert.assertEquals(0.81881, m.get(2, 0), Math.pow(10, -5));
		Assert.assertEquals(0.37061, m.get(2, 1), Math.pow(10, -5));
	}

	@Test
	public void testRandn() throws Exception {
		Matrix m = Matrix.randn(3, 2);
		Assert.assertEquals(0.87468, m.get(0, 0), Math.pow(10, -5));
		Assert.assertEquals(-0.91934, m.get(0, 1), Math.pow(10, -5));
		Assert.assertEquals(1.13299, m.get(1, 0), Math.pow(10, -5));
		Assert.assertEquals(-0.45983, m.get(1, 1), Math.pow(10, -5));
		Assert.assertEquals(0.73381, m.get(2, 0), Math.pow(10, -5));
		Assert.assertEquals(0.44279, m.get(2, 1), Math.pow(10, -5));
	}

	@Test
	public void testString() throws Exception {
		Matrix m = Matrix.randn(3, 2);

		Matrix a = new Matrix(3, 2);
		a.set(0, 100.0, 1.0);
		System.out.println(m.plus(a));

		Matrix b = new Matrix(3, 2);
		b.set(0, -100.0, 1.0);
		System.out.println(m.plus(b));
	}

	@Test
	public void testAll() throws Exception {
		mMatrix.all(1);
		Assert.assertEquals(1.0, mMatrix.get(0, 0), Math.pow(10, -6));
		Assert.assertEquals(1.0, mMatrix.get(0, 1), Math.pow(10, -6));
		Assert.assertEquals(1.0, mMatrix.get(0, 2), Math.pow(10, -6));
		Assert.assertEquals(1.0, mMatrix.get(1, 0), Math.pow(10, -6));
		Assert.assertEquals(1.0, mMatrix.get(1, 1), Math.pow(10, -6));
		Assert.assertEquals(1.0, mMatrix.get(1, 2), Math.pow(10, -6));
	}

	@Test
	public void testJson() throws Exception {
		mMatrix.random();
		Matrix temp = new Matrix(3.7);
		temp.loadJson(mMatrix.toJson());
		Assert.assertEquals(mMatrix, temp);
	}

	@Test
	public void testSetByRow() throws Exception {
		Matrix m = new Matrix(2, 3);
		m.set(0, 1.0, 2.1, 4.5);
		m.set(1, 3.0, -2.3, .0);
		Assert.assertEquals(1.0, m.get(0, 0), Math.pow(10, -6));
		Assert.assertEquals(2.1, m.get(0, 1), Math.pow(10, -6));
		Assert.assertEquals(4.5, m.get(0, 2), Math.pow(10, -6));
		Assert.assertEquals(3.0, m.get(1, 0), Math.pow(10, -6));
		Assert.assertEquals(-2.3, m.get(1, 1), Math.pow(10, -6));
		Assert.assertEquals(0, m.get(1, 2), Math.pow(10, -6));
	}

	@Test
	public void testClone() throws Exception {
		Matrix clone = mMatrix.clone();
		Assert.assertTrue(mMatrix != clone);
		Assert.assertEquals(mMatrix, clone);
	}

	@Test
	public void testCloneFrom() throws Exception {
		Matrix m = new Matrix(1, 1);
		m.clone(mMatrix);
		Assert.assertEquals(mMatrix, m);
	}

	@Test
	public void testMax() throws Exception {
		Assert.assertEquals(3.0, mMatrix.max(), Math.pow(10, -6));
	}

	@Test
	public void testMin() throws Exception {
		Assert.assertEquals(0.0, mMatrix.min(), Math.pow(10, -6));
	}

	@Test
	public void testSaveLoad() throws Exception {
		ObjectToFile saver = new ObjectToFile("/tmp/matrix.txt");
		File storage = saver.execute(mMatrix);
		FileToObject loader = new FileToObject();
		Matrix another = (Matrix) loader.execute(storage);
		Assert.assertEquals(mMatrix, another);
		storage.delete();
	}

}
