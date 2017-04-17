package com.marinshalamanov.codeforces.codeforces409;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class E {
	
	public void solve(InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        HashSet<Integer> tabu = new HashSet<>();
        
        for(int i = 0; i < n; i++) {
        	tabu.add(in.nextInt());
        }
        
        HashSet<Integer> nods[] = new HashSet[m+1];
        
        int maxlen[] = new int[m+1];
        int maxlenFrom[] = new int[m+1];
        
        nods[m] = new HashSet<>();
        for(int i = 0; i < m; i++) {
        	nods[i] = new HashSet<>();
        	if(!tabu.contains(i)) {
	        	if(i==0) nods[m].add(0);
	        	else nods[gcd(i, m)].add(i);
        	}
        }
        
        int maxmaxlen = 0;
        int maxmaxlenAt = -1;
        
        EratostenPrimes primes = new EratostenPrimes(201000);
        
        for(int i = 1; i <= m; i++) {
        	maxlen[i] = nods[i].size();
        }
        
        for(int i = 1; i <= m; i++) {	
        	for (int pi = 0; i*primes.primes.get(pi) <= m; pi++) {
        		int currPrime = primes.primes.get(pi);
//        		if(i % currPrime == 0) {
        		
        		int nexti = i*currPrime;
				if (maxlen[nexti] < nods[nexti].size() + maxlen[i]) {
					maxlen[nexti] = nods[nexti].size() + maxlen[i];
					maxlenFrom[nexti] = i;
				}
//        		}
        	}
        	
        	if(maxlen[i] > maxmaxlen) {
        		maxmaxlen = maxlen[i];
        		maxmaxlenAt = i;
        	}
        }
        
        Stack<Integer> path = new Stack<>();
        int curr = maxmaxlenAt;
        while(curr > 0) {
        	path.add(curr);
        	curr = maxlenFrom[curr];
        }
        
        long last = 1;
        ArrayList<Integer> res = new ArrayList<>();
        
        
        while (!path.isEmpty()) {
        	curr = path.pop();
//        	System.out.println("doing " + curr);
        	
        	HashSet<Integer> toBeDone = nods[curr];
        	
//        	for(int kk : toBeDone) System.out.print(kk + " ");
//        	System.out.println();
        	
        	for(int next : toBeDone) {
//        		System.out.println("last is: " + last);
//        		System.out.println("doing: " + next);

        		int d = (int) gcd(m, last);
        		int test = (int) (((next*modInverse(last/d, m/d))/d)%m);
        		res.add(test);
        		last = (last*test)%m;
        		
//        		System.out.println("number is " + test);
        		if(test < 0) return;
//	        		if(toBeDone.contains((last*test)%m) ) {
//	        			
//	        			toBeDone.remove((last*test)%m);
//	        			last *= test;
//	        			test--;
//	        		}
	        	
        	}
        }
        
        System.out.println(res.size());
        for(int i = 0; i < res.size(); i++) {
        	System.out.print(res.get(i) + " ");
        }
        System.out.println();
        
    }
	
	 class EratostenPrimes {
			
			private boolean sieve[];
			private int n;
			
			private List<Integer> primes = new ArrayList<>();
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
						primes.add(i);
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

	
	// Returns modulo inverse of a with respect to m using
		// extended Euclid Algorithm
		// Assumption: a and m are coprimes, i.e., gcd(a, m) = 1
		public static long modInverse(long a, long m)
		{
		    long m0 = m, t, q;
		    long x0 = 0, x1 = 1;
		 
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
		

	
	public static int gcd(int a, int b) {
		return (a==0)?(b):(gcd(b%a, a)); 
	}
	
	public static long gcd(long a, long b) {
		return (a==0)?(b):(gcd(b%a, a)); 
	}
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        E solver = new E();
        solver.solve(in, out);
        out.close();
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
        
        public long nextLong() {
            return Long.parseLong(next());
        }
        
        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
