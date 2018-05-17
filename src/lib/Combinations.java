package lib;

import java.util.ArrayList;
import java.util.List;

/**
 * 組み合わせ列挙
 */
public class Combinations<T> extends ArrayList<List<T>> {

	private final List<T> list;
	private final int r;
	private final int[] index;
	private final boolean[] contains;

	public Combinations(List<T> list, int r) {
		this.list = list;
		this.r = r;
		this.index = new int[r];
		this.contains = new boolean[list.size()];
		this.compute(0);
	}

	private void compute(int n) {
		if (n == r) {
			List<T> combination = new ArrayList<>();
			for (int i : index) combination.add(list.get(i));
			this.add(combination);
		} else {
			for (int i = 0; i < list.size(); i++) {
				if (n == 0 || !contains[i] && index[n - 1] < i) {
					index[n] = i;
					contains[i] = true;
					compute(n + 1);
					contains[i] = false;
				}
			}
		}
	}

}
