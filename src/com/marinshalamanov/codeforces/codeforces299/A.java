package com.marinshalamanov.codeforces.codeforces299;


	
	
	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.io.StreamTokenizer;
	import java.util.Scanner;
	
	public class A {
		
		
		
		public static void main(String[] args) throws IOException {
			int a, b, n;
			int m, t, l;
			
	//		Scanner input = new Scanner(System.in);
			StreamTokenizer in = new StreamTokenizer(new BufferedReader(
					new InputStreamReader(System.in)));
			
			
			in.nextToken();
			a = (int) in.nval;  //input.nextInt();
			in.nextToken();
			b = (int) in.nval;  //input.nextInt();
			in.nextToken();
			n = (int) in.nval;  //input.nextInt();
			
			out: 
			for (int i = 0; i < n; i++) {
				//System.out.println(i);
				in.nextToken();
				l = (int) in.nval;  //input.nextInt();
				in.nextToken();
				t = (int) in.nval;  //input.nextInt();
				in.nextToken();
				m = (int) in.nval;  //input.nextInt();
				
				if (a + (l-1)*b > t) {
					System.out.println("-1");
					continue out;
				}
				
				int left = l;
				int right = Integer.MAX_VALUE - l - 1;
				int mid;
				
				while (right - left > 3) {
					mid = (left + right) / 2;
					 
					int p2 =  Math.max(a + (mid-1)*b, (int) Math.ceil((2*a + (l+mid-2)*b)*(mid-l+1)/(2.0*m)));
					mid--;
					int p1 =  Math.max(a + (mid-1)*b, (int) Math.ceil((2*a + (l+mid-2)*b)*(mid-l+1)/(2.0*m)));
					mid++;
					
					if (p1 <= t && p2 > t) {
						System.out.println(mid-1);
						continue out;
					} else if (p1 > t) {
						right = mid;
					} else { // if (p2 < t) {
						left = mid;
					}
				}
				
				for (mid = left; mid <= right; mid++) {
					int p2 =  Math.max(a + (mid-1)*b, (int) Math.ceil((2*a + (l+mid-2)*b)*(mid-l+1)/(2.0*m)));
					mid--;
					int p1 =  Math.max(a + (mid-1)*b, (int) Math.ceil((2*a + (l+mid-2)*b)*(mid-l+1)/(2.0*m)));
					mid++;
					
					if (p1 <= t && p2 > t) {
						System.out.println(mid-1);
						continue out;
					} 
				}
			}
		}
	}
