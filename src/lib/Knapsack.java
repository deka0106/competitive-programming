package lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ナップサック問題
 */
public class Knapsack {

	private final int n, w;
	private final List<Item> items;
	private final boolean duplicate;
	private final int[][] table;

	/**
	 *
	 * @param n 商品数
	 * @param w ナップサック容量
	 * @param items 商品
	 * @param duplicate 2つ以上の同じ商品を許容するか
	 */
	public Knapsack(int n, int w, List<Item> items, boolean duplicate) {
		this.n = n;
		this.w = w;
		this.items = items;
		this.duplicate = duplicate;
		this.table = new int[n + 1][w + 1];
	}

	public Knapsack(int n, int w, List<Item> items) {
		this(n, w, items, false);
	}

	/**
	 * table[i][j] : i-1番目までの商品から重さwの和がj以下なるように選んだときの価値vの総和の最大値
	 */
	public int[][] solve() {
		for (int i = 0; i < table.length; i++) Arrays.fill(table[i], Integer.MAX_VALUE);
		for (int i = 0; i < table.length; i++) table[i][0] = 0;
		for (int i = 0; i < table[0].length; i++) table[0][i] = 0;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= w; j++) {
				if (j - items.get(i - 1).w >= 0) {
					int tmp = Math.max(table[i - 1][j], table[i - 1][j - items.get(i - 1).w] + items.get(i - 1).v);
					if (duplicate) tmp = Math.max(tmp, table[i][j - items.get(i - 1).w] + items.get(i - 1).v);
					table[i][j] = tmp;
				} else {
					table[i][j] = table[i - 1][j];
				}
			}
		}
		return table;
	}

	public static class Item {
		public final int v, w;

		public Item(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

}
