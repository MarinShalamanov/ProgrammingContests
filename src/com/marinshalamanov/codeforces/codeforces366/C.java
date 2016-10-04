package com.marinshalamanov.codeforces.codeforces366;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class C {
	
	public void solve(InputReader in, PrintWriter out) {
        int n, q;
        n = in.nextInt();
        q = in.nextInt();
        
        List<Integer> nots = new ArrayList<>();

        int elements[] = new int[n];
        int lastDelAll[] = new int[n];
//        int totalElements = 0, totalDel = 0;
        
        int maxT = 0;
//        int lastTUnread = 0;
        int unread = 0;        
        for(int k = 0; k < q; k++) {
        	int t = in.nextInt();
        	int x = in.nextInt();
        	switch (t) {
			case 1:
				x--;
				nots.add(x);
				elements[x]++;
//				totalElements++;
				unread++;
				System.out.println(unread);
				break;
			case 2:
				x--;
				unread -= (elements[x] - lastDelAll[x]);
//				totalDel += elements[x] - lastDelAll[x];
				lastDelAll[x] = elements[x];
				System.out.println(unread);
				break;
			case 3:
				for(int i = maxT; i < x; i++) {
					int app = nots.get(i);
					elements[app]--;
					if(lastDelAll[app] == 0) {
						unread--;
					} else {
						lastDelAll[app]--;
					}
//					lastDelAll[app] = Math.max(0, lastDelAll[app]-1);
				}
				maxT = Math.max(maxT, x);
				System.out.println(unread);
				break;
			default:
				break;
			}
        	
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
