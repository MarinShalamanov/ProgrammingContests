package com.marinshalamanov.softuniada;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Task4 {
	
	char a[][][];
	int n;
	
	boolean isStar(int x, int y, int z) {
		if(x!=0 && x != n-1 && y!=0 && y!=n-1 && z!=0 && z!=n-1) {
			char c = a[x][y][z];
			return c == a[x-1][y][z] &&
					c == a[x+1][y][z] &&
					c == a[x][y+1][z] &&
					c == a[x][y-1][z] &&
					c == a[x][y][z+1] &&
					c == a[x][y][z-1];
		} else {
			return false;
		}
	}
	
	public void solve(InputReader in, PrintWriter out) {
        n = in.nextInt();
		a = new char[n][n][n];
		for(int i = 0; i < n; i++) {
			int size = n*n + (n-1);
			
			for(int j = 0 ; j < n; j++) {
				if (j!=0) in.next();
				
				for(int k = 0; k < n; k++) {
					a[j][i][k] = in.next().charAt(0);
//					System.out.println(j + " " + i + " " + k + " " + a[j][i][k]);
				}
				
				
			}
		}
		
		Map<Character, Integer> map = new TreeMap<>();
		int total = 0;
		for(int i = 0; i < n; i++){
			for(int j = 0 ; j < n; j++) {
				for(int k = 0; k < n; k++) {
					if(isStar(i, j, k)) {
						total++;
						char c = a[i][j][k];
						if(!map.containsKey(c)) {
							map.put(c, 1);
						} else {
							map.put(c, map.get(c)+1);
						}
					}
				}
			}
		}
		
		out.println(total);
		for(char c = 'a'; c <= 'z'; c++) {
			if(map.containsKey(c)) {
				out.println(c + " -> " + map.get(c));
			}
		}
		
		
//		for(int i = 0; i < n; i++){
//			for(int j = 0 ; j < n; j++) {
//				for(int k = 0; k < n; k++) {
//					out.print(a[i][j][k] + " ");
//				}
//				out.println();
//			}
//			out.println();
//			out.println();
//		}
		
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task4 solver = new Task4();
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
