package com.marinshalamanov.hackerrank.hr17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class C {
	
	public static int gcd(int a, int b) {
		return (a==0)?(b):(gcd(b%a, a)); 
	}
	
	public void solve(InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        
        int q = in.nextInt();
        
        int a[] = new int[n];
        int b[] = new int[m];
        
        for(int i = 0; i < n; i++) a[i] = in.nextInt();
        for(int i = 0; i < m; i++) b[i] = in.nextInt();
        
        while(q-- > 0) {
        	int r1, c1, r2, c2;
        	r1 = in.nextInt();
        	c1 = in.nextInt();
        	r2 = in.nextInt();
        	c2 = in.nextInt();
        	
        	int size = 0;
        	int a1[] = new int[r2-r1+1];
        	int b1[] = new int[c2-c1+1];
        	
        	for(int i = r1; i <= r2; i++) {
        		a1[i-r1] = a[i];
        	}
        	
        	for(int j = c1; j <= c2; j++) {
        		b1[j-c1] = b[j];
        	}
        		
        	Arrays.sort(a1);
        	Arrays.sort(b1);
        	
        	
        	for(int g = 1; g <= 2 + 10e5; g++) {
        		
        	}
        	
        	System.out.println(size);
        	
        	
        }
        
		
		
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        C solver = new C();
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
