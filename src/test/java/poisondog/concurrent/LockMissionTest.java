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
package poisondog.concurrent;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import poisondog.core.NoMission;
import org.mockito.Mockito;
/**
 * @author Adam Huang
 * @since 2016-02-28
 */
public class LockMissionTest {
	private LockMission mLock;
	private long mFirst;
	private long mSecond;

	@Before
	public void setUp() throws Exception {
		mLock = new LockMission();
	}

	@Test
	public void testExecute() throws Exception {
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
////				try {
////					Thread.sleep(10);
////				} catch(Exception e) {
////					e.printStackTrace();
////				}
////				mLock.execute(true);
////				System.out.println("first");
//				mFirst = System.currentTimeMillis();
////				System.out.println(mFirst);
//			}
//		}).start();
//		Thread.sleep(10);
////		System.out.println("second");
//		mSecond = System.currentTimeMillis();
////		System.out.println(mSecond);
////		mLock.execute(false);
//		Assert.assertTrue(mSecond < mFirst);
	}
}
