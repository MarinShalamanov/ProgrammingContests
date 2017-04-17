package com.marinshalamanov.sdk.math;

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
	
	public static long powMod(long a, long pow, long mod) {
		long res = 1;
		long p = a;
		while(pow > 0) {
			if((pow & 1 ) != 0) {
				res = (res *p) % mod;
			}
			
			p = (p*p)%mod;
			pow >>= 1;			
		}
		
		return res;
	}
	
	// Returns modulo inverse of a with respect to m using
	// extended Euclid Algorithm
	// Assumption: a and m are coprimes, i.e., gcd(a, m) = 1
	public static int modInverse(int a, int m)
	{
	    int m0 = m, t, q;
	    int x0 = 0, x1 = 1;
	 
	    if (m == 1)
	      return 0;
	 
	    while (a > 1)
	    {
	        // q is quotient
	        q = a / m;
	 
	        t = m;
	 
	        // m is remainder now, process same as
	        // Euclid's algo
	        m = a % m;
	        a = t;
	 
	        t = x0;
	 
	        x0 = x1 - q * x0;
	 
	        x1 = t;
	    }
	 
	    // Make x1 positive
	    if (x1 < 0)
	       x1 += m0;
	 
	    return x1;
	}
	
	
	
	/**
	 *
	 * Computes the geometric series 1 + a^1 + a^2 + .. + a ^ n modulo m
	 * 
	 * Complexity O(log n)
	 * 
	 */
	public static void geometricSeriesSumMod() {
		// TODO: 

		// Not sure but this code should do the job
		
		/*
		 * Based on the approach of @braindoper a complete algorithm which calculates

			1 + a + a^2 + ... +a^n mod m
			looks like this in Mathematica:
			
			geometricSeriesMod[a_, n_, m_] := 
			   Module[ {q = a, exp = n, factor = 1, sum = 0, temp},
			
			   While[And[exp > 0, q != 0],
			     If[EvenQ[exp],
			       temp = Mod[factor*PowerMod[q, exp, m], m];
			       sum = Mod[sum + temp, m];
			       exp--];
			     factor = Mod[Mod[1 + q, m]*factor, m];
			     q = Mod[q*q, m];
			     exp = Floor[ exp /2];
			   ];
			
			   Return [Mod[sum + factor, m]]
			]
			
			source: http://stackoverflow.com/questions/1522825/calculating-sum-of-geometric-series-mod-m
		 */
		
		// OR use the formula
		// note a modular inverse should be used for the division
		// which can be computed in O(log m) if m is prime
		// still the first approach should word faster assuming n < m
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
