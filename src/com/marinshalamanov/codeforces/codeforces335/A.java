package com.marinshalamanov.codeforces.codeforces335;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class A {
	
	public void solve(InputReader in, PrintWriter out) {
        int a, b, c;
        int x, y, z;
        
        a = in.nextInt();
        b = in.nextInt();
        c = in.nextInt();
        x = in.nextInt();
        y = in.nextInt();
        z = in.nextInt();
        
        int pass1 = (a>x)?(a-x):(0);
        int pass2 = (b>y)?(b-y):(0);
        int pass3 = (c>z)?(c-z):(0);
		
        int passTotal = pass1/2 + pass2/2 + pass3/2;
        int need1 = (a<x)?(x-a):(0);
        int need2 = (b<y)?(y-b):(0);
        int need3 = (c<z)?(z-c):(0);
		int needTotal = need1 + need2 + need3;
		
		if(passTotal >= needTotal) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
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
