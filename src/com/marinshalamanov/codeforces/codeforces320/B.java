package codeforces320;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B {

	public void solve(InputReader in, PrintWriter out) {
		int n = 2 * in.nextInt();
		int a[][] = new int[n][n];
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				// System.out.println(i + " " + j);
				a[j][i] = a[i][j] = in.nextInt();
			}
		}

		int pairs[] = new int[n];
		boolean done[] = new boolean[n];
		int doneCount = 0;
		while (doneCount < n) {
			int best[] = new int[n];
			Arrays.fill(best, -1);

			int bestScore[] = new int[n];

			for (int i = 0; i < n; i++) {
				if (!done[i]) {
					for (int j = 0; j < n; j++) {
						if (!done[j] && i != j) {
							if (bestScore[i] < a[i][j]) {
								bestScore[i] = a[i][j];
								best[i] = j;
							}
						}
					}
				}
			}

			for (int i = 0; i < n; i++) {
				if (!done[i] && best[i] != -1 && i == best[best[i]]) {
					pairs[i] = best[i];
					pairs[best[i]] = i;
					done[i] = true;
					done[best[i]] = true;
					doneCount += 2;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			out.print(1 + pairs[i] + " ");
		}
		out.println();
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
