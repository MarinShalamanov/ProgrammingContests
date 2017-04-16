	package com.marinshalamanov.codeforces.ed19;

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
        int a[] = new int[n];
        
        int sumEven = 0;
        int sumOddPositive = 0;
        int numOddPositive = 0;
        int minOddPositive = Integer.MAX_VALUE;
        int maxOddNegative = Integer.MIN_VALUE;
        
        for(int i = 0; i < n; i++) {
        	a[i] = in.nextInt();
        	if(Math.abs(a[i]) % 2 == 0 && a[i] > 0) sumEven+=a[i];
        	
        	if(Math.abs(a[i]) % 2 == 1 && a[i] > 0) {
        		sumOddPositive += a[i];
        		numOddPositive ++;
        		minOddPositive = Math.min(minOddPositive, a[i]);
        	}
        	
        	if(Math.abs(a[i]) % 2 == 1 && a[i] < 0) {
        		maxOddNegative = Math.max(maxOddNegative, a[i]);
        	}
        }
        
//        System.out.println("sumEven: " + sumEven);
//        System.out.println("numOddPos: " + numOddPositive);
//        System.out.println("summOddPos: " + sumOddPositive);
//        System.out.println("maxOddNeg: " + maxOddNegative);
        if(numOddPositive > 0) {
        	if(numOddPositive%2 == 0) {
        		if(maxOddNegative != Integer.MIN_VALUE && maxOddNegative > -minOddPositive) {
        			System.out.println(sumEven + sumOddPositive + maxOddNegative);
        		} else {
        			System.out.println(sumEven + sumOddPositive - minOddPositive);
        		}
        	} else {
        		System.out.println(sumEven + sumOddPositive);
        	}
        } else {
        	System.out.println(sumEven + maxOddNegative);
        }
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
