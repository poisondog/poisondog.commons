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
package poisondog.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 */
public class RemoveWhitespaceTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testExecuteAll() throws Exception {
		RemoveWhitespaceAll task = new RemoveWhitespaceAll();
		Assert.assertEquals("grepcommand", task.process(" 	\tgrep command 	\t"));
		Assert.assertEquals("grepcommand", task.process("grep command"));
	}

	@Test
	public void testExecutePrefix() throws Exception {
		RemoveWhitespacePrefix task = new RemoveWhitespacePrefix();
		Assert.assertEquals("grep command 	\t", task.process(" 	\tgrep command 	\t"));
		Assert.assertEquals("grep command", task.process("grep command"));
	}

	@Test
	public void testExecutePostfix() throws Exception {
//		RemoveWhitespacePostfix task = new RemoveWhitespacePostfix();
//		Assert.assertEquals(" 	\tgrep command", task.process(" 	\tgrep command 	\t"));
//		Assert.assertEquals("grep command", task.process("grep command"));
	}
}
