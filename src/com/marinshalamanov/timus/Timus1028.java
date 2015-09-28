package com.marinshalamanov.timus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Timus1028 {
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		new Timus1028().solve();
	}

	final int MAXCOORD = 32000;
	char[] fenwick = new char[5 + 2 * MAXCOORD];
	char[] levels;

	void add(int i) {
		while (i < fenwick.length) {
			fenwick[i]++;
			i += i & -i;
		}
	}

	int get(int k) {
		int sum = 0;
		while (k > 0) {
			sum += fenwick[k];
			k -= k & -k;
		}
		return sum;
	}

	void solve() throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(in.readLine());
		levels = new char[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			levels[get(2 * x + 1)]++;
			add(2 * x + 1);
		}

		for (int i = 0; i < levels.length; i++) {
			out.println((int) levels[i]);
		}
		out.flush();
	}
}
