package com.marinshalamanov.softuniada;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task5 {

	class Rect {
		public double ax, ay, bx, by;

		@Override
		public String toString() {
			return "Rect [ax=" + ax + ", ay=" + ay + ", bx=" + bx + ", by=" + by + "]";
		}
		
		boolean isInside(double x, double y) {
			return ax <= x && x <= bx && by <= y && y <= ay;
		}

	}

	class Circ {
		public double ox, oy, r;

		@Override
		public String toString() {
			return "Circ [ox=" + ox + ", oy=" + oy + ", r=" + r + "]";
		}
		
		boolean isInside(double x, double y) {
			return (x-ox)*(x-ox) + (y - oy)*(y-oy) <= r*r;
		}
	}

	Rect r;
	Circ c;


	
	boolean isCircInside() {
		return r.isInside(c.ox, c.oy) && r.isInside(c.ox, c.oy+c.r) && r.isInside(c.ox, c.oy-c.r) &&
				r.isInside(c.ox + c.r, c.oy) && r.isInside(c.ox - c.r, c.oy);
	}
	
	boolean isRectInside() {
		return c.isInside(r.ax, r.ay) && c.isInside(r.ax, r.by) && c.isInside(r.bx, r.ay) && c.isInside(r.bx, r.by);
	}
	
	
	boolean isSomeCircInside() {
		return r.isInside(c.ox, c.oy) || r.isInside(c.ox, c.oy+c.r) || r.isInside(c.ox, c.oy-c.r) ||
				r.isInside(c.ox + c.r, c.oy) || r.isInside(c.ox - c.r, c.oy);
	}
	
	boolean isSomeRectInside() {
		return c.isInside(r.ax, r.ay) || c.isInside(r.ax, r.by) || c.isInside(r.bx, r.ay) || c.isInside(r.bx, r.by);
	}
	
	void read(InputReader in) {
		int numLines = 2;
		r = new Rect();
		c = new Circ();
		
		while (numLines-- > 0) {
			String s = in.next();
			

			if (s.charAt(0) == 'r') {
				r.ax = Double.parseDouble(s.substring(10, s.length() - 1));
				s = in.next();
				r.ay = Double.parseDouble(s.substring(0, s.length() - 1));

				s = in.next();
				r.bx = Double.parseDouble(s.substring(0, s.length() - 1));

				s = in.next();
				r.by = Double.parseDouble(s.substring(0, s.length() - 1));

			} else {
				c.ox = Double.parseDouble(s.substring(7, s.length() - 1));

				s = in.next();
				c.oy = Double.parseDouble(s.substring(0, s.length() - 1));

				s = in.next();
				c.r = Double.parseDouble(s.substring(0, s.length() - 1));
			}
		}
		
		
	}

	public void solve(InputReader in, PrintWriter out) {
		int t = in.nextInt();
		while(t-- > 0) {
			read(in);
			
			if(isCircInside()) {
				out.println("Circle inside rectangle");
			} else if(isRectInside()) {
				out.println("Rectangle inside circle");
			} else if(isSomeCircInside() || isSomeRectInside()){
				out.println("Rectangle and circle cross");
			} else {
				out.println("Rectangle and circle do not cross");
			}
		
		
		}
	}

	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		Task5 solver = new Task5();
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
