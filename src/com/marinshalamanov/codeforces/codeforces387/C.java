package com.marinshalamanov.codeforces.codeforces387;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = reader.readLine().split(" ");
		int n = Integer.parseInt(tokens[0]);
		int q = Integer.parseInt(tokens[1]);
		int[] s = new int[n];
		
		int t, k, d;
		for (int i = 0; i < q; i++) {
			tokens = reader.readLine().split(" ");
			t = Integer.parseInt(tokens[0]);
			k = Integer.parseInt(tokens[1]);
			d = Integer.parseInt(tokens[2]);
			int feer = 0;
			
			for (int j = 0; j < n; j++)
				if (s[j] < t) {
					feer++;
				}
			if (feer < k) {
				System.out.println("-1");
				continue;
			}
			int sum = 0;
			for (int j = 0; j < n && k > 0; j++) {
				if (s[j] < t) {
					s[j] =  d + t - 1;
					sum += 1 + j;
					k--;
				}
			}
			System.out.println(sum);
		}
	}

}
