package lib;

import java.util.*;

/**
 * ダイクストラ法
 */
public class Dijkstra {

	private final int n;
	private final List<List<Edge>> edges;

	/**
	 * @param n 頂点数
	 */
	public Dijkstra(int n) {
		this.n = n;
		edges = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			edges.add(new ArrayList<>());
		}
	}

	/**
	 * 辺追加
	 */
	public void addEdge(Edge edge) {
		edges.get(edge.source).add(edge);
	}

	/**
	 * startから全頂点への最短コスト
	 */
	public int[] solve(int start) {
		int[] distance = new int[n];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;

		Queue<Edge> q = new PriorityQueue<>();
		q.offer(new Edge(start, start, 0));

		while (!q.isEmpty()) {
			Edge edge = q.poll();
			if (distance[edge.target] < edge.cost) continue;
			for (Edge e : edges.get(edge.target)) {
				if (distance[e.target] > distance[edge.target] + e.cost) {
					distance[e.target] = distance[edge.target] + e.cost;
					q.offer(new Edge(edge.target, e.target, distance[e.target]));
				}
			}
		}

		return distance;
	}

	public static class Edge implements Comparable<Edge> {
		public final int source, target, cost;

		public Edge(int source, int target, int cost) {
			this.source = source;
			this.target = target;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge e) {
			return this.cost - e.cost;
		}
	}

}
