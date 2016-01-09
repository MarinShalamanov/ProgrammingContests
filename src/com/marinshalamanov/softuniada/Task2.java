package com.marinshalamanov.softuniada;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task2 {
	
	void rep(int n, char c,  PrintWriter out) {
		for(int i = 0; i < n; i++) {
			out.print(c);
		}
	}
	
	public void solve(InputReader in, PrintWriter out) {
        int n = in.nextInt();
        
        for(int i = n-1; i >= 0; i--) {
        	rep(i, '.', out);
        	if(i == n-1) {
        		out.print('*');
        	} else {
        		out.print('*');
        		if(i!=0) {
        			rep(2*(n-i-2)+1, ' ', out);
        		} else {
        			for(int j = 0; j < 2*(n-i-2)+1; j++) {
        				if(j%2==0) {
        					out.print(' ');
        				} else {
        					out.print('*');
        				}
        			}
        		}
        		out.print('*');
        	}
        	rep(i, '.', out);
        	out.println();
        }
        
        
        out.print('+');
        rep(2*(n-2)+1, '-', out);
        out.print('+');
        out.println();
        
        for(int i =0 ; i < n-2; i++) {
        	out.print('|');
            rep(2*(n-2)+1, ' ', out);
            out.print('|');

            out.println();
        }

        out.print('+');
        rep(2*(n-2)+1, '-', out);
        out.print('+');

        out.println();
        
        
        
		
		
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task2 solver = new Task2();
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
