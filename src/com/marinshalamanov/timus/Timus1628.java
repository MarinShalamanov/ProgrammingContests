package com.marinshalamanov.timus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Timus1628 {
	
	public void solve(InputReader in, PrintWriter out) {
		int n, m, k;
		n = in.nextInt();
		m = in .nextInt();
		k = in.nextInt();
		
		List< List<Integer> > rows = new ArrayList< List<Integer> >(n);
		List< List<Integer> > cols = new ArrayList< List<Integer> >(m);
		
		for(int i = 0; i < n; i++) {
			rows.add(new ArrayList<Integer>());
		}
		
		for(int i = 0; i < n; i++) {
			cols.add(new ArrayList<Integer>());
		}
		
		for(int i = 0; i < k; i++) {
			int x, y;
			x = in.nextInt(); 
			y = in.nextInt();
			rows.get(x).add(y);
			cols.get(y).add(x);
		}
		for(int i = 0; i < n; i++) {
			Collections.sort(rows.get(i));
		}
		for(int i = 0; i < m; i++) {
			Collections.sort(rows.get(m));
		}
		
		
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Timus1628 solver = new Timus1628();
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
