package com.marinshalamanov.codeforces.ed15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class D {
	
	long a, b, t, k, d;
	
	long getTime(long p) {
		long time = Long.MAX_VALUE;
		
		if(p*k <= d) {
    		time = Math.min(time, p*k*a + t*(p-1) + (d-p*k)*b);
    	} else {
    		time = Math.min(time, (p-1)*k*a + t*(p-1) + (d-(p-1)*k)*a );
    	}
    	
    	
    	return time;
		
	}
	
	public void solve(InputReader in, PrintWriter out) {
        d = in.nextLong();
        k = in.nextLong();
        a = in.nextLong();
        b = in.nextLong();
        t = in.nextLong();
        
        long p;
        long time;
        
        time = d*b;
        for(p = 1; p < 5; p++) {
        	if((p-1)*k < d ) {
        		time = Math.min(time, getTime(p));
        	}
        }
        
        for(p = (d/k) + 2; p > (d/k) - 5; p--) {
        	if((p-1)*k < d && p >= 1) {
        		time = Math.min(time, getTime(p));
        	}
        }
        
        System.out.println(time);
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
