// 2014-01-22
/*******************************
 * MockitoDemoTest
 *******************************/
package poisondog.commons;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
/**
 * @author Adam Huang
 */
public class MockitoDemoTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testApp() throws Exception {
		List mockedList = Mockito.mock(List.class);
		mockedList.add("one");
		mockedList.clear();
		Mockito.verify(mockedList).add("one");
		Mockito.verify(mockedList).clear();
		Mockito.when(mockedList.get(0)).thenReturn("first");
		Mockito.when(mockedList.get(0)).thenReturn("second");
	}
}
