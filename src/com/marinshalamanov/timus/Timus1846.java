package com.marinshalamanov.timus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Stack;

public class Timus1846 {
	public static void main(String[] args) throws IOException {
		new Timus1846().solve();
	}

	int tmp;

	int gcd(int a, int b) {
		while (b != 0) {
			tmp = a;
			a = b;
			b = tmp % b;
		}

		return a;
	}

	final int MAXQ = 1 << 17;

	int[] arr = new int[MAXQ * 2];

	HashMap<Integer, Stack<Integer>> pos = new HashMap<Integer, Stack<Integer>>();

	void update(int i, int d) {
		arr[i] = d;

		while (i != 0) {
			i /= 2;
			arr[i] = gcd(arr[2 * i], arr[2 * i + 1]);
			// if() break;
		}
	}

	void solve() throws IOException {
		StreamTokenizer  in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		
	    int q;
	    in.nextToken();
	    q = (int)in.nval;

	    char c;
	    int n;

	    int idx=MAXQ;
	    in.ordinaryChar('+');
	    in.ordinaryChar('-');
	    
	    while(q-->0) {
	    	c = (char)in.nextToken();
	    	
	    	in.nextToken();
	        n = (int)in.nval;

	        if(c=='+') {
	        	if(!pos.containsKey(n)) {
	        		pos.put(n, new Stack<Integer>());
	        	}

	            pos.get(n).push(idx);

	            update(idx++, n);

	        } else {
	            int currPos = pos.get(n).pop();

	            update(currPos, 0);
	        }

	        out.print(arr[1]!=0?arr[1]:1);
	        out.println();
	    }

		out.flush();
	}
}
