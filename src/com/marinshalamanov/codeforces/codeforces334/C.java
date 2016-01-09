package com.marinshalamanov.codeforces.codeforces334;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class C {
	
	public void solve(InputReader in, PrintWriter out) {
        int n = in.nextInt();
        String s = in.next();
        
        if (n==1) {
        	out.println(1);
        	return;
        } else if(n==2) {
        	out.println(2);
        	return;
        }
        
        int numZones = 1;
        for(int i = 1; i < n; i++) {
        	if(s.charAt(i) != s.charAt(i-1)) {
        		numZones++;
        	}
        }
        
        
        int more = 0;
        
        if(s.charAt(0) != s.charAt(1) && s.charAt(1) == s.charAt(2) ) {
        	more = 1;
        }
        
        if(s.charAt(n-1) != s.charAt(n-2) && s.charAt(n-2) == s.charAt(n-3) ) {
        	more = 1;
        }
        
        
        for(int i = 1; i < n; i++) {
        	if(s.charAt(i) == s.charAt(i-1)) {
        		more = 1;
        	}
        }
        
        for(int i = 2; i < n; i++) {
        	if(s.charAt(i) == s.charAt(i-1) && s.charAt(i) == s.charAt(i-2)) {
        		more = 2;
        		break;
        	}
        }
        
        for(int i = 3; i < n; i++) {
        	if(s.charAt(i) == s.charAt(i-1) && s.charAt(i-1) != s.charAt(i-2) && s.charAt(i-2)==s.charAt(i-3)) {
        		more = 2;
        		break;
        	}
        }
        
        boolean foundTwo = false;
        int zonesBetween = 0;
        boolean foundOtherTwo = false;
        for(int i = 1; i < n; i++) {
        	if(!foundTwo) {
	        	if(s.charAt(i) == s.charAt(i-1)) {
	        		foundTwo = true;
	        		
	        	}
        	} else {
        		if(zonesBetween < 2) {
        			if(s.charAt(i) != s.charAt(i-1)) {
    	        		zonesBetween++;
    	        	}
        		} else {
        			if(s.charAt(i) == s.charAt(i-1)) {
        				foundOtherTwo = true;
        				more = 2;
    	        		break;
    	        	}
        		}
        	}
        }
        
        
        
        
        numZones += more;
        out.println(numZones);
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
