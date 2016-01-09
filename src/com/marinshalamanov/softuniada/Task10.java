package com.marinshalamanov.softuniada;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Task10 {
	
	HashMap<String, Integer> map = new HashMap<>();
	
	public void solve(InputReader in, PrintWriter out) {
		int n = 0;
	    
		in.next();
		while(true) {
			
			String s = in.next();
			if(s.equals("Connections:")) {
				break;
			}
			
			map.put(s, map.size());
			n++;
		}
		
		List<Integer>[] g = new List[n];
		for (int i = 0; i < n; i++) {
			g[i] = new ArrayList<>();
	    }
		
		while(true) {
			
			String s = in.next();
			if(s.equals("END")) {
				break;
			}
			in.next();
			String t = in.next();
			
			int si = map.get(s);
			int ti = map.get(t);
			
			g[si].add(ti);
			g[ti].add(si);
		}
		
//		
//	    g[0].add(2);
//	    g[1].add(2);
//	    g[2].add(0);
//	    g[2].add(1);
//	    g[2].add(3);
//	    g[3].add(2);
//	    
//	    g[4].add(5);
//	    g[5].add(4);
//	    g[5].add(6);
//	    g[5].add(8);
//	    g[6].add(5);
//	    g[6].add(7);
//	    g[7].add(6);
//
//	    g[8].add(5);
//	    g[8].add(9);
//	    
//	    g[9].add(8);
	    System.out.println(MaxMatchingEdmonds.maxMatching(g));
		
		
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task10 solver = new Task10();
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
    
    static class MaxMatchingEdmonds {

    	  static int lca(int[] match, int[] base, int[] p, int a, int b) {
    	    boolean[] used = new boolean[match.length];
    	    while (true) {
    	      a = base[a];
    	      used[a] = true;
    	      if (match[a] == -1) break;
    	      a = p[match[a]];
    	    }
    	    while (true) {
    	      b = base[b];
    	      if (used[b]) return b;
    	      b = p[match[b]];
    	    }
    	  }

    	  static void markPath(int[] match, int[] base, boolean[] blossom, int[] p, int v, int b, int children) {
    	    for (; base[v] != b; v = p[match[v]]) {
    	      blossom[base[v]] = blossom[base[match[v]]] = true;
    	      p[v] = children;
    	      children = match[v];
    	    }
    	  }

    	  static int findPath(List<Integer>[] graph, int[] match, int[] p, int root) {
    	    int n = graph.length;
    	    boolean[] used = new boolean[n];
    	    Arrays.fill(p, -1);
    	    int[] base = new int[n];
    	    for (int i = 0; i < n; ++i)
    	      base[i] = i;

    	    used[root] = true;
    	    int qh = 0;
    	    int qt = 0;
    	    int[] q = new int[n];
    	    q[qt++] = root;
    	    while (qh < qt) {
    	      int v = q[qh++];

    	      for (int to : graph[v]) {
    	        if (base[v] == base[to] || match[v] == to) continue;
    	        if (to == root || match[to] != -1 && p[match[to]] != -1) {
    	          int curbase = lca(match, base, p, v, to);
    	          boolean[] blossom = new boolean[n];
    	          markPath(match, base, blossom, p, v, curbase, to);
    	          markPath(match, base, blossom, p, to, curbase, v);
    	          for (int i = 0; i < n; ++i)
    	            if (blossom[base[i]]) {
    	              base[i] = curbase;
    	              if (!used[i]) {
    	                used[i] = true;
    	                q[qt++] = i;
    	              }
    	            }
    	        } else if (p[to] == -1) {
    	          p[to] = v;
    	          if (match[to] == -1)
    	            return to;
    	          to = match[to];
    	          used[to] = true;
    	          q[qt++] = to;
    	        }
    	      }
    	    }
    	    return -1;
    	  }

    	  public static int maxMatching(List<Integer>[] graph) {
    	    int n = graph.length;
    	    int[] match = new int[n];
    	    Arrays.fill(match, -1);
    	    int[] p = new int[n];
    	    for (int i = 0; i < n; ++i) {
    	      if (match[i] == -1) {
    	        int v = findPath(graph, match, p, i);
    	        while (v != -1) {
    	          int pv = p[v];
    	          int ppv = match[pv];
    	          match[v] = pv;
    	          match[pv] = v;
    	          v = ppv;
    	        }
    	      }
    	    }

    	    int matches = 0;
    	    for (int i = 0; i < n; ++i)
    	      if (match[i] != -1)
    	        ++matches;
    	    return matches / 2;
    	  }
    	}
}
