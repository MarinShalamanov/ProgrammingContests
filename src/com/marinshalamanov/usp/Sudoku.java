package com.marinshalamanov.usp;

import java.util.Scanner;

public class Sudoku {
	
	Scanner in = new Scanner(System.in);
	
	int sud[][] = new int[9][9];
	
	boolean check() {
		// rows
		for(int i = 0; i < 9; i++) {
			boolean has[] = new boolean[10];
			for(int j = 0; j < 9; j++) {
				if(sud[i][j] == 0) {
					continue;
				}
				
				if(has[sud[i][j]]) {
					return false;
				} else {
					has[sud[i][j]] = true;
				}
			}
		}
		
		
		//cols
		for(int i = 0; i < 9; i++) {
			boolean has[] = new boolean[10];
			for(int j = 0; j < 9; j++) {
				if(sud[j][i] == 0) {
					continue;
				}
				
				if(has[sud[j][i]]) {
					return false;
				} else {
					has[sud[j][i]] = true;
				}
			}
		}
		
		// cells
		for(int i0 = 0; i0 < 3; i0++) {
			for(int j0 = 0; j0 < 3; j0++) {
				boolean has[] = new boolean[10];
				
				for(int i = 3*i0; i < 3*i0+3; i++) {
					for(int j = 3*j0; j < 3*j0+3; j++) {
						if(sud[i][j] == 0) {
							continue;
						}
						
						if (has[sud[i][j]]) {
							return false;
						} else {
							has[sud[i][j]] = true;
						}
					}
				}
			}
		}
		
		return true;
	}
	
	boolean done() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(sud[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
		boolean fillNext() {
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					if(sud[i][j] == 0) {
						
						for(int num = 1; num <= 9; num++) {
							sud[i][j] = num;
							if(check()) {
								if(fillNext()) {
									return true;
								}
							}
						}
						sud[i][j] = 0;
						return false;
					}
				}
			}
			
			return true;
		}
		
		void solve() {
			fillNext();
			
			if(done() && check()) {
				for(int i = 0; i < 9; i++) {
					for(int j = 0; j < 9; j++) {
						System.out.print(sud[i][j] + " ");
					}
					System.out.println();
				}
			} else {
				System.out.println(-1);
			}
		}
	
	void run() {
		int t = in.nextInt();
		while(t-- > 0) {
			for(int i = 0; i < 9; i++) {
				
//				String s = in.next();
				for(int j = 0; j < 9; j++) {
					sud[i][j] = in.nextInt();
//					sud[i][j] = s.charAt(j) - '0';	
				}
			}
			
			solve();
		}
		in.close();
	}
	
	public static void main(String[] args) {
		new Sudoku().run();
	}
}
