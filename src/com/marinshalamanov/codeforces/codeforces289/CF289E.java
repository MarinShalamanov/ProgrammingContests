package com.marinshalamanov.codeforces.codeforces289;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class CF289E {

	public static void main(String[] args) throws IOException {
		new CF289E();
	}

	String str;
	int n;
	long a[] = new long[500005];

	void read() throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(
				new InputStreamReader(System.in)));
		// Scanner in = new Scanner(System.in);
		in.nextToken();
		str = in.sval;

		n = str.length();
		final char vow[] = new char[] { 'I', 'E', 'A', 'O', 'U', 'Y' };
		for (int i = 0; i < n; i++) {
			boolean found = false;
			for (char v : vow) {
				if (str.charAt(i) == v) {
					a[i] = 1;
					found = true;
					break;
				}
			}
			if (!found) {
				a[i] = 0;
			}
		}
	}

	long[] d;

	long[] sum;

	double res = 0;

	long front[];

	void prec() {
		d = new long[n];
		sum = new long[n];
		for (int i = 0; i < n; i++) {
			d[i] = a[i] + a[n - 1 - i];
			if (i == 0) {
				sum[0] = a[i];
			} else {
				sum[i] = sum[i - 1] + a[i];
			}
		}
		front = new long[n];
		front[0] = d[0];
		for (int i = 1; i < n / 2 + 1; i++) {
			front[i] = front[i - 1] + (i + 1) * d[i];
		}
		/*
		 * for(int i = 0; i < n; i++) { System.out.print(a[i] + " "); }
		 * System.out.println();
		 * 
		 * for(int i = 0; i < n; i++) { System.out.print(sum[i] + " "); }
		 * System.out.println(); for(int i = 0; i < n; i++) {
		 * System.out.print(d[i] + " "); } System.out.println();
		 * 
		 * for(int i = 0; i < n; i++) { System.out.print(front[i] + " "); }
		 * System.out.println();
		 */
		long s;
		for (int i = 1; i <= n / 2; i++) {
			s = i * (sum[n - i - 1] - sum[i - 1]) + front[i - 1];
			// System.out.println(i + " " + s);
			res += s / (double) i;
			res += s / (double) (n - i + 1);
		}

		if (n % 2 == 1) {
			int i = n / 2;
			s = front[i - 1] + (i + 1) * a[i];
			// System.out.println(i + " " + s);
			res += s / (double) (i + 1);
		}

	}

	public CF289E() throws IOException {
		read();
		if(n==1) {
			System.out.println(a[0]);
		} else {
			prec();
			System.out.println(res);
		}
	}

}