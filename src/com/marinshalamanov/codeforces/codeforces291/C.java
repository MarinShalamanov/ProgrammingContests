package codeforces291;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashSet;

public class C {

	public static void main(String[] args) throws IOException {
		new C();
	}

	final long p = (long) 1e9 + 7;
	final long q = (long) 1e9 + 103;

	int n, m;

	String[] mem;
	long[] memq, memp;
	
	HashSet<Long> pow3pset = new HashSet<Long>();
	HashSet<Long> pow3qset = new HashSet<Long>();
	

	long[] pow3p = new long[(int) 6e5];
	long[] pow3q = new long[(int) 6e5];

	{
		pow3p[0] = 1;
		pow3q[0] = 1;

		for (int i = 1; i < pow3p.length; i++) {
			pow3p[i] = (pow3p[i - 1] * 3) % p;
			pow3q[i] = (pow3q[i - 1] * 3) % q;
			
			pow3pset.add(pow3p[i]);
			pow3qset.add(pow3q[i]);
		}
	}

	long getHashP(String str) {
		long hash = 0;
		for(int i = 0; i < str.length(); i++) {
			hash = (hash + (str.charAt(i)*pow3p[i])%p)%p;
		}
		return hash;
	}
	
	long getHashQ(String str) {
		long hash = 0;
		for(int i = 0; i < str.length(); i++) {
			hash = (hash + (str.charAt(i)*pow3q[i])%q)%q;
		}
		return hash;
	}
	
	boolean check(String curr) {
		long hashp = getHashP(curr);
		long hashq= getHashQ(curr);
		
		
		for(int i = 0; i < m; i++) {
			long dp = (memp[i] + p - hashp) % p;
			
			
		}

		return false;
	}

	public C() throws IOException {
		StreamTokenizer in = new StreamTokenizer(new BufferedReader(
				new InputStreamReader(System.in)));

		in.nextToken();
		n = (int) in.nval;
		in.nextToken();
		m = (int) in.nval;

		mem = new String[n];
		memq = new long[n];
		memp = new long[n];

		for (int i = 0; i < n; i++) {
			in.nextToken();
			mem[i] = in.sval;
			memq[i] = getHashQ(mem[i]);
			memp[i] = getHashP(mem[i]);
		}

		String curr;
		for (int q = 0; q < m; q++) {
			in.nextToken();
			curr = in.sval;
			if (check(curr)) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}

	}

}
