package com.marinshalamanov.codeforces.ed15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class C {
	
	final int CELL = 1;
	final int CITY = 0;
	
	class Point {
		public int x; 
		public  int type;
		
		
		
		public Point(int x, int type) {
			super();
			this.x = x;
			this.type = type;
		}
	}
	public void solve(InputReader in, PrintWriter out) {
        int n, m;
        n = in.nextInt();
        m = in.nextInt();
        
        int a[] = new int[n];
        int b[] = new int[m];
        
        Point p[] = new Point[n+m];
        
        for(int i = 0; i < a.length; i++) {
        	a[i] = in.nextInt();
        }
        for(int i = 0; i < b.length; i++) {
        	b[i] = in.nextInt();
        }
        
        int minDist[] = new int [n];
        
        for(int i = 0; i < n; i++) {
        	int currCityX = a[i];
        	
        	int pos = Arrays.binarySearch(b, currCityX);
        	if(pos < 0) {
        		pos = -(pos+1);
        	}
        	
        	minDist[i] = Integer.MAX_VALUE;
        	if(pos < m) {
        		minDist[i] = b[pos] - a[i];
        	}
        	if(pos-1>=0) {
        		minDist[i] = Math.min(minDist[i], a[i] - b[pos-1]);
        	}
        	
//        	System.out.println(minDist[i]);
        }
        
        int totMax = minDist[0];
        for(int tt : minDist) totMax = Math.max(totMax, tt);
        
        System.out.println(totMax);
        
        
//        int i, j;
//        i = j = 0;
        
//        int pointIdx = 0;
//        while (i < n || j < m) {
//        	if(i == n) {
//        		p[pointIdx++] = new Point(b[i], CELL);
//        		j++;
//        		continue;
//        	}
//        	
//        	if(j == m) {
//        		p[pointIdx++] = new Point(a[i], CITY);
//        		i++;
//        		continue;
//        	}
//        	
//        	if(a[i] <= b[j]) {
//        		p[pointIdx++] = new Point(a[i], CITY);
//        		i++;
//        	} else {
//        		p[pointIdx++] = new Point(b[i], CELL);
//        		j++;
//        	}
//        }
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        C solver = new C();
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
