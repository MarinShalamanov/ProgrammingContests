package com.marinshalamanov.codeforces.ed15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class E2 {
	
	
    int n, k;
	
    int f[], w[], s[], m[];
    boolean isCycle[];
    boolean visited[];
    
    void findCyclesFrom(int vert) {
    	Set<Integer> before  = new HashSet<Integer>();
    	visited[vert] = true;
    	
    	while(!before.contains(f[vert])) {
    		before.add(f[vert]);
    		vert = f[vert];
    		
    		if(visited[f[vert]]) { // already visited
    			return;
    		}
    		
    		visited[vert] = true;
    	}
    	
    	if(isCycle[f[vert]]) { // already visited
			return;
		}
    	isCycle[f[vert]] = true;
    	
    	long cycleSum = w[vert], cycleMin = w[vert];
    	
    	vert = f[vert];
    	int firstInCycle = vert;
    	int cycleLength = 1;
    	

    	
    	
    	while(!isCycle[f[vert]]) {
    		isCycle[f[vert]] = true;
    		cycleSum += w[vert];
    		cycleMin = Math.min(cycleMin, w[vert]);
    		
    		vert = f[vert];
    		cycleLength++;
    	}
    	
    	
    	
    	int head  = firstInCycle;
    	if (k >= cycleLength) {
    		// TODO
    	} else  {
        	int tailOffset = k;
//        	int tailLoops = k / cycleLength;
        	
        	int tail = head;
        	while(tailOffset > 0) {
        		tail = f[tail];
        		tailOffset--;
        	}
        	
        	
        	
    	}
    }

    void findCycles() {
    	Arrays.fill(visited, false);
    	for(int i = 0; i < n; i++) {
    		if(!visited[i]) {
    			findCyclesFrom(i);
    		}
    	}
    }
    
	public void solve(InputReader in, PrintWriter out) {
        n = in.nextInt();
        k = in.nextInt();
        f = new int[n];
        w = new int[n];
        
        
        isCycle = new boolean[n];
        visited = new boolean[n];
        
        for(int i = 0; i < f.length; i++) f[i] = in.nextInt();
        for(int i = 0; i < w.length; i++) w[i] = in.nextInt();
        
        
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        E2 solver = new E2();
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
