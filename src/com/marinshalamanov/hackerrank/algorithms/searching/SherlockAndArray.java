package com.marinshalamanov.hackerrank.algorithms.searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SherlockAndArray {
	
	int n;
	int sum[];
	
	int get (int i) {
		
		int left = (i==0)?(0):(sum[i-1]);
		int right = sum[n-1] - sum[i];
		
		return right - left;
	}
	
	public void solve(InputReader in, PrintWriter out) {
        int t = in.nextInt();
        
        test:
        while (t-- != 0) {
        	n = in.nextInt();
        	sum = new int[n];
        	sum[0] = in.nextInt();
        	
        	for(int i = 1; i < n; i++) {
        		sum[i] = sum[i-1] + in.nextInt();
        	}        
        
        	
        	if (n==1) {
            	System.out.println("YES");
//            	System.out.println(0);
            	continue test;
            }
            
            int left=0, right=n-1, mid;
            
            while (left+1 < right) {
            	mid = (left + right)/2;
            	
            	int v = get(mid);
            	if(v > 0) {
            		left = mid;
            	} else if(v < 0) {
            		right = mid;
            	} else {
            		System.out.println("YES");
//            		System.out.println(mid);
            		continue test;
            	}
            }
            
            if(get(left) == 0 || get(right)==0) {
            	System.out.println("YES");
            	continue test;
            }
    		System.out.println("NO");
        }
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        SherlockAndArray solver = new SherlockAndArray();
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
