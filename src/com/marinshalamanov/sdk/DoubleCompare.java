package com.marinshalamanov.sdk;

public class DoubleCompare {
	private double eps;

	public DoubleCompare(double eps) {
		super();
		this.eps = eps;
	}
	

	public boolean eq(double a, double b) {
		return Math.abs(a-b) < eps;
	}
	
	
	public static boolean eq(double a, double b, final double EPS) {
		return Math.abs(a-b) < EPS;
	}
	
}
