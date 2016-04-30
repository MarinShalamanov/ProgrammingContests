package com.marinshalamanov.hackerrank.algorithms.searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MissingNumbers {
	
	public void solve(InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int a[] = new int[n];
        for(int i = 0; i < n; i++) a[i] = in.nextInt();
        
        int m = in.nextInt();
        int b[] = new int[m];
        for(int i = 0; i < m; i++) b[i] = in.nextInt();
        
        if (n > m) {
        	int t = n;
        	n = m;
        	m = t;
        	
        	int _t[] = a;
        	a = b;
        	b = _t;
        }
        
        int countA[] = new int[101];
        int countB[] = new int[101];
        int offset = (int) 1e9;
        
        for(int i = 0; i < n; i++) {
        	offset = Math.min(offset, a[i]);
        }
        
        for(int i = 0; i < m; i++) {
        	offset = Math.min(offset, b[i]);
        }
        
        for(int i = 0; i < n; i++) {
        	countA[a[i]-offset]++;
        }
        
        for(int i = 0; i < m; i++) {
        	countB[b[i]-offset]++;
        }
        
        for (int i = 0; i < countA.length; i++) {
        	if (countA[i] < countB[i] ) {
        		System.out.print((i + offset) + " ");
        	}
        }
		
		System.out.println();
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        MissingNumbers solver = new MissingNumbers();
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
/*
13
203 204 204 205 206 207 205 208 203 206 205 206 204
9
203 205 206 207 208 203 204 205 206


*/
