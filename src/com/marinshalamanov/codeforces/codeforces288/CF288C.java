package com.marinshalamanov.codeforces.codeforces288;



import java.util.Scanner;


public class CF288C {
	public static void main(String[] args) {
	    new CF288C();
	}
	
	Scanner in = new Scanner(System.in);
	
	int m, t, r;
	int[] w = new int[305];
	
	int[] gostTimeline = new int[400];
	boolean[] lightTimeline = new boolean[605];
	final int OFFSET = 300;
	
	boolean lightTimeline(int i) {
		return lightTimeline[i+OFFSET];
	}

	void lightTimeline(int i, boolean v) {
		lightTimeline[i+OFFSET] = v;
	}
	
	
	public CF288C() {
	    m = in.nextInt();
	    t = in.nextInt();
	    r = in.nextInt();
	    for(int i = 0; i < m; i++) {
	    	w[i] = in.nextInt();
	    	gostTimeline[w[i]] = 1;
	    }
	    
	    if(r > t) {
	    	System.out.println("-1");
	    	return;
	    }
	    
	    
	    for (int i = m-1; i >= 0; i--) {
	    	int needToLight = r;
	    	for(int k = w[i]-t; k < w[i]; k++) {
	    		if(lightTimeline(k)) {
	    			needToLight--;
	    			if(r==0) break;
	    		}
	    	}
	    	
	    	for(int k = 0, lighten=0; lighten < needToLight; k++) {
	    		if(!lightTimeline(w[i]+k-t)) {
	    			lightTimeline(w[i]+k-t, true);
	    			lighten++;
	    		}
	    	}
	    }
	    
	    int total = 0;
	    for (int i = 0; i < lightTimeline.length; i++) {
	    	if(lightTimeline[i]) total++;
	    }
	    
	    System.out.println(total);
	    
    }
	


	
}
