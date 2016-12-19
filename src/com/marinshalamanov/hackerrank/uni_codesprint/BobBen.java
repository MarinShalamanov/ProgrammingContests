package com.marinshalamanov.hackerrank.uni_codesprint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BobBen {
	
	public void solve(InputReader in, PrintWriter out) {
        int g = in.nextInt();
        while (g-- > 0) {
        	
        	int n = in.nextInt();
        	int nech = 0, chet = 0;
        	int num2 = 0;
        	
        	int m[] = new int [n];
        	for(int i = 0; i < n; i++) {
        		int mi, ki;
        		mi = in.nextInt();
        		m[i] = mi;
        		ki = in.nextInt();
        		
        		if(mi%2 == 0) chet++;
        		else nech++;
        		
        		if(mi == 2) num2++;
        	}
        	
        	if(n%2 == 1) {
        		boolean hasWinningStrategy = false;
        		for(int i = 0 ; i < n; i++) {
        			if(m[i] != 2) {
        				if(m[i]%2 == 0) chet--;
        				else nech--;
        				
        				if(chet%2 == 0) {
                			hasWinningStrategy = true;
                			break;
        				}
        				
        				if(m[i]%2 == 0) chet++;
        				else nech++;
        			}
        		}
        		
        		if(hasWinningStrategy) {
        			System.out.println("BOB");
        		} else {
        			System.out.println("BEN");
        		}
        	} else {
        		if(chet%2 == 1) {
        			System.out.println("BOB");
        		} else {
        			System.out.println("BEN");
        		}
        	}

        	
        }
		
		
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        BobBen solver = new BobBen();
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
