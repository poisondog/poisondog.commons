/*
 * Copyright (C) 2015 Adam Huang <poisondog@gmail.com>
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

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2015-09-04
 */
public class ThreadPool implements Mission<Runnable> {
	private ExecutorService mExecutor;

	/**
	 * Constructor
	 */
	public ThreadPool() {
		setSize(5);
	}

	/**
	 * Constructor
	 */
	public ThreadPool(int size) {
		setSize(size);
	}

	public static ThreadPool createSerialPool() {
		ThreadPool pool = new ThreadPool();
		pool.setSize(1);
		return pool;
	}

	public void setSize(int size) {
		mExecutor = Executors.newFixedThreadPool(size);
	}

	public void close() {
		mExecutor.shutdown();
	}

	@Override
	public Runnable execute(Runnable task) {
		mExecutor.execute(task);
		return task;
	}
}
