package com.marinshalamanov.codeforces.codeforces363;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class E {
	
	double p[];
	double dp[][];
	
	double solve(int n, int k) {
		if(dp[n][k] != 0) {
			return dp[n][k];
		}
		
		double asw = 0;
		
		if( n < k) {
			asw = 0;
		} else if (k == 1) {
			for(int i = 0; i < n; i++) {
				asw += p[i];
			}
		} else {
			asw += solve(n-1, k); //dp[n-1][k];
			asw += solve(n-1, k-1) * k * p[n-1];
		}
		
		dp[n][k] = asw;
		return asw;
	}
	
	public void solve(InputReader in, PrintWriter out) {
		int n, k;
		
        n = in.nextInt();
        k = in.nextInt();
        
        p = new double[n];
        
        for(int i = 0; i < n; i++) p[i] = in.nextDouble();
        
        dp = new double[n+1][k+1];
        
        System.out.println(1 - solve(n-1, k));
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        E solver = new E();
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
