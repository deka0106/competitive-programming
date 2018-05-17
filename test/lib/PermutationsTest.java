package lib;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class PermutationsTest {

	@Test
	void get() {
		List<Integer> list = new ArrayList<>();
		list.add(0);
		list.add(1);
		list.add(2);
		List<List<Integer>> perm = new Permutations<>(list, 2);
		assertIterableEquals(Arrays.asList(0, 1), perm.get(0));
		assertIterableEquals(Arrays.asList(0, 2), perm.get(1));
		assertIterableEquals(Arrays.asList(1, 0), perm.get(2));
		assertIterableEquals(Arrays.asList(1, 2), perm.get(3));
		assertIterableEquals(Arrays.asList(2, 0), perm.get(4));
		assertIterableEquals(Arrays.asList(2, 1), perm.get(5));
	}

}
