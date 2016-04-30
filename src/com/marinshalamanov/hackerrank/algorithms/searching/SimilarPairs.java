package com.marinshalamanov.hackerrank.algorithms.searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class SimilarPairs {

	int fen[];

	void add(int i, int val) {
		while (i < fen.length) {
			fen[i] += val;
			i += i & -i;
		}
	}

	long get(int i) {
		long sum = 0;
		while (i > 0) {
			sum += fen[i];
			i -= i & -i;
		}
		return sum;
	}

	public void solve(InputReader in, PrintWriter out) {
		int n = in.nextInt();
		int t = in.nextInt();

		fen = new int[n + 1];
		int par[] = new int[n + 1];
		List<List<Integer>> children = new ArrayList<>(n + 1);

		for (int i = 0; i <= n; i++) {
			children.add(new ArrayList<Integer>());
		}

		int s, e;

		for (int i = 1; i < n; i++) {
			s = in.nextInt();
			e = in.nextInt();
			par[e] = s;
			children.get(s).add(e);
		}

		int root = 1;
		while (par[root] != 0)
			root = par[root];

		long count = 0;
		Stack<Integer> dfs = new Stack<>();
		dfs.add(root);
		// add(root, 1);

		Stack<Integer> path = new Stack<>();
		path.add(0); // the parent of the top, assures we'll never pop the top

		while (!dfs.isEmpty()) {
			int currElement = dfs.pop();

			while (!path.isEmpty() && path.peek() != par[currElement]) {
				int poped = path.pop();
				add(poped, -1);
			}

			long currCount = get(Math.min(n, currElement + t)) - get(Math.max(0, currElement - t - 1));
			count += currCount;

			path.add(currElement);
			add(currElement, 1);

			for (int child : children.get(currElement)) {
				dfs.add(child);
			}

			// System.out.println(path.toString());
			// System.out.println(currCount);
		}

		System.out.println(count);
	}

	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		SimilarPairs solver = new SimilarPairs();
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
