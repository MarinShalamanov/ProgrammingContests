package com.marinshalamanov.codeforces.ed18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A {
	
	public void solve(InputReader in, PrintWriter out) {
        int n;
        n = in.nextInt();
        
        int a[] = new int[n];
        for(int i = 0; i < n; i++) {
        	a[i] = in.nextInt();
        }
        Arrays.sort(a);
        
        int minDist = a[1]-a[0];
        int c = 0;
        int curr;
        for(int i = 1; i < n; i++) {
        	curr = a[i] - a[i-1];
        	
        	if(curr < minDist) {
        		minDist = curr;
        		c = 1;
        	} else  if(curr == minDist){
        		c++;
        	}
        }
        
        System.out.println(minDist + " " + c);
		
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
        
        public long nextLong() {
            return Long.parseLong(next());
        }
        
        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
