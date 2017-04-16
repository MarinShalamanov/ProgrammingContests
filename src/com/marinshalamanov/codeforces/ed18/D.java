package com.marinshalamanov.codeforces.ed18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class D {
	
	public void solve(InputReader in, PrintWriter out) {
        long  n, q;
        n = in.nextLong();
        q = in.nextLong();
        
        while(q-- > 0) {
        	long v = in.nextLong();
        	String s = in.next();
        	
        	for(int i = 0; i < s.length(); i++) {
        		char c = s.charAt(i);
        		if(c == 'U') {
        			if(v-1 == n/2) { // root
        				continue;
        			}
        			boolean isRight = false;
        			
        			long v1 = v - (v&(-v));
        			long v2 = v1 & (-v1);
        			isRight = ((v2 >> 1L) == (v&(-v)));
        			//System.out.println("v2 = " + v2);
        			//System.out.println("(v&(-v)) = " + (v&(-v)));
        			//System.out.println("isR " + isRight);
        			if(isRight) v -= v&(-v);
        			else v += v&(-v);
        		} else if(c=='L') {
        			if((v&(-v)) == 1L) { // leaf
        				continue;
        			}
        			v -= ((v&(-v))>>1L);
        		} else {  // c== 'R'
        			if((v&(-v)) == 1L) { // leaf
        				continue;
        			}
        			v += ((v&(-v))>>1L);
        		}
        	}
        	System.out.println(v);
        	
        	
        	
        	
        }
		
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        D solver = new D();
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
