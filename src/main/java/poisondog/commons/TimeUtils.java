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
package poisondog.commons;

import java.util.Calendar;
/**
 * // TODO delete it
 * @author Adam Huang
 */
public class TimeUtils {
	public static boolean isToday(long time) {
		Calendar today = Calendar.getInstance();
		today.setTimeInMillis(System.currentTimeMillis());
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.setTimeInMillis(today.getTimeInMillis());
		tomorrow.add(Calendar.DAY_OF_MONTH, 1);
		return time >= today.getTimeInMillis() && time < tomorrow.getTimeInMillis();
	}
}
