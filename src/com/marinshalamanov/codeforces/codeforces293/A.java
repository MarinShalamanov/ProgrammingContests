package codeforces293;

import java.util.Scanner;

public class A {

	public void A() {
		
		
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
		String trs = input.nextLine();
		StringBuilder r = new StringBuilder(str);
		boolean isCorrect = false;

		out: for (int _i = 0; _i < str.length(); _i++) {
			char ss = str.charAt(_i);
			char tt = trs.charAt(_i);
			if (tt - ss > 1) {
				char rr = (char) (ss + 1);
				r.replace(_i, _i + 1, "" + rr);
				isCorrect = true;
				break out;
			} else if (tt - ss == 1) {
				for (int j = _i + 1; j < str.length(); j++) {
					if (str.charAt(j) != 'z') {
						r.replace(j, j + 1, "z");
						isCorrect = true;
						break out;
					}
				}
				r = new StringBuilder(trs);
				for (int j = _i + 1; j < str.length(); j++) {
					if (trs.charAt(j) != 'a') {
						r.replace(j, j + 1, "a");
						isCorrect = true;
						break out;
					}
				}
			}
		}
		if (isCorrect) {
			System.out.println(r.toString());
		} else {
			System.out.println("No such string");
		}
	}
}