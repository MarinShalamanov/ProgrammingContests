package com.marinshalamanov.codeforces.codeforces347;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class B {
	
	public void solve(InputReader in, PrintWriter out) {
        int numPos = 1, numNeg = 0;
        int res;
        List<Character> l = new ArrayList<>();
        
        char next = '+';
        while(true) {
        	String s = in.next();
        	if(s.equals("?")) {
        		l.add(next);
        	}else if(s.equals("-")) {
        		next = '-';
        		numNeg++;
        	} else if(s.equals("+")) {
        		next = '+';
        		numPos++;
        	} else if(s.equals("=")) {
        		break;
        	}
        }
        res = in.nextInt();
        
        if (numPos == 1 && numNeg == 0) {
        	System.out.println("Possible");
        	System.out.println(res + " = " + res);
        	return;
        }
        
        if (numPos == 1 && numNeg == 1) {
        	System.out.println("Impossible");
        	return;
        }
        
        Stack<Integer> pos, neg;
        pos = new Stack<>();
        neg = new Stack<>();
//        System.out.println("numPos " + numPos);
//        System.out.println("numNeg " + numNeg);
        
        if(numPos - numNeg > 0 && numPos - numNeg <= res) {
        	for(int i = 0; i < numNeg; i++) {
        		neg.add(1);
        		pos.add(1);
        	}
        	
        	numPos -= numNeg;
        	numNeg = 0;
        }
        
        int posMin = Math.max(0, (numPos-res))*1;
        int posMax = (numPos-1)*res;
        int negMin = numNeg * 1;
        int negMax = numNeg * res;
        if (posMin <= negMax &&  negMin <= posMax) {
        	
        	int sum = Math.max(posMin, negMin);
        	//System.out.println("sum = " + sum);
        	int _sum = sum;
        	while(numNeg > 0) {
        		int nextNum = Math.min(res, _sum - numNeg + 1);
        		neg.add(nextNum);
        		
        		_sum -= nextNum;
        		numNeg--;
        	}
        	
        	_sum = sum;
        	if(numPos > res && (numPos - res)*res < _sum) {
        		while (numPos > res) {
	        		int nextNum = Math.min(res, _sum - (numPos - res) + 1);
	        		pos.add(nextNum);
	        		
	        		_sum -= nextNum;
	        		numPos--;
        		}
        	} else {
        		while(_sum > 0) {
        			int nextNum = Math.min(res, _sum);
        			pos.add(nextNum);
        			numPos--;
        			_sum -= nextNum;
        		}
        	}
        	
        	// numPos -= numNeg;
        	numNeg = 0;
        } else {
        	System.out.println("Impossible");
        	return;
        }
        
        if (numNeg == 0) {
        	if(numPos > res) {
        		System.out.println("Impossible");
            	return;
        	} else {
        		pos.add(res - numPos + 1);
        		for (int i = 1; i < numPos; i++) {
        			pos.add(1);
        		}
        	}
        }
        
        
        System.out.println("Possible");
        boolean first = true;
        for (char c : l) {
        	if(!first) System.out.print(c + " ");
        	if (c == '+') {
        		
        		System.out.print(pos.pop() + " ");
        	} else {
        		System.out.print(neg.pop() + " ");
        	}
        	
        	
        	if (first) first = false;
        }
        System.out.println("= " + res);
        
//      for(char c : l) System.out.print(c + " ");
//		System.out.println(res);
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        B solver = new B();
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
        
        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}

/**

? + ? - ? - ? - ? = 42
? + ? - ? - ? - ? - ? - ? = 4
? + ? - ? - ? - ? - ? = 4
? - ? - ? - ? = 42
 */
