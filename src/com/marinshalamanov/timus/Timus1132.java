package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.HashMap;

public class Timus1132 {
	
	static long getRem(int x, int pow, int mod) {
		long res = 1;
		while (pow != 0) {
			if((pow&1)==1) {
				res = (res*x)%mod;
			}
			x = (x*x)%mod;
			pow /= 2;
		}
		
		return res;
	}
	
	public static void main(String[] args) throws IOException {
		//Scanner input = new Scanner(System.in);
		StreamTokenizer  in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		
		HashMap<Short, HashMap<Short, Short>> mem = new HashMap<Short, HashMap<Short, Short>>();
		
		in.nextToken();
		int k = (int) in.nval;
		for (int i = 0; i < k; i++) {
			int a, n;
			
			in.nextToken();
			a = (int)in.nval;
			
			in.nextToken();
			n = (int)in.nval;
			
			int power = (n-1)/2;
			int rem = (int) getRem(a, power, n);
			//System.out.println("Rem " + rem );
			if (rem == 1) {
				HashMap<Short, Short> cache = mem.get(n);
				if (cache == null) {
					cache = new HashMap<Short, Short>();
					for(long j = 1 + n/2; j >= 0; j--) {
						short currRem = (short)((j*j) % n);
						//System.out.println(currRem + " " + j);
						cache.put(currRem, (short)j);
					}
					mem.put((short)n, cache);
				}
				
				Short sol = cache.get((short)a);
				if(sol != null) {
					out.print(sol);
					out.print(' ');
					out.print(n-sol);
					out.println();
				} else {
					out.print("No root\n");
				}
				
			} else {
				out.print("No root\n");
			}
		}
		out.flush();		
	    
	}
}
