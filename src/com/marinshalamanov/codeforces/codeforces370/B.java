package com.marinshalamanov.codeforces.codeforces370;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B {
	
	public void solve(InputReader in, PrintWriter out) {
        String s = in.next();
        
        if(s.length()%2 != 0) {
        	System.out.println(-1);
        	return;
        }
        
        int up = 0;
        int right = 0;
        for(int i = 0; i < s.length(); i++) {
        	switch (s.charAt(i)) {
			case 'U': up++; break;
			case 'D': up--; break;
			case 'R': right++; break;
			case 'L': right--; break;
			default:
				break;
			}
        }
		
        int changes = 0;
        up = Math.abs(up);
        right = Math.abs(right);
        while(up > 0 && right > 0) {
        	changes++;
        	up--;
        	right--;
        }
        
        while(up > 0) {
        	changes++;
        	up-=2;
        }
        
        while(right > 0) {
        	changes++;
        	right-=2;
        }
        
        System.out.println(changes);
		
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        B solver = new B();
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
