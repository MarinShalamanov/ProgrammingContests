package com.marinshalamanov.timus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Timus1005 {
	public static void main(String[] args) throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(
				new InputStreamReader(System.in)));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

		in.nextToken();
		int n = (int) in.nval;
		int[] arr = new int[n];
		int sum = 0;
		for (int i = 0; i < n; i++) {
			in.nextToken();
			arr[i] = (int) in.nval;
			sum += arr[i];
		}

		//sum /= 2.0;
		if (n == 1) {
			out.print(arr[0]);
			out.println();
			out.flush();
			System.exit(0);
		}

		int[] left = new int[1 << (n / 2)];
		int[] right = new int[1 << (n - n / 2)];

		for (int i = (1 << (n / 2)) - 1; i >= 0; i--) {
			left[i] = 0;
			for (int j = 0; j < n / 2; j++) {
				left[i] += ((i >> j) & 1) * arr[j];
			}
			//System.out.println(i + " " + left[i]);
		}

		for (int i = (1 << (n - n / 2)) - 1; i >= 0; i--) {
			right[i] = 0;
			for (int j = 0; n / 2 + j < n; j++) {
				right[i] += ((i >> j) & 1) * arr[n / 2 + j];
			}
		}

		Arrays.sort(left);
		Arrays.sort(right);
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < left.length; i++) {
			int pos = Arrays.binarySearch(right, (int) (sum/2 - left[i]));
			if (pos < 0)
				pos *= -1;
			for (int j = -1; j <= 1; j++) {
				try {
					//if (left[i] + right[pos + j] <= sum) {
						min = Math.min(min, Math.abs(sum - 2*(left[i] + right[pos + j])));
					//}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
		}

		out.print(min); // Math.abs(a-b));
		out.println();
		out.flush();
	}
}
