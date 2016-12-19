package com.marinshalamanov.codeforces.codeforces383;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class B {
	static int[] parent;
	static int[] size;
	static int count;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		int w = in.nextInt();
		int[] ww = new int[n];
		for (int i = 0; i < n; i++)
			ww[i] = in.nextInt();
		int[] bb = new int[n];
		for (int i = 0; i < n; i++)
			bb[i] = in.nextInt();

		// UF
		count = n;
		parent = new int[n];
		size = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			size[i] = 1;
		}

		for (int i = 0; i < m; i++) {
			int x = in.nextInt() - 1;
			int y = in.nextInt() - 1;
			union(x, y);
		}

		HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		for (int i = 0; i < n; i++) {
			int root = find(i);
			ArrayList<Integer> list = map.get(root);
			if (list == null)
				list = new ArrayList<Integer>();
			list.add(i);
			map.put(root, list);
		}
		int[] beauties = new int[w + 1];
		int[] beautiesCopy = new int[w + 1];
		for (ArrayList<Integer> list : map.values()) {
			for (int i = 0; i <= w; i++)
				beautiesCopy[i] = beauties[i];
			int gw = 0, gb = 0;
			for (int next : list) {
				gw += ww[next];
				gb += bb[next];
				if (ww[next] <= w) {
					if (beautiesCopy[ww[next]] < bb[next])
						beautiesCopy[ww[next]] = bb[next];
					for (int j = 1; j + ww[next] <= w; j++) {
						if (beauties[j] > 0 && beautiesCopy[j + ww[next]] < beauties[j] + bb[next]) {
							beautiesCopy[j + ww[next]] = beauties[j] + bb[next];
						}
					}
				}
			}
//			if (gw <= w) {
//				if (beautiesCopy[gw] < gb)
//					beautiesCopy[gw] = gb;
//				for (int j = 1; j + gw <= w; j++) {
//					if (beauties[j] > 0 && beautiesCopy[j + gw] < beauties[j] + gb) {
//						beautiesCopy[j + gw] = beauties[j] + gb;
//					}
//				}
//			}

			for (int i = 0; i <= w; i++)
				beauties[i] = beautiesCopy[i];
		}
		int max = 0;
		for (int i = 0; i <= w; i++) {
			if (beauties[i] > max)
				max = beauties[i];
		}
		System.out.println(max);
	}

	static int find(int p) {
		while (p != parent[p])
			p = parent[p];
		return p;
	}

	static void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		if (rootP == rootQ)
			return;

		// make smaller root point to larger one
		if (size[rootP] < size[rootQ]) {
			parent[rootP] = rootQ;
			size[rootQ] += size[rootP];
		} else {
			parent[rootQ] = rootP;
			size[rootP] += size[rootQ];
		}
		count--;
	}
}
