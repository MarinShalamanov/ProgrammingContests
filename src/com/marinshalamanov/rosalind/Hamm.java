package com.marinshalamanov.rosalind;

import java.util.Scanner;

public class Hamm {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String a = in.next();
		String b = in.next();
		int c = 0;
		for(int i = 0 ; i < a.length(); i++) {
			if(a.charAt(i) != b.charAt(i)) c++;
		}
		System.out.println(c);
	}
}
