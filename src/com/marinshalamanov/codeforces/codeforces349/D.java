package com.marinshalamanov.codeforces.codeforces349;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.management.Query;

public class D {
	
	int n, m;
	
	List < List <Integer> > vert;
	
	int dist[][];
	int dist2[][];
	int pathPoint2[][], pathPoint4[][];
	
	
	
	int getDist(int u, int v) {
		if(u != v && dist[u][v] == 0) return Integer.MAX_VALUE;
		else return dist[u][v];
	}
	
	public void solve(InputReader in, PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();
        
        dist = new int[n][n];
        dist2 = new int[n][n];
        vert = new ArrayList<>(n);
        for(int i = 0; i < n; i++) {
        	vert.add(new ArrayList<Integer>());
        }
        
        int u, v;
        for(int i = 0; i < m; i++) {
        	u = in.nextInt();
        	v = in.nextInt();
        	
        	u--; v--;
        	
        	if(u==v) continue;
        	
        	vert.get(u).add(v);
        }
        // 3 min
        
        int longestInDist[] = new int[n];
        int longestOutDist[] = new int[n];
        int longestInFrom[] = new int[n];
        int longestOutFrom[] = new int[n];
        
        // find all distances
        for (int startV = 0; startV < n; startV++) {
        	int queue[] = new int[n];
        	boolean visited[] = new boolean[n];
        	int queueEnd = 0;
        	
        	queue[queueEnd++] = startV;
        	
        	for(int i = 0; i < queueEnd; i++) {
        		visited[queue[i]] = true;
        		List<Integer> children = vert.get(queue[i]);
        		
        		for (int child : children) {
        			if (!visited[child]) {
        				queue[queueEnd++] = child;
        				dist[startV][child] = dist[startV][queue[i]] + 1;
        				
        				if(longestInDist[child] < dist[startV][child]) {
        					longestInDist[child] = dist[startV][child];
        					longestInFrom[child] = startV;
        				}
        				
        				if(longestOutDist[startV] < dist[startV][child]) {
        					longestOutDist[startV] = dist[startV][child];
        					longestOutFrom[startV] = child;
        				}
        				
        				visited[child] = true;
        			}
        		}
        	}
        }
        
        // 10 min

        pathPoint2 = new int[n][n];
        pathPoint4 = new int[n][n];
        
        int longestOut2[] = new int[n];
        int longestOut2To1[] = new int[n];
        int longestOut2To2[] = new int[n];
        
        for(int ver = 0; ver < n; ver++) {
        	for(int ver2 = 0; ver2 < n; ver2++) {
        		int cur = dist[ver][ver2] + longestOutDist[ver2];
        		if(cur > longestOut2[ver]) {
        			longestOut2[ver] = cur;
        			longestOut2To1[ver] = ver2;
        			longestOut2To2[ver] = longestOutFrom[ver2];
        		}
        	}
        }
        
        
        int longestOut3[] = new int[n];
        int longestOut3To1[] = new int[n];
        int longestOut3To2[] = new int[n];
        int longestOut3To3[] = new int[n];
        
        for(int ver = 0; ver < n; ver++) {
        	for(int ver2 = 0; ver2 < n; ver2++) {
        		int cur = dist[ver][ver2] + longestOut2[ver2];
        		if(cur > longestOut3[ver]) {
        			longestOut3[ver] = cur;
        			longestOut3To1[ver] = ver2;
        			longestOut3To2[ver] = longestOut2To1[ver2];
        			longestOut3To3[ver] = longestOut2To2[ver2];
        		}
        	}
        }
        
        int longestDist = -1, longestIdx = -1;
        for (int ver = 0; ver < n; ver++) {
        	if(longestOut3[ver] > longestDist) {
        		longestDist = longestOut3[ver];
        		longestIdx = ver;
        	}
        }
        
        System.out.println(longestIdx + " " + longestOut3To1[longestIdx] + " " + longestOut3To2[longestIdx] + " " + longestOut3To3[longestIdx]);
        
        
        // 22 min
        
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
        
        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
