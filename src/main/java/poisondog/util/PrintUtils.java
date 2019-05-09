/*
 * Copyright (C) 2018 Adam Huang <poisondog@gmail.com>
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

/**
 * @author Adam Huang
 * @since 2018-08-08
 */
public class PrintUtils {
	public static final String RESET = "\u001B[0m";
	public static final String BLACK = "\u001B[30m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String WHITE = "\u001B[37m";

	public static final String BLACK_BACKGROUND = "\u001B[40m";
	public static final String RED_BACKGROUND = "\u001B[41m";
	public static final String GREEN_BACKGROUND = "\u001B[42m";
	public static final String YELLOW_BACKGROUND = "\u001B[43m";
	public static final String BLUE_BACKGROUND = "\u001B[44m";
	public static final String PURPLE_BACKGROUND = "\u001B[45m";
	public static final String CYAN_BACKGROUND = "\u001B[46m";
	public static final String WHITE_BACKGROUND = "\u001B[47m";

	public static String color(Object target, String... colors) {
		StringBuilder builder = new StringBuilder();
		for (String color : colors) {
			builder.append(color);
		}
		builder.append(target.toString());
		builder.append(RESET);
		return builder.toString();
	}

	public static String replaceColorString(String str) {
		String result = str.replaceAll(RESET, "").replaceAll(BLACK, "").replaceAll(RED, "").replaceAll(GREEN, "").replaceAll(YELLOW, "");
		result = result.replaceAll(BLUE, "").replaceAll(PURPLE, "").replaceAll(CYAN, "").replaceAll(WHITE, "");
		result = result.replaceAll(BLACK_BACKGROUND, "").replaceAll(RED_BACKGROUND, "").replaceAll(GREEN_BACKGROUND, "").replaceAll(YELLOW_BACKGROUND, "");
		result = result.replaceAll(BLUE_BACKGROUND, "").replaceAll(PURPLE_BACKGROUND, "").replaceAll(CYAN_BACKGROUND, "").replaceAll(WHITE_BACKGROUND, "");
		return result;
	}

	public static String black(Object obj) {
		return color(obj, BLACK);
	}

	public static String red(Object obj) {
		return color(obj, RED);
	}

	public static String green(Object obj) {
		return color(obj, GREEN);
	}

	public static String yellow(Object obj) {
		return color(obj, YELLOW);
	}

	public static String blue(Object obj) {
		return color(obj, BLUE);
	}

	public static String purple(Object obj) {
		return color(obj, PURPLE);
	}

	public static String cyan(Object obj) {
		return color(obj, CYAN);
	}

	public static String white(Object obj) {
		return color(obj, WHITE);
	}

}
