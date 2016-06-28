package com.marinshalamanov.codeforces.ed13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class C {
	
	public static long gcd(long a, long b) {
		return (a==0)?(b):(gcd(b%a, a)); 
	}
	
	public static long lcm(long a, long b) {
		return (long)a*(long)b / gcd(a, b);
	}
	
	public void solve(InputReader in, PrintWriter out) {
        long n, a, b, p, q;
        n = in.nextLong();
        a = in.nextLong();
        b = in.nextLong();
        p = in.nextLong();
        q = in.nextLong();
        
//        if(a == b) {

    		
//        } else {
        	long mAB = n / (lcm (a, b));
            long mA = (n / a) - mAB;
            long mB = (n / b) - mAB;
            
            if (p > q) {
            	System.out.println((mA + mAB)*p + mB*q);
    		} else {
    			System.out.println(mA*p + (mB + mAB)*q);
    		}
//        }
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
