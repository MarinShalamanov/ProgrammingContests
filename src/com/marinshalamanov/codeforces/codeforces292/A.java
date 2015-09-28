package com.marinshalamanov.codeforces.codeforces292;



import java.util.Scanner;

public class A {

	static long[] dits = new long[10];

	private static void add(int digit) {
		if (digit <= 1)
			return;

		if (digit == 9) {
			dits[2]++;
			dits[3] += 2;
			dits[7]++;
		} else if (digit == 8) {
			dits[2] += 3;
			dits[7]++;
		} else if (digit == 6) {
			dits[3]++;
			dits[5]++;
		} else if (digit == 4) {
			dits[2] += 2;
			dits[3]++;
		} else {
			dits[digit]++;
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		in.nextInt();
		String x = in.next();
		
		
		for (int i = 0; i < x.length(); i++) {
			int d = x.charAt(i) - '0';
			if (d <= 1){
				continue;
			}
			add(d);
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 7; i >= 2; i--) {
			char c = (char) ('0' + i);
			while (dits[i] > 0) {
				stringBuilder.append(c);
				dits[i]--;
			}
		}
		System.out.println(stringBuilder.toString());
	}
	
}