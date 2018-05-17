package lib;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

	@Test
	void binarySearch() {
		int[] a = { 0, 1, 3, 4, 6 };
		assertEquals(2, Utils.binarySearch(0, 5, mid -> a[mid] <= 3));
		assertEquals(3, Utils.binarySearch(0, 5, mid -> a[mid] <= 5));
	}

	@Test
	void gcd() {
		assertEquals(2, Utils.gcd(6, 10));
	}

	@Test
	void lcm() {
		assertEquals(30, Utils.lcm(6, 10));
	}

	@Test
	void extgcd() {
		int[] x = new int[1];
		int[] y = new int[1];
		Utils.extgcd(4, 6, x, y);
		assertEquals(2, 4 * x[0] + 6 * y[0]);
	}

	@Test
	void modPow() {
		assertEquals(1, Utils.modPow(2, 3, 7));
		assertEquals(120678297
			, Utils.modPow(123456789, 6574837563712L, 234567894));
	}

	@Test
	void modInv() {
		assertEquals(1, Utils.modInv(4, 3));
		assertEquals(BigInteger.valueOf(1919).modInverse(BigInteger.valueOf(1_000_000_007)).intValue(),
			Utils.modInv(1919, 1_000_000_007));
	}

	@Test
	void isPrime() {
		assertTrue(Utils.isPrime(1_000_000_007));
		assertFalse(Utils.isPrime(114514));
		assertTrue(Utils.isPrime(2));
		assertFalse(Utils.isPrime(1));
	}

	@Test
	void primes() {
		assertIterableEquals(Arrays.asList(2, 3, 5, 7, 11, 13), Utils.primes(16));
	}

	@Test
	void nextPermutation() {
		int[] a = { 1, 2, 0 };
		assertTrue(Utils.nextPermutation(a));
		assertArrayEquals(new int[] { 2, 0, 1 }, a);
		assertTrue(Utils.nextPermutation(a));
		assertArrayEquals(new int[] { 2, 1, 0 }, a);
		assertFalse(Utils.nextPermutation(a));
	}

	@Test
	void deepCopyOf() {
		int[][] a = { { 0, 1, 2 }, { 3, 4, 5 } };
		int[][] b = Utils.deepCopyOf(a);
		b[0][0] = 114514;
		assertEquals(0, a[0][0]);
		assertEquals(114514, b[0][0]);
	}

	@Test
	void deepFill() {
		int[][][] a = { { { 0, 0 } } };
		Utils.deepFill(a, 114514);
		assertEquals(114514, a[0][0][0]);
	}

	@Test
	void join() {
		int[] a = { 1, 1, 4, 5, 1, 4 };
		assertEquals("1 1 4 5 1 4", Utils.join(" ", a));
	}

	@Test
	void replace() {
		String actual = Utils.replace("<@ab12|cd34>+<@ef56>", "<(@[a-z0-9]+)(\\|([a-z0-9]+))?>", group ->
			group.get(3) != null ? ("@" + group.get(3)) : group.get(1));
		assertEquals("@cd34+@ef56", actual);
	}

}
