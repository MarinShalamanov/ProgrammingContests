package com.marinshalamanov.codeforces.codeforces293;

import java.util.Scanner;

public class C {
	
	public static void main(String[] args) {
		new C();
	}
	
	int n, m, k;
	int[] order;
	int[] appPos;
	
	int getTouches(int id) {
		return (appPos[id]/k) + 1;
	}
	
	void swap(int id) {
		if(appPos[id]==0) return;
		else {
			
			int lastId = order[appPos[id]-1];
			int currId = id;
			int lastPos = appPos[id]-1;
			int currPos = appPos[id];
			
			appPos[currId] = currPos - 1;
			appPos[lastId] = lastPos + 1;
			
			order[appPos[currId]] = currId;
			order[appPos[lastId]] = lastId;
		}
		
	}
	
	public C() {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		k = in.nextInt();
		
		order = new int[n];
		appPos = new int[n];
		
		for(int i = 0; i < n; i++) {
			order[i] = in.nextInt() - 1;
			appPos[order[i]] = i;
		}
		
		int currId;
		long sum = 0;
		for (int i = 0; i < m; i++) {
			currId = in.nextInt() - 1;
			sum += getTouches(currId);
			swap(currId);
		}
		
		System.out.println(sum);
		
	}
}
