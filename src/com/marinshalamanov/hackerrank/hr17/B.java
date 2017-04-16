package com.marinshalamanov.hackerrank.hr17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B {
	
	public void solve(InputReader in, PrintWriter out) {
        
		int q = in.nextInt();
		while (q-- > 0 ) {
			int n = in.nextInt();
			int arr[][] = new int[n][n];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					arr[i][j] = in.nextInt();
				}
			}
			
			int rowsSum[] = new int[n];
			int colsSum[] = new int[n];
			
			for(int i = 0; i < n; i++) {
				rowsSum[i] = 0;
				for(int j = 0; j < n; j++) {
					rowsSum[i] += arr[i][j];
				}
			}
			
			for(int j = 0; j < n; j++) {
				colsSum[j] = 0;
				for(int i = 0; i < n; i++) {
					colsSum[j] += arr[i][j];
				}
			}
			
			Arrays.sort(rowsSum);
			Arrays.sort(colsSum);
			
			boolean can = true;
			for(int i = 0; i < n; i++) {
				if(rowsSum[i] != colsSum[i]) {
					can = false;
					break;
				}
			}
			
			if(can) {
				System.out.println("Possible");
			} else {
				System.out.println("Impossible");
			}
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
