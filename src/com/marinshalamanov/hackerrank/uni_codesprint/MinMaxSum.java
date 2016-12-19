package com.marinshalamanov.hackerrank.uni_codesprint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MinMaxSum {
	
	public void solve(InputReader in, PrintWriter out) {
		long arr[] = new long[5];
        
        long sum = 0;
        long min = Integer.MAX_VALUE;
        long max = Integer.MIN_VALUE;
        for(int i = 0; i < 5; i++) {
        	arr[i] = in.nextInt();
        	sum += arr[i];
        	min = Math.min(min, arr[i]);
        	max = Math.max(max, arr[i]);
        	
        }
        
        System.out.println((sum-max) + " " + (sum-min));
        
		
        
		
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        MinMaxSum solver = new MinMaxSum();
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
