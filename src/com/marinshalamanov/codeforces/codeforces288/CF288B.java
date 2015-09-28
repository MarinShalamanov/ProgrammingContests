package codeforces288;

import java.util.Scanner;



public class CF288B {
	public static void main(String[] args) {
	    new CF288B();
	}
	
	Scanner in = new Scanner(System.in);
	
	String str; 
	
	public CF288B() {
	    str = in.next();
	    
	    boolean chetno = false;
	    
	    for(int i = 0; i < str.length(); i++) {
	    	if ((str.charAt(i)-'0')%2 == 0) {
	    		chetno = true;
	    		break;
	    	}
	    }
	    
	    if (!chetno) {
	    	System.out.println("-1");
	    	return;
	    }
	    
	    int last = str.charAt(str.length()-1)-'0';
	    if (last%2==1) {
	    	if(str.length()==2) {
	    		System.out.println(str.charAt(1) + "" + str.charAt(0));
	    		return;
	    	}
	    	
	    	int change = -1;
	    	
	    	for(int i = 0; i <= str.length()-2; i++) {
	    		int curr = str.charAt(i)-'0';
		    	if (curr%2 == 0 && last > curr) {
		    		change = i;
		    		break;
		    	}
		    }
	    	
	    	if (change == -1) {
	    		for(int i = str.length()-2; i >= 0; i--) {
	    			int curr = str.charAt(i)-'0';
	    			if(curr%2 == 0) {
	    				change = i;
	    				break;
	    			}
	    		}
	    	}
	    	
	    	StringBuilder sb = new StringBuilder(str);
	    	char lastCh = sb.charAt(sb.length()-1);
	    	sb.setCharAt(sb.length()-1, sb.charAt(change));
	    	sb.setCharAt(change, lastCh);
	    	System.out.println(sb.toString());
	    } else {
	    	if(str.length()==2) {
	    		if((str.charAt(0)-'0')%2 == 0) {
	    			System.out.println(str.charAt(1) + "" + str.charAt(0));
		    		return;
	    		} else {
	    			System.out.println("-1");
		    		return;
	    		}
	    	}
	    	
	    	int c1, c2;
	    	c1 = 0;
	    	while(str.charAt(c1)=='9') c1++;
	    	
	    	if(c1 == str.length()-1) {
	    		System.out.println(str);
	    		return;
	    	}
	    	
	    	c2 = c1 + 1;
	    	if(c2 == str.length()-1) {
	    		c2 = c1 - 1;
	    	}
	    	int biggest = -1;
	    	
	    	for(int i = c1+1; i < str.length()-1; i++) {
	    		int curr = str.charAt(i)-'0';
	    		if(biggest < curr) { 
	    			curr = biggest;
	    			c2 = i;
	    		}
	    	}
	    	
	    	
	    	StringBuilder sb = new StringBuilder(str);
	    	char lastCh = sb.charAt(c1);
	    	sb.setCharAt(c1, sb.charAt(c2));
	    	sb.setCharAt(c2, lastCh);
	    	System.out.println(sb.toString());	    	
	    }
	    
    }
	


	
}
