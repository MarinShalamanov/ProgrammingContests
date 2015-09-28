package com.marinshalamanov.codeforces.codeforces322;

import java.util.Scanner;

public class B {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n  = in.nextInt();
		int h[] = new int[n+2];
		for(int i = 0 ; i < n; i++) {
			h[i] = in.nextInt();
		}
		
		int max[] = new int[n+2];
		max[n] = 0;
		
		for(int i = n-1; i >= 0; i--) {
			max[i] = Math.max(max[i+1], h[i]);
		}
		
		for(int i = 0; i < n; i++) {
			System.out.print(Math.max(0, max[i+1] - h[i] + 1) + " ");
		}
		System.out.println();
		in.close();
	}
}
