package com.marinshalamanov.codeforces.codeforces365;

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
        int k = in.nextInt();
        
        long c[] = new long[n];
        boolean cap[] = new boolean[n];
        long sumAll = 0;
//        long sumCap = 0;
        for(int i = 0; i < n; i++) {
        	c[i] = in.nextInt();
        	sumAll += c[i];
        }
        
        for(int i = 0; i < k; i++) cap[in.nextInt()-1] = true;
		
        long sum = 0;
        long curr;
        for(int i = 0; i < n; i++) {
        	if(cap[i]) {
        		if(i > 0) {
	        		if(!cap[i-1])
	        			curr = c[i]*(sumAll - c[i] - c[i-1]);
	        		else 
	        			curr = c[i]*(sumAll - c[i]);
        		} else {
        			if(!cap[n-1]) {
        				curr = c[i]*(sumAll - c[i] - c[n-1]);
        			} else {
        				curr = c[i]*(sumAll - c[i]);
        			}
        		}
        		
        		sumAll -= c[i];
        	} else {
        		curr = c[i]*(c[(i+1)%n]); // + c[ (i-1+n)%n ]);
        	}
        	
//        	System.out.println((i+1) + " " + curr);
        	sum += curr;
        }
        
        System.out.println(sum);
        
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
