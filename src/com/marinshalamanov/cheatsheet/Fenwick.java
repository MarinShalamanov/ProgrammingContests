package com.marinshalamanov.cheatsheet;

public class Fenwick {
	
	int n;
	int fen[];
	
	public Fenwick(int n) {
		n++;
		
		this.n = n;
		fen = new int[n];
		
	}
		
	public void add(int i, int delta) {
		i++;
		while(i < n) {
			fen[i] += delta;
			i += i&-i;
		}
	}
	
	public int sum(int i) {
		i++;
		int sum = 0; 
		while(i > 0) {
			sum += fen[i];
			i -= i&-i;
		}
		return sum;
	}	
}
