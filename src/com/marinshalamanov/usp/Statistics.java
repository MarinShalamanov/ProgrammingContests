package com.marinshalamanov.usp;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Statistics {
	
	
	static class Interval {
		public int left, righ;

		public Interval(int left, int righ) {
			this.left = left;
			this.righ = righ;
		}
	}

	void run() {
		Scanner in = new Scanner(System.in);
		int nt = in.nextInt();
		while (nt-- > 0) {
			int n = in.nextInt();
			int q = in.nextInt();
			String a[] = new String[n];
			for (int i = 0; i < n; i++) {
				a[i] = in.next();
			}
			
			Stack<Interval> s = new Stack<Interval>();
			s.push(new Interval(0, n));
			while (q-- > 0) {
				String com = in.next();
				switch (com.charAt(0)) {
				case 'E':
					s.push(new Interval(in.nextInt(), in.nextInt()));
					break;
				case 'L':
					s.pop();
					break;
				case 'Q':
					String pref = in.next();
					int last = (-1)*Arrays.binarySearch(a, pref+(char)('z'+1)) - 1;
					int first = (-1)*Arrays.binarySearch(a, pref.substring(0, pref.length()-1) + (char)(pref.charAt(pref.length()-1)-1) +(char)('z'+1)) - 1;
					
//					System.out.println(pref.substring(0, pref.length()-1) + (char)(pref.charAt(pref.length()-1)-1) +(char)('z'+1));
//					System.out.println(last + " " + first);
					if(first == last) {
						System.out.println("0");
						continue;
					}else {
						last--;
						if(first < s.peek().left) {
							if(last >= s.peek().left) {
								first = s.peek().left;
							} else {
								System.out.println("0");
								continue;
							}
						}
						if(last > s.peek().righ) {
							if(first <= s.peek().righ) {
								last = s.peek().righ;
							} else {
								System.out.println("0");
								continue;
							}
						}
						System.out.println(last - first + 1);
					}
					
					break;
				default:
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		new Statistics().run();
	}
}
