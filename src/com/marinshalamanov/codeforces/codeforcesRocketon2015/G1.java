package codeforcesRocketon2015;

import java.util.Arrays;
import java.util.Scanner;

public class G1 {

	int arr[];

	int inversion(int a[]) {
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[i] > a[j]) {
					count++;
				}
			}
		}
		return count;
	}

	int[] apply(int[] arr, int... ops) {
		int[] res = Arrays.copyOf(arr, arr.length);

		for (int i = 0; i < ops.length; i += 2) {
			int l = ops[i];
			int r = ops[i + 1];
			while (l < r) {
				int t = res[l];
				res[l] = res[r];
				res[r] = t;
				l++;
				r--;
			}
		}

		return res;
	}

	public static void main(String[] args) {
		new G1();
	}

	public G1() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();

		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}

		long totalInv = 0;
		long totalCases = 0;
		int a, b, c, d, e, f, g, h;

		if (k == 4) {
			for (a = 0; a < n; a++)
				for (b = a; b < n; b++)
					for (c = 0; c < n; c++)
						for (d = c; d < n; d++)
							for (e = 0; e < n; e++)
								for (f = e; f < n; f++)
									for (g = 0; g < n; g++)
										for (h = g; h < n; h++) {
											totalInv += inversion(apply(arr, a,
													b, c, d, e, f, g, h));
											totalCases++;
										}
		} else if (k == 3) {
			for (a = 0; a < n; a++)
				for (b = a; b < n; b++)
					for (c = 0; c < n; c++)
						for (d = c; d < n; d++)
							for (e = 0; e < n; e++)
								for (f = e; f < n; f++) {
									totalInv += inversion(apply(arr, a, b, c,
											d, e, f));
									totalCases++;
								}

		} else if (k == 2) {
			for (a = 0; a < n; a++)
				for (b = a; b < n; b++)
					for (c = 0; c < n; c++)
						for (d = c; d < n; d++) {
							totalInv += inversion(apply(arr, a, b, c, d));
							totalCases++;
						}
		} else if (k == 1) {
			for (a = 0; a < n; a++)
				for (b = a; b < n; b++) {
					totalInv += inversion(apply(arr, a, b));
					totalCases++;
				}
		}

		System.out.println(String.format("%.9f", (1. * totalInv) / totalCases));

	}
}
