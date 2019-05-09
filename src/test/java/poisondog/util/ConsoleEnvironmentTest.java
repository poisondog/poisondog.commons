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

import java.util.Scanner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import poisondog.core.Mission;
import poisondog.core.NoMission;
/**
 * @author Adam Huang
 */
public class ConsoleEnvironmentTest {
	private ConsoleEnvironment mConsole;

	@Before
	public void setUp() throws Exception {
		mConsole = new ConsoleEnvironment();
	}

	@Test
	public void testSimple() throws Exception {
		Mission<String> task = Mockito.spy(new NoMission());
		mConsole.addCommand("test", task);
		mConsole.execute(new Scanner("test para1 para2\nq\n"));
		Mockito.verify(task).execute("test para1 para2");
	}

	@Test
	public void testWrongCommand() throws Exception {
		Mission<String> task = Mockito.spy(new NoMission());
		mConsole.addCommand("test", task);
		mConsole.execute(new Scanner("tet para1 para2\nq\n"));
		Mockito.verify(task, Mockito.never()).execute("tet para1 para2\nq\n");
	}

	@Test
	public void testEmptyWithoutCommand() throws Exception {
		mConsole.execute(new Scanner("\nq\n"));
	}

	@Test
	public void testEmptyWithCommand() throws Exception {
		Mission<String> task = Mockito.spy(new NoMission());
		mConsole.addCommand("", task);
		mConsole.execute(new Scanner("\nq\n"));
		Mockito.verify(task).execute("");
	}
}
