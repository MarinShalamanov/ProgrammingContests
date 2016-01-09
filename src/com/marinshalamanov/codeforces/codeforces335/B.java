package com.marinshalamanov.codeforces.codeforces335;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class B {
	
	public void solve(InputReader in, PrintWriter out) {
        int x, y;
        int x0, y0;
        
        x = in.nextInt();
        y = in.nextInt();
        x0 = in.nextInt();
        y0 = in.nextInt();
        
        String s = in.next();
        
        
        boolean a[][] = new boolean[x+1][y+1];
        int k[] = new int[s.length()+1];
        
        int tot = 0;
        
        for(int i = 0; i < s.length(); i++) {
        	if(!a[x0][y0]) {
        		a[x0][y0] = true;
        		k[i]++;
        		tot++;
        	}
        	switch (s.charAt(i)) {
			case 'U': if(x0>1) x0--; break;
			case 'D': if(x0<x) x0++; break;
			case 'R': if(y0<y) y0++; break;
			case 'L': if(y0>1) y0--; break;
			default: break;
			}
        	
//        	System.out.println(x0 + " " + y0);
        }
        
        
       for(int i = 0; i < k.length-1; i++) {
    	   System.out.print(k[i] + " ");
       }
       System.out.println(x*y - tot);
        
        
		
        
				
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

        public int nextInt() {
            return Integer.parseInt(next());
        }
        
        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
