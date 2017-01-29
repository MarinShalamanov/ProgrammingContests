package com.marinshalamanov.hackerrank.world_codespring9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class QueensAtt {
	
	public void solve(InputReader in, PrintWriter out) {
        int n, k;
        n = in.nextInt();
        k = in.nextInt();
        
        int rq, cq;
        rq = in.nextInt();
        cq = in.nextInt();
        
        HashSet<Long> obs = new HashSet<>();
        
        for(int i = 0; i < k; i++) {
        	int ro, co;
        	ro = in.nextInt();
        	co = in.nextInt();
        	
        	obs.add((long)ro*(long)n + (long)co);
        }
        
        int count = 0;
        
        // check up
        for(int i = rq+1; i <= n; i++) {
        	long code = (long) i * n + (long)cq;
        	if(obs.contains(code)) {
        		break;
        	} else {
        		count++;
        	}
        }
        
        // check down
        for(int i = rq-1; i >= 1; i--) {
        	long code = (long) i * n + (long)cq;
        	if(obs.contains(code)) {
        		break;
        	} else {
        		count++;
        	}
        }
        
        
        // check right
        for(int i = cq+1; i <= n; i++) {
        	long code = (long) rq * n + (long)i;
        	if(obs.contains(code)) {
        		break;
        	} else {
        		count++;
        	}
        }
        
        // check left
        for(int i = cq-1; i >= 1; i--) {
        	long code = (long) rq * n + (long)i;
        	if(obs.contains(code)) {
        		break;
        	} else {
        		count++;
        	}
        }
        
        // check diag ^ >
        for(int i = rq+1, j = cq+1; i <= n && j <= n; i++, j++) {
        	long code = (long) i * n + (long)j;
        	if(obs.contains(code)) {
        		break;
        	} else {
        		count++;
        	}
        }
		
        // check diag ^ <
        for(int i = rq+1, j = cq-1; i <= n && j >= 1; i++, j--) {
        	long code = (long) i * n + (long)j;
        	if(obs.contains(code)) {
        		break;
        	} else {
        		count++;
        	}
        }
        
        
        // check diag v <
        for(int i = rq-1, j = cq-1; i >= 1 && j >= 1; i--, j--) {
        	long code = (long) i * n + (long)j;
        	if(obs.contains(code)) {
        		break;
        	} else {
        		count++;
        	}
        }
        
        // check diag v >
        for(int i = rq-1, j = cq+1; i >= 1 && j <= n; i--, j++) {
        	long code = (long) i * n + (long)j;
        	if(obs.contains(code)) {
        		break;
        	} else {
        		count++;
        	}
        }
		
        System.out.println(count);
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        QueensAtt solver = new QueensAtt();
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
