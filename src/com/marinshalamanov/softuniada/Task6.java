package com.marinshalamanov.softuniada;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Task6 {
	
	/*
	 * 
	1
	1 3 4 5 3 2
	
	 */
	
	public void solve(InputReader in, PrintWriter out) {
        Scanner input = new Scanner(System.in);
		int t = input.nextInt();
		input.nextLine();
		
		while(t-- > 0) {
			String s = input.nextLine();
			StringTokenizer st = new StringTokenizer(s);
			ArrayList<Integer> arr = new ArrayList<>();
			
			while(st.hasMoreTokens()) {
				arr.add(Integer.parseInt(st.nextToken()));
			}

			
			int sum = 0; 
			for(int i : arr) sum += i;
			
			if(sum % 3 != 0) {
				out.println("No");
				continue;
			}
			
			sum /= 3;
			arr.add(sum);
			
			List<Integer> half = getItemsThatSumTo(arr, sum+sum);
			if(half == null) {
				out.println("No");
				continue;
			}
			for(Integer h : half)
				arr.remove(h);
			
//			System.out.println(sum);
			
//			System.out.println("half=" + half);
//			System.out.println("arr=" + arr);
			
			List<Integer> halfToSplit;
			if(half.contains(sum)) {
				halfToSplit = arr;
			} else {
				halfToSplit = half;
			}
//			System.out.println("halfToSplit" + halfToSplit);
			
			
			List<Integer> third = getItemsThatSumTo(halfToSplit, sum);
			if(third == null) {
				out.println("No");
				continue;
			}
			
			out.println("Yes");
		}
		
//		System.out.println(getItemsThatSumTo(new int[]{1, 3, 4, 5, 3, 2}, 11));
		
    }
	
	public List<Integer> getItemsThatSumTo(List<Integer> arr, int k) {
		if(arr.size() == 0) {
			if(k==0) {
				return new ArrayList<Integer>();
			} else {
				return null;
			}
		}
		
		boolean dp[][] = new boolean[arr.size()][k+1];
		for(int i = 0; i < arr.size(); i++) {
			dp[i][0] = true;
		}
		
		for(int i = 1; i < arr.size(); i++) {
			for(int j = 0; j <= k; j++) {
				if(!dp[i][j]) {
					dp[i][j] = dp[i-1][j] || (j-arr.get(i) >=0 && dp[i-1][j-arr.get(i)]);
				}
			}
		}
		
//	
//		for(int i = 0; i < arr.size(); i++ ) {
//			for(int j = 0; j <= k; j++) {
//				System.out.print(((dp[i][j])?(1):(0)) + " ");
//			}
//			System.out.println();
//		}
//		
		if(!dp[arr.size()-1][k]) {
			return null;
		} else {
			ArrayList<Integer> elements = new ArrayList<>();
			int i = arr.size()-1;
			int j = k;
			while(i >= 0 && j >= 0) {
				if(i-1>=0 && j-arr.get(i) >=0 && dp[i-1][j-arr.get(i)]) {
					elements.add(arr.get(i));
					
					j-=arr.get(i);
					i-=1;
				} else {
					i-=1;
				}
			}
			return elements;
		}
	}
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task6 solver = new Task6();
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
