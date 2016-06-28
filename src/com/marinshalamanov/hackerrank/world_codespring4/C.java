package com.marinshalamanov.hackerrank.world_codespring4;

import java.util.Scanner;

public class C {
	
	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		int q = Integer.parseInt(in.nextLine());
		for (int i = 0; i < q; i++) solve();
	}
	private static void solve() {
		int k = Integer.parseInt(in.nextLine());
		StringBuilder a = new StringBuilder(in.nextLine());
		StringBuilder b = new StringBuilder(in.nextLine());
		StringBuilder c = new StringBuilder(in.nextLine());
		int maxLen = Math.max(a.length(), b.length());
		maxLen = Math.max(maxLen, c.length());
		
		while (a.length() < maxLen) a.insert(0, "0");
		while (b.length() < maxLen) b.insert(0, "0");
		while (c.length() < maxLen) c.insert(0, "0");
	
		for (int i = 0; i < maxLen; i++) {
			k = change(a,b,c,k,i);
			if (k < 0) {System.out.println(-1); return;}
		}
		for (int i = 0; i < maxLen; i++) {
			if (k == 0) break;
			k = change2(a,b,c,k,i);
		}
		
		while (a.charAt(0) == '0' && a.length() > 1) a.replace(0, 1, "");
		while (b.charAt(0) == '0' && b.length() > 1) b.replace(0, 1, "");


		System.out.println(a.toString());
		System.out.println(b.toString());
		//00101011
		//10011111
		//01011000
		
		//00001000 08
		//01011000 58
		//01011000
				
	}
	static int[] ost = new int[]{1,2,4,8};
	private static int change(StringBuilder a, StringBuilder b, StringBuilder c, int k, int index) {
		int a0 = Character.isDigit(a.charAt(index))?a.charAt(index)-'0':10+a.charAt(index)-'A';
		int b0 = Character.isDigit(b.charAt(index))?b.charAt(index)-'0':10+b.charAt(index)-'A';
		int c0 = Character.isDigit(c.charAt(index))?c.charAt(index)-'0':10+c.charAt(index)-'A';
		for (int i = 0; i < 4; i++) {
			int a1 = a0&ost[i]; int b1 = b0&ost[i]; int c1 = c0&ost[i];
			if (c1 == 0) {
				if (a1 > 0) {k--; a0-=ost[i];}
				if (b1 > 0) {k--; b0-=ost[i];}
			} else {
				if (a1 == 0 && b1 == 0) {k--; b0+=ost[i];}
			}
		}
		char a3 = a0 < 10 ? (char)('0'+a0):(char) ('A' + a0 - 10);
		char b3 = b0 < 10 ? (char)('0'+b0):(char) ('A' + b0 - 10);
		a.replace(index, index+1, String.valueOf(a3));
		b.replace(index, index+1, String.valueOf(b3));

		return k;
	}
	
	private static int change2(StringBuilder a, StringBuilder b, StringBuilder c, int k, int index) {
		int a0 = Character.isDigit(a.charAt(index))?a.charAt(index)-'0':10+a.charAt(index)-'A';
		int b0 = Character.isDigit(b.charAt(index))?b.charAt(index)-'0':10+b.charAt(index)-'A';
		int c0 = Character.isDigit(c.charAt(index))?c.charAt(index)-'0':10+c.charAt(index)-'A';
		for (int i = 3; i >= 0; i--) {
			int a1 = a0&ost[i]; int b1 = b0&ost[i]; int c1 = c0&ost[i];
			if (a1 > 0) {
				if (b1 > 0 && k > 0) {k--; a0-=ost[i];}
				if (b1 == 0 && k > 1) {k-=2; a0-=ost[i]; b0+=ost[i];}
			}
		}
		char a3 = a0 < 10 ? (char)('0'+a0):(char) ('A' + a0 - 10);
		char b3 = b0 < 10 ? (char)('0'+b0):(char) ('A' + b0 - 10);
		a.replace(index, index+1, String.valueOf(a3));
		b.replace(index, index+1, String.valueOf(b3));
		
		return k;
	}
}
