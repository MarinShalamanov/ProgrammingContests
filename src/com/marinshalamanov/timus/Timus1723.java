package com.marinshalamanov.timus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Timus1723 {
	
	public void solve(InputReader in, PrintWriter out) {
        String s = in.next();
		int count;
		int bestCount = 0;
		char bestChar = 'a';
        for(char c = 'a'; c <= 'z'; c++) {
			count = 0;
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == c) count++;
			}
			if(count > bestCount) {
				bestCount = count;
				bestChar = c;
			}
		}
        
        System.out.println(bestChar+"");
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Timus1723 solver = new Timus1723();
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
