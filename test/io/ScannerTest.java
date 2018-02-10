package io;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class ScannerTest {

	private InputStream input(String str) {
		return new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
	}

	@Test
	void next() {
		Scanner sc = new Scanner(input(" 123 abc \n ABC"));
		assertTrue(sc.hasNext());
		assertEquals("123", sc.next());
		assertTrue(sc.hasNext());
		assertEquals("abc", sc.next());
		assertTrue(sc.hasNext());
		assertEquals("ABC", sc.next());
		assertFalse(sc.hasNext());
	}

	@Test
	void nextInt() {
		Scanner sc = new Scanner(input(" 123 2147483647 -123 "));
		assertEquals(123, sc.nextInt());
		assertEquals(2147483647, sc.nextInt());
		assertEquals(-123, sc.nextInt());
	}

	@Test
	void nextIntArray() {
		Scanner sc = new Scanner(input(" 1 2 3 4 5 "));
		assertArrayEquals(new int[] { 1, 2, 3, 4, 5 }, sc.nextIntArray(5));
		sc = new Scanner(input("1\n2\n3\n4\n5"));
		assertArrayEquals(new int[] { 1, 2, 3, 4, 5 }, sc.nextIntArray(5));
	}

}
