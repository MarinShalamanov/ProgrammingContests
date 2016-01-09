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
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Task9 {
	
	HashMap<String, Integer> map = new HashMap<>();
	int n;
	List<Road>[] g;
	
	int getId(String s) {
		if(!map.containsKey(s)) {
			map.put(s, map.size());
		} 
		
		return map.get(s);
	}
	
	class Road {
		public int to;
		public double time;
		public Road(int to, double time) {
			super();
			this.to = to;
			this.time = time;
		}
		
		
	}
	
	class Record {
		public int place; 
		public long time;
		public Record(int place, long time) {
			super();
			this.place = place;
			this.time = time;
		}
		
	}
	
	Map<String, List<Record> > records = new TreeMap<>();
	
	double dist[][];
	
	Set<String> globi = new TreeSet<>();
	
	public void solve(InputReader in, PrintWriter out) {
		read(in);
		
		n = map.size();
		dist = new double[n][n];
		
		for(int i = 0; i < n; i ++) {
			for(Road r : g[i]) {
				dist[i][r.to] =  r.time;
			}
		}
		
		for(int i = 0; i < n; i++)
	 		for(int j = 0; j<n; j++)
	 		{
	 			if(dist[i][j] == 0) dist[i][j] = 1e200;
	 		}
	
		
		 for (int k = 0; k < n; k++)
		 	for(int i = 0; i < n; i++)
		 		for(int j = 0; j<n; j++)
		 		{
		 			if (dist[i][k] + dist[k][j]< dist[i][j])
		 			{
		 				dist[i][j] = dist[i][k] + dist[k][j];
//		 				P[i][j] = k	
		 			}
		 		}
		 
		 
//		 printDist();
		 
		 
		 for(Map.Entry<String, List<Record>> entry : records.entrySet()) {
			 List<Record> rec = entry.getValue();
			 
			 for(int i = 0; i < rec.size(); i++) {
				 for(int j = i+1; j < rec.size(); j++) {
					 Record e1 = rec.get(i);
					 Record e2 = rec.get(j);
					 
					 if(dist[e1.place][e2.place] > 1e100) {
//						 System.out.println("here");
						 continue;
					 }
					 
					 if (e2.time < e1.time) {
						 Record tmp = e1;
						 e1 = e2;
						 e2 = tmp;
					 }
					 
					 long distTimeSec = Math.abs(e1.time - e2.time);
					 if (1e-3 + dist[e1.place][e2.place]*3600 > distTimeSec) {
						 globi.add(entry.getKey());
					 }
				 }
			 }
		 }
		 
		 
		
		 for(String regnum : globi) {
			 out.println(regnum);
		 }
    }

	private void printDist() {
		for(int i = 0; i < n; i++) {
			 for(int j = 0; j < n; j++) {
				 System.out.print(dist[i][j] + " ");
			 }
			 System.out.println();
		 }
		 System.out.println();
	}

	private void read(InputReader in) {
		n = 3000;
		g = new List[n];
		for (int i = 0; i < n; i++) {
			g[i] = new ArrayList<>();
	    }
		
		in.next();
		
		while(true) {
			
			String s = in.next();
			if(s.equals("Records:")) {
				break;
			}
			String t = in.next();
			
			double time = in.nextDouble() / in.nextDouble();
			//System.out.println(time);
			int si = getId(s);
			int ti = getId(t);
			
			g[si].add(new Road(ti, time));
			g[ti].add(new Road(si, time));
		}
		
		
		while(true) {
			
			String city = in.next();
			if(city.equals("End")) {
				break;
			}
			String regnum = in.next();
			String timeS = in.next();
			long time = Integer.parseInt(timeS.substring(0, 2))* 60 * 60 +
					Integer.parseInt(timeS.substring(3, 5))* 60 + 
					Integer.parseInt(timeS.substring(6, 8));
			
			Record r = new Record(getId(city), time);
			
			if(records.containsKey(regnum)) {
				List<Record> rec = records.get(regnum);
				rec.add(r);
				records.put(regnum, rec);
			} else {
				List<Record> rec = new ArrayList<>();
				rec.add(r);
				records.put(regnum, rec);
			}
		}
	}
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task9 solver = new Task9();
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
