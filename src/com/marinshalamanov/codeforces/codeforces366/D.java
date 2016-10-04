package com.marinshalamanov.codeforces.codeforces366;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class D {
	int x[], a[], b[], c[], d[];
	int n, s, e;
	
	int w(int i, int j) {
		if(i < j) {
			return x[j] - x[i] + d[i] + a[j];
		} else {
			return x[i] - x[j] + c[i] + b[j];
		}
	}
	public void solve(InputReader in, PrintWriter out) {
        n = in.nextInt();
        s = in.nextInt();
        e = in.nextInt();
		
        x = new int[n];
		a = new int[n];
		b = new int[n];
		c = new int[n];
		d = new int[n];
		
		for(int i = 0; i < n; i++) x[i] = in.nextInt();
		for(int i = 0; i < n; i++) a[i] = in.nextInt();
		for(int i = 0; i < n; i++) b[i] = in.nextInt();
		for(int i = 0; i < n; i++) c[i] = in.nextInt();
		for(int i = 0; i < n; i++) d[i] = in.nextInt();
		
		
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        D solver = new D();
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
