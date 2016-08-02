package com.marinshalamanov.codeforces.ed06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class E {
	
	int n, m;
	
	List<List<Integer>> adj;
	int p[];
	long mask[];
	int col[];
	
	int tin[], tout[];
	
	int bitsUp(long mask) {
		int count = 0;
		while(mask > 0) {
			if((mask & 1) != 0) {
				count++;
			}
			mask >>= 1;
		}
		return count;
	}
	
	
	void dfs() {
		p[0] = 0;
		Stack<Integer> st = new Stack<>();
		st.add(0);
		
		while(!st.isEmpty()) {
			int top = st.pop();
			for(int child : adj.get(top)) {
				if(child != p[top]) {
					p[child] = top;
					st.add(child);
				}
			}
		}
	}
	
	int _t = 0;
	void eulerTour(int i) {
		tin[i] = _t++;
		
		for(int child : adj.get(i)) {
			if(child != p[i]) {
				eulerTour(child);
			}
		}
		
		tout[i] = _t;
	}
	
	SegmentTreeLazy st;
	
	public void solve(InputReader in, PrintWriter out) {
		n = in.nextInt();
		m = in.nextInt();
		
		adj = new ArrayList<>();
		p = new int[n];
		mask = new long[n];
		col = new int[n];
		
		for (int i = 0; i < n; i++) adj.add(new ArrayList<Integer>());
		
		for (int i = 0; i < n; i++) {
			col[i] = in.nextInt() - 1;
			mask[i] = (1L << col[i]);
		}
		
		for(int i = 0; i < n-1; i++) {
			int u, v;
			u = in.nextInt() - 1;
			v = in.nextInt() - 1;
			
			adj.get(u).add(v);
			adj.get(v).add(u);
		}
		
		dfs();
		
		tin = new int[n];
		tout = new int[n];
		eulerTour(0);
		
		long stContent[] = new long[n];
		for(int i = 0; i < n; i++) {
			stContent[tin[i]] = (1L << col[i]); 
		}
		st = new SegmentTreeLazy(stContent);
		
		for (int k = 0; k < m; k++) {
			int t, v, c;
			t = in.nextInt();
			
			if(t == 1) {
				v = in.nextInt() - 1;
				c = in.nextInt() - 1;
				
				st.update_tree(tin[v], tout[v]-1, (1L << c));
				
			} else {
				v = in.nextInt() - 1;
				long res = st.query_tree(tin[v], tout[v]-1);
				System.out.println(bitsUp(res));
			}
		}
    }
	
	public class SegmentTreeLazy {

		int N;
		int MAX = (1+(1<<6));
//		final int inf = 0x7fffffff;

		long arr[];
		long tree[];
		long lazy[];

		public SegmentTreeLazy(long _arr[]) {
			N = _arr.length;
			
			MAX = getSegmentTreeSize(N);
			
			tree = new long[MAX];
			lazy = new long[MAX];
			arr = _arr;
			
			build_tree(1, 0, arr.length - 1);
		}
		
		int getSegmentTreeSize(int x) {
		  int size = 1;
		  for (; size < x; size <<= 1);
		  return size << 1;
		}
		
		/**
		 * Build and init tree
		 */
		void build_tree(int node, int a, int b) {
		  	if(a > b) return; // Out of range
		  	
		  	if(a == b) { // Leaf node
		    		tree[node] = arr[a]; // Init value
				return;
			}
			
			build_tree(node*2, a, (a+b)/2); // Init left child
			build_tree(node*2+1, 1+(a+b)/2, b); // Init right child
			
			tree[node] = tree[node*2] | tree[node*2+1]; // Init root value
		}

		/**
		 * Increment elements within range [i, j] with value value
		 */
		void update_tree(int i, int j, long value) {
			update_tree(1, 0, arr.length-1, i, j, value);
		}
		/**
		 * Increment elements within range [i, j] with value value
		 */
		void update_tree(int node, int a, int b, int i, int j, long value) {
		  
		  	if(lazy[node] != 0) { // This node needs to be updated
		   		tree[node] = lazy[node]; // Update it

				if(a != b) {
					lazy[node*2] = lazy[node]; // Mark child as lazy
		    			lazy[node*2+1] = lazy[node]; // Mark child as lazy
				}

		   		lazy[node] = 0; // Reset it
		  	}
		  
			if(a > b || a > j || b < i) // Current segment is not within range [i, j]
				return;
		    
		  	if(a >= i && b <= j) { // Segment is fully within range
		    	tree[node] = value;

				if(a != b) { // Not leaf node
					lazy[node*2] = value;
					lazy[node*2+1] = value;
				}

				return;
			}

			update_tree(node*2, a, (a+b)/2, i, j, value); // Updating left child
			update_tree(1+node*2, 1+(a+b)/2, b, i, j, value); // Updating right child

			tree[node] = tree[node*2] | tree[node*2+1]; // Updating root with max value
		}

		/**
		 * Query tree to get max element value within range [i, j]
		 */
		long query_tree(int i, int j) {
			return query_tree(1, 0, arr.length - 1, i, j);
		}
		
		/**
		 * Query tree to get max element value within range [i, j]
		 */
		long query_tree(int node, int a, int b, int i, int j) {
			
			if(a > b || a > j || b < i) return 0; // Out of range

			if(lazy[node] != 0) { // This node needs to be updated
				tree[node] = lazy[node]; // Update it

				if(a != b) {
					lazy[node*2] = lazy[node]; // Mark child as lazy
					lazy[node*2+1] = lazy[node]; // Mark child as lazy
				}

				lazy[node] = 0; // Reset it
			}

			if(a >= i && b <= j) // Current segment is totally within range [i, j]
				return tree[node];

			long q1 = query_tree(node*2, a, (a+b)/2, i, j); // Query left child
			long q2 = query_tree(1+node*2, 1+(a+b)/2, b, i, j); // Query right child

			long res = q1 | q2; // Return final result
			
			return res;
		}

	}
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        E solver = new E();
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
