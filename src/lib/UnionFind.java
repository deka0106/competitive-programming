package lib;

/**
 * 素集合データ構造
 */
public class UnionFind {

	private final int[] parent, rank;

	/**
	 * @param n ノード数
	 */
	public UnionFind(int n) {
		parent = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) parent[i] = i;
	}

	/**
	 * xを含む木の根
	 */
	public int root(int x) {
		if (parent[x] == x) return x;
		else return parent[x] = root(parent[x]);
	}

	/**
	 * x, yの木を連結
	 */
	public void unite(int x, int y) {
		x = root(x);
		y = root(y);
		if (x == y) return;
		if (rank[x] < rank[y]) {
			parent[x] = y;
		} else {
			parent[y] = x;
			if (rank[x] == rank[y]) rank[x]++;
		}
	}

	/**
	 * x, yが同集合内？
	 */
	public boolean same(int x, int y) {
		return root(x) == root(y);
	}

}
