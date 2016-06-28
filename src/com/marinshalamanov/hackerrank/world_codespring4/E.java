package com.marinshalamanov.hackerrank.world_codespring4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class E {
	
	int n;
	String a, b;
	String ar, br;
	
	
//	HashMap<Long, Set<Long> > set = new HashMap<>();
	HashSet<Long> set = new HashSet<>();
	
	long mod = 1000000007;
	long p[] = {31, 37};
	
	long pPow[][] = new long[600*4 + 1][2];
//	long qPow[] = new long[600*4 + 1];
	long hash[];
	
	String a1;
	
	void add(long hashp, long hashq) {
		
		set.add(hashp*mod + hashq);
//		set.add(hashp);
		
//		if(set.containsKey(hashp)) {
//			set.get(hashp).add(hashq);
//		} else {
//			HashSet<Long> s = new HashSet<>();
//			s.add(hashq);
//			set.put(hashp, s);
//		}
	}
	
//	void machkai() {
//		long hash = 0;
//		long hashq = 0;
//    	for(int j = 0; j < 2*n; j++) {
//			hash = (hash + (((a1.charAt(j) - 'a')*pPow[2*n-j-1])%mod)   ) %mod;
//			hashq = (hashq + (((a1.charAt(j) - 'a')*qPow[2*n-j-1])%mod)   ) %mod;
//		}
//    	
//    	add(hash, hashq);
//    	
//    	for(int i = 1; i < 2*n; i++) {
//			hash = (hash + mod - (((a1.charAt(i-1) - 'a')*pPow[2*n-1])%mod) )%mod;
//			hash = ((hash*p)%mod + (a1.charAt(i+2*n-1)-'a') ) %mod;
//			
//			hashq = (hashq + mod - (((a1.charAt(i-1) - 'a')*qPow[2*n-1])%mod) )%mod;
//			hashq = ((hashq*q)%mod + (a1.charAt(i+2*n-1)-'a') ) %mod;
//			
//			add(hash, hashq);
//    	}
//	}
//	
	long aRightToMiddle[][] = new long[602][2];
	long aLeftToMiddle[][] = new long[602][2];
	long aMiddleToRight[][] = new long[602][2];
	long aMiddleToLeft[][] = new long[602][2];
	long bRightToMiddle[][] = new long[602][2];
	long bLeftToMiddle[][] = new long[602][2];
	long bMiddleToRight[][] = new long[602][2];
	long bMiddleToLeft[][] = new long[602][2];
	
	
	void machkaiGooo() {
		aRightToMiddle = new long[602][2];
    	aLeftToMiddle = new long[602][2];
    	aMiddleToRight = new long[602][2];
    	aMiddleToLeft = new long[602][2];
    	bRightToMiddle = new long[602][2];
    	bLeftToMiddle = new long[602][2];
    	bMiddleToRight = new long[602][2];
    	bMiddleToLeft = new long[602][2];
    	
    	for(int h = 0; h < 2; h++) {
	    	aRightToMiddle[n-1][h] = a.charAt(n-1)-'a';
	        for (int i = n-2; i >= 0; i--) aRightToMiddle[i][h] = ((aRightToMiddle[i+1][h]*p[h])%mod + (a.charAt(i)-'a'))%mod;
	        
	        bRightToMiddle[n-1][h] = b.charAt(n-1)-'a';
	        for (int i = n-2; i >= 0; i--) bRightToMiddle[i][h] = ((bRightToMiddle[i+1][h]*p[h])%mod + (b.charAt(i)-'a'))%mod;
	        
	        aLeftToMiddle[0][h] = a.charAt(0) - 'a';
	        for(int i = 1; i < n; i++) aLeftToMiddle[i][h] = ((aLeftToMiddle[i-1][h]*p[h])%mod + (a.charAt(i)-'a'))%mod;
	        
	        bLeftToMiddle[0][h] = b.charAt(0) - 'a';
	        for(int i = 1; i < n; i++) bLeftToMiddle[i][h] = ((bLeftToMiddle[i-1][h]*p[h])%mod + (b.charAt(i)-'a'))%mod;
	        
	        aMiddleToRight[n-1][h] = a.charAt(n-1)-'a';
	        for (int i = n-2; i >= 0; i--) aMiddleToRight[i][h] = (aMiddleToRight[i+1][h] + ((a.charAt(i)-'a')*pPow[(n-1) - i][h])%mod ) % mod;
	        
	        bMiddleToRight[n-1][h] = b.charAt(n-1)-'a';
	        for (int i = n-2; i >= 0; i--) bMiddleToRight[i][h] = (bMiddleToRight[i+1][h] + ((b.charAt(i)-'a')*pPow[(n-1) - i][h])%mod ) % mod;
	        
	        aMiddleToLeft[0][h] = a.charAt(0)-'a';
	        for (int i = 1; i < n; i++) aMiddleToLeft[i][h] = (aMiddleToLeft[i-1][h] + ((a.charAt(i)-'a')*pPow[i][h])%mod ) % mod;
	        
	        bMiddleToLeft[0][h] = b.charAt(0)-'a';
	        for (int i = 1; i < n; i++) bMiddleToLeft[i][h] = (bMiddleToLeft[i-1][h] + ((b.charAt(i)-'a')*pPow[i][h])%mod ) % mod;
    	}
    	
    	
    	long snakeHash[], leftHash[], rightHash[], hash[];
    	
    	for (int left = 0; left < n; left++) {
    		leftHash = new long[]{0, 0};
    		if(left > 0) {
    			for(int h = 0; h < 2; h++)
    				leftHash[h] = ((aMiddleToLeft[left-1][h]*pPow[left][h])%mod + bLeftToMiddle[left-1][h])%mod;
    		}
    		int pow = 2*n-1 - 2*left + 1;
    		
    		for(int h = 0; h < 2; h++)
    			leftHash[h] = (leftHash[h]*pPow[pow][h])%mod;
    		
    		snakeHash = new long[]{0, 0};
    		
    		for(int right = left; right <= n; right++) {
    			int snakel = right - left;
    			
    			rightHash = new long[]{0, 0};
    			
    			if(right < n) {
    				for(int h = 0; h < 2; h++)
	    				if(snakel % 2 == 0) {
	    					rightHash[h] = ((bMiddleToRight[right][h]*pPow[n-right][h])%mod + aRightToMiddle[right][h])%mod;
	    				} else {
	    					rightHash[h] = ((aMiddleToRight[right][h]*pPow[n-right][h])%mod + bRightToMiddle[right][h])%mod;
	    				}
    			}
    			
    			hash = new long[2];
    			for(int h = 0; h < 2; h++)
    				hash[h] = (leftHash[h]+ snakeHash[h] + rightHash[h])%mod;
//    			System.out.println(left + " " + right + " " + set.size() + " " + hash + " "); //+ map.get(hash));
    			add(hash[0], hash[1]);
    			
    			if(right < n) {
    				for(int h = 0; h < 2; h++)
		    			if(snakel % 2 == 0) {
		    				snakeHash[h] = (snakeHash[h] + ((b.charAt(right)-'a')*pPow[pow-1][h])%mod + ((a.charAt(right)-'a')*pPow[pow-2][h])%mod )%mod;
		    			} else {
		    				snakeHash[h] = (snakeHash[h] + ((a.charAt(right)-'a')*pPow[pow-1][h])%mod + ((b.charAt(right)-'a')*pPow[pow-2][h])%mod )%mod;
		    			}
    				
    				pow -= 2;
    			}
    		}
    	}
	}
	
	
	public void solve(InputReader in, PrintWriter out) {
		for(int h = 0; h < 2; h++) {
			pPow[0][h] = 1;
	        for(int i = 1; i < pPow.length; i++) {
	        	pPow[i][h] = (pPow[i-1][h]*p[h]) % mod;
	        }
		}
		
		int tt = in.nextInt();
        while(tt-- > 0) {
        	n = in.nextInt();
        	a = in.next();
        	b = in.next();
        	ar = new StringBuilder(a).reverse().toString();
        	br = new StringBuilder(b).reverse().toString();

        	set.clear();
        	
        	
        	machkaiGooo();
        	String _temp = a;
        	a = b;
        	b = _temp;
        	
//        	System.out.println("swap");
        	
        	machkaiGooo();
        	
//        	System.out.println("rev");
        	
        	a = ar; 
        	b = br;
        	
        	machkaiGooo();
        	
        	
//        	System.out.println("swap");
        	_temp = a;
        	a = b;
        	b = _temp;
        	
        	machkaiGooo();
        	

        	
//        	int tot = 0;
//        	for(Set<Long> s : set.values()) {
//        		tot += s.size();
//        	}
//        	
//        	System.out.println(tot);
        	
        	System.out.println(set.size());
        }
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
