package com.marinshalamanov.codeforces.codeforces349;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class A {
	
	public void solve(InputReader in, PrintWriter out) {
        
		int d = in.nextInt();
		int h = in.nextInt();
		int v = in.nextInt();
		int e = in.nextInt();
		
		double water = (d/2.0)*(d/2.0)*Math.PI * h;
		double speedMan = v;
		double speedRain = (d/2.0)*(d/2.0)*Math.PI * e;
		
		double fillInSpeed = (speedRain - speedMan);
		if (fillInSpeed >= 0) {
			System.out.println("NO");
			return;
		}
		
		double res = water / (-fillInSpeed);
		System.out.println("YES");
		System.out.println(res);
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
