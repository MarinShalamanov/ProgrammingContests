package com.marinshalamanov.hackerrank.world_codespring9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class KingDiv {
	
	class Node {
		public List<Integer> children = new ArrayList<Integer>();
		public int parent; 
		
		public long red_par_any;
		public long blue_par_any;
		public long red_par_red;
		public long blue_par_blue;
		
	}
	
	long m = (long) (1e9 + 7);
	int root = 0;
	
	public void solve(InputReader in, PrintWriter out) {
        int n = in.nextInt();
        
        
        Node adj[] = new Node[n];
        for(int i = 0; i < n; i++) {
        	adj[i] = new Node();
        }
        
        for(int i = 1; i < n; i++) {
        	int u = in.nextInt();
        	int v = in.nextInt();
        	u--; v--;
        	adj[u].children.add(v);
        	adj[v].children.add(u);
        }
		
        if(n==1) {
        	System.out.println(2);
        	return;
        }
        
        if(n==2) {
        	System.out.println(2);
        	return;
        }
        
        int q[] = new int[2*n];
        int qEnd = 0;
        
        q[qEnd++] = root;
        for(int i = 0; i < qEnd; i++) {
        	int curr = q[i];
        	
        	for(int child : adj[curr].children) {
        		if(curr == root || child != adj[curr].parent) {
        			adj[child].parent = curr;
        			q[qEnd++] = child;
        		}
        	}
        }
        
        // go back
        for(int i = qEnd-1; i >= 0; i--) {
        	int curr = q[i];
        	
        	Node cur = adj[curr];
        	
        	if(root != curr && cur.children.size()==1) { // leaf
        		cur.red_par_red = 1;
        		cur.blue_par_blue = 1;
        		cur.red_par_any = 0;
        		cur.blue_par_any = 0;
        	} else {
        		if(root != curr) {
        			cur.red_par_red = 1;
        			for(int child : cur.children) {
        				if(child != cur.parent) {
        					cur.red_par_red = (cur.red_par_red*adj[child].blue_par_any)%m;
        				}
        			}
        			
//        			System.out.println("node " + curr + " red par red = " +cur.blue_par_blue);
        			
        			
        			
        			cur.blue_par_blue = 1;
        			for(int child : cur.children) {
        				if(child != cur.parent) {
        					cur.blue_par_blue = (cur.blue_par_blue *adj[child].red_par_any)%m;
        				}
        			}
        			

//        			System.out.println("node " + curr + " blue par blue = "+ cur.blue_par_blue);
        			
        			
        			cur.blue_par_any = 1;
        			// at least one child is blue
        			// cases for which is the first blue child
        			long except = 1;
        			
        			for(int child : cur.children) {
        				if(child != cur.parent) {
        					// this child is the first blue 
        					cur.blue_par_any = (cur.blue_par_any*(adj[child].blue_par_any + adj[child].blue_par_blue + adj[child].red_par_any))%m;
        					except = (except*adj[child].red_par_any)%m;
        				}
        			}
        			cur.blue_par_any = (m+cur.blue_par_any-except)%m;
        			
//        			System.out.println("node " + curr + " blue par any = "+ cur.blue_par_any);
        			
        			
        			
        			cur.red_par_any = 1;
        			// at least one child is blue
        			// cases for which is the first blue child
        			except = 1;
        			
        			for(int child : cur.children) {
        				if(child != cur.parent) {
        					// this child is the first blue 
        					cur.red_par_any = (cur.red_par_any*(adj[child].blue_par_any + adj[child].red_par_red + adj[child].red_par_any))%m;
        					except = (except *adj[child].blue_par_any)%m;
        				}
        			}
        			cur.red_par_any = (m+cur.red_par_any-except)%m;
        			
//        			System.out.println("node " + curr + " red par any = "+ cur.red_par_any);
        			
        			
        			
        			
        			
        		} else { 
        			// curr is root
        			
//        			cur.red_par_red = 1;
//        			for(int child : cur.children) {
//        				if(child != cur.parent) {
//        					cur.red_par_red *= adj[child].blue_par_any;
//        				}
//        			}
        			
        			
//        			
//        			cur.blue_par_blue = 1;
//        			for(int child : cur.children) {
//        				if(child != cur.parent) {
//        					cur.blue_par_blue *= adj[child].red_par_any;
//        				}
//        			}
        			
        			
        			cur.blue_par_any = 1;
        			// at least one child is blue
        			long except = 1;
        			
        			for(int child : cur.children) {
    					cur.blue_par_any = (cur.blue_par_any *(adj[child].blue_par_any + adj[child].blue_par_blue + adj[child].red_par_any))%m;
    					except = (except *adj[child].red_par_any)%m;
    				
        			}
        			cur.blue_par_any = (m+cur.blue_par_any-except)%m;
        			
        			
        			cur.red_par_any = 1;
        			// at least one child is blue
        			except = 1;
        			
        			for(int child : cur.children) {
    					cur.red_par_any = (cur.red_par_any *(adj[child].blue_par_any + adj[child].red_par_red + adj[child].red_par_any))%m;
    					except *= (adj[child].blue_par_any);
        			}
        			cur.red_par_any = (m+cur.red_par_any -except)%m;
        			
//        			System.out.println("root blue " + cur.blue_par_any);
//        			System.out.println("root red " + cur.red_par_any);
        			System.out.println((cur.blue_par_any + cur.red_par_any)%m);
        			return;
        		}
        	}
        }
		
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        KingDiv solver = new KingDiv();
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
