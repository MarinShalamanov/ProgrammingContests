package codeforces293;

import java.util.HashSet;
import java.util.Scanner;

public class B {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
		String trs = input.nextLine();
		int[] le1 = new int[1000];
		int[] le2 = new int[1000];
		HashSet<Integer> test = new HashSet<Integer>();
		for (int i = 0; i < str.length(); i++) {
			le1[str.charAt(i)]++;
			le2[str.charAt(i)]++;
		}
		int a = 0, b = 0;
		for (int i = 0; i < trs.length(); i++) {
			char p = trs.charAt(i);
			if (le1[p] > 0) {
				a++;
				le1[p]--;
				test.add(i);
			}
		}
		for (int i = 0; i < trs.length(); i++) {
			if (test.contains(i))
				continue;
			char p = trs.charAt(i);
			if ('a' <= p && 'z' >= p) {
				p = Character.toUpperCase(p);
			} else {
				p = Character.toLowerCase(p);
			}
			if (le1[p] > 0) {
				le1[p]--;
				b++;
			}
		}
		System.out.println(a + " " + b);
	}
}