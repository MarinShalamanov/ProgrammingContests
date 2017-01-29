package com.marinshalamanov.hackerrank.world_codespring9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class WeightedUnif {
	
	public void solve(InputReader in, PrintWriter out) {
        String s = in.next();
        
        HashSet<Long> w = new HashSet<>();
        
        char last = s.charAt(0);
        long wei = 1+last-'a';
        w.add(wei);
        
        for(int i = 1; i < s.length(); i++) {
        	if(s.charAt(i) != s.charAt(i-1)) {
        		wei = 1+s.charAt(i)-'a';
        	} else {
        		wei += 1+s.charAt(i)-'a';
        	}
        	
        	w.add(wei);
        	//System.out.println("added " + wei);
        }
        
        int n = in.nextInt();
        for(int i =0; i < n; i++) {
        	long q = in.nextLong();
        	if(w.contains(q)) {
        		System.out.println("Yes");
        	} else {
        		System.out.println("No");
        	}
        }
		
		
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        WeightedUnif solver = new WeightedUnif();
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
