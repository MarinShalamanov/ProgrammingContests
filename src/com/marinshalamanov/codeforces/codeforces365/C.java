package com.marinshalamanov.codeforces.codeforces365;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class C {
	
	public void solve(InputReader in, PrintWriter out) {
        int n;
        long w, v, u;
        n = in.nextInt();
        w = in.nextLong();
        v = in.nextLong();
        u = in.nextLong();
		
        Point p[] = new Point[n];
        
        int lowPoint = -1, highPoint = -1; 
        long minY, maxY;
        minY = Long.MAX_VALUE;
        maxY = Long.MIN_VALUE;
        
        for(int i = 0; i < n; i++) {
        	p[i] = new Point(in.nextLong(), in.nextLong());
        	if(p[i].y > maxY) {
        		maxY = p[i].y;
        		highPoint = i;
        	}
        	
        	if(p[i].y < minY) {
        		maxY = p[i].y;
        		lowPoint = i;
        	}
        }
        
        // going straight
        boolean f = true;
        int i = lowPoint;
        while(true) {
        	if (u * p[i].x < p[i].y * v ) {
        		f = false;
        		break;
        	}
        	if(i == highPoint) break;
        	
        	i = (i-1 + n)%n;
        }
        
        if(f) {
        	System.out.println(w / (double) u);
        	return;
        }
        
        
        // go back
        double currTime = 0;
        long currY = 0;
        
        i = lowPoint;
        while(true) {
//        	speed = Math.min(u, (p[i].y - currY) / ((p[i].x - v*currTime) / v));
        	
        	if(u * (p[i].x - v*currTime) <=  (p[i].y - currY) * v) {
        		currTime += (p[i].y - currY) / (double) u;
        	} else {
        		currTime +=  (p[i].x - v*currTime) / (double) v;
        	}
//        	currTime += (p[i].y - currY) / Math.min(u, ));
        	currY = p[i].y;
        	if(i == highPoint) break;
        	
        	i = (i+1)%n;
        }
        
        currTime += (w - currY) / (double) u;
        
        System.out.println(currTime);
    }
	
	class Point {
		long x, y;

		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
		
		public double dist2(Point other) {
			double dx = other.x - x;
			double dy = other.y - y;
			return dx*dx + dy*dy;
		}
		
		public double dist(Point other) {
			return Math.sqrt(dist2(other));
		}
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
