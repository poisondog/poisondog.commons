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
package poisondog.net.http;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Adam Huang
 */
public class HttpGetTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testExecute() throws Exception {
		HttpParameter parameter = Mockito.spy(new HttpParameter());
		Mockito.when(parameter.getUrl()).thenReturn("http://google.com");
		HttpGet get = Mockito.spy(new HttpGet());
		get.execute(parameter);

		Mockito.verify(parameter).withAuthentication();
		Mockito.verify(parameter).getUrl();
		Mockito.verify(parameter).httpQuery();
		Mockito.verify(parameter).neverUseFile();
	}
}
