package com.marinshalamanov.hackerrank.world_codespring_april_2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	
	public void solve(InputReader in, PrintWriter out) {
        int n = in.nextInt();
        
        List< List<Integer>> rods = new ArrayList<>(4);
        for(int i = 0; i < 4; i++) {
        	rods.add(new ArrayList<Integer>());
        }
        
        for(int i = 0; i < n; i++) {
        	int rod = in.nextInt();
        	rods.get(rod-1).add(i);
        }
        
        for(int i = 0; i < 4; i++) {
        	rods.get(i).sort((Integer a, Integer b) -> b-a );
        	System.out.println(rods.get(i).toString());
        }
        
//        rods.get(i)
        
        
		
		
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Solution solver = new Solution();
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
