package com.marinshalamanov.codeforces.codeforces360;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class E {
	
	int used[][];
	long dp[][];
	int c[] ;
	
	void f(int i, int j) {
		if(j < 0) return;
		
		if(used[i][j] == 1) 
			return;
		
		used[i][j] = 1;
		
		f(i, j-1);
		if (i - c[j] >= 0)
			f(i - c[j], j-1);

	}
	
	public void solve(InputReader in, PrintWriter out) {
        int n, k;
        n = in.nextInt();
        k = in.nextInt();
        
        c = new int[n];
        for(int i = 0; i < n; i++) {
        	c[i] = in.nextInt();
        }
        

        used = new int[k+1][n];
        dp = new long[k+1][n];
        dp[c[0]][0] = 1;
        
        for(int i = 0; i < n; i++) {
        	dp[0][i] = 1;
        }
        
        for(int j = 1; j < n; j++) {
        	for(int i = 0; i <= k; i++) {
        		dp[i][j] = dp[i][j-1] + 
        				((i - c[j] >= 0)?(dp[i - c[j]][j-1]):(0));
        	}
        }
        
        f(k, n-1);
        
        for(int i = 0; i < dp.length; i++) {
        	for(int j = 0; j < dp[i].length; j++) {
        		System.out.print(dp[i][j] + " ");
        	}
        	System.out.println();
        }
        
        System.out.println();
        for(int i = 0; i < used.length; i++) {
        	for(int j = 0; j < used[i].length; j++) {
        		System.out.print(used[i][j] + " ");
        	}
        	System.out.println();
        }
        
        List<Integer> op = new ArrayList<>();
		op.add(0);
        for(int x = 1; x <= k; x++) {
			for(int j = 0; j < n; j++) {
				if(dp[x][j] != 0 && used[x][j]==1) {
					op.add(x);
					break;
				}
			}
		}
        
        System.out.println(op.size());
        for(int x : op) System.out.print(x + " ");
        System.out.println();
		
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
