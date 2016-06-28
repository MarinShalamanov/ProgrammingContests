package com.marinshalamanov.hackerrank.world_codespring4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B {
	
    public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
    	int n1 = in.nextInt();
    	int n2 = in.nextInt();
    	int n3 = in.nextInt();
    	Queue<Integer> s1 = new LinkedList<>();
    	Queue<Integer> s2 = new LinkedList<>();
    	Queue<Integer> s3 = new LinkedList<>();
    	int h1 = 0; int h2 = 0; int h3 = 0;
    	for (int i = 0; i < n1; i++) { int p = in.nextInt(); s1.add(p); h1 += p; }
    	for (int i = 0; i < n2; i++) { int p = in.nextInt(); s2.add(p); h2 += p; }
    	for (int i = 0; i < n3; i++) { int p = in.nextInt(); s3.add(p); h3 += p; }
    	while (h1 != h2 || h2 != h3) {
    		if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty()) {
    			System.out.println(0); return;
    		}
    		if (h1 >= h2 && h1 >= h3) h1 -= s1.remove();
    		else if (h2 >= h1 && h2 >= h3) h2 -= s2.remove();
    		else h3 -= s3.remove();
    	}
    	System.out.println(h1);
    }
    
}
