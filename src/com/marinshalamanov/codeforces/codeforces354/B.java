package com.marinshalamanov.codeforces.codeforces354;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B {
	int n, t;
	void s() {
		double g[][] = new double[n + 1][n + 1];

		

		g[0][0] = t;

		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				if (g[i][j] >= 1) {
					g[i + 1][j] += (g[i][j] - 1) / 2.0;
					g[i + 1][j + 1] += (g[i][j] - 1) / 2.0;
					g[i][j] = 1;
				}

				if (g[i][j] > 1 - 1e-7) {
					count++;
				}
			}
		}

		System.out.println(count);
	}

	public void solve(InputReader in, PrintWriter out) {
//		n = 10;
//		for(int i =0 ; i < 100; i++) {
//			t = i;
//			s();
//		}
		
		n = in.nextInt();
		int _t = in.nextInt();
		t = _t;
		s();
		
	}

	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		B solver = new B();
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
