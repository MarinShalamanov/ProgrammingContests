package com.marinshalamanov.hackerrank.world_codespring4;

import java.util.Arrays;
import java.util.Scanner;

public class A {
	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int A[] = new int[n];
        for(int A_i=0; A_i < n; A_i++){
            A[A_i] = in.nextInt();
        }
        int b []= new int[100002];
        Arrays.fill(b, Integer.MIN_VALUE+111111);
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            if(i - b[A[i]] < min) min = i - b[A[i]];
            b[A[i]] = i;
        }
        
        System.out.println((min < 5000)?(min):(-1));
    }
}
