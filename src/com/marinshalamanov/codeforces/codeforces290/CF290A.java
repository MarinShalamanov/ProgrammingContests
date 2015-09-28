package codeforces290;

import java.util.Scanner;

public class CF290A {

	private static Scanner input = new Scanner(System.in);

	static String snakeSSString(int m) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			sb.append("#");
		}
		return sb.toString();
	}

	static String dotString(int m) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m - 1; i++) {
			sb.append(".");
		}
		return sb.toString();
	}

	
	public static void main(String[] args) {
		int n = input.nextInt();
		int m = input.nextInt();
		String s = snakeSSString(m);
		String d = dotString(m);

		for (int k = 0; k < n; k++) {
			if (k % 2 == 0) {
				System.out.println(s);
			} else if (k % 4 == 1) {
				System.out.println(d + "#");
			} else {
				System.out.println("#" + d);
			}
		}

	}
	
}
