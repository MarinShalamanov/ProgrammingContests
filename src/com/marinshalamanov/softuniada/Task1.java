package com.marinshalamanov.softuniada;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task1 {
	
	public void solve(InputReader in, PrintWriter out) {
        int a[] = new int[4];
        int sum = 0;
        for(int i =0 ; i < 4; i++) {
        	a[i] = in.nextInt();
        	sum += a[i];
        }
        
        if(sum%2 == 1) {
        	out.println("No");
        	return;
        }
        
        sum /= 2;
        
        for(int i = 0; i < 4; i++) {
        	if(a[i] == sum) {
        		out.println("Yes");
    			out.println(sum);
    			return;
        	}
        }
        
        for(int i = 0; i < 4; i++) {
        	for(int j = i+1; j < 4; j++) {
        		if(a[i] + a[j] == sum) {
        			out.println("Yes");
        			out.println(sum);
        			return;
        		}
        	}
        }
        
        
        
        out.println("No");
		
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task1 solver = new Task1();
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
