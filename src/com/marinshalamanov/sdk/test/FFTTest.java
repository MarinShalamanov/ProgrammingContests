package com.marinshalamanov.sdk.test;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

import com.marinshalamanov.sdk.ComplexNumber;
import com.marinshalamanov.sdk.FFT;

public class FFTTest {

	private final double EPS = 1e-6;

	public ComplexNumber[] generatePolynomial(int degree) {
		Random random = new Random();
		ComplexNumber[] coeff = new ComplexNumber[degree];
		for (int i = 0; i < degree; i++) {
			coeff[i] = new ComplexNumber(random.nextDouble(), random.nextDouble());
		}
		return coeff;
	}

	@Test
	public void test() {
		
		final int NUM_TESTS = 50;
		for (int t = 0; t < NUM_TESTS; t++) {
			int n = 1 << (1+ (t % 10)); // generate n as a power of 2
			ComplexNumber a[] = generatePolynomial(n);

			ComplexNumber rootsOfOne[] = new ComplexNumber[n];
			for (int i = 0; i < rootsOfOne.length; i++) {
				rootsOfOne[i] = new ComplexNumber(Math.cos(i * 2 * Math.PI / n), Math.sin(i * 2 * Math.PI / n));
			}

			ComplexNumber[] expected = FFT.DTF(a, rootsOfOne);
			FFT.FFT(a, 1);
			ComplexNumber[] actual = a;

			assertEquals(expected.length, actual.length);

			for (int i = 0; i < expected.length; i++) {
				assertEquals(expected[i].getRe(), actual[i].getRe(), EPS);
				assertEquals(expected[i].getIm(), actual[i].getIm(), EPS);
			}
		}
	}
}
