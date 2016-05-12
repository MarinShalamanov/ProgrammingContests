package com.marinshalamanov.codeforces.codeforces352;

import java.awt.Label;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.LayoutStyle;

public class D {
	
	class Level implements Comparable<Long>{
		long level;
		int widht;
		long amount;
		
		public Level(long level, int widht, long amount) {
			super();
			this.level = level;
			this.widht = widht;
			this.amount = amount;
		}

		@Override
		public int compareTo(Long o) {
			if (level - o > 0) return 1;
			if (level == o) return 0;
			else return -1;
		}
	}
	
	List<Level>	levelsMin, levelsMax;
	
	long getAmountForLevelMin(long level) {
		int loc = Collections.binarySearch(levelsMin, level);
		
		if (loc >= 0) {
			return levelsMin.get(loc).amount;
		} else {
			loc = (loc+1) * (-1);
			
			Level high = levelsMin.get(loc);
			return high.amount - (high.level - level)*high.widht;
		}
	}
	
	long getAmountForLevelMax(long level) {
		int loc = Collections.binarySearch(levelsMax, level);
		
		if (loc >= 0) {
			return levelsMax.get(loc).amount;
		} else {
			loc = (loc+1) * (-1);
			
			Level high = levelsMax.get(loc-1);
			return high.amount - (level - high.level)*high.widht;
		} 
	}
	
	public void solve(InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        
        long sum = 0;
        
        int c[] = new int[n];
        for(int i = 0; i < n; i++) {
        	c[i] = in.nextInt();
        	sum += c[i];
        }
        
        Arrays.sort(c);
        
        long optimalLevel = sum / n;
        long optimalRem = sum % n;
        
        levelsMin = new ArrayList<>();
        levelsMin.add(new Level(c[0], 0, 0));
        
        for (int i = 1; i < n; i++) {
        	if (c[i] != c[i-1]) {
        		Level lastLevel = levelsMin.get(levelsMin.size()-1);
        		long amount = lastLevel.amount + i * (c[i] - lastLevel.level);
        		levelsMin.add(new Level(c[i], i, amount));
        	}
        }
        
        levelsMax = new ArrayList<>();
        levelsMax.add(new Level(c[n-1], 0, 0));
        
        for (int i = n-2; i >= 0; i--) {
        	if (c[i] != c[i+1]) {
        		Level lastLevel = levelsMax.get(levelsMax.size()-1);
        		int width = n-1-i;
        		long amount = lastLevel.amount + width * (lastLevel.level - c[i]);
        		levelsMax.add(new Level(c[i], width, amount));
        	}
        }
        
        Collections.reverse(levelsMax);
        
        
        
        
        
    }
	
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        D solver = new D();
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
