package com.marinshalamanov.usp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Euleonora {
	
	List<Integer> primes;
	
	void eratosten() {
		primes = new ArrayList<Integer>();
		boolean nums[] = new boolean[10000001];
		for(int i = 2; i < nums.length; i++) {
			if(!nums[i]) {
				primes.add(i);
				for(int j = i; j < nums.length; j+=i) {
					nums[j]=true;
				}
			}
		}
	}
	
	long pow(long a, long b, long m) {
		long res = 1;
		
		while(b != 0) {
			if((b&1)==1) {
				res = (res*a)%m;
			}
			a = (a*a)%m;
			b >>= 1;
		}
		
		return res;
	}
	
	void run() {
//		System.out.println(pow(2, 10, 1000));
		eratosten();
//		System.out.println(primes.size());
//		for(int i = 0; i < 100; i++) System.out.println(primes.get(i));
		Scanner in = new Scanner(System.in);
		int nt = in.nextInt();
		
		long a, b, m;
		
		long prod[];
		
		
		while(nt-- > 0) {
			a = in.nextLong();
			b = in.nextLong();
			m = in.nextLong();
			
			
			long sol = 1;
			int i;
			int p;
			for(i = 0; i < primes.size(); i++) {
				p = primes.get(i);
				sol = (sol * pow(p, (b/p  - (a-1)/p), m))%m;
			}
			System.out.println(sol);
			
		}
	}
	
	public static void main(String[] args) {
		new Euleonora().run();
	}

}
