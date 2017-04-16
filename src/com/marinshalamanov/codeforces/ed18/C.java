package com.marinshalamanov.codeforces.ed18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class C {
	
	public void solve(InputReader in, PrintWriter out) {
        String s = in.next();
        int count = 0;
        
        for(int i = 0; i < s.length(); i++) {
        	count += (s.charAt(i) - '0');
        }
        
        count = count % 3;
        
        if(count == 0) {
        	System.out.println(s);
        	
        } else {  //if (count == 1) {
        	// search for a single digit to erase
        	for(int i = s.length()-1; i >=0; i--) {
            	int dig  = (s.charAt(i) - '0');
            	if(dig % 3 == count) {
            		if(i == 0 && s.length()==1) {
            			System.out.println(-1);
            			return;
            		} else if(i == 0 && s.length() > 1 && s.charAt(i+1) == '0') {
            			int numZeros = 0;
            			for (int j = 1; j < s.length() && s.charAt(j)=='0'; j++) {
            				numZeros++;
            			}
            			
            			if(numZeros+ 1 == s.length()) {
        					System.out.println(0);
            				return;
            			}
            			//System.out.println("num zeros: " + numZeros);
            			if(numZeros > 1) {
            				List<Integer> pos = new ArrayList<Integer>();
            				
            				for (int j = 1; j < s.length(); j++) {
                				int curd = s.charAt(j) - '0';
                				if (curd%3 == 3-count) {
                					pos.add(j);
                					if(pos.size() ==2) {
                						break;
                					}
                				}
                			}
            				
            				if(pos.size() == 2) {
            					for(int j = 0; j < s.length(); j++) {
        	            			if(!pos.contains(j)) {
        	            				System.out.print(s.charAt(j));
        	            			}
            					}
            					System.out.println();
            					return;
            				} 
            			}
            			
            			for(int j = numZeros+1; j < s.length(); j++) {
	            			System.out.print(s.charAt(j));
    					}
    					System.out.println();
    					return;
            		} else {
	            		for(int j = 0; j < s.length(); j++) {
	            			if(j != i) {
	            				System.out.print(s.charAt(j));
	            			}
	            		}
	            		System.out.println();
	            		return;
            		}
            	} 
            }
        	
        	// search for two to delete
        	List<Integer> pos = new ArrayList<Integer>();
			
			for (int j = s.length()-1; j >= 0; j--) {
				int curd = s.charAt(j) - '0';
				if (curd%3 == 3-count) {
					pos.add(j);
					if(pos.size() ==2) {
						break;
					}
				}
			}
			
			if(pos.size() == 2) {
				boolean noPrinted = true;
				boolean hasZero = false;
				for(int j = 0; j < s.length(); j++) {
					if(s.charAt(j)=='0') hasZero = true;
        			if(!pos.contains(j)) {
        				if(! (noPrinted && s.charAt(j) == '0')) {
        					System.out.print(s.charAt(j));
        					noPrinted = false;
        				}
        			}
				}
				if(noPrinted) {
					if(hasZero) System.out.println(0);
					else System.out.println(-1);
					return;
				}

				System.out.println();
				return;
//			} else if(pos.size() == 1 && (s.charAt(0) - '0')%3 == 3-count) {
//				pos.add(0);
//				int numZeros = 0;
//    			for (int j = 1; j < s.length() && s.charAt(j)=='0'; j++) {
//    				numZeros++;
//    			}
//    			
//    			if(numZeros+2 == s.length()) {
//    				if(numZeros > 0) {
//    					System.out.println(0);
//    					return;
//    				} else {
//    					System.out.println(-1);
//    					return;
//    				}
//    				
//    			} else {
//    				for(int j = 0; j < s.length(); j++) {
//            			if(!pos.contains(j)) {
//            				System.out.print(s.charAt(j));
//            			}
//    				}
//    				System.out.println();
//    				return;
//    			}
			} else {
				// check if it has a zero
				boolean hasZero = false;
				for (int j = 0; j < s.length(); j++) {
    				if(s.charAt(j) == '0') {
    					hasZero = true;
    				}
    			}
				
				if(hasZero) {
					System.out.println(0);
					return;
				} else {
					System.out.println(-1);
					return;
				}
			}
        } 
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        C solver = new C();
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
