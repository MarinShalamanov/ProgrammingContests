package codeforces293;

import java.util.Scanner;

public class D {
	public static void main(String[] args) {
		new D();
	}

	int n;
	double p;

	public double getP(int k, int t) {
		if (n == k) {
			double res = 0; 
			for(int i = k-1; i < t; i++) {
				res += getP(k-1, i);
			}
			
			return res*p*k/(double)Math.max(1, (k-1));
		} else {
			double res = 1;

			int numP = k;
			int numP1 = t - k;
			int denom = Math.max(1, k - 1);
			int nom = t;
			final int nomLast = t - k;

//			System.out.println("denom " + denom);
//			System.out.println("nom " + nom + " " + nomLast);

			while (nom != nomLast) {
				res *= nom;
				nom--;
//				System.out.println("res" + res);
				while (res > k) {
					if (denom > 0) {
						res /= (double) denom;
						denom--;
					} else if (numP > 0) {
						res *= p;
						numP--;
					} else if (numP1 > 0) {
						res *= 1 - p;
						numP1--;
					} else {
						break;
					}
				}
			}
//
//			System.out.println("res" + res);
//			System.out.println("denom " + denom);
//			System.out.println("nom " + nom + " " + nomLast);

			while (denom > 0) {
				res /= (double) denom;
				denom--;
			}

//			System.out.println("res" + res);

			while (numP > 0) {
				res *= p;
				numP--;
			}

//			System.out.println("res" + res);

			while (numP1 > 0) {
				res *= 1 - p;
				numP1--;
			}

//			System.out.println("res" + res);

			return res;
		}
	}

	public D() {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		p = in.nextDouble();
		int t = in.nextInt();

		double res = 0;
		int maxPeople = Math.min(t, n);
		for (int i = 1; i <= maxPeople; i++) {
			double p = getP(i, t);
//			System.out.println("p" + i + " " + p);
			res += p;
		}

		System.out.println(res);

	}
}
