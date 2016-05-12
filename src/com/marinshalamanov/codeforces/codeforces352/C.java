package com.marinshalamanov.codeforces.codeforces352;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class C {
	
	final double eps = 1e-8;
	
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
	
	class Pair implements Comparable<Pair> {
		int idx;
		double value;
		public Pair(int idx, double value) {
			super();
			this.idx = idx;
			this.value = value;
		}
		@Override
		public int compareTo(Pair o) {
			if( Math.abs(value - o.value) < eps ) {
				return 0;
			} else 
				return (int) (value - o.value);
		}
	}
	
	Point a, b, t;
	Point bok[];
	
	double distToT[];
	double distToA[];
	double distToB[];
	
	public void solve(InputReader in, PrintWriter out) {
        a = new Point(in.nextLong(), in.nextLong());
        b = new Point(in.nextLong(), in.nextLong());
        t = new Point(in.nextLong(), in.nextLong());
        int n = in.nextInt();
        bok = new Point[n];
        
        for(int i = 0; i < n; i++) {
        	bok[i] = new Point(in.nextLong(), in.nextLong());
        }
		
        BestK<Pair> bestA = new BestK<>(2);
        BestK<Pair> bestB = new BestK<>(2);
        
        distToT = new double[n];
        distToA = new double[n];
        distToB = new double[n];
        
        for(int i = 0; i < bok.length; i++) {
        	distToT[i] = bok[i].dist(t);
        	distToA[i] = distToT[i] - bok[i].dist(a);
        	distToB[i] = distToT[i] - bok[i].dist(b);
        	
        	bestA.add(new Pair(i, distToA[i]));
        	bestB.add(new Pair(i, distToB[i]));
        }
        
        double totDist = 0;
        List<Integer> consumed = new ArrayList<Integer>();
        
        if (bestA.get(0).idx != bestB.get(0).idx) {
        	if(bestA.get(0).value > 0 || bestB.get(0).value > 0) { // at least one positive
	        	if(bestA.get(0).value > 0 ) {
	        		totDist += bok[bestA.get(0).idx].dist(a);
	        		totDist += distToT[bestA.get(0).idx];
	        		consumed.add(bestA.get(0).idx);
	        	}
	        	
	        	if (bestB.get(0).value > 0 ) {
	        		totDist += bok[bestB.get(0).idx].dist(b);
	        		totDist += distToT[bestB.get(0).idx];
	        		consumed.add(bestB.get(0).idx);
	        	}
        	} else { // both negative
        		if(bestA.get(0).value > bestB.get(0).value) { // get the less harmful
        			totDist += bok[bestA.get(0).idx].dist(a);
	        		totDist += distToT[bestA.get(0).idx];
	        		consumed.add(bestA.get(0).idx);
        		} else {
        			totDist += bok[bestB.get(0).idx].dist(b);
	        		totDist += distToT[bestB.get(0).idx];
	        		consumed.add(bestB.get(0).idx);
        		}
        	}
        } else {
        	if (bestA.get(0).value > 0 ) { 
        		// get the one with the better second if the second is postive
        		if (bestA.get(1).value > 0 || bestB.get(1).value > 0) {
        			if(bestA.get(1).value > bestB.get(1).value) { 
            			totDist += bok[bestA.get(1).idx].dist(a);
    	        		totDist += distToT[bestA.get(1).idx];
    	        		consumed.add(bestA.get(1).idx);
    	        		
    	        		totDist += bok[bestB.get(0).idx].dist(b);
    	        		totDist += distToT[bestB.get(0).idx];
    	        		consumed.add(bestB.get(0).idx);
            		} else {
            			totDist += bok[bestB.get(1).idx].dist(b);
    	        		totDist += distToT[bestB.get(1).idx];
    	        		consumed.add(bestB.get(1).idx);
    	        		
    	        		totDist += bok[bestA.get(0).idx].dist(a);
    	        		totDist += distToT[bestA.get(0).idx];
    	        		consumed.add(bestA.get(0).idx);
            		}
        		} else { // both seconds are negative
        			totDist += bok[bestA.get(0).idx].dist(a);
            		totDist += distToT[bestA.get(0).idx];
            		consumed.add(bestA.get(0).idx);
        		}
        	} else { // both are negative => 
        		if(bestA.get(0).value > bestB.get(0).value) { // get the less harmful
        			totDist += bok[bestA.get(0).idx].dist(a);
	        		totDist += distToT[bestA.get(0).idx];
	        		consumed.add(bestA.get(0).idx);
        		} else {
        			totDist += bok[bestB.get(0).idx].dist(b);
	        		totDist += distToT[bestB.get(0).idx];
	        		consumed.add(bestB.get(0).idx);
        		}
        	}
        }
        
        double _temp = 0;
        for(int i = 0; i < bok.length; i++) {
        	if(!consumed.contains(i)) {
        		_temp += distToT[i];
        	}
        }
        
        totDist += _temp*2;
        
        System.out.println(totDist);
		
    }
	
	public class BestK <T extends Comparable<T>> {
		
		private int k;
		private Object arr[];
		private int arrEnd;
		
		public BestK (int k) {
			this.k = k;
			arr = new Object[k];
			arrEnd = 0;
		}
		
		@SuppressWarnings("unchecked")
		public void add(T el) {
			int pos;
			if (arrEnd < k) {
				pos = arrEnd++;
			} else if(el.compareTo(((T)arr[k-1])) > 0) {
				pos = k-1;
			} else {
				return;
			}

			arr[pos] = el;
			while(pos > 0 && ((T)arr[pos]).compareTo(((T)arr[pos-1])) > 0) {
				Object t = arr[pos];
				arr[pos] = arr[pos-1];
				arr[pos-1] = t;
				pos--;
			}
		}
		
		@SuppressWarnings("unchecked")
		public T get(int i) {
			return (T) arr[i];
		}
		
		public int size() {
			return k;
		}
		
		public String toString() {
			return Arrays.toString(arr);
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
