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

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import poisondog.json.Jsonable;
import poisondog.json.JsonUtils;

/**
 * @author Adam Huang
 */
public class Matrix implements Cloneable, Jsonable, Serializable {
	private static Random sRandom = new Random();
	private int mRow;
	private int mCol;
	private double mTolerance;
	private Map<String, Double> mMatrix;

	/**
	 * Constructor
	 */
	public Matrix(double value) {
		this(1, 1);
		set(0, 0, value);
	}

	/**
	 * Constructor
	 */
	public Matrix(int row, int col) {
		mTolerance = Math.pow(10, -6);
		mRow = row;
		mCol = col;
		mMatrix = new HashMap<String, Double>();
		for (int i = 0; i < mRow; i++) {
			for (int j = 0; j < mCol; j++) {
				set(i, j, 0);
			}
		}
	}

	public static Matrix zeros(int row, int col) {
		Matrix zeros = new Matrix(row, col);
		for (int i = 0; i < zeros.mRow; i++) {
			for (int j = 0; j < zeros.mCol; j++) {
				zeros.set(i, j, 0);
			}
		}
		return zeros;
	}

	public static Matrix ones(int row, int col) {
		Matrix ones = new Matrix(row, col);
		for (int i = 0; i < ones.mRow; i++) {
			for (int j = 0; j < ones.mCol; j++) {
				ones.set(i, j, 1.0);
			}
		}
		return ones;
	}

	public static Matrix rand(int row, int col) {
		Matrix ones = new Matrix(row, col);
		for (int i = 0; i < ones.mRow; i++) {
			for (int j = 0; j < ones.mCol; j++) {
				ones.set(i, j, sRandom.nextDouble());
			}
		}
		return ones;
	}

	public static Matrix randn(int row, int col) {
		Matrix ones = new Matrix(row, col);
		for (int i = 0; i < ones.mRow; i++) {
			for (int j = 0; j < ones.mCol; j++) {
				ones.set(i, j, sRandom.nextGaussian());
			}
		}
		return ones;
	}

	public static Matrix identity(int size) {
		Matrix result = new Matrix(size, size);
		for (int i = 0; i < result.mRow; i++) {
			for (int j = 0; j < result.mCol; j++) {
				result.set(i, j, i == j ? 1 : 0);
			}
		}
		return result;
	}

	public Matrix zeros() {
		return Matrix.zeros(mRow, mCol);
	}

	public Matrix ones() {
		return Matrix.ones(mRow, mCol);
	}

	public void setTolerance(double value) {
		mTolerance = value;
	}

	public double get(int row, int col) {
		checkIndex(row, col);
		return mMatrix.get(toIndex(row, col));
	}

	public void set(int row, int col, double value) {
		checkIndex(row, col);
		mMatrix.put(toIndex(row, col), value);
	}

	public void set(int row, double... values) {
		for (int col = 0; col < values.length; col++) {
			set(row, col, values[col]);
		}
	}

	public static void setSeed(long seed) {
		sRandom.setSeed(seed);
	}

	private void checkIndex(int row, int col) {
		if (row < 0 || row >= mRow)
			throw new IllegalArgumentException("index out of row");
		if (col < 0 || col >= mCol)
			throw new IllegalArgumentException("index out of column");
	}

	public Matrix plus(Matrix target) {
		Matrix another = target;
		if (sameRow(target) && target.getColumnSize() == 1)
			another = target.dot(Matrix.ones(1, mCol));
		if (target.getRowSize() == 1 && sameCol(target))
			another = Matrix.ones(mRow, 1).dot(target);
		if (!sameSize(another))
			throw new IllegalArgumentException("input matrix size not match.");
		Matrix result = new Matrix(mRow, mCol);
		for (int i = 0; i < mRow; i++) {
			for (int j = 0; j < mCol; j++) {
				result.set(i, j, get(i, j) + another.get(i, j));
			}
		}
		return result;
	}

	public Matrix plus(double d) {
		return plus(Matrix.ones(mRow, mCol).scalar(d));
	}

	public Matrix scalar(Matrix another) {
		if (!sameSize(another))
			throw new IllegalArgumentException("input matrix size not match.");
		Matrix result = new Matrix(mRow, mCol);
		for (int i = 0; i < mRow; i++) {
			for (int j = 0; j < mCol; j++) {
				result.set(i, j, get(i, j) * another.get(i, j));
			}
		}
		return result;
	}

	public Matrix scalar(double d) {
		Matrix result = new Matrix(mRow, mCol);
		for (int i = 0; i < mRow; i++) {
			for (int j = 0; j < mCol; j++) {
				result.set(i, j, d * get(i, j));
			}
		}
		return result;
	}

	public Matrix pow(Matrix target) {
		Matrix another = target;
		if (sameRow(target) && target.getColumnSize() == 1)
			another = target.dot(Matrix.ones(1, mCol));
		if (target.getRowSize() == 1 && sameCol(target))
			another = Matrix.ones(mRow, 1).dot(target);
		if (!sameSize(another))
			throw new IllegalArgumentException("input matrix size not match.");
		Matrix result = new Matrix(mRow, mCol);
		for (int i = 0; i < mRow; i++) {
			for (int j = 0; j < mCol; j++) {
				result.set(i, j, Math.pow(get(i, j), another.get(i, j)));
			}
		}
		return result;
	}

	public Matrix pow(double d) {
		Matrix result = new Matrix(mRow, mCol);
		for (int i = 0; i < mRow; i++) {
			for (int j = 0; j < mCol; j++) {
				result.set(i, j, Math.pow(get(i, j), d));
			}
		}
		return result;
	}

	public Matrix dot(Matrix another) {
		if (mCol != another.mRow)
			throw new IllegalArgumentException("input matrix row size not match.");
		Matrix result = new Matrix(mRow, another.mCol);
		for (int i = 0; i < mRow; i++) {
			for (int j = 0; j < another.mCol; j++) {
				double value = 0;
				for (int x = 0; x < mCol; x++) {
					value += get(i, x) * another.get(x, j);
				}
				result.set(i, j, value);
			}
		}
		return result;
	}

//	public Matrix inverse() {
//		if (mCol != mRow)
//			throw new IllegalArgumentException("this Matrix didn't have inverse Matrix.");
//		Matrix result = new Matrix(mRow, mRow);
//		return result;
//	}

	public Matrix transpose() {
		Matrix result = new Matrix(mCol, mRow);
		for (int i = 0; i < mRow; i++) {
			for (int j = 0; j < mCol; j++) {
				result.set(j, i, get(i, j));
			}
		}
		return result;
	}

	public double sum() {
		double result = 0;
		for (int i = 0; i < mRow; i++) {
			for (int j = 0; j < mCol; j++) {
				result += get(i, j);
			}
		}
		return result;
	}

	public void random() {
		for (int i = 0; i < mRow; i++) {
			for (int j = 0; j < mCol; j++) {
				set(i, j, sRandom.nextDouble());
			}
		}
	}

	public void randomGaussian() {
		for (int i = 0; i < mRow; i++) {
			for (int j = 0; j < mCol; j++) {
				set(i, j, sRandom.nextGaussian());
			}
		}
	}

	public void all(double value) {
		for (int i = 0; i < mRow; i++) {
			for (int j = 0; j < mCol; j++) {
				set(i, j, value);
			}
		}
	}

	public Matrix getRow(int row) {
		Matrix result = new Matrix(1, mCol);
		for (int j = 0; j < mCol; j++) {
			result.set(0, j, get(row, j));
		}
		return result;
	}

	public Vector getCol(int col) {
		Vector result = new Vector(mRow);
		for (int i = 0; i < mRow; i++) {
			result.set(i, get(i, col));
		}
		return result;
	}

	public double max() {
		double result = Double.NEGATIVE_INFINITY;
		for (int i = 0; i < mRow; i++) {
			for (int j = 0; j < mCol; j++) {
				result = Math.max(result, get(i, j));
			}
		}
		return result;
	}

	public double min() {
		double result = Double.POSITIVE_INFINITY;
		for (int i = 0; i < mRow; i++) {
			for (int j = 0; j < mCol; j++) {
				result = Math.min(result, get(i, j));
			}
		}
		return result;
	}

	private boolean sameRow(Matrix another) {
		if (mRow != another.mRow)
			return false;
		return true;
	}

	private boolean sameCol(Matrix another) {
		if (mCol != another.mCol)
			return false;
		return true;
	}

	private boolean sameSize(Matrix another) {
		return sameRow(another) && sameCol(another);
	}

	private String toIndex(int row, int col) {
		StringBuilder builder = new StringBuilder();
		builder.append("(");
		builder.append(row);
		builder.append(",");
		builder.append(col);
		builder.append(")");
		return builder.toString();
	}

	public int getRowSize() {
		return mRow;
	}

	public int getColumnSize() {
		return mCol;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Matrix))
			return false;
		Matrix another = (Matrix)obj;
		if (!sameSize(another))
			return false;
		for (int i = 0; i < mRow; i++) {
			for (int j = 0; j < mCol; j++) {
				if (Math.abs(get(i, j) - another.get(i, j)) > mTolerance)
					return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(mRow);
		builder.append(mCol);
		builder.append(mMatrix);
		return builder.toHashCode();
	}

	private String format(int i, int j) {
		double temp = Math.max(Math.abs(max()), Math.abs(min()));
		int max = Math.max(1, (int) (Math.log(temp) / Math.log(10) + 1));
		double d = get(i, j);
		int current = Math.max(1, (int) (Math.log(Math.abs(get(i, j))) / Math.log(10) + 1));
		StringBuilder builder = new StringBuilder();
		for (int z = 0; z < max - current; z++) {
			builder.append(" ");
		}
		builder.append(String.format("%+.5f", d));
		return builder.toString();
	}

	@Override
	public String toString() {
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < mRow; i++) {
			content.append("│");
			for (int j = 0; j < mCol; j++) {
				content.append(format(i, j));
				if (j < mCol - 1)
					content.append(" ");
			}
			content.append("│");
			content.append("\n");
		}

		int max = 0;
		String[] all = content.toString().split("\n");
		for (String line : all) {
			max = Math.max(max, line.length());
		}

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < max; i++) {
			if (i == 0)
				builder.append("┌");
			else if (i == max - 1)
				builder.append("┐");
			else
				builder.append(" ");
		}
		builder.append("\n");
		builder.append(content.toString());
		for (int i = 0; i < max; i++) {
			if (i == 0)
				builder.append("└");
			else if (i == max - 1)
				builder.append("┘");
			else
				builder.append(" ");
		}

		return builder.toString();
	}

	@Override
	public String toJson() {
		JSONObject result = JsonUtils.toJSONObject(this);
		result.put("row", mRow);
		result.put("col", mCol);
		JSONArray content = new JSONArray();
		for (int i = 0; i < mRow; i++) {
			JSONArray row = new JSONArray();
			for (int j = 0; j < mCol; j++) {
				row.put(get(i, j));
			}
			content.put(row);
		}
		result.put("content", content);
		return result.toString();
	}

	@Override
	public void loadJson(String json) {
		// TODO refactor for Json
		JSONObject obj = new JSONObject(json);
		assert obj.getString("class").equals(getClass().getName());
		mRow = obj.getInt("row");
		mCol = obj.getInt("col");
		mMatrix = new HashMap<String, Double>();
		JSONArray content = obj.getJSONArray("content");
		for (int i = 0; i < content.length(); i++) {
			JSONArray row = content.getJSONArray(i);
			for (int j = 0; j < row.length(); j++) {
				set(i, j, row.getDouble(j));
			}
		}
	}

	@Override
	public Matrix clone() {
		return dot(identity(mCol));
	}

	public void clone(Matrix another) {
		mRow = another.mRow;
		mCol = another.mCol;
		mMatrix = new HashMap<String, Double>();
		for (int i = 0; i < mRow; i++) {
			for (int j = 0; j < mCol; j++) {
				set(i, j, another.get(i, j));
			}
		}
	}
}
