package com.marinshalamanov.codeforces.ed10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class A {
	
	public void solve(InputReader in, PrintWriter out) {
        int h1, h2;
        h1 = in.nextInt();
        h2 = in.nextInt();
        
        int a = in.nextInt();
        int b = in.nextInt();
        
        int height = h1;
        int days = 0;
        

        if (height + 8*a >= h2) {
        	System.out.println(0);
        	return;
        }
        
        while(true) {
        	int newHeight = height + 12*(a-b);
        	
        	if (newHeight <= h1) {
        		System.out.println(-1);
        		return;
        	}
        	
        	days++;
        	int endOfDay = newHeight + 8*a;

        	if (endOfDay >= h2) {
        		System.out.println(days);
        		return;
        	}
        	height = newHeight;
        }
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        A solver = new A();
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
