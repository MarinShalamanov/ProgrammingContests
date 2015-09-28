package codeforces320;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class C {

	static final double eps = 1e-10;

	public void solve(InputReader in, PrintWriter out) {
		long a = in.nextInt();
		long b = in.nextInt();

		if (b > a) {
			out.println(-1);
			return;
		} else if (a == b) {
			out.println(a);
			return;
		}

		// case /
		long a0 = a - b;
		long b0 = 0;
		double x0 = a0 / (double) (2 * (a0 / (2 * b)));

		// case \
		long a1 = a + b;
		long b1 = 0;
		double x1 = a1 / (double) (2 * (a1 / (2 * b)));

		double x = Math.min(x0, x1);
		if (x == Double.MAX_VALUE) {
			x = -1;
		}
		out.println(x);
	}

	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		C solver = new C();
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

		public long nextInt() {
			return Long.parseLong(next());
			// return Integer.parseInt(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}
	}
}
