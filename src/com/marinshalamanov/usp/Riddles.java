package com.marinshalamanov.usp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Riddles {
	
	void run() {
		Scanner in = new Scanner(System.in);
		int nt = in.nextInt();
		
		nextTest:
		while(nt-- > 0) {
			int n, k;
			n = in.nextInt();
			k = in.nextInt();
			
			
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
			
			long p = 0;
			
			int coins[] = new int[n];
			for(int i = 0; i < n; i++) {
				coins[i] = in.nextInt();
			}
			
			int i;
			for(i = 0; p < k && i < n; i++) {
				if(coins[i] <= p+1) {
					p += coins[i];
					
					while (!pq.isEmpty() && pq.peek() <= p+1) {
						p += pq.poll();
					}					
				} else {
					pq.add(coins[i]);
				}
			}
			
			if(p >= k) {
				System.out.println(i);
			} else {
				System.out.println(-1);
			}
//			
//			Set<Integer> values = new HashSet<Integer>();
//			values.add(0);
//			
//			for(int i = 0; i < n; i++) {
////				Integer a[] = new Integer[values.size()];
////				values.toArray(a);
////				System.out.println(Arrays.toString(a));
//				List<Integer> newEls = new ArrayList<Integer>();
//				for(Integer v : values) {
//					if(v+coins[i] <= k) {
//						newEls.add(v+coins[i]);
//					}
//				}
//				values.addAll(newEls);
//				
//				if(values.size() == k+1) {
//					System.out.println(i+1);
//					continue nextTest;
//				}
//			}
//			System.out.println(-1);
//			
			
		}
	}
	
	public static void main(String[] args) {
		new Riddles().run();
	}
}

/*
3
7 10
1 2 3 4 5 6 7
3 3
2 4 1
3 6
3 1 4 
*/
