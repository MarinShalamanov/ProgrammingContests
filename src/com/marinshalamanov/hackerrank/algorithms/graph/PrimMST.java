package com.marinshalamanov.hackerrank.algorithms.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;



public class PrimMST {
	
	public void solve(InputReader in, PrintWriter out) {
		out.println(new GraphReader().readWeightedUndirected(in).getMSTKruskalWeight());
    }
	
	class GraphReader {
		
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
				u = in.nextInt()-1;
				v = in.nextInt()-1;
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
				u = in.nextInt()-1;
				v = in.nextInt()-1;
				w = in.nextInt();
				
				g.addEdge(u, v, w);
			}
			
			return g;
		}

	}
	
	class UnionFind {
		
		private int[] parent;
		private int[] size;
		private int n;
		private int numComponents;
		
		public UnionFind(int n) {
			this.n = n;
			numComponents = n;
			
			parent = new int[n];
			size = new int[n];
			
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}
		
		public int find(int i) {
			while(i != parent[i]) {
				parent[i] = parent[parent[i]];
				i = parent[i];
			}
			
			return i;
		}
		
		public int findRecursive(int i) {
			return i==parent[i] ? i : (parent[i]=find(parent[parent[i]]));
		}
		
		public boolean areConnected(int u, int v) {
			return find(u) == find(v);
		}
		
		public void union(int u, int v) {
			int rootU = find(u);
			int rootV = find(v);
			
			if (rootU == rootV) return;
			
			if(size[rootU] > size[rootV]) {
				parent[rootV] = rootU;
				size[rootV] = Math.max(size[rootV], size[rootU]+1);
			} else {
				parent[rootU] = rootV;
				size[rootU] = Math.max(size[rootU], size[rootV]+1);
			}
			
			numComponents--;
			
		}
		
		public int numComponents() {
			return numComponents;
		}	
		
		
	}

	class Graph {
		
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
		
		public int getNumVertices() {
			return n;
		}
		
		public boolean hasAdjMatrix() {
			return hasAdjMatrix;
		}

		public boolean hasListEdges() {
			return hasListEdges;
		}

		public boolean hasListAdj() {
			return hasListAdj;
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
			return getMSTKruskalWeight(null);
		}
		
		public int getMSTKruskalWeight(Comparator<Edge> comparatorOrNull) {
			int totW = 0;
			
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
					totW += e.getWeight();
				}
			}
			
			return totW;
		}
		
		public int getNumConnectedComponents() {
			// TODO: Implement me
			return -1;
		}
	}
	
	public class Edge implements Comparable<Edge> {
		
		private int from, to;
		
		private int weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		public int getFrom() {
			return from;
		}

		public void setFrom(int from) {
			this.from = from;
		}

		public int getTo() {
			return to;
		}

		public void setTo(int to) {
			this.to = to;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        PrimMST solver = new PrimMST();
        solver.solve(in, out);
        out.close();
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
        
        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
