/*
 * Copyright (C) 2009 Adam Huang <poisondog@gmail.com>
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

import java.util.List;

/**
 * @author Adam Huang
 */
public class Vector extends Matrix {

	/**
	 * Constructor
	 */
	public Vector(double value) {
		super(value);
	}

	/**
	 * Constructor
	 */
	public Vector(int row) {
		super(row, 1);
	}

	/**
	 * Constructor
	 */
	public Vector(double... ds) {
		this(ds.length);
		for (int i = 0; i < ds.length; i++) {
			super.set(i, 0, ds[i]);
		}
	}

	/**
	 * Constructor
	 */
	public Vector(List<Double> ds) {
		this(ds.size());
		for (int i = 0; i < ds.size(); i++) {
			super.set(i, 0, ds.get(i));
		}
	}

	public double get(int row) {
		return super.get(row, 0);
	}

	public void set(int row, double value) {
		super.set(row, 0, value);
	}

	public int length() {
		return getRowSize();
	}

	@Override
	public Vector plus(Matrix another) {
		return toVector(super.plus(another));
	}

	@Override
	public Vector scalar(double d) {
		return toVector(super.scalar(d));
	}

	public static Vector toVector(Matrix m) {
		if (m.getColumnSize() != 1)
			throw new IllegalArgumentException("this matrix can't parse to Vector, because column size not equals 1");
		Vector result = new Vector(m.getRowSize());
		for (int i = 0; i < m.getRowSize(); i++) {
			result.set(i, m.get(i, 0));
		}
		return result;
	}
}
