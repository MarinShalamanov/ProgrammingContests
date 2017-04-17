package com.marinshalamanov.codeforces.codeforces409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class D {
	
	public void solve(InputReader in, PrintWriter out) {
        int n = in.nextInt();
        Pt pts[] = new Pt[n];
        for(int i = 0; i < n; i++) {
        	pts[i] = new Pt(in.nextInt(), in.nextInt());
        }
        
        double d = 1e15;
        
        for(int i = 0; i < n; i++) {
        	Pt a = pts[i];
        	Pt b = pts[(i+1)%n];
        	Pt c = pts[(i+2)%n];
        	
        	double currd = LineToPointDistance2D(a.getArr(), c.getArr(), b.getArr(), false) / 2.0;
        	d = Math.min(d, currd);
        	
        }
        System.out.println(d);
        
    }
	
	//Compute the dot product AB . AC
	private double DotProduct(double[] pointA, double[] pointB, double[] pointC)
	{
	    double[] AB = new double[2];
	    double[] BC = new double[2];
	    AB[0] = pointB[0] - pointA[0];
	    AB[1] = pointB[1] - pointA[1];
	    BC[0] = pointC[0] - pointB[0];
	    BC[1] = pointC[1] - pointB[1];
	    double dot = AB[0] * BC[0] + AB[1] * BC[1];

	    return dot;
	}

	//Compute the cross product AB x AC
	private double CrossProduct(double[] pointA, double[] pointB, double[] pointC)
	{
	    double[] AB = new double[2];
	    double[] AC = new double[2];
	    AB[0] = pointB[0] - pointA[0];
	    AB[1] = pointB[1] - pointA[1];
	    AC[0] = pointC[0] - pointA[0];
	    AC[1] = pointC[1] - pointA[1];
	    double cross = AB[0] * AC[1] - AB[1] * AC[0];

	    return cross;
	}

	//Compute the distance from A to B
	double Distance(double[] pointA, double[] pointB)
	{
	    double d1 = pointA[0] - pointB[0];
	    double d2 = pointA[1] - pointB[1];

	    return Math.sqrt(d1 * d1 + d2 * d2);
	}

	//Compute the distance from AB to C
	//if isSegment is true, AB is a segment, not a line.
	double LineToPointDistance2D(double[] pointA, double[] pointB, double[] pointC, 
	    boolean isSegment)
	{
	    double dist = CrossProduct(pointA, pointB, pointC) / Distance(pointA, pointB);
	    if (isSegment)
	    {
	        double dot1 = DotProduct(pointA, pointB, pointC);
	        if (dot1 > 0) 
	            return Distance(pointB, pointC);

	        double dot2 = DotProduct(pointB, pointA, pointC);
	        if (dot2 > 0) 
	            return Distance(pointA, pointC);
	    }
	    return Math.abs(dist);
	} 
	
	class Pt {
		int x; int y;
		
		double[] getArr() {
			double res[] = new double[] {x, y};
			return res;
		}
		
		public Pt(int x, int y) {
			super();
			this.x = x;
			this.y = y;
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
