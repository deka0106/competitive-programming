package lib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

	@Test
	void solve() {
		Dijkstra dijkstra = new Dijkstra(4);
		dijkstra.addEdge(new Dijkstra.Edge(0, 1, 5));
		dijkstra.addEdge(new Dijkstra.Edge(1, 0, 5));
		dijkstra.addEdge(new Dijkstra.Edge(0, 2, 6));
		dijkstra.addEdge(new Dijkstra.Edge(2, 0, 6));
		dijkstra.addEdge(new Dijkstra.Edge(0, 3, 2));
		dijkstra.addEdge(new Dijkstra.Edge(3, 0, 2));
		dijkstra.addEdge(new Dijkstra.Edge(1, 2, 1));
		dijkstra.addEdge(new Dijkstra.Edge(2, 1, 1));
		dijkstra.addEdge(new Dijkstra.Edge(2, 3, 3));
		dijkstra.addEdge(new Dijkstra.Edge(3, 2, 3));
		assertArrayEquals(new int[] { 0, 5, 5, 2 }, dijkstra.solve(0));
	}

}
