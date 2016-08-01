package com.marinshalamanov.codeforces.ed15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class B {
	
	public void solve(InputReader in, PrintWriter out) {
		int n = in.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) a[i] = in.nextInt();
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			if (map.containsKey(a[i])) map.put(a[i], map.get(a[i]) + 1);
			else map.put(a[i], 1);
		}
		Set<Integer> keys = map.keySet();
		int[] b = new int[keys.size()];
		int i = 0;
		for(int key: keys) {
			b[i++] = key;
		}
		long result = 0;
		for (i = 0; i < b.length; i++) {
			for (int p = 2; p <= b[0] + b[b.length-1]; p*=2) {
				int diff = p - b[i];
				if (diff < b[i]) continue;
				else if (diff == b[i]) {
					int c = map.get(b[i]);
					result += c*(c-1)/2;
				} else if (map.containsKey(diff)){
					result += map.get(b[i])*map.get(diff);
				}
			}
		}
		System.out.println(result);
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
