package com.marinshalamanov.codeforces.ed13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class E {
	
	int n;
	double p[][];
	
	int bitsUp(int mask) {
		int count = 0;
		while(mask > 0) {
			if((mask & 1) != 0) {
				count++;
			}
			mask >>= 1;
		}
		return count;
	}
	
//	HashMap<Integer, Double> cache = new HashMap<Integer, Double>();
	
	double dp[][];
	
//	double dp(int mask, int arena) {
//		// mask = alive
//		// arena = id of person on arena
//		int hash = (mask << 3) + arena;
//		if(cache.containsKey(hash)) {
//			return cache.get(hash);
//		}
//		
//		double result = 0;
//		if (bitsUp(mask) == 1) {
//			if(arena == 0) return 1.0;
//			else return 0.0;
//		}
//		
//		for(int i = 0; i < n; i++) {
//			if(i != arena && ( (mask & (1 << i)) != 0)) {
//				double p1 = p[i][arena]*dp(mask-(1<<arena),i) + p[arena][i] * dp(mask-(1<<i), arena);
//				result = Math.max(result, p1);
//			}
//		}
//		
//		
//		cache.put(hash, result);
//		return result;
//	}
//	
	public void solve(InputReader in, PrintWriter out) {
        n = in.nextInt();
        p = new double[n][n];
        
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < n; j++) {
        		p[i][j] = in.nextDouble();
        	}
        }
        
        

        dp = new double[1<<n][n];
        dp[1][0] = 1;
        for(int mask = 3; mask < (1 << n); mask++) {
        	for(int arena = 0; arena < n; arena++) {
        		if((mask & (1 << arena)) != 0) {
        			for(int i = 0; i < n; i++) {
        				if(i != arena && ( (mask & (1 << i)) != 0)) {
        					double p1 = p[i][arena] * dp[mask-(1<<arena)][i] + p[arena][i] * dp[mask-(1<<i)][arena];
        					dp[mask][arena] = Math.max(dp[mask][arena], p1);
        				}
        			}
        		}
        	}
        }
        
        double sol = 0;
        for (int i = 0; i < n; i++) {
        	sol = Math.max(sol, dp[(1 << n) -1][i] );
        }
//        System.out.println(bitsUp((1 << n) -1));
        System.out.println(sol);
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
