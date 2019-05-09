/*
 * Copyright (C) 2014 Adam Huang
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
package poisondog.format;

/**
 * @author Adam Huang
 */
public class SizeFormatUtils {
	
	public static String toString(long size) {
		if((long)(size / Math.pow(2, 60)) > 0) {
			return String.format("%.2fEB", size / Math.pow(2, 60));
		}
		if((long)(size / Math.pow(2, 50)) > 0) {
			return String.format("%.2fPB", size / Math.pow(2, 50));
		}
		if((long)(size / Math.pow(2, 40)) > 0) {
			return String.format("%.2fTB", size / Math.pow(2, 40));
		}
		if((long)(size / Math.pow(2, 30)) > 0) {
			return String.format("%.2fGB", size / Math.pow(2, 30));
		}
		if((long)(size / Math.pow(2, 20)) > 0) {
			return String.format("%.2fMB", size / Math.pow(2, 20));
		}
		if((long)(size / Math.pow(2, 10)) > 0) {
			return String.format("%.2fKB", size / Math.pow(2, 10));
		}
		return Long.toString(size) + "B";
	}
}
