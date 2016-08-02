package com.marinshalamanov.codeforces.codeforces362;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class D {
	
	int n, p[];
	List<List<Integer>> children;
	
	double time[];
	
	int size[];
	
	int compSize(int vert) {
		if(size[vert] != 0) {
			return size[vert];
		}
		
		int sz = 1; 
		for(int child : children.get(vert)) {
			sz += compSize(child);
		}
		
		size[vert] = sz;
		return sz;
	}
	
	
	public void solve(InputReader in, PrintWriter out) {
		n = in.nextInt();
		
		time = new double[n];
		size = new int[n];
		p = new int[n];
		p[0] = 0;
		children = new ArrayList<>();
		for(int i = 0; i < n; i++) children.add(new ArrayList<>());
		
		for(int i = 1; i < n; i++) {
			p[i] = in.nextInt() - 1;
			children.get(p[i]).add(i);
		}
		
		compSize(0);
		
		Stack<Integer> s = new Stack<>();
		s.add(0);
		time[0] = 1.0;
		
		while(!s.isEmpty()) {
			int top = s.pop();
			
			double A = 0;
			for(int child : children.get(top)) A += size[child];
//			A /= (double) children.get(top).size();
			
//			int numCh = children.get(top).size();
			
			for(int child : children.get(top)) {
				s.add(child);
//				double t = 1;
//				double currT = 1;
//				for(int k = 1 ; k < numCh; k++) { // can be written w/o a loop
//					currT = currT + (A - size[child]) / (double) (numCh-1);
//					t += currT;
//				}
//				t /= numCh;
				
				double t = 1 + (A - size[child]) / 2.0;
				time[child] = time[top] + t;
			}
		}
		
		for(int i = 0; i < n; i++) {
			System.out.print(time[i] + " ");
		}
		System.out.println();
		
		
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
