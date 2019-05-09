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
package poisondog.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import poisondog.concurrent.ThreadPool;
import poisondog.core.Mission;
import java.nio.channels.Channels;
/**
 * @author Adam Huang
 */
public class ConsoleEnvironment implements Mission<Scanner> {
	private Map<String, Mission<String>> mCommands;
	private Scanner mScanner;
	private boolean mActive;
	private boolean mPrint;
	private ThreadPool mPool;

	public ConsoleEnvironment() throws IOException {
		mActive = true;
		mPrint = true;
		mCommands = new HashMap<String, Mission<String>>();
		mScanner = new Scanner(System.in);
		mPool = ThreadPool.createSerialPool();
		addCommand("", new Mission<String>() {
			@Override
			public String execute(String input) {
				mPrint = !mPrint;
				return input;
			}
		});
	}

	public void addCommand(String key, Mission<String> command) {
		mCommands.put(key, command);
	}

	@Override
	public Void execute(Scanner scanner) throws Exception {
		mActive = true;
		if (scanner != null)
			mScanner = scanner;
		String input = mScanner.nextLine();
		while(!input.equals("q") && mActive) {
			execute(input);
			input = mScanner.nextLine();
		}
		mPool.close();
		mActive = false;
		return null;
	}

	public void finish() {
		mActive = false;
	}

	public Void execute(String input) throws Exception {
		GetCommand getCommand = new GetCommand();
		String command = getCommand.execute(input);
		Mission<String> mission = mCommands.get(command);
		if (mission != null)
			mission.execute(input);
		else
			System.out.println("can't find this command.");
		return null;
	}

	public void println(final Object obj) {
		if (mActive) {
			mPool.execute(new Runnable() {
				@Override
				public void run() {
					if (mPrint)
						System.out.println(obj.toString());
				}
			});
		}
	}

	public boolean isActive() {
		return mActive;
	}

	public static void run(final ConsoleEnvironment env) {
		new Thread(new Runnable() {
			public void run() {
				try {
					env.execute(new Scanner(Channels.newChannel(System.in)));
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
