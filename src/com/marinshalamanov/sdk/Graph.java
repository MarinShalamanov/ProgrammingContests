package com.marinshalamanov.sdk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {
	
	private int[][] adj;
	private List<Edge> listEdges;
	
	private int n;
	
	private boolean directed;
	
	private boolean hasAdjMatrix, hasListEdges, hasListAdj;
	
	public Graph(int n) {
		init(n, false, true, true, true);
	}
	
	public Graph(int n, boolean directed, boolean hasAdjMatrix, boolean hasListEdges, boolean hasListAdj) {
		init(n, directed, hasAdjMatrix, hasListEdges, hasListAdj);
	}
	
	private void init(int n, boolean directed, boolean hasAdjMatrix, boolean hasListEdges, boolean hasListAdj) {
		this.n = n;
		this.directed = directed;
		
		this.hasAdjMatrix = hasAdjMatrix;
		this.hasListAdj = hasListAdj;
		this.hasListEdges = hasListEdges;
		
		if (hasAdjMatrix) {
			adj = new int[n][n];
		}
		
		if (hasListEdges) {
			listEdges = new ArrayList<Edge>();
		}
	}
	
	public boolean isEdge(int u, int v) {
		return adj[u][v] != 0;
	}
	
	public int edgeWieght(int u, int v) {
		return adj[u][v];
	}
	
	public void addEdge(int from, int to) {
		addEdge(from, to, 1);
	}
	
	public void addEdge(int from, int to, int weight) {
		if(hasAdjMatrix) {
			if(directed) {
				adj[from][to] = weight;
			} else {
				adj[from][to] = weight;
				adj[to][from] = weight;
			}
		}
		
		if (hasListEdges) {
			listEdges.add(new Edge(from, to, weight));
		}
	}
	
	public Graph getMSTPrim() {
		Graph mst = new Graph(n);
		
		Set<Integer> used = new HashSet<Integer>(n);
		used.add(0);
		
		// TODO
		
		return mst;
	}
	
	/**
	 * HasRep		Time complexity		Memory complexity
	 * listEdjes	    O(1)				O(1)
	 * adjm			    O(|V|**2)			O(|E|)
	 * 
	 * @return
	 */
	public List<Edge> getListOfEdges() {
		if (hasListEdges) {
			return listEdges;
		}
		
		if(hasAdjMatrix) {
			List<Edge> edges = new ArrayList<Edge>(n);
			
			if (!directed) {
				for(int i = 0; i < n; i++) {
					for(int j = 0; j < n; j++) {
						if(isEdge(i, j)) {
							edges.add(new Edge(i, j, edgeWieght(i, j)));
						}
					}
				}
			} else {
				for(int i = 0; i < n; i++) {
					for(int j = i; j < n; j++) {
						if(isEdge(i, j)) {
							edges.add(new Edge(i, j, edgeWieght(i, j)));
						}
					}
				}
			}
			return edges;
		}
		
		return null;
	}
	
	public Graph getMSTKruskal(Comparator<Edge> comparatorOrNull, Graph out) {
		Graph mst = (out==null)?(new Graph(n)):(out);
		
		UnionFind uf = new UnionFind(n);
		
		List<Edge> edges = new ArrayList<>();
		edges.addAll (getListOfEdges());
		
		if (comparatorOrNull == null) {
			Collections.sort(edges);
		} else {
			edges.sort(comparatorOrNull);
		}
		
		for (Edge e : edges) {
			if (!uf.areConnected(e.getFrom(), e.getTo())) {
				uf.union(e.getFrom(), e.getTo());
				mst.addEdge(e.getFrom(), e.getTo(), e.getWeight());
			}
		}
		
		return mst;
	}
	
	public int getMSTKruskalWeight() {
		int totW = 0;
		
		UnionFind uf = new UnionFind(n);
		
		List<Edge> edges = new ArrayList<>();
		edges.addAll (getListOfEdges());
		
		for (Edge e : edges) {
			if (!uf.areConnected(e.getFrom(), e.getTo())) {
				uf.union(e.getFrom(), e.getTo());
				totW += e.getWeight();
			}
		}
		
		return totW;
	}
	
	
}
