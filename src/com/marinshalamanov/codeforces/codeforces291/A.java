package com.marinshalamanov.codeforces.codeforces291;



import java.util.Scanner;

public class A {
	public static void main(String[] args) {
		new A();
	}
	
	public A() {
		Scanner in = new Scanner(System.in);
		String num = in.next();
		String num2 = "";
		
		for(int i = 0; i < num.length(); i++) {
			if (num.charAt(i) >= '5') {
				if(i==0 && num.charAt(i)=='9') {
					num2 += num.charAt(i);
				} else {
					num2 += (char)('0' + (9 - (num.charAt(i) - '0')));
				}
			} else {
				num2 += num.charAt(i);
			}
			//System.out.println(num2);
		}
		System.out.println(num2);	
	}
}
