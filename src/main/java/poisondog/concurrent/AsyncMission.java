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
package poisondog.concurrent;

import poisondog.core.Mission;
import poisondog.core.NoMission;
/**
 * @author Adam Huang
 */
public class AsyncMission<T> implements Mission<T> {
	private Mission<T> mMission;
	private Mission mHandler;
	private Object mResult;
	private ThreadPool mPool;

	public AsyncMission(Mission<T> mission) {
		mMission = mission;
		mHandler = new NoMission();
		mPool = ThreadPool.createSerialPool();
	}

	@Override
	public Void execute(final T input) throws Exception {
		Runnable task = new Runnable() {
			public void run() {
				try{
					mResult = mMission.execute(input);
					mHandler.execute(mResult);
				}catch(Exception e) {
				}
			}
		};
		mPool.execute(task);
		return null;
	}

	public void setHandler(Mission handler) {
		mHandler = handler;
	}

	public void setThreadPool(ThreadPool pool) {
		mPool = pool;
	}

}
