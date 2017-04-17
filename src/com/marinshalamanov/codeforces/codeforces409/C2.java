package com.marinshalamanov.codeforces.codeforces409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class C2 {
	
	public void solve(InputReader in, PrintWriter out) {
        int n, p;
        n = in.nextInt();
        p = in.nextInt();
        
        long a[] = new long[n];
        long b[] = new long[n];
        
        long totalCons = 0, totalStorage = 0;
        
        
        for(int i = 0; i < n; i++) {
        	a[i] = in.nextLong();
        	b[i] = in.nextLong();
        	

            totalCons += a[i];
            totalStorage += b[i];
        }
        
        
        long residuals = 0;
        for(int i = 0; i < n; i++) {
        	residuals += a[i];
        }
        
        if(residuals <= p) {
        	System.out.println("-1");
        	return;
        }
        
        double left=0, right=1.0 * totalStorage/(totalCons - p);
        
        int reps = 75;
//        while (right - left > 1e-5) {
        while(reps-- > 0) {
        	double mid = (right + left)/2.0;
        		
        	double sum = 0;
        	for(int i = 0; i < n; i++) {
        		double curr = a[i] - b[i]/mid;
        		if(curr > 0) {
        			sum += curr;
        		}
        	}
        	
        	if(sum  < p + 1e-6) { //possible	
        		left = mid;
        	} else {
        		right = mid;
        	}
        }
        
        System.out.println((right+left)/2);
		
    }
	
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        C2 solver = new C2();
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
