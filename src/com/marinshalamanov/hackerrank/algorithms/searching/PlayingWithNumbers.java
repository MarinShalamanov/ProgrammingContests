package com.marinshalamanov.hackerrank.algorithms.searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class PlayingWithNumbers {
	
	int n;
	
	List<Long> arr;
	long prefixSum[];
	
	int coolBS(List<Long> l, long element) {
		int id1 = Collections.binarySearch(arr, element);
    	if(id1 < 0) id1 = -(id1+1);
    	
    	while (id1 > 0 && l.get(id1-1) == element) {
    		id1--;
    	}
    	
    	return id1;
	}
	
	public void solve(InputReader in, PrintWriter out) {
        n = in.nextInt();
        arr = new ArrayList<>(n);
        prefixSum = new long[n+1];
        
        for(int i = 0; i < n; i++) {
        	arr.add(in.nextLong());
        }
        
        Collections.sort(arr);

        prefixSum[0] = 0;
        for(int i = 0; i < n; i++) {
        	prefixSum[i+1] = prefixSum[i] + arr.get(i);
        }
        
        int q = in.nextInt();
        
        long newSum = 0;
        
        long x = 0;
        
        while (q-- > 0 ) {
        	x += in.nextInt();
        	
        	newSum = 0;
        	
        	int id2 = coolBS(arr, -x); 
        	newSum += (prefixSum[n] - prefixSum[id2]) + x*(n - id2);		// positive
        	newSum += (prefixSum[id2] + x*id2)*(-1);						// negative
        	
        	System.out.println(newSum);
        }
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        PlayingWithNumbers solver = new PlayingWithNumbers();
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

        public Long nextLong() {
        	return Long.parseLong(next());
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
