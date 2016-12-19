package com.marinshalamanov.codeforces.codeforces387;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class D {
	
	public void solve(InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        
//        int left=0;
        int right=0;
		PriorityQueue<Integer> intervals = new PriorityQueue<>();
		
		int currInterval = 0;
//		boolean isFirst = true;
		boolean hasNeg = false;
		for(int i = 0; i < n; i++) {
			int curr = in.nextInt();
			if (curr < 0) {
//				if(isFirst) {
//					left = currInterval;
//					isFirst = false;
//				} else {
				if(currInterval!=0)
					intervals.add(currInterval);
//				}
//					System.out.println(currInterval);
				hasNeg = true;
				k--;
				currInterval=0;
			} else {
				currInterval++;
			}
		}
		right = currInterval;
		
		if(!hasNeg) {
			System.out.println("0");
			return;
		}
		
		if(k<0) {
			System.out.println("-1");
			return;
		}
		
		while(!intervals.isEmpty() && k >= intervals.peek()) {
			k -= intervals.poll();
		}
//		System.out.println("right" + right);
//		System.out.println("k=" + k);
		
//		if (left > right) {
//			int t = left;
//			left = right;
//			right = t;
//		}
		
//		if(k >= left) k -= left;
		if(k >= right) {
			k-= right;
			right = 0;
		}
		
		int res = 1+intervals.size()*2;
		if(right != 0) res++;
		
		System.out.println(res);
		
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
