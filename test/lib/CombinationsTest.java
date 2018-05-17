package lib;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class CombinationsTest {

	@Test
	void get() {
		List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 3));
		List<List<Integer>> combis = new Combinations<>(list, 3);
		assertIterableEquals(Arrays.asList(0, 1, 2), combis.get(0));
		assertIterableEquals(Arrays.asList(0, 1, 3), combis.get(1));
		assertIterableEquals(Arrays.asList(0, 2, 3), combis.get(2));
		assertIterableEquals(Arrays.asList(1, 2, 3), combis.get(3));
	}

}
