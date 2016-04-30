package com.marinshalamanov.codeforces.vkcup2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class D {
	int n, k;
	BigInteger a[];
	BigInteger kBi, mkBi;
	
	public void solve(InputReader in, PrintWriter out) {
        n = in.nextInt();
        k = in.nextInt();
        a = new BigInteger[n+1];
        
        kBi = new BigInteger(Integer.toString(k));
        mkBi =new BigInteger(Integer.toString(-k));
        
        for(int i = 0; i <= n; i++) a[i] = new BigInteger(in.next()); //.nextInt();
        
        BigInteger res = new BigInteger("0");
        BigInteger pow2 = BigInteger.ONE;
        
        for(int i = 0; i <= n; i++) {
        	
        	res = res.add(pow2.multiply(a[i])) ;
//        	System.out.println(pow2.multiply(a[i]));
        	
        	pow2 = pow2.add(pow2);
        }
        
        pow2 = BigInteger.ONE;
//        System.out.println(res);
        int count = 0;
        for(int i = 0; i <= n; i++) {
        	BigInteger[] divrem = res.divideAndRemainder(pow2);
        	if (divrem[1].compareTo(BigInteger.ZERO) == 0) {
        		
        		BigInteger changed = a[i].subtract(divrem[0]);
        		if(i==n && changed.compareTo(BigInteger.ZERO)==0) {
        			break;
        		}
        		
//        		System.out.println(changed + "\t" + divrem[0]);
        		if(changed.compareTo(kBi) <= 0 && changed.compareTo(mkBi) >= 0) {
        			count ++;
        		}
        	}
        	pow2 = pow2.add(pow2);
        }
        
        System.out.println(count);
        
        
        
		
		
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
        
        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
