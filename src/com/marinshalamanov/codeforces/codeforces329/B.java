package com.marinshalamanov.codeforces.codeforces329;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class B {
	
	long n;
	long s1, s2;
	
	long lines[][];
	
	
	
	List<Intersection> is1, is2;
	
	void read(InputReader in) {
		n = in.nextInt();
		s1 = in.nextInt();
		s2 = in.nextInt();
		
		lines = new long[(int)n][2];
		for(int i = 0; i < n; i++) {
			lines[i][0] = in.nextInt();
			lines[i][1] = in.nextInt();
		}
	}
	
	public void solve(InputReader in, PrintWriter out) {
        read(in);
        
        is1 = new ArrayList<Intersection>();
        is2 = new ArrayList<Intersection>();
        
        for(int i = 0; i < n; i++) {
        	Intersection _is1 = new Intersection();
        	Intersection  _is2 = new Intersection();
        	
        	_is1.y = lines[i][0] * s1 + lines[i][1];
        	_is1.idx = i;
        	
        	
        	_is2.y = lines[i][0] * s2 + lines[i][1];
        	_is2.idx = i;
        	
        	_is1.otherY = _is2.y;
        	_is2.otherY = _is1.y;
        	
        	is1.add(_is1);
        	is2.add(_is2);
    
		}
        
        is1.sort(new Cmp());
        is2.sort(new Cmp());
        
        //out.println(is1);
        //out.println(is2);
        
        for(int i = 0; i < n; i++) {
        	if(is1.get(i).idx != is2.get(i).idx) {
        		out.println("YES");
        		return;
        	}
        }
        
        
        
        out.println("NO");
		return;
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        B solver = new B();
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

        public long nextInt() {
            return Long.parseLong(next());
        }
        
        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
    
    static class Intersection {
		long y;
		long idx;
		long otherY;
		
		public String toString() {
			return y + " " + idx;
		}
	}
	
	static class Cmp implements Comparator<Intersection> {

		@Override
		public int compare(Intersection o1, Intersection o2) {
			if(o1.y == o2.y) {
				if(o1.otherY < o2.otherY) return -1;
				else if (o1.otherY == o2.otherY) return 0;
				else return 1;
			} 
			
			if(o1.y < o2.y) return -1;
			else if(o1.y == o2.y) return 0;
			else return 1;
			//return (int) (o1.y - o2.y);
		}
    	
    }
}
