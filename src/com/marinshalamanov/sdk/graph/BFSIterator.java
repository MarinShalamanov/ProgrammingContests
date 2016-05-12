package com.marinshalamanov.sdk.graph;

import java.util.Iterator;

public class BFSIterator implements Iterator<Integer>, Iterable<Integer> {

	private Graph g;

	private int queue[];
	private int qEnd = 0;
	private boolean visited[];
	private int qIdx;

	public BFSIterator(Graph g, int start) {
		this.g = g;

		int n = g.getNumVertices();
		queue = new int[n];
		visited = new boolean[n];
		queue[qEnd++] = start;
		visited[start] = true;

		qIdx = 0;

	}

	@Override
	public boolean hasNext() {
		return qIdx < qEnd;
	}

	@Override
	public Integer next() {
		int curr = queue[qIdx];

		if (g.hasAdjMatrix()) {
			for (int j = 0; j < g.getNumVertices(); j++) {
				if (g.isEdge(curr, j) && !visited[j]) {
					queue[qEnd++] = j;
					visited[j] = true;

				}
			}
		}

		qIdx++;

		return curr;
	}

	public static void main(String[] args) {
		Graph g = new Graph(6);
		g.addEdge(0, 1);
		g.addEdge(1, 5);
		g.addEdge(1, 4);
		g.addEdge(5, 2);
		g.addEdge(4, 3);
		

		BFSIterator bfs = g.bfs(0);
		
		for (Integer v : bfs) {
			
			System.out.println(v);
		}

	}

	@Override
	public Iterator<Integer> iterator() {
		return this;
	}
}
