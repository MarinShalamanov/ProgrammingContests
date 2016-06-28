package com.marinshalamanov.codeforces.codeforces357;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class C {
	
	static final int INS = 1;
	static final int REM = 2;
	static final int GET = 3;
	
	static class Operation {
		int type;
		int x;
		
		public Operation(int type, int x) {
			this.type = type;
			this.x = x;
		}
		public Operation(int type) {
			this.type = type;
		}
		
		public String toString() {
			if(type == INS) return "insert " + x;
			if(type == GET) return "getMin " + x;
			
			return "removeMin";
		}
	}
	
	public void solve(InputReader in, PrintWriter out) {
        
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int n = in.nextInt();
		String op;
		int x = -1;
		int opt = -1;
		
		Stack<Operation> inserted = new Stack<Operation>();
		List<String> log = new ArrayList<String>();
		
		
		int insertPoint = 0;
		boolean foundIP = false;
		
		int maxval = Integer.MAX_VALUE;
		
		for(int i = 0; i < n; i++) {
			op = in.next();
			
			String logEntry = op;
			if(op.startsWith("i")) opt = INS;
			if(op.startsWith("r")) opt = REM;
			if(op.startsWith("g")) opt = GET;
			
			if(opt == INS || opt == GET) {
				x = in.nextInt();
				logEntry += " " + x;
			}
			log.add(logEntry);
			
			if(opt == INS) {
				pq.add(x);
			} else if(opt == REM) {
				if(pq.isEmpty()) {
					if(!foundIP) {
						insertPoint = i;

//						System.out.println("insert point " + insertPoint);
						foundIP = true;
					}
					
					inserted.add(new Operation(INS, maxval));
					
				} else {
					pq.remove();
				}
			} else if(opt == GET) {
				Integer peek = pq.peek();
				if(peek == null) {
					if(!foundIP) {
						insertPoint = i;
						foundIP = true;
					}
					
					inserted.add(new Operation(INS, x));
					pq.add(x);
				} else if(peek != x) {
					if(!foundIP) {
						insertPoint = i;
						foundIP = true;
					}
					Stack<Operation> currInserted = new Stack<>();
					if (peek < x) {
						while(!pq.isEmpty() && pq.peek() < x) {
							currInserted.add(new Operation(REM));
							pq.remove();
						}
					} 
					
					if (pq.isEmpty() || pq.peek() > x) {
						currInserted.add(new Operation(INS, x));
						pq.add(x);
					}
					
					while(!currInserted.isEmpty()) {
						inserted.add(currInserted.pop());
					}
				}
			}
		}
		
//		PriorityQueue<Integer> pqInsert = 
		System.out.println(n + inserted.size());
		
		int lastInserted = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++) {
			if(i == insertPoint) {
				while(!inserted.isEmpty()) {
					Operation t = inserted.pop();
					if(t.type == INS) {
						if(t.x == Integer.MAX_VALUE) {
							t.x = lastInserted;
						} else {
							lastInserted = t.x;
						}
					}
					System.out.println(t.toString());
				}
			}

			System.out.println(log.get(i));
		}
		
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
