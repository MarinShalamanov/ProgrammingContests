package com.marinshalamanov.codeforces.ed19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class D {
	
	public void solve(InputReader in, PrintWriter out) {
        int n = in.nextInt();
        
        long val[] = new long[n+1];
        int leftIdx[] = new int[n+1];
        int rightIdx[] = new int[n+1];
        int parent[] = new int[n+1];
        
        for(int i = 1; i <= n; i++) {
        	long v;
        	int l, r;
        	v = in.nextLong();
        	l = in.nextInt();
        	r = in.nextInt();
        	
        	val[i] = v;
        	leftIdx[i] = l;
        	rightIdx[i] = r;
        	
        	if(l != -1) parent[l] = i;
        	if(r != -1) parent[r] = i;
        }
        
        int root = 1;
        while(parent[root] != 0) root = parent[root];
        
        Stack<Tr> st = new Stack<>();
        
        st.push(new Tr(root, Long.MIN_VALUE, Long.MAX_VALUE));
        
        HashSet<Long> willBeFound = new HashSet<>();
        
        while(!st.isEmpty()) {
        	Tr curr = st.pop();
        	
        	if(val[curr.nodeIdx] <= curr.high && val[curr.nodeIdx] >= curr.low) {
        		willBeFound.add(val[curr.nodeIdx]);
        	}
        	
        	if(leftIdx[curr.nodeIdx] != -1) {
        		st.push(new Tr(leftIdx[curr.nodeIdx], curr.low, Math.min(curr.high, val[curr.nodeIdx]-1)));
        	}
        	
        	if(rightIdx[curr.nodeIdx] != -1) {
        		st.push(new Tr(rightIdx[curr.nodeIdx], Math.max(curr.low, val[curr.nodeIdx]+1), curr.high));
        	}
        	
        }
        
        int count = 0;
        for(int i = 1; i <= n; i++) {
        	if(!willBeFound.contains(val[i])) {
        		count++;
        	}
        }
        
        System.out.println(count);
    }
	
	class Tr {
		int nodeIdx; 
		long low, high;

		public Tr(int nodeIdx, long low, long l) {
			super();
			this.nodeIdx = nodeIdx;
			this.low = low;
			this.high = l;
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
