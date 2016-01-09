package com.marinshalamanov.codeforces.codeforces335;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class C {
	
	public void solve(InputReader in, PrintWriter out) {
        int n;
        n = in.nextInt();
        int a[] = new int[n];
        
        int numPos[] = new int[n+1];
        int longesUpTo[] = new int[n+1];
        
        for (int i = 0; i < n; i++) {
			a[i] = in.nextInt();
			numPos[a[i]] = i;
		}

        
        for (int i = 0; i < n; i++) {
			if(a[i] == 1) {
//				longesUpTo[] = 0;
			} else if(numPos[a[i]-1] < i) {
				longesUpTo[a[i]] = longesUpTo[a[i]-1] + 1;
			}
		}
        
        int maxx = -1;
        for (int i = 0; i <= n; i++) {
        	maxx = Math.max(maxx, longesUpTo[i]);
        }
        maxx++;
        
        System.out.println(n-maxx);
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
        
        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
