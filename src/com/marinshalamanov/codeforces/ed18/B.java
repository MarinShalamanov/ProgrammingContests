package com.marinshalamanov.codeforces.ed18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B {
	
	public void solve(InputReader in, PrintWriter out) {
        int n, k;
        n = in.nextInt();
        k = in.nextInt();
        //int a[] = new int[k];
        boolean dead[] = new boolean [n];
        
        int count = n;
        int leader = 0;
        
        for(int i = 0; i < k; i++) {
        	int a = in.nextInt();
        	
        	a %= count;
        	int j = 0;
        	j = leader;
        	for(; a>0; a--) {
        		j = (j+1)%n;
        		while(dead[j]) j = (j+1)%n;
        	}
        	
        	
        	System.out.print((j+1) + " ");
        	dead[j] = true;
        	count--;
        	
        	leader = (j+1)%n;
        	while(dead[leader]) {
        		leader = (leader+1)%n;
        	}
        	
        	//System.out.println("leader is " + (leader+1));
        }
        System.out.println();
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
