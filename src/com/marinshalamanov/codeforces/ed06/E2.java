package com.marinshalamanov.codeforces.ed06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class E2 {
	
	int n, m;
	
	List<List<Integer>> adj;
	int p[];
	long mask[];
	int col[];
	
	int bitsUp(long mask) {
		int count = 0;
		while(mask > 0) {
			if((mask & 1) != 0) {
				count++;
			}
			mask >>= 1;
		}
		return count;
	}
	
	boolean isFirstChild(int v) {
		int par = p[v];
		int gran = p[par];
		
		if(adj.get(par).get(0) == gran) return adj.get(par).get(1) == v;
		else return adj.get(par).get(0) == v;
	}
	
	public void solve(InputReader in, PrintWriter out) {
		n = in.nextInt();
		m = in.nextInt();
		
		adj = new ArrayList<>();
		p = new int[n];
		mask = new long[n];
		col = new int[n];
		
		for (int i = 0; i < n; i++) adj.add(new ArrayList<Integer>());
		
		for (int i = 0; i < n; i++) {
			col[i] = in.nextInt() - 1;
			mask[i] = (1 << col[i]);
		}
		
		for(int i = 0; i < n-1; i++) {
			int u, v;
			u = in.nextInt() - 1;
			v = in.nextInt() - 1;
			
			adj.get(u).add(v);
			adj.get(v).add(u);
		}
		
		System.out.println("read graph");
		p[0] = 0;
		Stack<Integer> st = new Stack<>();
		st.add(0);
		
		Queue<Integer> leaves = new LinkedList<>();
		while(!st.isEmpty()) {
			int top = st.pop();
			for(int child : adj.get(top)) {
				if(child != p[top]) {
					p[child] = top;
					st.add(child);
				}
			}
			
			if(adj.get(top).size() == 1 && top != 0) {
				leaves.add(top);
			}
		}
		
//		System.out.println("parent");
		
		while(!leaves.isEmpty()) {
			int curr = leaves.poll();
			for(int child : adj.get(curr)) {
				if(child != p[curr]) {
					mask[curr] = (mask[curr] | mask[child]);
				}
			}
			
			if(isFirstChild(curr))
				leaves.add(p[curr]);
			
//			System.out.println(leaves.size());
		}
		
		
//		System.out.println("queries");
		for(int k = 0; k < m; k++) {
			int t, v, c;
			t = in.nextInt();
			
			if(t == 1) {
				v = in.nextInt() - 1;
				c = in.nextInt() - 1;
				
				col[v] = c;
				mask[v] = (1 << c);
				
				// update down
				Stack<Integer> s = new Stack<>();
				s.add(v);
				
				while(!s.isEmpty()) {
					int top = s.pop();
					col[top] = c;
					mask[top] = (1 << c);
					
					for(int child : adj.get(top)) {
						if(child != p[top]) {
							s.add(child);
						}
					}
				}
				
				// update up
				v = p[v];
				while(p[v] != v) {
					long newMask = (1 << col[v]);
					for(int child : adj.get(v)) {
						if(child != p[v]) {
							newMask = (newMask | mask[child]);
						}
					}
					if(newMask == mask[v]) {
						break;
					}
					mask[v] = newMask;
					
					v = p[v];
				}
				
				
				
			} else {
				v = in.nextInt() - 1;
				System.out.println(bitsUp(mask[v]));
			}
			
		}
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        E2 solver = new E2();
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
