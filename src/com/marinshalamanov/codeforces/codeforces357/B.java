package com.marinshalamanov.codeforces.codeforces357;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B {
	
	public void solve(InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int a = 1234567;
        int b = 123456;
        int c = 1234;
//        System.out.println(a+b+c);
        for(int x = 0; x*a <=n ;x++) {
//        	System.out.println(x);
        	for(int y = 0; x*a + y*b <= n;y++) {
//        		System.out.println("y = " + y + " " + (n - x*a - y*b));
        		if( (n - x*a - y*b) % c == 0) {
        			System.out.println("YES");
        			return;
        		}
        	}
        }
        System.out.println("NO");
	
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
