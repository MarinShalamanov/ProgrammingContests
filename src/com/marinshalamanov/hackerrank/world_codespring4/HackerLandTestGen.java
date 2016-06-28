package com.marinshalamanov.hackerrank.world_codespring4;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class HackerLandTestGen {
	public static void main(String[] args) throws FileNotFoundException {
		int n = (int) 1e5;
		
		PrintWriter writer = new PrintWriter("test.txt");
		
		writer.println(n + " " + (n-1));
		for(int i = 1; i < n; i++) {
			writer.println(i + " " + (i+1) + " " + (n+ i-1));
		}
		
		writer.close();
		System.out.println("done");
	}
}
