package com.marinshalamanov.codeforces.codeforces365;

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
        
        int ac, bc;
        ac = bc = 0;
        int a, b;
        
        for(int i = 0; i < n ; i++) {
        	a = in.nextInt();
        	b = in.nextInt();
        	if ( a > b) {
        		ac++;
        	}
        	if( a < b) {
        		bc++;
        	}
        }
        
        if(ac > bc) {
        	System.out.println("Mishka");
        } else if(ac==bc) {
        	System.out.println("Friendship is magic!^^");
        } else {
        	System.out.println("Chris");
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
        
        public long nextLong() {
            return Long.parseLong(next());
        }
        
        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
