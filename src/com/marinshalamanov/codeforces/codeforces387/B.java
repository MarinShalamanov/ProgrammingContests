package com.marinshalamanov.codeforces.codeforces387;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B {
	
	public void solve(InputReader in, PrintWriter out) {
        int n = in.nextInt();
        char arr[] = in.next().toCharArray();
        
        if(n%4 != 0) {
        	System.out.println("===");
        	return;
        }
        
        int k = n/4;
        
        int A = 0;
        int G = 0;
        int T = 0;
        int C = 0;
        for(int i = 0; i < n; i++) {
        	switch(arr[i]) {
        	case 'A': A++; break;
        	case 'G': G++; break;
        	case 'T': T++; break;
        	case 'C': C++; break;
        	}
        }
        
        
        if(A > k || G > k || T > k || C > k) {
        	System.out.println("===");
        	return;
        }
        
        for(int i = 0; i < n; i++) {
        	if (arr[i] == '?') {
        		if(A < k) {
        			arr[i] = 'A';
        			A++;
        		} else if(G < k) {
        			arr[i] = 'G';
        			G++;
        		} else if(T < k) {
        			arr[i] = 'T';
        			T++;
        		} else if(C < k) {
        			arr[i] = 'C';
        			C++;
        		}
        	} 
        	
        	System.out.print(arr[i]);
        }
		System.out.println();
		
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
