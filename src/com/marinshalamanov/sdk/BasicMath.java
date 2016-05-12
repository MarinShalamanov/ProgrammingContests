package com.marinshalamanov.sdk;

public class BasicMath {

	public static int gcdIterative(int a, int b) {
		int c;
		while (a != 0) {
			c = a;
			a = b % a;
			b = c;
		}
		return b;
	}
	
	public static int gcd(int a, int b) {
		return (a==0)?(b):(gcd(b%a, a)); 
	}
	
	public static long gcd(long a, long b) {
		return (a==0)?(b):(gcd(b%a, a)); 
	}
	
	public static short gcd(short a, short b) {
		return (a==0)?(b):(gcd((short)(b%a), a)); 
	}
	
	public static byte gcd(byte a, byte b) {
		return (a==0)?(b):(gcd((byte)(b%a), a)); 
	}
	
	public static long lcm(long a, long b) {
		return (long)a*(long)b / gcd(a, b);
	}
	
	
	public static boolean isPrime(long n) {
		if(n < 2) return false;
	    if(n == 2 || n == 3) return true;
	    if(n%2 == 0 || n%3 == 0) return false;
	    
	    long sqrtN = (long)Math.sqrt(n)+1;
	    
	    for(long i = 6; i <= sqrtN; i += 6) {
	        if(n%(i-1) == 0 || n%(i+1) == 0) return false;
	    }
	    
	    return true;
	}
	
	public static long pow(int a, int pow) {
		long res = 1;
		
		while (pow > 0) {
			if(pow%2 == 1) {
				res *= a;
			}
			pow /= 2;
			a *= a;
		}
		
		return res;
	}

	private static void performanceTest() {	
	
		final int START = (int) 1e9;
		final int STOP = START + 10000;
		
		long start, end;
		
		start = System.nanoTime();
		for(int i = START; i < STOP; i ++) {
			for(int j = START; j < STOP; j++) {
				gcd(i, j);
			}
		}
		System.out.println(System.nanoTime() - start);
		
		
		start = System.nanoTime();
		for(int i = START; i < STOP; i ++) {
			for(int j = START; j < STOP; j++) {
				gcdIterative(i, j);
			}
		}
		System.out.println(System.nanoTime() - start);
		
		
		start = System.nanoTime();
		for(int i = START; i < STOP; i ++) {
			for(int j = START; j < STOP; j++) {
				gcd(i, j);
			}
		}
		System.out.println(System.nanoTime() - start);
		
		
		start = System.nanoTime();
		for(int i = START; i < STOP; i ++) {
			for(int j = START; j < STOP; j++) {
				gcdIterative(i, j);
			}
		}
		System.out.println(System.nanoTime() - start);
	}
	
	
	
}
