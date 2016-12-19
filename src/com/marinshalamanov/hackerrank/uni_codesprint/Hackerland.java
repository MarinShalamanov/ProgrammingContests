package com.marinshalamanov.hackerrank.uni_codesprint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Hackerland {
	
	public void solve(InputReader in, PrintWriter out) {
		int n, k;
		n = in.nextInt();
		k = in.nextInt();
		
		int arr[] = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}
		
		Arrays.sort(arr);

		int firstUncovered = 0;
		
		int i;
		int count = 0;
		
		while(true) {
			for (i = firstUncovered; i < arr.length && arr[i] - arr[firstUncovered] <= k; i++);
			if (i < arr.length) {
				int antena = i-1;
//				System.out.println("antena " + antena);

				count++;
				
				for (firstUncovered = antena+1; firstUncovered < arr.length && arr[firstUncovered] - arr[antena] <= k ; firstUncovered++);
				if(firstUncovered >= arr.length) break;
//				System.out.println("first uncov " + firstUncovered);
			} else {
				count++; // put the last antena
				break;
			}
		}
		
		System.out.println(count);
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Hackerland solver = new Hackerland();
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
