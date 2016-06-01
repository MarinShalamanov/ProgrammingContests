package com.marinshalamanov.codeforces.codeforces354;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A {
	
	public void solve(InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int a[] = new int[n];
        int pos1=-1, posn=-1;
        
        for(int i = 0; i < n; i++) {
        	a[i] = in.nextInt();
        	if(a[i]==1) {
        		pos1 = i;
        	}
        	if(a[i]==n) {
        		posn = i;
        	}
        }
        
//        System.out.println(pos1);
//        System.out.println(posn);
        int pos[] = {
        		posn - pos1,
        		Math.abs(posn - 0), 
        		Math.abs(posn - (n-1) ), 
        		Math.abs(pos1 - 0), 
        		Math.abs(pos1 - (n-1)) };
        Arrays.sort(pos);
        System.out.println(pos[pos.length-1]);
   
		
		
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        A solver = new A();
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
