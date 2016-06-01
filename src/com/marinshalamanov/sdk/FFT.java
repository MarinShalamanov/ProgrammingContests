package com.marinshalamanov.sdk;

public class FFT {

	/**
	 * Time complexity O(coeff.length * points.length)
	 * 
	 * @param coeff
	 *            - coeff representation of the polynomial coeff[i] is the
	 *            coefficient of x^i
	 * @param points
	 *            - the points where P(x) is evaluated
	 * 
	 * @return values array - values[i] = P(points[i])
	 */
	public static ComplexNumber[] DTF(ComplexNumber[] coeff, ComplexNumber[] points) {
		ComplexNumber[] values = new ComplexNumber[points.length];

		for (int i = 0; i < points.length; i++) {
			values[i] = new ComplexNumber(0, 0);

			ComplexNumber x = points[i];
			ComplexNumber powX = new ComplexNumber(1, 0);

			for (int j = 0; j < coeff.length; j++) {
				values[i] = values[i].sum(powX.mult(coeff[j]));

				powX = powX.mult(x);
			}
		}

		return values;
	}

	 
	/**
	 * Time complexity O (N*logN) N = coeff.length
	 * 
	 * Decimation-in-time radix-2 FFT.
	
		 Computes in-place the following transform:
		 y[i] = A(w^(dir*i)),
		 where
		 w = exp(2pi/N) is N-th complex principal root of unity,
		 A(x) = a[0] + a[1] x + ... + a[n-1] x^{n-1},
		 dir \in {-1, 1} is FFT's direction (+1=forward, -1=inverse).
		
		 Notes:
		 	* N must be a power of 2,
		 	* scaling by 1/N after inverse FFT is caller's resposibility.
	 */
	public static void FFT(ComplexNumber[] a, int dir) {
		int N = a.length;
		int lgN;
		for (lgN = 1; (1 << lgN) < N; lgN++) ;
		
		
		for (int i = 0; i < N; i++) {
			int j = 0;
			for (int k = 0; k < lgN; k++)
				j |= ((i >> k) & 1) << (lgN - 1 - k);
			if (i < j) {
				ComplexNumber _t = a[i];
				a[i] = a[j];
				a[j] = _t;
			}
		}
		for (int s = 1; s <= lgN; s++) {
			int h = 1 << (s - 1);
			ComplexNumber t, w, w_m = ComplexNumber.exp(new ComplexNumber(0, dir * Math.PI / h));
			for (int k = 0; k < N; k += h + h) {
				w = new ComplexNumber(1, 0);
				for (int j = 0; j < h; j++) {
					t = w.mult(a[k + j + h]);
					a[k + j + h] = a[k + j].sum(t.negative());
					a[k + j] = a[k + j].sum(t);
					w = w.mult(w_m);
				}
			}
		}
	}

}
