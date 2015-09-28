package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Timus1423 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		String s1 = br.readLine();
		String s2 = br.readLine();

		/*
		 * String s1, s2;
		 * 
		 * in.nextToken(); int n = (int) in.nval; in.nextToken(); s1 = in.sval;
		 * in.nextToken(); s2 = in.sval;
		 */
		int n = s2.length();
		/*
		 * 
		 * Scanner in = new Scanner(System.in); int n = in.nextInt(); String s1
		 * = in.next(), s2 = in.next();
		 */

		s1 += s1;

		/*
		 * int i = s1.indexOf(s2); if(i==-1) System.out.println("-1"); else {
		 * System.out.println((n - i) % n); }
		 */

		long[] hash = new long[s1.length()];

		final long p = (long) 1e9 + 9; // 1000003;

		long pow = 1;
		hash[0] = s1.charAt(0) * pow;

		for (int i = 1; i < hash.length; i++) {
			// System.out.println(0 + s1.charAt(i));
			pow = (pow * 257) % p;
			hash[i] = (hash[i - 1] + (s1.charAt(i) * pow) % p) % p;
		}

		long h2 = 0;
		pow = 1;
		for (int i = 0; i < n; i++) {
			h2 = (h2 + (s2.charAt(i) * pow) % p) % p;
			pow = (pow * 257) % p;
		}
		// System.out.println(h2);

		pow = 1;
		for (int i = 0; i < n; i++) {
			if (h2 == (p + hash[i + n - 1] - ((i == 0) ? (0) : (hash[i - 1])))
					% p) {

				System.out.println((n - i) % n);
				System.exit(0);
			}
			h2 = (h2 * 257) % p;
			// pow = (pow * 257) % p;
		}
		System.out.println("-1");

	}
}
