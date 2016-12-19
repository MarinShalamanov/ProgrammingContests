package com.marinshalamanov.hackerrank.uni_codesprint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Kindergarten {
	
	Fenwick f;
	
	public void solve(InputReader in, PrintWriter out) {
        f = new Fenwick(300_005);
		int n = in.nextInt();
		
		int t[] = new int[n];
		for(int i = 0; i < n; i++) {
			t[i] = in.nextInt();
			f.add(t[i]-i+2*n, 1);
		}
		
		int best = f.sum(2*n);
		int bestPos = 0;
		for(int x = 1; x < n; x++) {
			f.add(t[x-1]-(x-1)+2*n, -1);
			f.add(t[x-1]-(x+n-1)+2*n, 1);
			
			
			int current = f.sum(2*n-x);
//			System.out.println("start " + x + " res " + current);
			if (current > best) {
				best = current;
				bestPos = x;
			}
		}
		
		System.out.println(bestPos+1);
		
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Kindergarten solver = new Kindergarten();
        solver.solve(in, out);
        out.close();
    }
    
    public class Fenwick {
    	
    	int n;
    	int fen[];
    	
    	public Fenwick(int n) {
    		n++;
    		
    		this.n = n;
    		fen = new int[n];
    		
    	}
    		
    	public void add(int i, int delta) {
//    		System.out.println("add " + i + " " + delta);
    		i++;
    		while(i < n) {
    			fen[i] += delta;
    			i += i&-i;
    		}
    	}
    	
    	public int sum(int i) {
    		i++;
    		int sum = 0; 
    		while(i > 0) {
    			sum += fen[i];
    			i -= i&-i;
    		}
    		return sum;
    	}	
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
