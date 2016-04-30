package com.marinshalamanov.hackerrank.algorithms.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BiggerIsGreater {
	
	private static Comparable[] nextPermutation( final Comparable[] c ) {
		// 1. finds the largest k, that c[k] < c[k+1]
		int first = getFirst( c );
		if ( first == -1 ) return null; // no greater permutation
		// 2. find last index toSwap, that c[k] < c[toSwap]
		int toSwap = c.length - 1;
		while ( c[ first ].compareTo( c[ toSwap ] ) >= 0 )
			--toSwap;
		// 3. swap elements with indexes first and last
		swap( c, first++, toSwap );
		// 4. reverse sequence from k+1 to n (inclusive) 
		toSwap = c.length - 1;
		while ( first < toSwap )
			swap( c, first++, toSwap-- );
		return c;
	}
	
	private static int getFirst( final Comparable[] c ) {
		for ( int i = c.length - 2; i >= 0; --i )
			if ( c[ i ].compareTo( c[ i + 1 ] ) < 0 )
				return i;
		return -1;
	}
	
	// swaps two elements (with indexes i and j) in array 
	private static void swap( final Comparable[] c, final int i, final int j ) {
		final Comparable tmp = c[ i ];
		c[ i ] = c[ j ];
		c[ j ] = tmp;
	}
	
	public void solve(InputReader in, PrintWriter out) {
        int t = in.nextInt();
        while(t-- > 0) {
        	String w = in.next();
        	boolean impossible = true;
        	
        	
        	for (int i = 1; i < w.length(); i++) {
        		if(w.charAt(i) > w.charAt(i-1)) {
        			impossible = false;
        			break;
        		}
        	}
        	
        	if(impossible) {
        		System.out.println("no answer");
        		continue;
        	}
        	
        	Character cha[] = new Character[w.length()];
        	for(int i = 0; i < w.length(); i++) {
        		cha[i] = w.charAt(i);
        	}
        	
        	cha = (Character[]) nextPermutation(cha);
        	String next = "";
        	for(char c : cha) next += c;
        	System.out.println(next);
        }
		
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        BiggerIsGreater solver = new BiggerIsGreater();
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
