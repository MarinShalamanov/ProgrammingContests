package com.marinshalamanov.hackerrank.world_codespring4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class HackerLand {
	
	public void solve(InputReader in, PrintWriter out) {

		//Integer.parseInt("10000010000");
		
		int n = in.nextInt();
		int m = in.nextInt();
		
		@SuppressWarnings("unchecked")
		LinkedList<Edge>[] edges = new LinkedList[n]; 
		
		for (int i = 0; i < n; i++) edges[i] = new LinkedList<Edge>();
		int startVertex = -1;
		
		for (int i = 0; i < m; i++) {
			Edge edge = new Edge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt());
			edges[edge.x].add(edge);
			edges[edge.y].add(edge);
			if (edge.w == 0) startVertex = edge.x;
		}
		
		HashSet<Integer> selectedVertexes = new HashSet<>();		
		LinkedList<Edge> selectedEdges = new LinkedList<Edge>();
		PriorityQueue<Edge> queueEdges = new PriorityQueue<>();
		selectedVertexes.add(startVertex);
		queueEdges.addAll(edges[startVertex]);
		
		while (selectedVertexes.size() < n) {
			Edge nextEdge = queueEdges.poll();
			if (!selectedVertexes.contains(nextEdge.x) || !selectedVertexes.contains(nextEdge.y)) {
				selectedEdges.add(nextEdge);
				if (!selectedVertexes.contains(nextEdge.x)) {
					selectedVertexes.add(nextEdge.x);
					queueEdges.addAll(edges[nextEdge.x]);
				}
				if (!selectedVertexes.contains(nextEdge.y)) {
					selectedVertexes.add(nextEdge.y);
					queueEdges.addAll(edges[nextEdge.y]);
				}
			}
		}
		
		//for (Edge e: selectedEdges) {
		//	System.out.println(e.x + " " + e.y);
		//}
		@SuppressWarnings("unchecked")
		HashSet<Edge>[] mstEdges = new HashSet[n];
		for (int i = 0; i < n; i++) mstEdges[i] = new HashSet<Edge>();
		for (Edge edge: selectedEdges) {
			mstEdges[edge.x].add(edge);
			mstEdges[edge.y].add(edge);
		}
		
		
		int root = 0;
		int depth[] = new int[n];
		depth[root] = 0;
		
		Queue<Integer> q = new LinkedList<>();
		
		long size[] = new long[n];
		boolean visited[] = new boolean[n];
		visited[root] = true;
		q.add(root);
		
		PriorityQueue<VertDepth> vd = new PriorityQueue<>();
		
		while(!q.isEmpty()) {
			int currVert = q.poll();
			visited[currVert] = true;
			
			for(Edge e : mstEdges[currVert]) {
				int otherVert = ((e.x == currVert)?(e.y):(e.x));
				if (!visited[otherVert]) {
					q.add(otherVert);
				} else { 
					depth[currVert] = depth[otherVert] + 1;
				}
			}
			
			vd.add(new VertDepth(currVert, depth[currVert]));
		}
		
		
		while(!vd.isEmpty()) {
			int currVert = vd.poll().vert;
			
			size[currVert] = 1;
			
			for(Edge e : mstEdges[currVert]) {
				int otherVert = ((e.x == currVert)?(e.y):(e.x));
				if (visited[otherVert]) { 
					size[currVert] += size[otherVert];
				}
			}
		}
		
//		for(int i = 0; i < n; i++) {
//			System.out.println((i+1) + " " + size[i]);
//		}
		
		
		
//		PrintWriter outt = null;
//		try {
//			outt = new PrintWriter("out.txt");
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
		
		long[] bits = new long[(int) (100 + 2*(1e5))];
		for (Edge e: selectedEdges) {
			int lower = (depth[e.x] > depth[e.y]) ? (e.x) : (e.y);
			
			
			bits[e.w] = size[lower]*(n-size[lower]);
		}
		
		Stack<Character> res = new Stack<>();
		for(int i = 0; i < bits.length-1; i++) {
//			if(bits[i] < 0) {
//				outt.print(i + "\n");
//				
//			}
			char c = (char) ('0' + (bits[i]%2));
			res.push(c);
			
			int j = 1;
			bits[i] /= 2;
			while(bits[i] > 0) {
				bits[i+j] += bits[i]%2;
				j++;
				bits[i] /= 2;
			}
//			bits[i+1] += bits[i]/2;
		}
		
		while(bits[bits.length-1] > 0) {
			char c = (char) ('0' + (bits[bits.length-1]%2));
			res.push(c);
			bits[bits.length-1] /= 2;
		}
		
		
		while(res.peek() == '0') res.pop();
		
		while(!res.isEmpty()) System.out.print(res.pop());
		System.out.println();
		
//		while(!res.isEmpty()) outt.print(res.pop());
//		outt.println();
//		outt.close();
		
		
		
//		PriorityQueue<VertexCount> vertexCount = new PriorityQueue<>();
//		int[] vertexC = new int[n];
//		for (int i = 0; i < n; i++) {
//			vertexCount.add(new VertexCount(i, mstEdges[i].size()));
//			vertexC[i] = mstEdges[i].size();
//		}
//		HashSet<Integer> expandedVertexes = new HashSet<>();
//		int[] vertexCoefficient = new int[n];
//		Arrays.fill(vertexCoefficient, 1);
//		BigInteger result = BigInteger.ZERO;
//		while (expandedVertexes.size() < n-1) {
//			VertexCount vc = vertexCount.poll();
//			if (expandedVertexes.contains(vc.x)) continue;
//			Edge edge = mstEdges[vc.x].iterator().next();
//			mstEdges[edge.x].remove(edge); mstEdges[edge.y].remove(edge);
//			result = result.add(new BigInteger(String.valueOf(vertexCoefficient[vc.x]))
//					.multiply(new BigInteger(String.valueOf(n-expandedVertexes.size() - 1))
//							.multiply(new BigInteger("2").pow(edge.w))));
//			expandedVertexes.add(vc.x);
//			if (edge.x != vc.x) {
//				vertexCoefficient[edge.x] += vertexCoefficient[vc.x];
//				vertexC[edge.x]--;
//				vertexCount.add(new VertexCount(edge.x, vertexC[edge.x]));
//			}
//			if (edge.y != vc.x) {
//				vertexCoefficient[edge.y] += vertexCoefficient[vc.x];
//				vertexC[edge.y]--;
//				vertexCount.add(new VertexCount(edge.y, vertexC[edge.y]));
//			}
//		}
//		System.out.println(result.toString());
		
		
    }
	
	static class VertDepth implements Comparable<VertDepth> {
		int vert, depth;

		public VertDepth(int vert, int depth) {
			super();
			this.vert = vert;
			this.depth = depth;
		}

		@Override
		public int compareTo(VertDepth o) {
			return -( depth - o.depth);
		}
	}
	
	static class VertexCount implements Comparable<VertexCount> {
		int x, count;
		public VertexCount(int x, int count) {
			this.x = x; this.count = count;
		}
		@Override
		public int compareTo(VertexCount o) {
			return count - o.count;
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int x, y, w;
		public Edge(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return w - o.w;
		}
	}
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        HackerLand solver = new HackerLand();
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
        
        public long nextLong() {
            return Long.parseLong(next());
        }
        
        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
