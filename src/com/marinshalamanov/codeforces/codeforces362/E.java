package com.marinshalamanov.codeforces.codeforces362;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class E {
	
	long k, a[];
	final long mod = (long) (1e9 + 7);
	
	public void solve(InputReader in, PrintWriter out) {
        
		k = in.nextInt();
		a = new long[(int) k];
		for(int i = 0; i < k; i++) a[i] = in.nextLong();
		
		Matrix m = new Matrix(2, 2);
		m.a = new long[][] {{1, 1}, {1, 0}};
		
		Matrix p = new Matrix(2, 2);
		p.a = new long[][] {{1, 1}, {2, 0}};
		
	
		int power = 1;
		for(int i = 0; i < k; i++)
			power *= (a[i] % (mod-1));
		
//		for(int i = 0; i < k; i++)
//			p =  p.pow(a[i]);
//		
//		m = m.mult(p);
//		
//		long nom = ((m.a[0][1] - m.a[1][1] + mod) % mod);
//		if(nom%2 == 0) nom /= 2;
//		else nom = ((nom + mod)/2 ) %mod;
		
		long denom = 2;
		for(int i = 0; i < k; i++)
			denom = powMod(denom, a[i], mod);
		
		if(denom%2 == 0) denom /= 2;
		else denom = ((denom + mod)/2 ) %mod;
		
		boolean nChetno = false;
		for(int i = 0; i < k; i++) {
			if(a[i]%2==0){
				nChetno = true;
			}
		}
		long nom;
		if(nChetno) {
			nom = (denom+1)%mod;
		} else {
			nom = (denom-1 + mod) % mod;
		}
		
//		System.out.println(nom);
		
		if(nom%3 == 0) nom /= 3;
		else if((nom + mod)%3 == 0) nom = (nom + mod)/3;
		else if((nom + mod + mod)%3 == 0) nom = (nom + mod + mod)/3;
		
		System.out.println(nom + "/" + denom);
		
    }
	
	long powMod(long a, long pow, long mod) {
		long res = 1;
		long p = a;
		while(pow > 0) {
			if((pow & 1L ) != 0) {
				res = (res *p) % mod;
			}
			
			p = (p*p)%mod;
			pow >>= 1;			
		}
		
		return res;
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

    public class Matrix {
    	
    	int n, m;
    	long a[][];
    	
    	public Matrix(int n, int m) {
    		this.n = n;
    		this.m = m;
    		a = new long[n][m];
    	}
    	
    	public Matrix(Matrix m) {
    		this.n = m.n;
    		this.m = m.m;
    		a = new long[n][this.m];
    		for(int i = 0; i < n; i++) {
    			for(int j = 0; j < this.m; j++) {
    				a[i][j] = m.a[i][j];
    			}
    		}
    	}
    	
//    	public void add(Matrix other) {
//    		for(int i = 0; i < n; i++) {
//    			for(int j = 0; j < m; j++) {
//    				a[i][j] += other.a[i][j];
//    			}
//    		}
//    	}
    	
//    	public Matrix sum(Matrix other) {
//    		Matrix res = new Matrix(n, m);
//    		for(int i = 0; i < n; i++) {
//    			for(int j = 0; j < m; j++) {
//    				res.a[i][j] = a[i][j] + other.a[i][j];
//    			}
//    		}
//    		return res;
//    	}
//    	
    	public Matrix mult(Matrix o) {
    		Matrix res = new Matrix(n, o.m); 
    		for(int i = 0; i < res.n ; i++) {
    			for(int j = 0; j < res.m; j++) {
    				for(int k = 0; k < m; k++) {
    					res.a[i][j] = ( res.a[i][j] +  (a[i][k]*o.a[k][j])%mod )%mod;
    				}
    			}
    		}
    		return res;
    	}
    	
    	public Matrix pow(long exp) {
    		Matrix p = new Matrix(this);
    		
    		
    		Matrix res = new Matrix(n, m);
    		for(int i = 0; i < n; i++) {
    			res.a[i][i] = 1;
    		}
    		
    		while(exp > 0) {
    			if((exp & 1) != 0) {
    				res = res.mult(p);
    			}
    			
    			p = p.mult(p);
    			exp >>= 1;
    		}
    			
    		return res;
    	}
    	
    	
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
