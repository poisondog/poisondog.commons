// 2013-09-14
/*******************************
 * HashFunctionTest
 *******************************/
package poisondog.commons;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Adam Huang
 */
public class HashFunctionTest {
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMD5() throws Exception {
		Assert.assertEquals("d41d8cd98f00b204e9800998ecf8427e", HashFunction.md5(""));
		Assert.assertEquals("9e107d9d372bb6826bd81d3542a419d6", HashFunction.md5("The quick brown fox jumps over the lazy dog"));
		Assert.assertEquals("1055d3e698d289f2af8663725127bd4b", HashFunction.md5("The quick brown fox jumps over the lazy cog"));
	}

	@Test
	public void testInstance() throws Exception {
		HashFunction hash = new HashFunction("md5");
		Assert.assertEquals("d41d8cd98f00b204e9800998ecf8427e", hash.execute("".getBytes()));
		Assert.assertEquals("9e107d9d372bb6826bd81d3542a419d6", hash.execute("The quick brown fox jumps over the lazy dog".getBytes()));
		Assert.assertEquals("1055d3e698d289f2af8663725127bd4b", hash.execute("The quick brown fox jumps over the lazy cog".getBytes()));
	}

	@Test
	public void testSHA256() throws Exception {
		Assert.assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855", HashFunction.sha256(""));
		String input1 = "The quick brown fox jumps over the lazy dog";
		Assert.assertEquals("d7a8fbb307d7809469ca9abcb0082e4f8d5651e46d3cdb762d02d0bf37c9e592", HashFunction.sha256(input1));
		String input2 = "The quick brown fox jumps over the lazy dog.";
		Assert.assertEquals("ef537f25c895bfa782526529a9b63d97aa631564d5d789c2b765448c8635fb6c", HashFunction.sha256(input2));
	}

	@Test
	public void testSHA384() throws Exception {
		String result = "38b060a751ac96384cd9327eb1b1e36a21fdb71114be07434c0cc7bf63f6e1da274edebfe76f65fbd51ad2f14898b95b";
		Assert.assertEquals(result, HashFunction.sha384(""));
		String input1 = "The quick brown fox jumps over the lazy dog";
		String result1 = "ca737f1014a48f4c0b6dd43cb177b0afd9e5169367544c494011e3317dbf9a509cb1e5dc1e85a941bbee3d7f2afbc9b1";
		Assert.assertEquals(result1, HashFunction.sha384(input1));
		String input2 = "The quick brown fox jumps over the lazy dog.";
		String result2 = "ed892481d8272ca6df370bf706e4d7bc1b5739fa2177aae6c50e946678718fc67a7af2819a021c2fc34e91bdb63409d7";
		Assert.assertEquals(result2, HashFunction.sha384(input2));
	}

	@Test
	public void testSHA512() throws Exception {
		String result = "cf83e1357eefb8bdf1542850d66d8007d620e4050b5715dc83f4a921d36ce";
		result += "9ce47d0d13c5d85f2b0ff8318d2877eec2f63b931bd47417a81a538327af927da3e";
		Assert.assertEquals(result, HashFunction.sha512(""));
		String input1 = "The quick brown fox jumps over the lazy dog";
		String result1 = "07e547d9586f6a73f73fbac0435ed76951218fb7d0c8d788a309d785436bb";
		result1 += "b642e93a252a954f23912547d1e8a3b5ed6e1bfd7097821233fa0538f3db854fee6";
		Assert.assertEquals(result1, HashFunction.sha512(input1));
		String input2 = "The quick brown fox jumps over the lazy dog.";
		String result2 = "91ea1245f20d46ae9a037a989f54f1f790f0a47607eeb8a14d12890cea77a";
		result2 += "1bbc6c7ed9cf205e67b7f2b8fd4c7dfd3a7a8617e45f3c463d481c7e586c39ac1ed";
		Assert.assertEquals(result2, HashFunction.sha512(input2));
	}
}
