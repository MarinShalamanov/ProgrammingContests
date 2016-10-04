package com.marinshalamanov.codeforces.bublecup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class E {

	final static short BLACK = 1;
	final static short PINK = -1;

	int numPink = 0;
	
	class Node {
		public int id;
		public Stack<Node> adj = new Stack<>();
		public short col;
		public boolean visited;

		public Node parent;
		public int childrenVisited;

		public void change() {
			col *= -1; 
			
			if(col == BLACK) {
				numPink--;
			} else {
				numPink++;
			}
		}

		public boolean areAllChildrenVisited() {
			if (parent == null)
				return adj.size() == 0 ; //childrenVisited == adj.size();
			else
				return adj.size() == 0 || (adj.size() == 1 && adj.peek().id == parent.id); //childrenVisited + 1 == adj.size();
		}
	}

	public void solve(InputReader in, PrintWriter out) {
		int n = in.nextInt();
		Node nodes[] = new Node[n];
		for (int i = 0; i < n; i++) {
			nodes[i] = new Node();
			nodes[i].id = i;
			nodes[i].col = (short) in.nextInt();
			if(nodes[i].col == PINK) {
				numPink++;
			}
		}

		for (int i = 1; i < n; i++) {
			int u, v;
			u = in.nextInt();
			v = in.nextInt();
			u--;
			v--;
			nodes[u].adj.add(nodes[v]);
			nodes[v].adj.add(nodes[u]);
		}

//		LinkedList<Integer> path = new LinkedList<>();
//		path.add(0);
		System.out.print(1 + " ");

		Node curr = nodes[0];
		while (numPink != 0) {
//			System.out.println(path.toString());
			if (curr.areAllChildrenVisited()) {
				if (curr.id == nodes[0].id) {
//					if(curr.col == PINK) {
//						
//					}
					
					
					break;
				}

				if (curr.col == PINK) {
					System.out.print((curr.parent.id+1) + " " + (curr.id+1) + " ");
//					path.add(curr.parent.id);
//					path.add(curr.id);

					curr.parent.change();
					curr.change();
				}
				
				if(numPink == 0) break;
				
				System.out.print((curr.parent.id+1) + " ");
//				path.add(curr.parent.id);
				curr.parent.change();
				curr = curr.parent;
			} else {
				Node a = curr.adj.pop();
				if(curr.parent != null && a.id == curr.parent.id) a = curr.adj.pop();
				
//				for (Node a : curr.adj) {
//					if ((curr.parent == null || a.id != curr.parent.id) && !a.visited) {
				System.out.print((a.id+1) + " ");		
//				path.add(a.id);
						a.change();
						a.visited = true;
						a.parent = curr;
						curr.childrenVisited++;
						curr = a;
//						break;
//					}
//				}
			}
		}

//		System.out.println(path.size());
//		for (int p : path) {
//			System.out.print((p + 1) + " ");
//		}
		System.out.println();

	}

	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		E solver = new E();
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
