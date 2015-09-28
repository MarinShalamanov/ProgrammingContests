package com.marinshalamanov.timus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Timus1316 {

	
	public static void main(String[] args) throws IOException {
		//Scanner input = new Scanner(System.in);
		StreamTokenizer  in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		
		

		
		final int MAXN = 1000005;
	    final double EPS = 1e-3;
	
	    int[] bids = new int[2*MAXN];
	    int bidsCount = 0;
			
	    long total = 0;
	    
	    while(true) {
	    	in.nextToken();
	    	char firstLetter = in.sval.charAt(0);
	    	
	    	if (firstLetter == 'Q') {
	    		break;
	    	}
	    	
	    	in.nextToken();
	    	double priceD = in.nval;
	    	int price = (int) Math.floor(priceD*100 + 0.5);
	    	price = 2*price + 1;
	    	
	    	if(firstLetter == 'B') {
	    		bidsCount++;
	    		while(price < 2*MAXN) {
	    			bids[price]++;
	    			price += price&-price;
	    		}
	    	} else if(firstLetter == 'D') {
	    		bidsCount--;
	    		while(price < 2*MAXN) {
	    			bids[price]--;
	    			price += price&-price;
	    		}
	    	} else if(firstLetter == 'S') {
	    		price -= 2;
	    		int sum = 0;
	    		while(price > 0) {
	    			sum += bids[price];
	    			price -= price&-price;
	    		}
	    		in.nextToken();
	    		total += Math.min((int)in.nval, bidsCount - sum);
	    		//System.out.println(total);
	    	} else {
	    		break;
	    	}
	    }
	    
	    //System.out.println();
	    System.out.println(String.format("%.2f", total/100.0 + EPS));
	    
	}
}
