package lib;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KnapsackTest {

	@Test
	void solve() {
		int n = 3;
		int w = 4;
		List<Knapsack.Item> items = new ArrayList<>();
		items.add(new Knapsack.Item(3, 2));
		items.add(new Knapsack.Item(2, 1));
		items.add(new Knapsack.Item(6, 3));
		Knapsack ks = new Knapsack(n, w, items);
		int[][] table = ks.solve();
		int[][] expected = {
			{ 0, 0, 0, 0, 0 },
			{ 0, 0, 3, 3, 3 },
			{ 0, 2, 3, 5, 5 },
			{ 0, 2, 3, 6, 8 }
		};
		for (int i = 0; i < table.length; i++) {
			assertArrayEquals(expected[i], table[i]);
		}
	}

	@Test
	void solve2() {
		int n = 3;
		int w = 4;
		List<Knapsack.Item> items = new ArrayList<>();
		items.add(new Knapsack.Item(3, 2));
		items.add(new Knapsack.Item(5, 3));
		items.add(new Knapsack.Item(7, 4));
		Knapsack ks = new Knapsack(n, w, items, true);
		int[][] table = ks.solve();
		int[][] expected = {
			{ 0, 0, 0, 0, 0 },
			{ 0, 0, 3, 3, 6 },
			{ 0, 0, 3, 5, 6 },
			{ 0, 0, 3, 5, 7 }
		};
		for (int i = 0; i < table.length; i++) {
			assertArrayEquals(expected[i], table[i]);
		}
	}

}
