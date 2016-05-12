package com.marinshalamanov.sdk.graph;

import com.marinshalamanov.sdk.InputReader;

public class GraphReader {
	
	public GraphReader() {
	}
	
	/**
	 * Format:
	 * n - num vertices,
	 * m - num edges
	 * 
	 * n m
	 * u v w - from, to, weight (m rows)
	 * u v w
	 * ....
	 * u v w
	 * 
	 * @param in
	 * @return
	 */
	public Graph readWeightedUndirected(InputReader in) {
		int n = in.nextInt();
		int m = in.nextInt();
		Graph g = new Graph(n);
		
		int u, v, w;
		
		for (int i = 0; i < m; i++) {
			u = in.nextInt();
			v = in.nextInt();
			w = in.nextInt();
			
			g.addEdge(u, v, w);
			g.addEdge(v, u, w);
		}
		
		
		return g;
	}
	
	
	/**
	 * Format:
	 * n - num vertices,
	 * m - num edges
	 * 
	 * n m
	 * u v w - from, to, weight (m rows)
	 * u v w
	 * ....
	 * u v w
	 * 
	 * @param in
	 * @return
	 */
	public Graph readWeightedDirected(InputReader in) {
		int n = in.nextInt();
		int m = in.nextInt();
		Graph g = new Graph(n);
		
		int u, v, w;
		
		for (int i = 0; i < m; i++) {
			u = in.nextInt();
			v = in.nextInt();
			w = in.nextInt();
			
			g.addEdge(u, v, w);
		}
		
		return g;
	}

}
