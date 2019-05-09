/*
 * Copyright (C) 2017 Adam Huang <poisondog@gmail.com>
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

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2017-09-04
 */
public class FutureMission<T> implements Mission<T> {
	private Mission<T> mMission;
	private ThreadPool mPool;

	/**
	 * Constructor
	 */
	public FutureMission(Mission<T> mission) {
		mMission = mission;
		mPool = ThreadPool.createSerialPool();
	}

	public void setThreadPool(ThreadPool pool) {
		mPool = pool;
	}

	@Override
	public FutureTask<Object> execute(final T input) throws Exception {
		FutureTask<Object> future = new FutureTask<Object>(new Callable<Object>(){
			@Override
			public Object call() throws Exception {
				return mMission.execute(input);
			}
		});
		mPool.execute(future);
		return future;
	}
}
