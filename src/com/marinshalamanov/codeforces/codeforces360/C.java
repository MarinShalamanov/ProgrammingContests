package com.marinshalamanov.codeforces.codeforces360;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class C {
	
	public void solve(InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        
        List<Set<Integer> > a = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
        	a.add(new HashSet<>());
        }
        
        for(int i = 0; i < m; i++) {
        	int u, v;
        	u = in.nextInt();
        	v = in.nextInt();
        	u--; v--;
        	
        	a.get(u).add(v);
        	a.get(v).add(u);
        }
        
        int visited[] = new int[n];
        
        List<Integer> g1 = new ArrayList<>();
        List<Integer> g2 = new ArrayList<>();
        
        
        for(int i = 0; i < n; i++) {
        	if(visited[i] == 0) {
        		visited[i] = 1;
        		g1.add(i);
        		
        		Queue<Integer> q = new LinkedList<>();
        		q.add(i);
        		
        		while(!q.isEmpty()) {
        			int c = q.poll();
        			for(int x : a.get(c)) {
            			if(visited[x] == visited[c]) {
            				System.out.println(-1);
            				return;
            			} else {
            				if(visited[x] == 0) {
                				q.add(x);
                				if((-1)*visited[c] > 0) {
                					g1.add(x);
                				} else {
                					g2.add(x);
                				}
            				}
            				
            				visited[x] = (-1)*visited[c];
            				
            			}
            		}
        		}
        	}
        }
        
        System.out.println(g1.size());
        for(int x : g1) System.out.print((x+1) + " ");
        System.out.println();

        System.out.println(g2.size());
        for(int x : g2) System.out.print((x+1) + " ");
        System.out.println();
		
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
