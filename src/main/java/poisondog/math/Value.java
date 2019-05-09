/*
 * Copyright (C) 2016 Adam Huang <poisondog@gmail.com>
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

/**
 * @author Adam Huang
 * @since 2016-08-27
 */
public class Value extends Vector {

	/**
	 * Constructor
	 */
	public Value(double value) {
		super(value);
	}

	public double get() {
		return get(0, 0);
	}

	public void set(double value) {
		set(0, 0, value);
	}

	public static Value toValue(Matrix m) {
		if (m.getColumnSize() != 1)
			throw new IllegalArgumentException("this matrix can't parse to Value, because column size not equals 1");
		if (m.getRowSize() != 1)
			throw new IllegalArgumentException("this matrix can't parse to Value, because row size not equals 1");
		return new Value(m.get(0, 0));
	}

}
