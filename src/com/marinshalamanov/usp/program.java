package com.marinshalamanov.usp;

import java.util.Arrays;
import java.util.Scanner;

public class program {

	int dp[][] = new int[400][400];

	String s;

	int getPalLen(int i, int j) {
		if (i > j) {
			return 0;
		}
		
		if (i == j) {
			return 1;
		}
		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		if (s.charAt(i) != s.charAt(j)) {
			return dp[i][j] = Math.max(getPalLen(i + 1, j), getPalLen(i, j - 1));
		}
		return dp[i][j] = 2 + getPalLen(i + 1, j - 1);
	}

	String getPalyndrome(int i, int j) {
		if (i > j) {
			return "";
		}
		if (i == j) {
			char x = s.charAt(i);
			return Character.toString(x);
		}
		int a = getPalLen(i + 1, j);
		int b = getPalLen(i, j - 1);
		
		if (s.charAt(i) != s.charAt(j) && a < b) {
			return getPalyndrome(i, j - 1);
		}
		
		if (s.charAt(i) != s.charAt(j) && a >= b) {
			return getPalyndrome(i + 1, j);
		}
		
		String mid = getPalyndrome(i + 1, j - 1);
		String res = s.charAt(i) + mid + s.charAt(i);
		return res;
	}

	void solve() {
		int t;
		Scanner input = new Scanner(System.in);
		t = input.nextInt();
		for (int k = 0; k < t; k++) {
			for (int i = 0; i < 400; i++) {
				Arrays.fill(dp[i], -1);
			}
			
			s = input.next();
			System.out.println(getPalLen(0, s.length() - 1) + " " + getPalyndrome(0, s.length() - 1));
		}
		input.close();
	}

	public static void main(String[] args) {
		new program().solve();

	}
}
