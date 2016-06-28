package com.marinshalamanov.codeforces.ed13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B {
	
	
	boolean isLeap(int y) {
		return y % 400 == 0 || (y % 4 == 0 && y % 100 != 0);
	}
	
	public void solve(InputReader in, PrintWriter out) {
		int n = in.nextInt();
		boolean isLeap = isLeap(n);
		int diff = 0;
		while (true) {
			if (isLeap(n)) {
				diff += 366 % 7;
			} else {
				diff += 365 % 7;
			}
			diff %= 7;
			n++;
			if (diff == 0 && isLeap(n) == isLeap) {
				System.out.println(n);
				return;
			}
		}
		
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
