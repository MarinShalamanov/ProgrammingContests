package codeforces291;

import java.util.Arrays;
import java.util.Scanner;

public class B {
	
	public static void main(String[] args) {
		Scanner inputt = new Scanner(System.in);
		int num = inputt.nextInt();
		int a = inputt.nextInt();
		int b = inputt.nextInt();
		double[] angles = new double[num];
		for (int i = 0; i < num; i++) {
			int x = inputt.nextInt();
			int y = inputt.nextInt();
			angles[i] = getMeTheA(a, b, x, y);
		}
		Arrays.sort(angles);
		int count = 1;
		double curr = angles[0];
		
		for (int i = 1; i < num; i++) {

			//System.out.println(angles[i] + " " + curr);
			if (Math.abs(angles[i] - curr) < 1e-15) continue;
			//if (angles[i] == curr) continue;
			count++;
			curr = angles[i];
		}
		System.out.println(count);
	}
	private static double getMeTheA(int a, int b, int x, int y) {
		if (x == a) return 1e9;
		return 1.*(y-b)/(x-a);
	}
}
