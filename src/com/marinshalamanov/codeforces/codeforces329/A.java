package com.marinshalamanov.codeforces.codeforces329;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class A {
	
	public void solve(InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String[] words = new String[n];
        List<Set<Character>> letters = new ArrayList<Set<Character>>();
        for(int i = 0; i < n; i++) {
        	words[i] = in.next();
        	letters.add(new HashSet<Character>());
        	for(int k = 0; k < words[i].length(); k++) {
        		letters.get(i).add(words[i].charAt(k));
        	}
        	
        	
        }
		
        int maxLen = 0;
        for(char a = 'a'; a <= 'z'; a++) {
        	for(char b = a; b <= 'z'; b++) {
        		int currLen = 0;
        	
        		for(int i = 0; i < n; i++) {
        			Set<Character> currSet = letters.get(i);
					boolean el2 = currSet.size() == 2 && currSet.contains(a) && currSet.contains(b);
        			boolean el1 = currSet.size() == 1 && (currSet.contains(a) || currSet.contains(b));
        			
        			if(a==b) el2 = false;
        			
        			if(el1 || el2 ) {
	        			currLen += words[i].length();
        			}
        			
        		}
        		
        		//out.println(a + " " + b + " " + currLen);
        		
        		maxLen = Math.max(currLen, maxLen);
        		
        	}
                
        }
        
        out.println(maxLen);
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
