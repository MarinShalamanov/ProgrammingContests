package com.marinshalamanov.usp;

import java.util.Scanner;

/**
 * pranka2009-06-02
 * @author I323707
 *
 */
public class Game {
	
	
	public void run() {
		
		
		boolean dp[] = new boolean[2000001];
		dp[1] = true;
		dp[2] = false;
		for(int i = 3; i < dp.length; i++) {
			dp[i] = !dp[i-1] || !dp[(i)/2];
		}
		
//		for(int i = 1; i < 100; i++) {
//			System.out.println(i + " " + solve(i));
//		}
		
		Scanner in = new Scanner(System.in);
		int nt = in.nextInt();
		
		while(nt-- > 0) {
			int n = in.nextInt();
			System.out.println(dp[n]?"Stancho":"Pancho");
		}
	}
	
	public static void main(String[] args) {
		new Game().run();
	}
}
