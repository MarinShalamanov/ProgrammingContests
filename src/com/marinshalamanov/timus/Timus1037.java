package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Timus1037 {
	public static void main(String[] args) throws IOException {
		new Timus1037().solve();
	}
	
	final int T = 600;
	final int N = 30000;
	final int MAXRQ = 80001;

	char[] mem = new char[N+1];

	char[][] qu = new char[MAXRQ][2];
	int quFront = 0;
	int quBack = 0;
	
	void solve() throws IOException {
		BufferedReader  in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		
	    TreeSet<Character> empt = new TreeSet<Character>();
	    for(int i = 0 ; i < N; i++) empt.add((char) i);
	    
	    int t, n;
	    char operation;

	    while(true) {
	    	String line = in.readLine();
	    	if(line ==null)break;
	    	StringTokenizer st = new StringTokenizer(line);
	    	
	    	t = Integer.parseInt(st.nextToken());
	    	//System.out.println(t);
	    	
	    	operation = st.nextToken().charAt(0);
	    	//System.out.println("op="+operation);

	        while(quFront < quBack && qu[quFront][0] < t) {
	            if(mem[qu[quFront][1]] == qu[quFront][0] - T + 1) {
	                empt.add(qu[quFront][1]);
	            }
	            quFront++;
	        }

	        if(operation=='q') break;
	        else if(operation=='.') {
	        	n = Integer.parseInt(st.nextToken());
	        	//System.out.println("n=" + n);
	            n--;
	            if(mem[n] > 0 && mem[n]+T > t) {
	            	out.print("+\n");
	                mem[n] = (char) t;
	                qu[quBack][0] = (char) (t + T - 1);
	                qu[quBack][1] = (char) n;
	            } else {
	            	out.print("-\n");
	            }
	            quBack++;
	        } else if(operation=='+'){
	            int curr = empt.first();
	            
	            out.print(curr+1);
	            out.print("\n");

	            empt.remove((char)curr);
	            mem[curr] = (char) t;
	            qu[quBack][0] = (char) (t + T - 1);
	            qu[quBack][1] = (char) curr;
	            quBack++;
	        }
	    }

	    out.flush();
	}
}
