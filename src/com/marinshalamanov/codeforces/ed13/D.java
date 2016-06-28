package com.marinshalamanov.codeforces.ed13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.invoke.LambdaConversionException;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class D {
	
	 long m = (long) (1e9 + 7);
	 
	public void solve(InputReader in, PrintWriter out) {
//       System.out.println(-1mod);
//        System.out.println(m);
		long a, b, n, x;
        a = in.nextLong() % m;
        b = in.nextLong() % m;
        n = in.nextLong() ;
        x = in.nextLong() % m;
        
//        long ax = (a*x) % m;
        long an = pow(a, n);
        
//        if(n!=1) {
	        if(a != 1) {
		        long div = pow(a-1, m-2); // Long.parseLong(new BigInteger(Long.toString(a - 1)).modInverse(new BigInteger("" + m)).toString());
		        
		        long prog = ( ((an - 1+m)%m) *div) % m ;
		        long res = ( ((prog*b)%m) + ((an*x)%m) ) %m;
		        
		        System.out.println(res);
	        } else {
	        	long prog = n % m;
		        long res = ((prog*b)%m + ((an*x)%m) ) %m;
		        
		        System.out.println(res);
	        }
//        } else {
//        	System.out.println( ((a*x)%m + b)%m );
//        }
        
        
//        if(n!=1) {
//	        if(ax != 1) {
//		        long div = Long.parseLong(new BigInteger(Long.toString(ax - 1)).modInverse(new BigInteger("" + m)).toString());
//		        
//		        
//		        long prog = ((axn - 1)*div) % m ;
//		        long res = ((prog*b)%m + axn ) %m;
//		        
//		        System.out.println(res);
//	        } else {
//	        	long prog = n % m;
//		        long res = ((prog*b)%m + axn ) %m;
//		        
//		        System.out.println(res);
//	        }
//        } else {
//        	System.out.println( ((a*x)%m + b)%m );
//        }
		
    }
	
	public long pow(long a, long pow) {
		long res = 1;
		
		while (pow > 0) {
			if(pow%2 == 1) {
				res = (res * a) % m;
			}
			pow /= 2;
			a = (a * a) % m;
		}
		
		return res;
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
