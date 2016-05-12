package com.marinshalamanov.hackerrank.world_codespring_april_2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class JumpingOnTheCloud {
	
	public void solve(InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int c[] = new int[n];
        for(int i = 0; i < n; i++) c[i] = in.nextInt();
        int count = 0;
        
        int i = 0;
        while(i != n-1) {
        	if(i+2 < n && c[i+2] == 0) {
        		i = i+2;
        	} else {
        		i++;
        	}
        	count++;
        }
        
        System.out.println(count);
		
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        JumpingOnTheCloud solver = new JumpingOnTheCloud();
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
