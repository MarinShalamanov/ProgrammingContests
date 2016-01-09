package com.marinshalamanov.usp;

import java.util.Scanner;

public class Quidditch {
	
	
	void run() {
		Scanner in = new Scanner(System.in);
		int nt = in.nextInt();
		while(nt-- > 0){
			int n = in.nextInt();
			int a[] = new int[n];
			for(int i = 0; i < n; i++) {
				a[i] = in.nextInt();
			}
			
			int oc[] = new int [4];
			for(int el : a) {
				oc[el]++;
			}
			
			
			int m[][] = new int[4][4];
			for(int i = 0; i < a.length; i++) {
				int shouldBe;
				if(i < oc[1]) shouldBe = 1;
				else if(i < oc[1] + oc[2]) shouldBe = 2;
				else shouldBe = 3;
				
				m[a[i]][shouldBe]++;
			}
			
			
//			for(int j, i = 1; i < 4; i++) {
//				for(j = 1; j < 4; j++) {
//					System.out.print(m[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			int swaps = 0;
			int currSwaps;
			for(int i = 2; i < 4; i++) {
				for(int j = 1; j < i; j++) {
					currSwaps = Math.min(m[i][j], m[j][i]);
					swaps += currSwaps;
					m[i][j] -= currSwaps;
					m[j][i] -= currSwaps;
				}
			}
//			for(int j, i = 1; i < 4; i++) {
//				for(j = 1; j < 4; j++) {
//					System.out.print(m[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			swaps += 2*(m[1][2] + m[1][3]);
			System.out.println(swaps);
		}
		
	}
	
	public static void main(String[] args) {
		new Quidditch().run();
		
	}
}
