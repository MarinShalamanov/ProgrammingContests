package com.marinshalamanov.sdk.string;

// Not tested thoroughly
public class KMP {
	
	int table[];
	String pattern;
	
	public KMP(String pattern) {
		this.pattern = pattern;
		table = new int[pattern.length()+1];
		buildTable();
	}
	
	void buildTable() {
		table[0] = -1;
				
		int i = 1, j = 0;
		while(i < pattern.length()) {
			System.out.println(i + " " + j);
			if(pattern.charAt(i) == pattern.charAt(j)) {
				table[i] = j;
				i++;
				j++;
			} else if(j==0){ 
				table[i] = -1;
				i++;
			} else {
				j = table[j-1]+1;
			}
		}
	}
	
	public boolean test(String text) {
		int i = 0, j = 0;
		while(j < pattern.length() && i < text.length()) {
			if (text.charAt(i) == pattern.charAt(j)){
				i++; j++;
			} else if(j==0) {
				i++;
			} else {
				j = table[j-1]+1;
			}
		}
		
		if(j == pattern.length()) {
			return true;
		} else {
			return false;
		}
	}
	
//	public static void main(String[] args) {
//		KMP k = new KMP("aa");
//		System.out.println(Arrays.toString(k.table));
//		System.out.println(k.test("aa"));
//		System.out.println(k.test("aaa"));
//		System.out.println(k.test("a"));
//	}
}
