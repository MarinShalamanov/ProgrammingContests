package com.marinshalamanov.codeforces.ed19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class E {
	
	public void solve(InputReader in, PrintWriter out) {
        int n = in.nextInt();
        
        int a[] = new int[n+1];
        for(int i = 1; i <= n; i++) {
        	a[i] = in.nextInt();
        }
        
        
        final int nn = 320;
        
        int sol[][] = new int[nn+1][n+1];
        for(int k = 1; k <= nn; k++) {
        	for(int i = n; i >= 1; i--) {
        		if(i + a[i] + k > n) {
        			sol[k][i] = 1;
        		} else {
        			sol[k][i] = 1 + sol[k][i + a[i] + k];
        		}
        	}
        }
        
        int q = in.nextInt();
        
        while(q-- > 0) {
        	int p, k;
        	p = in.nextInt();
        	k = in.nextInt();
        	
        	if(k > nn) {
        		int steps = 0;
        		
        		while(p <= n) {
        			p = p + a[p] + k;
        			steps++;
        		}
        		
        		System.out.println(steps);
        	} else {
        		System.out.println(sol[k][p]);
        	}
        }
        
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
