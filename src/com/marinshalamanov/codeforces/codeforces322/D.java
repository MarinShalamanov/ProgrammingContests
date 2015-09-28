package com.marinshalamanov.codeforces.codeforces322;

import java.util.Arrays;
import java.util.Scanner;

public class D {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int x[] = new int[6];
		for (int i = 0; i < x.length; i++) {
			x[i] = in.nextInt();
		}
		
		for (int k, i = 0; i < x.length; i+=2) {
			if(x[i] < x[i+1]) {
				k = x[i];
				x[i] = x[i+1];
				x[i+1] = k;
			}
		}
		
		//System.out.println(Arrays.toString(x));
				
		
		// trite edno nad drugo
		if(x[0] == x[2] && x[4] == x[2] && (x[1] + x[3] + x[5]) == x[0]) {
			int n = x[0];
			char c = 'A';
			System.out.println(n);
			for(int k, j, i = 1; i < 6; i+=2, c++) {
				for(j = 0; j < x[i]; j++) {
					for(k = 0; k < n; k++) {
						System.out.print(c);
					}
					System.out.println();
				}
			}
			
			return;
		}
		
		
		/*
		 *  BBBBBB
			BBBBBB
			AAAACC
			AAAACC
			AAAACC
			AAAACC
		 */
		int n = Math.max(x[0], Math.max(x[2], x[4]));
		int h = -1;
		
		char let[]  = new char[] {'A', 'B', 'C'};
		for(int i = 0; i < 6; i+=2) {
			if(x[i] == n) {
				h = x[i+1];
				
				char _t = let[2];
				let[2] = let[i/2];
				let[i/2] = _t;
				
				x[i] = x[4];
				x[i+1] = x[5];
				x[4] = -1;
				x[5] = -1;
				break;
			}
		}
		
		if(h==-1) {
			System.out.println(-1);
			return;
		}
		
		boolean ok = false;
		int p = -1;
		
		if (x[0] == x[2] && x[0] == n-h && x[1] + x[3] == n) {
			ok = true;
			p = x[1];
		} else if(x[0] == x[3] && x[0] == n-h && x[1] + x[2] == n) {
			ok = true;
			p = x[1];
		} else if(x[1] == x[2] && x[1] == n-h && x[0] + x[3] == n) {
			ok = true;
			p = x[0];
		} else if(x[1] == x[3] && x[1] == n-h && x[0] + x[2] == n) {
			ok = true;
			p = x[0];
		}
		
		
		
		if(!ok) {
			System.out.println(-1);
			return;
		}
		
		
		System.out.println(n);
		for(int k, j = 0; j < h; j++) {
			for(k = 0; k < n; k++) {
				System.out.print(let[2]);
			}
			System.out.println();
		}
		
		for(int k, j = h; j < n; j++) {
			for(k = 0; k < p; k++) {
				System.out.print(let[0]);
			}
			for(k = p; k < n; k++) {
				System.out.print(let[1]);
			}
			System.out.println();
		}	
		
		
	}
}
