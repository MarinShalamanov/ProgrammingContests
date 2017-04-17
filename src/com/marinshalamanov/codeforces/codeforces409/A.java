package com.marinshalamanov.codeforces.codeforces409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class A {
	
	public void solve(InputReader in, PrintWriter out) {
        String s = in.next();
        
        
        int count = 0;
        boolean change = false;
        for(int i = 1; i < s.length(); i++) {
        	if(s.charAt(i-1) == 'V' && s.charAt(i) == 'K') {
        		count++;
        		i++;
        		continue;
        	} 
        	
        	if(!change) {
        		if(i+1 < s.length()) {
        			if((s.charAt(i-1) == s.charAt(i) && s.charAt(i) == 'V' && s.charAt(i+1) != 'K') || 
        					(s.charAt(i-1) == s.charAt(i) && s.charAt(i) == 'K')) {
        				
        				count++;
                		i++;
                		change = true;
                		continue;
        			}
        		} else {
        			if(s.charAt(i-1) == s.charAt(i)) {
        				count++;
                		i++;
                		change = true;
                		continue;
        			}
        		}
        	}
        	
        }
        
        System.out.println(count);
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
        
        public long nextLong() {
            return Long.parseLong(next());
        }
        
        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
