package com.marinshalamanov.sdk.graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class DFSIterator implements Iterator<Integer>, Iterable<Integer> {

	private Graph g;

	private Stack<Integer> stack;
	private Stack<Integer> path;
	
	private boolean visited[];
	private int parent[];
	
	boolean calcPath;
	
	/**
	 * Creates iterable of the given graph that visited each node
	 * in order created by DFS, starting from a given vertex. Note, 
	 * only a one connected component will be iterated.
	 *
	 * @param g - the graph.
	 * @param start - id of the start vertex
	 * @param calcPath - if true at each step the current path from 
	 * 		the top could be get with getCurrentPath. Note, this will 
	 * 		add 2*|V| to the worst case time complexity and + |V| to 
	 * 		memory.
	 */
	public DFSIterator(Graph g, int start, boolean calcPath) {
		this.g = g;
		this.calcPath = calcPath;
		
		int n = g.getNumVertices();
		
		visited = new boolean[n];
		visited[start] = true;

		stack = new Stack<Integer>();
		stack.add(start);
	
		
		if (calcPath) {
			path = new Stack<Integer>();
			parent = new int[n];
			
			Arrays.fill(parent, -1);
			
			path.add(start);
			parent[start] = -1;
		}
	}

	@Override
	public boolean hasNext() {
		return !stack.empty();
	}

	@Override
	public Integer next() {
		int curr = stack.pop();
		
		if (calcPath) {
			while(stack.peek() != parent[curr]) {
				stack.pop();
			}
		}
		
		if (g.hasAdjMatrix()) {
			for (int j = 0; j < g.getNumVertices(); j++) {
				if (g.isEdge(curr, j) && !visited[j]) {
					stack.add(j);
					visited[j] = true;
					if (calcPath) {
						parent[j] = curr;
					}
				}
			}
		}

		return curr;
	}
	
	/** 
	 * The top of the stack is the current element. 
	 * The bottom of the stack is the start element.
	 */
	public Stack<Integer> getCurrentPath() {
		return path;
	}
	
	
	/**
	 * visited[i] = vertex i is visited
	 * 
	 * Can be used to iterate over other connected components.
	 * @return
	 */
	public boolean[] getVisited() {
		return visited;
	}
	
	public static void main(String[] args) {
		Graph g = new Graph(6);
		g.addEdge(0, 1);
		g.addEdge(1, 5);
		g.addEdge(1, 4);
		g.addEdge(5, 2);
		g.addEdge(4, 3);
		

		DFSIterator dfs = g.dfs(0);
		
		for (Integer v : dfs) {
			System.out.println(v);
		}
	}

	@Override
	public Iterator<Integer> iterator() {
		return this;
	}
}
