package com.marinshalamanov.sdk.math;

public class Matrix {
	
	int n, m;
	int a[][];
	
	public Matrix(int n, int m) {
		this.n = n;
		this.m = m;
		a = new int[n][m];
	}
	
	public Matrix(Matrix m) {
		this.n = m.n;
		this.m = m.m;
		a = new int[n][this.m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < this.m; j++) {
				a[i][j] = m.a[i][j];
			}
		}
	}
	
	public void add(Matrix other) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				a[i][j] += other.a[i][j];
			}
		}
	}
	
	public Matrix sum(Matrix other) {
		Matrix res = new Matrix(n, m);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				res.a[i][j] = a[i][j] + other.a[i][j];
			}
		}
		return res;
	}
	
	public Matrix mult(Matrix o) {
		Matrix res = new Matrix(n, o.m); 
		for(int i = 0; i < res.n ; i++) {
			for(int j = 0; j < res.m; j++) {
				for(int k = 0; k < m; k++) {
					res.a[i][j] += a[i][k]*o.a[k][j];
				}
			}
		}
		return res;
	}
	
	public Matrix pow(int exp) {
		Matrix p = new Matrix(this);
		
		
		Matrix res = new Matrix(n, m);
		for(int i = 0; i < n; i++) {
			res.a[i][i] = 1;
		}
		
		while(exp > 0) {
			if((exp & 1) != 0) {
				res = res.mult(p);
			}
			
			p = p.mult(p);
			exp >>= 1;
		}
			
		return res;
	}
	
	
}
