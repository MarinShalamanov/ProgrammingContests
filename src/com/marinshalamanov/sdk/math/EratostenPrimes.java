package com.marinshalamanov.sdk.math;

public class EratostenPrimes {
	
	private boolean sieve[];
	private int n;
	
	/**
	 * Computes the primes <b> less than </b> upTo
	 * @param upTo 
	 */
	public EratostenPrimes(int upTo) {
		n = upTo;
		sieve = new boolean[upTo];
		computePrimes();
	}
	
	public void computePrimes() {
		sieve[0] = true;
		sieve[1] = true;
		
		for(int i = 2; i < n; i++) {
			if (!sieve[i]) {
				for (int j = 2*i; j < n; j += i) {
					sieve[j] = true;
				}
			}
		}
	}
	
	public boolean isPrime(int number) {
		return sieve[number] == false;
	}
	
	public boolean[] getSieve() {
		return sieve;
	}
}
