package com.marinshalamanov.codeforces.codeforces360;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class D {
	
	boolean primes[] = new boolean[1000005];
	List<Integer> p = new ArrayList<>();
	
	public void solve(InputReader in, PrintWriter out) {
        int n, k;
        n = in.nextInt();
        k = in.nextInt();
        
        long c[] = new long[n];
        for(int i = 0; i < n; i++) {
        	c[i] = in.nextLong();
        }
        
        primes[0] = true;
        primes[1] = true;
        for(int i = 0; i < primes.length; i++) {
        	if(!primes[i]) {
        		p.add(i);
        		for(int j = 2*i; j < primes.length; j+=i) {
        			primes[j] = true;
        		}
        	}
        }
        
        for(int i = 0; i < p.size(); i++) {
        	int pi = p.get(i);
        	int pow = 1;
        	int _k = k;
        	while (_k % pi == 0) {
        		_k /= pi;
        		pow *= pi;
        	}
        	
        	if(pow != 1) {
        		boolean f = false;
        		for(int j = 0; j < n; j++) {
        			if(c[j] % pow == 0) {
        				f = true;
        				break;
        			}
        		}
        		if(!f) {
        			System.out.println("No");
        			return;
        		}
        	}
        }
        
        
		System.out.println("Yes");
		
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
