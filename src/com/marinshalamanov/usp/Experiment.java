package com.marinshalamanov.usp;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * pranka2009-05-26
 * @author I323707
 */
public class Experiment {
	
	Scanner in;
	
	void run() {
		in = new Scanner(System.in);
		
		int nt = in.nextInt();
		
		BigInteger dp[] = new BigInteger[92];
		dp[0] = new BigInteger("1");
		dp[1] = new BigInteger("2");
		dp[2] = new BigInteger("4");
		dp[3] = new BigInteger("6");
		//dp[4] = 9;
		for(int i = 4; i < 92; i++) {
			dp[i] = dp[i-1].add(dp[i-3]).add(dp[i-4]);
		}
		
		//System.out.println(Arr1ays.toString(dp));
		
		while(nt-- > 0) {
			int n = in.nextInt();
			System.out.println(dp[n]);
			
		}
		
	}
	
	public static void main(String[] args) {
		 new Experiment().run();
	}
}
