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

import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2016-05-22
 */
public class Distance implements Mission<Vector> {
	private Vector mOrigin;

	/**
	 * Constructor
	 */
	public Distance(Vector origin) {
		mOrigin = origin;
	}

	@Override
	public Double execute(Vector input) {
		double sum = 0;
		for (int i = 0; i < mOrigin.length(); i++) {
			sum += Math.pow(mOrigin.get(i) - input.get(i), 2);
		}
		return Math.sqrt(sum);
	}

}
