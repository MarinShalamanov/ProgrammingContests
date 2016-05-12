package com.marinshalamanov.hackerrank.world_codespring_april_2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BeautifulTriplets {
	
	public void solve(InputReader in, PrintWriter out) {
        
		int n = in.nextInt();
		int d = in.nextInt();
		
		Set<Integer> s = new TreeSet<>();
        int count = 0;
		for(int i = 0; i < n; i++) {
        	int x = in.nextInt();
        	if(s.contains(x-d) && s.contains(x-2*d)) count++;
        	s.add(x);
        	
        }
		System.out.println(count);
        
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        BeautifulTriplets solver = new BeautifulTriplets();
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
