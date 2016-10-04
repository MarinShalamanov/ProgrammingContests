package com.marinshalamanov.sdk.math;

import com.marinshalamanov.sdk.DoubleCompare;

public class ComplexNumber {
	
	private double re, im;
	
	public ComplexNumber(double re, double im) {
		this.re = re;
		this.im = im;
	}

	public double getRe() {
		return re;
	}
	
	public double getIm() {
		return im;
	}
	
	public ComplexNumber conjugate() {
		return new ComplexNumber(re, -im);
	}
	
	public ComplexNumber sum(ComplexNumber... nums) {
		double sumRe = re, sumIm = im;
		
		for (ComplexNumber c : nums) {
			sumRe += c.getRe();
			sumIm += c.getIm();
		}
		
		ComplexNumber result = new ComplexNumber(sumRe, sumIm);
		return result;
	}
	
	public ComplexNumber negative() {
		return new ComplexNumber(-re, -im);
	}
	
	public ComplexNumber mult (ComplexNumber other) {
		ComplexNumber res = new ComplexNumber(re*other.re - im*other.im, im*other.re + re*other.im);
		return res;
	}
	
	public ComplexNumber pow(int pow) {
		ComplexNumber base = new ComplexNumber(re, im);
		ComplexNumber res = new ComplexNumber(1, 0);
		
		while (pow > 0) {
			if(pow%2 == 1) {
				res = res.mult(base);
			}
			pow /= 2;
			base = base.mult(base);
		}
		
		return res;
	}
	
	public static ComplexNumber exp(ComplexNumber c) {
		double realExp = Math.exp(c.re);
		ComplexNumber res = new ComplexNumber(realExp*Math.cos(c.im), realExp*Math.sin(c.im));
		
		return res;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComplexNumber other = (ComplexNumber) obj;
		if (!DoubleCompare.eq(im, other.im, 1e-6))
			return false;
		if (!DoubleCompare.eq(re, other.re, 1e-6))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + re + " + " + im + "i)";
	}
}
