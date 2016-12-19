package com.marinshalamanov.hackerrank.uni_codesprint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Array {
	
	int N;
	int d[] = new int[100];
	
	boolean solve(int n, int s, int k, int first) {
		
		d[n-1] = first;

		int idx = N - n;
		if(s < first) return false;
		
		if((2*idx-N+1) > 0 && k < 0) return false; 
		
		if(n == 1) {
			return first == s && (2*idx-N+1)*first == k; 
		} else {
			for(int next = first; next <= s; next++) {
				if(solve(n-1, s-first, k - (2*idx-N+1)*first, next) ) {
					return true;
				}
			}
			return false;
		}
	}
	
	public void solve(InputReader in, PrintWriter out) {
        
		int q = in.nextInt();
		nextq:
		while(q-- > 0) {
			int n = in.nextInt();
			int s = in.nextInt();
			int k = in.nextInt();
			N = n;
			
			for(int first = 0; first <= s; first++) {
				if(solve(N, s, k, first)) {
					for(int i = 0; i < n; i++) {
						System.out.print(d[n-i-1] + " ");
					}
					System.out.println();
					continue nextq;
				} 
			}

			System.out.println("-1");
			
			
//			System.out.println(Arrays.toString(d));
			
			
//			int dig[] = new int[n];
//			
//			final int max = 11*11*11*11*11;
//			
//			for(long x = 0; x < max; x++) {
//				long mask = x;
//				
//				for(int i = 0; i < n; i++) {
//					dig[i] = (int) (mask % 11);
//					mask /= 11;
//				}
//				
//				int sum = 0; 
//				for(int i = 0; i < n; i++) {
//					sum += dig[i];
//				}
//				
//				if (sum != s) { 
//					continue;
//				}
//				
//				
//				int sumd = 0;
//				for(int i = 0; i < n; i++) {
//					for(int j = i+1; j < n; j++) {
//						sumd += Math.abs(dig[j]-dig[i]);
//					}
//				}
//				
//				if(sumd == k){
//					for(int i = 0; i < n; i++) {
//						System.out.print(dig[n-i-1] + " ");
//					}
//					System.out.println();
//					continue nextq;
//				}
//				
//			}
//			System.out.println("-1");
			
		}
		
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Array solver = new Array();
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
