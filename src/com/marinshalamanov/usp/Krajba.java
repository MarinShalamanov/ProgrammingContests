package com.marinshalamanov.usp;

import java.util.Scanner;
import java.util.Stack;

/**
 * pranka2009-05-26
 * 
 * @author I323707
 */
public class Krajba {
	
	Scanner in;
	
	int dp[][] = new int[1000][1001];
	boolean dpTaken[][] = new boolean[1000][1001];
	final boolean TAKEN = true;
	final boolean NOT_TAKEN = false;
	
	void solve() {
		int numBoxes = in.nextInt();
		int truckW = in.nextInt();
		
		int boxC[] = new int[numBoxes];
		int boxW[] = new int[numBoxes];
		
		for(int i = 0; i < numBoxes; i++) {
			boxC[i] = in.nextInt();
			boxW[i] = in.nextInt();
		}
		
		for(int i = 0; i < 1001; i++) {
			dp[0][i] = 0;
		}
		
		for(int i = 1; i <= numBoxes; i++) {
			int currBoxIdx = i-1;
			for(int w = 0; w < 1001; w++) {
				if(boxW[currBoxIdx] > w) {
					dp[i][w] = dp[i-1][w];
					dpTaken[i][w] = NOT_TAKEN;
				} else {
					int valueIfNotTaken = dp[i-1][w];
					int valueIfTaken = dp[i-1][w-boxW[currBoxIdx]] + boxC[currBoxIdx];
					if(valueIfTaken < valueIfNotTaken) {
						dp[i][w] = valueIfNotTaken;
						dpTaken[i][w] = NOT_TAKEN;
					} else {
						dp[i][w] = valueIfTaken;
						dpTaken[i][w] = TAKEN;
					}
					
					if(dp[i][w] == 5) {
						System.out.println("why?");
					}
				}
			}
		}
		
		System.out.println(dp[numBoxes][truckW]);
		Stack<Integer> boxesTaken = new Stack<Integer>();
		int ci, cw;
		ci = numBoxes;
		cw = truckW;
		while(ci > 0 && cw >= 0) {
			
			if(dpTaken[ci][cw]) {
				boxesTaken.push(ci);
				cw -= boxW[ci-1];
				ci--;
			} else {
				ci--;
			}
		}
		System.out.println(boxesTaken.size());
		while(!boxesTaken.empty()) {
			System.out.print(boxesTaken.pop() + " ");
		}
		System.out.println();
		
//		printDP(11, 30);
//		System.out.println("-------");
//		printDPTaken(11, 30);
	}
	
	void printDP(int h, int w) {
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	void printDPTaken(int h, int w) {
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				System.out.print(dpTaken[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	void run() {
		in = new Scanner(System.in);
		int nt = in.nextInt();
		while(nt--  > 0) {
			solve();
		}
		
	}
	
	public static void main(String[] args) {
		
		new Krajba().run();
	}
}
