package com.marinshalamanov.hackerrank.algorithms.searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class MaximiseSum {
	
	int n, m;
	int arr[];
	int prefSum[];
	List<Integer> prefSumsOrdered;	
	
	int solveFast() {
		prefSum = new int[n+1];
    	prefSumsOrdered = new ArrayList<Integer>();	
    	
    	prefSum[0] = 0;
    	prefSumsOrdered.add(prefSum[0]);
    	
    	int maxSum = -1;
    	
    	for (int i = 0; i < n; i++) {
    		prefSum[i+1] = (prefSum[i] + arr[i]) % m;
    		//prefSums.add();
    		
    		
    		int currentPrefSum = prefSum[i+1];
			int index = Collections.binarySearch(prefSumsOrdered, currentPrefSum);
    		
    		if (index < 0) {
    			index = -(index+1);
    		}
    		
    		int ii = index;	
    		
    		while(ii < prefSumsOrdered.size() && prefSumsOrdered.get(ii) == currentPrefSum) {
    			ii++;
    		}
    		
    		if (ii < prefSumsOrdered.size()) {
    			maxSum = Math.max(maxSum, (currentPrefSum - prefSumsOrdered.get(ii) + m) % m );
    		} 
    		
    		maxSum = Math.max(maxSum, currentPrefSum);
    			
    		prefSumsOrdered.add(index, currentPrefSum);
//    		System.out.println(prefSumsOrdered.toString());
//    		System.out.println(currentPrefSum + " " + maxSum);
    	}
    	
    	return maxSum;
    	
	}
	
	int solveSlow() {
		int curr; 
		int max = 0;
		for(int i = 0; i < n; i++) {
			for(int j = i+1; j < n; j++) {
				curr = (prefSum[j+1] - prefSum[i] + m)%m;
			
				max = Math.max(max, curr);
			}
		}
		
		return max;
	}
	
	public void solve(InputReader in, PrintWriter out) {
        int t = in.nextInt();
        
        while (t-- > 0) {
        	n = in.nextInt();
        	m = in.nextInt();
        	
        	arr = new int[n];
        	
        	for(int i = 0; i < n; i++) {
        		arr[i] = (int) (in.nextLong() % m);
        	}
        	
//        	System.out.println(solveFast() + " " + solveSlow());
        	
        	solveFast();
        	System.out.println(solveSlow());
        	
        }
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        MaximiseSum solver = new MaximiseSum();
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

/*
3
5 7
3 3 9 9 5
5 7
3 2 9 8 4
3 2
3 2 1
 */
