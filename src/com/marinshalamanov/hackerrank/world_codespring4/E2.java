package com.marinshalamanov.hackerrank.world_codespring4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class E2 {
	
	int n;
	String a, b;
	String ar, br;
	
	
	//HashMap<Long, Set<Long> > set = new HashMap<>();
	HashSet<Long> set = new HashSet<>();
	
	long mod = 1000000007;
	long p = 31;
	long q = 37;
	
	long pPow[] = new long[600*4 + 1];
	long qPow[] = new long[600*4 + 1];
	long hash[];
	
	String a1;
	
	void add(long hashp, long hashq) {
		
		set.add(hashp);
		
//		if(set.containsKey(hashp)) {
//			set.get(hashp).add(hashq);
//		} else {
//			HashSet<Long> s = new HashSet<>();
//			s.add(hashq);
//			set.put(hashp, s);
//		}
	}
	
	void machkai() {
		long hash = 0;
		long hashq = 0;
    	for(int j = 0; j < 2*n; j++) {
			hash = (hash + (((a1.charAt(j) - 'a')*pPow[2*n-j-1])%mod)   ) %mod;
			hashq = (hashq + (((a1.charAt(j) - 'a')*qPow[2*n-j-1])%mod)   ) %mod;
		}
    	
    	add(hash, hashq);
    	
    	for(int i = 1; i < 2*n; i++) {
			hash = (hash + mod - (((a1.charAt(i-1) - 'a')*pPow[2*n-1])%mod) )%mod;
			hash = ((hash*p)%mod + (a1.charAt(i+2*n-1)-'a') ) %mod;
			
			hashq = (hashq + mod - (((a1.charAt(i-1) - 'a')*qPow[2*n-1])%mod) )%mod;
			hashq = ((hashq*q)%mod + (a1.charAt(i+2*n-1)-'a') ) %mod;
			
			add(hash, hashq);
    	}
	}
	
	long aRightToMiddle[] = new long[602];
	long aLeftToMiddle[] = new long[602];
	long aMiddleToRight[] = new long[602];
	long aMiddleToLeft[] = new long[602];
	long bRightToMiddle[] = new long[602];
	long bLeftToMiddle[] = new long[602];
	long bMiddleToRight[] = new long[602];
	long bMiddleToLeft[] = new long[602];
	
	public void solve(InputReader in, PrintWriter out) {
		pPow[0] = 1;
        for(int i = 1; i < pPow.length; i++) {
        	pPow[i] = (pPow[i-1]*p) % mod;
        }
        
        qPow[0] = 1;
        for(int i = 1; i < qPow.length; i++) {
        	qPow[i] = (qPow[i-1]*q) % mod;
        }
        
        
//        hash = new long[1+n*4];
		
		int tt = in.nextInt();
        while(tt-- > 0) {
        	n = in.nextInt();
        	a = in.next();
        	b = in.next();
        	ar = new StringBuilder(a).reverse().toString();
        	br = new StringBuilder(b).reverse().toString();

        	
        	aRightToMiddle = new long[602];
        	aLeftToMiddle = new long[602];
        	aMiddleToRight = new long[602];
        	aMiddleToLeft = new long[602];
        	bRightToMiddle = new long[602];
        	bLeftToMiddle = new long[602];
        	bMiddleToRight = new long[602];
        	bMiddleToLeft = new long[602];
        	
        	aRightToMiddle[n-1] = a.charAt(n-1)-'a';
            for (int i = n-2; i >= 0; i--) aRightToMiddle[i] = ((aRightToMiddle[i+1]*p)%mod + (a.charAt(i)-'a'))%mod;
            
            bRightToMiddle[n-1] = b.charAt(n-1)-'a';
            for (int i = n-2; i >= 0; i--) bRightToMiddle[i] = ((bRightToMiddle[i+1]*p)%mod + (b.charAt(i)-'a'))%mod;
            
            aLeftToMiddle[0] = a.charAt(0) - 'a';
            for(int i = 1; i < n; i++) aLeftToMiddle[i] = ((aLeftToMiddle[i-1]*p)%mod + (a.charAt(i)-'a'))%mod;
            
            bLeftToMiddle[0] = b.charAt(0) - 'a';
            for(int i = 1; i < n; i++) bLeftToMiddle[i] = ((bLeftToMiddle[i-1]*p)%mod + (b.charAt(i)-'a'))%mod;
            
            aMiddleToRight[n-1] = a.charAt(n-1)-'a';
            for (int i = n-2; i >= 0; i--) aMiddleToRight[i] = (aMiddleToRight[i+1] + ((a.charAt(i)-'a')*pPow[(n-1) - i])%mod ) % mod;
            
            bMiddleToRight[n-1] = b.charAt(n-1)-'a';
            for (int i = n-2; i >= 0; i--) bMiddleToRight[i] = (bMiddleToRight[i+1] + ((b.charAt(i)-'a')*pPow[(n-1) - i])%mod ) % mod;
            
            aMiddleToLeft[0] = a.charAt(0)-'a';
            for (int i = 1; i < n; i++) aMiddleToLeft[i] = (aMiddleToLeft[i-1] + ((a.charAt(i)-'a')*pPow[i])%mod ) % mod;
            
            bMiddleToLeft[0] = b.charAt(0)-'a';
            for (int i = 1; i < n; i++) bMiddleToLeft[i] = (bMiddleToLeft[i-1] + ((b.charAt(i)-'a')*pPow[i])%mod ) % mod;
            
        	set.clear();

        	a1 = a + br + a + br;
        	machkai();

        	a1 = ar + b + ar + b;
        	machkai();
        	
        	int n2 = 2*n;
        	
//        	System.out.println(set.size());
        	// start form (0, 0)
        	long hash = 0;
        	long hashq = 0;
        	long _hash = 0;
        	
        	long hashrev = 0;
        	
        	for(int i = 0; i < n2; i++) {
        		char c  = 'a';
        		if(i%4 == 0) {
        			int idx = (i/4)*2 + 0;
        			c = a.charAt(idx);
        			
        			_hash = (hash + (aMiddleToRight[idx]*pPow[n-idx])%mod + bRightToMiddle[idx])%mod;
        			add(_hash, _hash);
        			
        			_hash = (hashrev + (aRightToMiddle[idx]*pPow[i])%mod + (bMiddleToRight[idx]*pPow[n - idx + i])%mod )%mod;
        			add(_hash, _hash);
        		}
        		
        		if(i%4 == 3) {
        			c = a.charAt((i/4)*2 + 1);
        		}
        		
        		if(i%4 == 1) {
        			c = b.charAt((i/4)*2 + 0);
        		}
        		
        		if(i%4 == 2) {
        			int idx = (i/4)*2 + 1;
        			c = b.charAt(idx);
        			_hash = (hash + (bMiddleToRight[idx]*pPow[n-idx])%mod + aRightToMiddle[idx])%mod;
        			add(_hash, _hash);
        			
        			_hash = (hashrev + (bRightToMiddle[idx]*pPow[i])%mod + (aMiddleToRight[idx]*pPow[n - idx + i])%mod)%mod;
        			add(_hash, _hash);
        		}
        		
        		hash = (hash + (((c - 'a')*pPow[n2-i-1])%mod)   ) %mod;
        		hashq = (hashq + (((c - 'a')*qPow[n2-i-1])%mod)   ) %mod;
        		
        		hashrev = (hashrev + (((c - 'a')*pPow[i])%mod)   ) %mod;
        	}
        	add(hash, hashq);
        	
        	// start form (1, 0)
        	hash = 0;
        	hashq = 0;
        	hashrev = 0;
        	for(int i = 0; i < n2; i++) {
        		char c  = 'a';
        		if(i%4 == 0) {
        			int idx = (i/4)*2 + 0;
        			c = b.charAt(idx);
        			
//        			System.out.println(set.size());
        			_hash = (hash + (bMiddleToRight[idx]*pPow[n-idx])%mod + aRightToMiddle[idx])%mod;
        			add(_hash, _hash);
//        			System.out.println(set.size() + " " + i + " " + _hash );
        			
        			_hash = (hashrev + (bRightToMiddle[idx]*pPow[i])%mod + (aMiddleToRight[idx]*pPow[n - idx + i])%mod )%mod;
        			add(_hash, _hash);
        		}
        		if(i%4 == 3) {
        			c = b.charAt((i/4)*2 + 1);
        		}
        		
        		if(i%4 == 1) {
        			c = a.charAt((i/4)*2 + 0);
        		}
        		
        		if(i%4 == 2) {
        			int idx = (i/4)*2 + 1;
        			c = a.charAt(idx);
        			
        			_hash = (hash + (aMiddleToRight[idx]*pPow[n-idx])%mod + bRightToMiddle[idx])%mod;
        			add(_hash, _hash);
        			
        			_hash = (hashrev + (aRightToMiddle[idx]*pPow[i])%mod + (bMiddleToRight[idx]*pPow[n - idx + i])%mod )%mod;
        			add(_hash, _hash);
        		}
        		
        		hash = (hash + (((c - 'a')*pPow[n2-i-1])%mod)   ) %mod;
        		hashq = (hashq + (((c - 'a')*qPow[n2-i-1])%mod)   ) %mod;
        		
        		hashrev = (hashrev + (((c - 'a')*pPow[i])%mod)   ) %mod;
        	}
        	add(hash, hashq);
        	
        	// start from (0, n-1)
        	hash = 0;
        	hashq = 0;
        	hashrev = 0;
        	for(int i = 0; i < n2; i++) {
        		char c  = 'a';
        		if(i%4 == 0) {
        			int idx = (n-1) - ((i/4)*2 + 0);
        			c = a.charAt(idx);
        			
        			_hash = (hash + (aMiddleToLeft[idx]*pPow[idx+1])%mod + bLeftToMiddle[idx])%mod;
        			add(_hash, _hash);
        			
        			_hash = (hashrev + (aLeftToMiddle[idx]*pPow[i])%mod + (bMiddleToLeft[idx]*pPow[i + idx + 1])%mod )%mod;
        			add(_hash, _hash);
        		}
        		if(i%4 == 3) {
        			c = a.charAt((n-1) - ((i/4)*2 + 1));
        		}
        		
        		if(i%4 == 1) {
        			c = b.charAt((n-1) - ((i/4)*2 + 0));
        		}
        		
        		if(i%4 == 2) {
        			int idx = (n-1) - ((i/4)*2 + 1);
        			c = b.charAt(idx);
        			
        			_hash = (hash + (bMiddleToLeft[idx]*pPow[idx+1])%mod + aLeftToMiddle[idx])%mod;
        			add(_hash, _hash);
        			
        			_hash = (hashrev + (bLeftToMiddle[idx]*pPow[i])%mod + (aMiddleToLeft[idx]*pPow[i + idx + 1])%mod )%mod;
        			add(_hash, _hash);
        		}
        		
        		hash = (hash + (((c - 'a')*pPow[n2-i-1])%mod)   ) %mod;
        		hashq = (hashq + (((c - 'a')*qPow[n2-i-1])%mod)   ) %mod;
        		
        		hashrev = (hashrev + (((c - 'a')*pPow[i])%mod)   ) %mod;
        	}
        	add(hash, hashq);
        	
        	// start from (1, n-1)
        	hash = 0;
        	hashq = 0;
        	hashrev = 0;
        	for(int i = 0; i < n2; i++) {
        		char c  = 'a';
        		if(i%4 == 0) {
        			int idx = (n-1) - ((i/4)*2 + 0);
        			c = b.charAt(idx);
        			
        			_hash = (hash + (bMiddleToLeft[idx]*pPow[idx+1])%mod + aLeftToMiddle[idx])%mod;
        			add(_hash, _hash);
        			
        			_hash = (hashrev + (bLeftToMiddle[idx]*pPow[i])%mod + (aMiddleToLeft[idx]*pPow[i + idx + 1])%mod )%mod;
        			add(_hash, _hash);
        		}
        		if(i%4 == 3) {
        			c = b.charAt((n-1) - ((i/4)*2 + 1));
        		}
        		
        		if(i%4 == 1) {
        			c = a .charAt((n-1) - ((i/4)*2 + 0));
        		}
        		
        		if(i%4 == 2) {
        			int idx = (n-1) - ((i/4)*2 + 1);
        			c = a.charAt(idx);
        			
        			_hash = (hash + (aMiddleToLeft[idx]*pPow[idx+1])%mod + bLeftToMiddle[idx])%mod;
        			add(_hash, _hash);
        			
        			_hash = (hashrev + (aLeftToMiddle[idx]*pPow[i])%mod + (bMiddleToLeft[idx]*pPow[i + idx + 1])%mod )%mod;
        			add(_hash, _hash);
        		}
        		
        		hash = (hash + (((c - 'a')*pPow[n2-i-1])%mod)   ) %mod;
        		hashq = (hashq + (((c - 'a')*qPow[n2-i-1])%mod)   ) %mod;
        		
        		hashrev = (hashrev + (((c - 'a')*pPow[i])%mod)   ) %mod;
        	}
        	add(hash, hashq);
        	
        	
        	int tot = set.size();
//        	for(Set<Long> s : set.values()) {
//        		tot += s.size();
//        	}
        	
        	
        	System.out.println(tot);
        }
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        E2 solver = new E2();
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
