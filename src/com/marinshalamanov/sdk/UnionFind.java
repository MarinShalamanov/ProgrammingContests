package com.marinshalamanov.sdk;

public class UnionFind {
	
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
