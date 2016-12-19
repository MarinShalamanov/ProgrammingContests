package com.marinshalamanov.codeforces.codeforces387;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;

public class E {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(",");
		int l[] = new int[s.length];
		Stack<Integer> stack = new Stack<Integer>();
		int maxl = 0;
		for (int i = 0; i < s.length; i += 2) {
			int lev = 1;
			if (!stack.isEmpty())
				lev = stack.pop();
			if (lev > maxl)
				maxl = lev;
			l[i] = lev;
			int children = Integer.parseInt(s[i + 1]);
			for (int j = 0; j < children; j++) {
				stack.push(lev + 1);
			}
		}
		
		PrintWriter out = new PrintWriter(System.out);
		out.println(maxl);
		ArrayList<Integer>[] levels = new ArrayList[maxl];
		for (int i = 0; i < maxl; i++) {
			levels[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < s.length; i++) {
			if (l[i] > 0) {
				levels[l[i] - 1].add(i);
			}
		}
		for (int i = 0; i < maxl; i++) {
			for (int idx : levels[i]) {
				out.print(s[idx] + " ");
			}
			out.println();
		}
		out.close();
	}
}
