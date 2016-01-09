package com.marinshalamanov.softuniada;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Task8 {
	
	abstract class Fig {
		public String name;
		
		public Fig(String _N) {
			name = _N;
		}
		
		abstract boolean isInside(Fig f);
	}
	
	class Rect extends Fig {
		public long ax, ay, bx, by;

		
		public Rect(String _N, long ax, long ay, long bx, long by) {
			super(_N);
			this.ax = ax;
			this.ay = ay;
			this.bx = bx;
			this.by = by;
		}

		@Override
		public String toString() {
			return "Rect [ax=" + ax + ", ay=" + ay + ", bx=" + bx + ", by=" + by + "]";
		}
		
		boolean isInside(double x, double y) {
			return ax <= x && x <= bx && by <= y && y <= ay;
		}
		
		@Override
		boolean isInside(Fig f) {
			if(f instanceof Circ) {
				Rect r = this;
				Circ c = (Circ) f;
				return c.isInside(r.ax, r.ay) && c.isInside(r.ax, r.by) && c.isInside(r.bx, r.ay) && c.isInside(r.bx, r.by);
			} else {
				Rect r = this;
				Rect c = (Rect) f;
				return c.isInside(r.ax, r.ay) && c.isInside(r.ax, r.by) && c.isInside(r.bx, r.ay) && c.isInside(r.bx, r.by);
			}
		}

	}

	class Circ extends Fig {
		
		public Circ(String _N, long ox, long oy, long r) {
			super(_N);
			this.ox = ox;
			this.oy = oy;
			this.r = r;
		}

		public long ox, oy, r;

		@Override
		public String toString() {
			return "Circ [ox=" + ox + ", oy=" + oy + ", r=" + r + "]";
		}
		
		boolean isInside(double x, double y) {
			return (x-ox)*(x-ox) + (y - oy)*(y-oy) <= r*r;
		}
	
		
		@Override
		boolean isInside(Fig f) {
			if(f instanceof Circ) {
				Circ c = (Circ) f;
				long distance2 = (c.ox-ox)*(c.ox-ox) + (c.oy - oy)*(c.oy-oy);
				
				return (distance2 <= Math.abs(r - c.r)*Math.abs(r - c.r));
			} else {
				Rect r = (Rect) f;
				Circ c = this;
				return r.isInside(c.ox, c.oy) && r.isInside(c.ox, c.oy+c.r) && r.isInside(c.ox, c.oy-c.r) &&
						r.isInside(c.ox + c.r, c.oy) && r.isInside(c.ox - c.r, c.oy);
			}
		}
	}


	Fig read(InputReader in) {
		String s = in.next();
		if(s.charAt(0)=='r') {
			String name = in.next();
			int ax = in.nextInt();
			int ay = in.nextInt();
			int bx = in.nextInt();
			int by = in.nextInt();
			return new Rect(name, ax, ay, bx, by);
			
		} else if(s.charAt(0)=='s') {
			String name = in.next();
			int ax = in.nextInt();
			int ay = in.nextInt();
			int ss = in.nextInt();
			return new Rect(name, ax, ay, ax+ss, ay +ss);
		} else if(s.charAt(0) == 'c') {
			String name = in.next();
			int ax = in.nextInt();
			int ay = in.nextInt();
			int ss = in.nextInt();
			return new Circ(name, ax, ay, ss);
		} else {
			return null;
		}
	}
	
	List<Fig> l = new ArrayList<>();
	int n;
	int a[][];
	
	public void solve(InputReader in, PrintWriter out) {
        while(true) {
        	Fig f = read(in);
        	if(f == null) break;
        	
        	l.add(f);
        }
		
        n = l.size();
        a = new int[n][n];
        for(int i = 0; i < n; i++) {
        	for(int j = i+1; j < n; j++) {
        		if(l.get(i).isInside(l.get(j))) {
        			a[i][j] = 1;
        		}
        	}
        }

        
        boolean changee = true;
        while(changee) {
        	changee = false;
        for (int k = 0; k < n; k++)
		 	for(int i = 0; i < n; i++)
		 		for(int j = 0; j<n; j++)
		 			if(a[i][k]==1 && a[k][j]==1 && a[i][j]==1) {
		 				a[i][j] = 0;
		 				changee = true;
		 			}
        }
        

//        System.out.println("here2");
        TreeSet<String> paths = new TreeSet<>();
        int maxsize = 0;
        for(int i = 0; i < n; i++) {
        	int size = 1;
        	String s = "";
        	s += l.get(i).name;
        	int curri = i;
        	while(true) {
        		boolean change = false;
        		for(int j = 0; j < n; j++) {
        			if(a[curri][j]==1) {
        				change = true;
        				s += " < ";
        				s += l.get(j).name;
        				curri = j;
        				size++;
        				break;
        			}
        		}
        		
        		if(!change) {
        			break;
        		}
        	}
        	
        	if(size > maxsize) {
        		maxsize = size;
        		paths.clear();
        		paths.add(s);
        	}
        	if(size == maxsize) {
        		paths.add(s);
        	}
        }
//        System.out.println("he2re");
        for(String path : paths) {
        	System.out.println(path);
        	break;
        }
        
		
		
		
		
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task8 solver = new Task8();
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
