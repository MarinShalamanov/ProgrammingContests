package com.marinshalamanov.codeforces.bublecup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D {
	
	public void solve(InputReader in, PrintWriter out) {
		
		long startTime = System.currentTimeMillis();
		
        long n = in.nextLong();
        int x = in.nextInt();
        
        double p[] = new double[129];
		for(int i = 0; i <= x; i++) {
			p[i] = in.nextDouble();
		}
		
		int CURR = 1;
		int LAST = 0;
		double prop[][] = new double[2][129];
		
		for(int i=0; i <= x; i++) {
			prop[0][i] = p[i];
		}
		
		
		int stop = (int) Math.min(n, 1000);
		for(int col = 1; col < n; col++) {
			
//			Arrays.fill(prop[CURR], 0);
			
//			double sum = 0.0;
			for(int i=0; i < 128; i++) {
				prop[CURR][i] = 0;
				for(int j = 0; j <= x; j++) {
					prop[CURR][i] += prop[LAST][i^j]*p[j];
				}
				
//				sum += prop[CURR][i];
			}
//			System.out.println("s=" + sum); 
			
			
			int t = CURR;
			CURR = LAST;
			LAST = t;
			
			if(col%100 == 0 && (1000 - (System.currentTimeMillis() - startTime) < 200)) {
//				System.out.println("c" + col);
				break;
			}
			
//			if(col%100 == 0) System.out.println(col + " " + prop[LAST][0]);
//			System.out.println(1 - prop[LAST][0]);
		}
		
		System.out.println(1 - prop[LAST][0]);
	}
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        D solver = new D();
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
