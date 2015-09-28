package com.marinshalamanov.codeforces.codeforces289;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class CF289B {

	public static void main(String[] args) throws IOException {
		new CF289B();
	}

	int n, k;
	int a[];
	int color[][];

	int maxa, mina;
	int zeros;

	void read() throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(
				new InputStreamReader(System.in)));
		// Scanner in = new Scanner(System.in);
		in.nextToken();
		n = (int) in.nval;
		in.nextToken();
		k = (int) in.nval;

		a = new int[n];

		maxa = -1;
		mina = 1000;
		for (int i = 0; i < n; i++) {
			in.nextToken();
			a[i] = (int) in.nval;
			maxa = Math.max(maxa, a[i]);
			mina = Math.min(mina, a[i]);
		}

		color = new int[n][k];
		zeros = k - mina;
	}

	void test(int c, int minNum, int col) {
		if (col == n) {
			if (zeros > 0) {
				zeros -= 1;
				test(c + 1, 0, 0);
				zeros += 1;
			} else {
				for (int j = 0; j < maxa; j++) {
					if(j==0) { zeros -= 1; }
					test(c + 1, j, 0);
					if(j==0) { zeros += 1; }
				}

			}
			return;
		}

		if (c == k) {
			boolean ok = true;
			for (int i = 0; i < n; i++) {
				if (a[i] != 0) {
					ok = false;
					break;
				}
			}

			if (!ok) {
				return;
			}

			PrintWriter out = new PrintWriter(
					new OutputStreamWriter(System.out));

			out.println("YES");
			for (int i = 0; i < n; i++) {
				for (int _c = 0; _c < k; _c++) {
					if (color[i][_c] != 0) {
						for (int t = 0; t < color[i][_c]; t++) {
							out.print(_c + 1 + " ");
						}

					}
				}
				out.println();
			}
			out.flush();
			System.exit(0);
		}

		if (a[col] < minNum) {
			return;
		} else if (a[col] == minNum) {
			color[col][c] = minNum;
			a[col] -= minNum;

			test(c, minNum, col + 1);

			color[col][c] = 0;
			a[col] += minNum;
		} else {
			color[col][c] = minNum + 1;
			a[col] -= minNum + 1;

			test(c, minNum, col + 1);

			color[col][c] = 0;
			a[col] += minNum + 1;

			color[col][c] = minNum;
			a[col] -= minNum;

			test(c, minNum, col + 1);

			color[col][c] = 0;
			a[col] += minNum;
		}

	}

	public CF289B() throws IOException {
		read();

		if (zeros > 0) {
			zeros -= 1;
			test(0, 0, 0);
			zeros += 1;
		} else {
			for (int j = 0; j < 100; j++) {
				if (j == 0) {
					zeros -= 1;
					test(0, j, 0);
					zeros += 1;
				} else {
					test(0, j, 0);
				}
			}
		}

		System.out.println("NO");

	}

}
