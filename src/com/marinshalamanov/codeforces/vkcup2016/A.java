package com.marinshalamanov.codeforces.vkcup2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class A {
	
	public void solve(InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int c = in.nextInt();
        int p[] = new int[n];
        int t[] = new int[n];
        
        for(int i = 0; i < n; i++) p[i] = in.nextInt();
        for(int i = 0; i < n; i++) t[i] = in.nextInt();
        
        int l = 0;
        int r = 0;
        
        int time = 0;
        for(int i = 0; i < n; i++) {
        	time += t[i];
        	l += Math.max(0, p[i] - time*c);
        }
        
        time = 0;
        for(int i = n-1; i >=0; i--) {
        	time += t[i];
        	r += Math.max(0, p[i] - time*c);
        }
        
        if(l > r) System.out.println("Limak");
        else if(l==r) System.out.println("Tie");
        else System.out.println("Radewoosh");
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
