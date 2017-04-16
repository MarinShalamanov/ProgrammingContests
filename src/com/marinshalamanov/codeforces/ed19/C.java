package com.marinshalamanov.codeforces.ed19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class C {
	
	public void solve(InputReader in, PrintWriter out) {
        String s = in.next();
        char minLet[] = new char[s.length()];
        
        minLet[s.length()-1] = s.charAt(s.length()-1);
        
        for(int i = s.length()-2; i >= 0; i--) {
        	minLet[i] = (char) Math.min(s.charAt(i), minLet[i+1]);
        }
        
        Stack<Character> st = new Stack<>();
        int si = 0;
        
        while(si != s.length()) {
        	if(st.isEmpty()) {
        		st.push(s.charAt(si));
        		si++;
        	}
        	
        	while(si != s.length() && st.peek() > minLet[si]) {
        		st.push(s.charAt(si));
        		si++;
        	} 
        	
        	System.out.print(st.pop());
        }
        
        while(!st.isEmpty()) {
        	System.out.print(st.pop());
        }
        
        System.out.println();
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
        
        public long nextLong() {
            return Long.parseLong(next());
        }
        
        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
