package com.marinshalamanov.usp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SoftuniTest {
	
	public void solve(InputReader in, PrintWriter out) {
        long a = in.nextInt();
        long b = in.nextInt();
        
        if(a > 0 && b > 0) {
        	out.println(1);
        } else if(a < 0 && b > 0) {
        	out.println(2);
        } else if(a > 0 && b < 0) {
        	out.println(4);
        } else if(a < 0 && b < 0) {
        	out.println(3);
        } else if(a == 0 && b != 0) { 
        	out.println(5);
        } else if(a != 0 && b == 0) {
        	out.println(6);
        } else if(a == 0 && b == 0) {
        	out.println(0);
        }
		
        
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        SoftuniTest solver = new SoftuniTest();
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
