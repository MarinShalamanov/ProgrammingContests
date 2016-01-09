package com.marinshalamanov.softuniada;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task3 {

	Date parse(String s1) {
		Date d = new Date();
		if (s1.contains("::")) {
			int iff = s1.indexOf(':');
			int is = s1.indexOf(':', iff + 2);
			d.d = Integer.parseInt(s1.substring(0, iff));
			d.h = Integer.parseInt(s1.substring(iff + 2, is));
			d.m = Integer.parseInt(s1.substring(is + 1));
		} else {
			d.d = 0;
			int iff = s1.indexOf(':');
			d.h = Integer.parseInt(s1.substring(0, iff));
			d.m = Integer.parseInt(s1.substring(iff + 1));
		}

		return d;
	}

	public void solve(InputReader in, PrintWriter out) {
		Date d1 = parse(in.next());
		Date d2 = parse(in.next());
		
		d1.sum(d2);
		
		System.out.println(d1.toString());
//		System.out.println(d + " " + h + " " + m);
		
	}

	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		Task3 solver = new Task3();
		solver.solve(in, out);
		out.close();
	}

	static class Date {
		public int d, h, m;
		
		void sum(Date d) {
			this.m += d.m;
			if(m >= 60) {
				m -= 60;
				h++;
			}
			this.h += d.h;
			if(h >= 24) {
				h -= 24;
				this.d++;
			}
			this.d += d.d;
		}
		
		public String toString() {
			String s = "";
			if(d != 0) {
				s += d + "::";
			} 
			
			s += h + ":";
			if(m < 10) {
				s+= "0";
			}
			s += m;
			
			return s;
		}
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
