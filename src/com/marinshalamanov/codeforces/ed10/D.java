package com.marinshalamanov.codeforces.ed10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class D {
	
	public void solve(InputReader in, PrintWriter out) {
       int n = in.nextInt();

       Segment s[] = new Segment[n];
       for(int i = 0; i < n; i++) {
    	   s[i] = new Segment(in.nextInt(), in.nextInt(), i);
       }

       Arrays.sort(s);

       
       Map<Integer, Integer> map = new HashMap<>();
       Fenwick fen = new Fenwick(n);
       
       int ends[] = new int[n];
       for(int i = 0; i < n; i++) {
    	   ends[i] = s[i].b;
       }
       Arrays.sort(ends); 
       for(int i = 0; i < n; i++) {
    	   map.put(ends[i], i);
    	   fen.add(i, 1);
       }

       int answ[] = new int[n];
       for(int i = 0; i < n; i++) {
    	   int endIdx = map.get(s[i].b);
    	   answ[s[i].idx] = fen.sum(endIdx-1);
    	   fen.add(endIdx, -1);
       }
       
       
       
       for(int i = 0; i < n; i++) {
    	   System.out.println(answ[i]);
       }
       
    }
	
	public class Fenwick {
		
		int n;
		int fen[];
		
		public Fenwick(int n) {
			n++;
			
			this.n = n;
			fen = new int[n];
			
		}
		
		public void add(int i, int delta) {
			i++;
			while(i < n) {
				fen[i] += delta;
				i += i&-i;
			}
		}
		
		public int sum(int i) {
			i++;
			int sum = 0; 
			while(i > 0) {
				sum += fen[i];
				i -= i&-i;
			}
			return sum;
		}	
	}
	
	class Segment implements Comparable<Segment> {
		int a, b;
		int idx; 
		public Segment(int a, int b, int idx) {
			super();
			this.a = a;
			this.b = b;
			this.idx = idx;
		}

		@Override
		public int compareTo(Segment o) {
			return a - o.a;
		}
	}
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        D solver = new D();
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
