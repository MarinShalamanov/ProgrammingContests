package com.marinshalamanov.codeforces.ed15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class E {
	
	long k;
	int n, f[][];
	long w[];
	long s[][], m[][];

	final int dep = 35;
	
	public void solve(InputReader in, PrintWriter out) {
		n = in.nextInt();
		k = in.nextLong();
		f = new int[n][dep];
		w = new long[n];
		
		for(int i = 0; i < f.length; i++) f[i][0] = in.nextInt();
	    for(int i = 0; i < w.length; i++) w[i] = in.nextLong();
	        
		s = new long[n][dep];
		m = new long[n][dep];
		
		for(int i = 0; i < n; i++) s[i][0] = m[i][0] = w[i];
		
		for(int p = 1; p < dep; p++) {
			for(int i = 0; i < n; i++) {
				f[i][p] = f[f[i][p-1]][p-1];
				s[i][p] = s[i][p-1] + s[f[i][p-1]][p-1];
				m[i][p] = Math.min(m[i][p-1], m[f[i][p-1]][p-1]);
			}
		}
		
		for(int i = 0; i < n; i++) {
			long sum, min;
			sum = 0; 
			min = Long.MAX_VALUE;
			
			int vert = i;
			for(int j = 0; j < dep && (k >> j) > 0; j++) {
				if( (k & (1L << j)) != 0) {
					sum += s[vert][j];
					min = Math.min(min, m[vert][j]);
					
					vert = f[vert][j];
				}
			}
				
			System.out.println(sum + " " + min);
		}
		
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
