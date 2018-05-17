package lib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CombinationTest {

	@Test
	void get() {
		int mod = 101;
		Combination combi = new Combination(20, mod);
		assertEquals((20 * 19 * 18) / (3 * 2) % mod, combi.get(20, 3));
		assertEquals((14 * 13 * 12) / (3 * 2) % mod, combi.get(14, 3));
	}

}
