package com.marinshalamanov.softuniada;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;



public class Task7 {
	
	int n;
	
	class Point {
		double x, y;

		public Point(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
		
		public double dist2(Point p) {
			return (p.x-x)*(p.x-x) + (p.y-y)*(p.y-y);
		}

	}
	
	
	
	class Kup implements Comparable<Kup> {
		public String name;
		public List<Point> points = new ArrayList<>();
		public Point center;
		
		@Override
		public String toString() {
			return "Kup [name=" + name + ", points=" + points + "]";
		}
		
		public void calcCenter() {
			double x=0, y = 0; 
			for(Point p : points) {
				x += p.x;
				y += p.y;
			}
			x /= (double)points.size();
			y /= (double)points.size();
			center = new Point(x, y);
		}

		@Override
		public int compareTo(Kup o) {
			return name.compareTo(o.name);
		}
	}
	
	Kup k[];
	
	
	Kup getNearest(Point p) {
		Kup near = null;
		double dist = Long.MAX_VALUE;
		
		for(int i = 0; i < n; i++) {
			double d = k[i].center.dist2(p);
			if(d < dist) {
				dist = d;
				near = k[i];
			}
		}
		
		return near;
	}
	
	
	Point readNextPoint (InputReader in) {
		String s1 = in.next();
		String s2 = in.next();
//		System.out.println(s1 + "|"  +s2);
		Point p = new Point(Integer.parseInt(s1.substring(1, s1.length()-1)), 
				Integer.parseInt(s2.substring(0, s2.length()-1)));
		return p;
	}
	
	
	List<Point> stars = new ArrayList<>();
	
	long round(double v) {
		int down = (int)v;
		if(Math.abs(v - down - 0.5) <  1e-5) {
			if (down%2 == 0) {
				return down;
			} else {
				return down+1;
			}
		} else {
			return Math.round(v);
		}
	}
	
	public void solve(InputReader in, PrintWriter out) {
		
        n = in.nextInt();
		k = new Kup[n];
		
		for(int i = 0; i < n; i++) {
			k[i] = new Kup();
			k[i].name = in.next();
			
			Point p = readNextPoint(in);
			k[i].points.add(p);
		}
		
		try {
			while(true)
			stars.add(readNextPoint(in));
		} catch(Exception e) {
//			e.printStackTrace();j
		}
		
//		out.println(stars);
		
		final int gen = 100;
		
		for(int i = 0; i < gen; i++) {
			
			for(Kup k1 : k) {
				k1.calcCenter();
			}
			
			for(Kup k1 : k) {
				stars.addAll(k1.points);
				k1.points.clear();
			}
			
			for(Point p : stars) {
				getNearest(p).points.add(p);
			}
			
			stars.clear();
		}
		
		for(Kup k1 : k) {
			k1.calcCenter();
		}
		
		Arrays.sort(k);
		
		for(int i = 0; i < n; i++) {
			out.println(k[i].name + " (" + round(k[i].center.x) + ", " + round(k[i].center.y) + ") -> " + k[i].points.size() + " stars");
		}
		
		
//		out.println(Arrays.toString(k));
		
		
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task7 solver = new Task7();
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
