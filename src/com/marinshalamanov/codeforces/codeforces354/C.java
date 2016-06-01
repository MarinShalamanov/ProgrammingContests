package com.marinshalamanov.codeforces.codeforces354;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class C {
	int n, k;
	String s;
	
	int numA[], numB[];
	
	boolean isPossible(int len) {
		for(int start = 0; start+len <= n; start++) {
			if(numA[start+len] - numA[start] <= k) {
				return true;
			}
			
			if(numB[start+len] - numB[start] <= k) {
				return true;
			}
		}
		
		return false;
	}
	
	public void solve(InputReader in, PrintWriter out) {
        n = in.nextInt();
        k = in.nextInt();
        
        s = in.next();
        
//        int maxBeaty[] = new int [k];
        numA = new int [n+1];
        numB = new int [n+1];
		
        numA[0] = 0;
        numB[0] = 0;
        
        for(int i = 1; i <= n; i++) {
        	char c = s.charAt(i-1);
        	numA[i] = numA[i-1] + ((c=='a')?1:0);
        	numB[i] = numB[i-1] + ((c=='b')?1:0);
        }
		
//        System.out.println(Arrays.toString(numA));
//        System.out.println(Arrays.toString(numB));
        
        int low = 0, high = n;
        int mid;
        while(low+3 < high) {
        	mid = (low+high)/2;
        	if(isPossible(mid)) {
        		low = mid;
        	} else {
        		high = mid;
        	}
        }
        
//        System.out.println(low + " " + high);
        for (int i = low; i <= high; i++) {
        	if(!isPossible(i)) {
        		System.out.println(i-1);
        		return;
        	}
        }
        
        System.out.println(high);
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
        
        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
