package com.marinshalamanov.codeforces.codeforces384;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class D {
	
	int n ;
	int a[];
	
	List<Integer> adj[];
	
	long m[], k[], s[];
	int d[];
	
	public void solve(InputReader in, PrintWriter out) {
		n = in.nextInt();
		a = new int[n];
		for(int i =0 ; i < n; i++) a[i] = in.nextInt();

		adj = new ArrayList[n];
		for(int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		
		m = new long[n];
		k = new long[n];
		s = new long[n];
		d = new int[n];
		
		Arrays.fill(m, Long.MIN_VALUE);
		Arrays.fill(k, Long.MIN_VALUE);
		Arrays.fill(s, Long.MIN_VALUE);
		
		int u, v;
		for(int i = 1; i < n; i++) {
			u = in.nextInt();
			v = in.nextInt();
			u--; v--;
			
			adj[u].add(v);
			adj[v].add(u);
		}
		
		boolean used[] = new boolean[n];
		Arrays.fill(used, false);
		
		Stack<Integer> f = new Stack<>();
		f.add(0);
		used[0] = true;
		d[0] = 0;
		
		while(f.isEmpty()) {
			int curr = f.pop();
			for(int child : adj[curr]) {
				if(!used[child]) {
					used[child] = true;
					f.add(child);
					d[child] = d[curr]+1;
				}
			}
		}
		
		Integer backoreder[] = new Integer[n];
		for(int i = 0 ; i < n; i++) backoreder[i] = i;
		
		
		Arrays.sort(backoreder, new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return Integer.compare(d[b], d[a]);
			}
		});
		
		for (int i = 0; i < n; i++) {
			int cv = backoreder[i];
			
			s[cv] = 0;
			
			for(int child : adj[cv]) {
				if(m[child] != Long.MIN_VALUE) {
					s[cv] += s[child];
					m[cv] = Math.max(m[child], m[cv]);
				}
			}
			
			m[cv] = Math.max(m[cv], s[cv]);
			
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
        
        public long nextLong() {
            return Long.parseLong(next());
        }
        
        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
