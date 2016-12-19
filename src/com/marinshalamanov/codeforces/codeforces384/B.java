package com.marinshalamanov.codeforces.codeforces384;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B {
	
	long s (long n, long k) {
		if(n==1) return 1;
		
		
		//long len = (1L << n)-1;
		long halflen = (1L << (n-1) )-1;
		
		if(k <= halflen) {
			return s(n-1, k);
		} else if(k == halflen+1) {
			return n;
		} else {
			return s(n-1, k-halflen-1);
		}
		
	}
	
//	1 - 1
//	2 - 3
//	3 - 7
//	4 - 15   2^4  -1 
	public void solve(InputReader in, PrintWriter out) {
        long n, k;
		n = in.nextLong();
		k = in.nextLong();
		
//		for(int i = 1; i <= 15; i++) {
//			System.out.print(s(4, i) + " ");
//		}
		System.out.println(s(n, k));
		
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
        
        public long nextLong() {
            return Long.parseLong(next());
        }
        
        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
