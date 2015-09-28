package com.marinshalamanov.codeforces.codeforces290;

import java.util.Scanner;

public class CF290C {

	public static void main(String[] args) {
		new CF290C();
	}

	boolean[][] prev = new boolean[27][27];
	boolean[][] next = new boolean[27][27];
	boolean[] printed = new boolean[27];
	
	String[] names = new String[105];
	int n;
	
	int numPrinted = 0;

	int numUnprintedPrev(int x) {
		int num = 0;
		for(int i = 0; i < 26; i++) {
			if(prev[x][i] && !printed[i]) {
				num++;
			}
		}
		return num;
	}
	
	
	public CF290C() {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for (int i = 0; i < n; i++) {
			names[i] = in.next();
		}
		
		for(int i = 0; i < n; i++) {
			pesho: for(int j = i+1; j < n; j++) {
				int k = 0;
				while(names[i].charAt(k) == names[j].charAt(k)) {
					k++;
					
					if(names[i].length() <= k || names[j].length() <= k) {
						if(names[i].length() > names[j].length()) {
							System.out.println("Impossible");
							System.exit(0);
						} else {
							continue pesho;
						}
					}
				}
				
				int c1 = names[i].charAt(k)-'a';
				int c2 = names[j].charAt(k)-'a';
				prev[c2][c1] = true;
				next[c1][c2] = true;
			}
		}
		
		boolean change = true;
		StringBuilder sb = new StringBuilder();
		while(change && numPrinted < 26) {
			change = false;
			for(int i = 0; i < 26; i++) {
				if(!printed[i] && numUnprintedPrev(i) == 0) {
					change = true;
					printed[i] = true;
					numPrinted++;
					sb.append((char)('a' + i));
					
					if(numPrinted==26) {
						System.out.println(sb.toString());
						System.exit(0);
					}
					
				}
			}
		}
		if(numPrinted==26) {
			System.out.println(sb.toString());
			System.exit(0);
		}
		System.out.println("Impossible");

	}

}

/*
 * package codeforces290;

import java.util.Scanner;

public class CF290C {

	public static void main(String[] args) {
		new CF290C();
	}

	boolean[][] prev = new boolean[27][27];
	boolean[][] next = new boolean[27][27];
	boolean[] printed = new boolean[27];
	
	String[] names = new String[105];
	int n;
	
	int numPrinted = 0;

	int numUnprintedPrev(int x) {
		int num = 0;
		for(int i = 0; i < 26; i++) {
			if(prev[x][i] && !printed[i]) {
				num++;
			}
		}
		return num;
	}
	
	
	public CF290C() {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		for (int i = 0; i < n; i++) {
			names[i] = in.next();
		}
		
		for(int i = 0; i < n; i++) {
			pesho: for(int j = i+1; j < n; j++) {
				int k = 0;
				while(names[i].charAt(k) == names[j].charAt(k)) {
					k++;
					
					if(names[i].length() >= k || names[j].length() >= k) {
						if(names[i].length() > names[j].length()) {
							System.out.println("Impossible");
							System.exit(0);
						} else {
							continue pesho;
						}
					}
				}
				
				int c1 = names[i].charAt(k)-'a';
				int c2 = names[j].charAt(k)-'a';
				prev[c2][c1] = true;
				next[c1][c2] = true;
			}
		}
		
		boolean change = true;
		StringBuilder sb = new StringBuilder();
		while(change && numPrinted < 26) {
			change = false;
			for(int i = 0; i < 26; i++) {
				if(!printed[i] && numUnprintedPrev(i) == 0) {
					change = true;
					printed[i] = true;
					numPrinted++;
					sb.append((char)('a' + i));
					
					if(numPrinted==26) {
						System.out.println(sb.toString());
						System.exit(0);
					}
					
				}
			}
		}
		if(numPrinted==26) {
			System.out.println(sb.toString());
			System.exit(0);
		}
		System.out.println("Impossible");

	}

}
*/
