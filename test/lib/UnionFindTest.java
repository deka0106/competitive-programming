package lib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnionFindTest {

	@Test
	void same() {
		UnionFind uf = new UnionFind(3);
		assertFalse(uf.same(0, 1));
		uf.unite(0, 1);
		assertTrue(uf.same(0, 1));
		assertFalse(uf.same(0, 2));
		uf.unite(1, 2);
		assertTrue(uf.same(0, 2));
	}

}
