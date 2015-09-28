package codeforcesRocketon2015;

import java.util.Scanner;

public class A {
	public static void main(String[] args) {
		int n1, n2, k1, k2;
		
		Scanner in = new Scanner(System.in);
		n1 = in.nextInt();
		n2 = in.nextInt();
		k1 = in.nextInt();
		k2 = in.nextInt();
		
		if(n1 <= n2) {
			System.out.println("Second");
		} else {
			System.out.println("First");
		}
	}
}
