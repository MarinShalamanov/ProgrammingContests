package com.marinshalamanov.codeforces.codeforces334;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class A {
	
	final double e = 1e-5;
	
	public void solve(InputReader in, PrintWriter out) {
		double x[] = {500, 1000, 1500, 2000, 2500};
        double m[] = new double[5];
		double w[] = new double[5];
		double hs, hu;
		
		for(int i = 0; i < 5; i++) {
			m[i] = in.nextDouble();
		}
		
		for(int i = 0; i < 5; i++) {
			w[i] = in.nextDouble();
		}
		
		hs = in.nextDouble();
		hu = in.nextDouble();
		
		double total = 0;
		for(int i = 0; i < 5; i++) {
			total += Math.max(0.3*x[i], (x[i] - (m[i]*x[i])/250.0) - 50*w[i]);
		}
		
		total += 100*hs - 50*hu;
		
		out.println((int)(total+e));
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        A solver = new A();
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
