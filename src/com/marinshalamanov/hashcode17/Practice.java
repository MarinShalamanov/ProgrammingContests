package com.marinshalamanov.hashcode17;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class Practice {
	

	int rows, cols;
	int minIngred;
	int maxTotalCells;
	
	boolean pizza[][];
	int partialArea[][];
	
	List<Dimen> dimens;
	Random rand = new Random();
	
	void readInput(InputReader in) {
		rows = in.nextInt();
		cols = in.nextInt();
		minIngred = in.nextInt();
		maxTotalCells = in.nextInt();
		
		pizza = new boolean[rows][cols];
		for(int i = 0; i < rows; i++) {
			String rowEnc = in.next();
			for(int j = 0; j < cols; j++) {
				pizza[i][j] = (rowEnc.charAt(j) == 'T');
			}
		}
	}
	
	
	List<Dimen> getPossibleDimens() {
		List<Dimen> dimens = new ArrayList<>();
		int minArea = 2*minIngred;

		for(int i = 1; i <= 1000; i++) {
			for(int j = i; j <= 1000; j++) {
				if(i*j >= minArea && i*j <= maxTotalCells) {
					dimens.add(new Dimen(i-1, j-1));
				}
			}
		}
		
		Collections.sort(dimens); 
		return dimens;
	}
	
	Solution getRandomSolution() {
		Solution sol = new Solution();
		
		int prefDims = (dimens.size() <= 10) 
				? (dimens.size()) 
				: (dimens.size()/5);
		
		for(int it = 0; it < 50000; it++) {
			if(it%1000==0) System.out.println("it: " + it); 
			Dimen d = dimens.get(rand.nextInt(prefDims));
			int smallerDim = Math.min(d.w, d.h);
			
			testing:
			for(int i = 0; i+smallerDim < rows; i++) {
				for(int j = 0; j+smallerDim < cols; j++) {
					if(i+d.h < rows && j+d.w < cols) {
						Slice sl = new Slice(j, j+d.w, i, i+d.h);
						if(sl.isFeasible() && !sol.conflicts(sl)) {
							sol.addSlice(sl);
							break testing;
						}
					}
					
					if(i+d.w < rows && j+d.h < cols) {
						Slice sl = new Slice(j, j+d.h, i, i+d.w);
						if(sl.isFeasible() && !sol.conflicts(sl)) {
							sol.addSlice(sl);
							break testing;
						}
					}
				}
			}
		}
		return sol;
	}
	
	Solution getRandomSolution2() {
		Solution sol = new Solution();
		
		int prefDims = (dimens.size() <= 10) 
				? (dimens.size()) 
				: (dimens.size()/5);
		
		int piecesToPut = 80000;
		
		int lastPcs = -1;
		for(int it = 0; piecesToPut > 0; it++) {
			if(lastPcs == piecesToPut) break;
			lastPcs = piecesToPut;
			
			//if(it%1000==0) System.out.println("it: " + it + "   pcs: " + piecesToPut); 
			Dimen d = dimens.get(rand.nextInt(prefDims));
			int smallerDim = Math.min(d.w, d.h);
			
			testing:
			for(int i = 0; i+smallerDim < rows; i++) {
				for(int j = 0; j+smallerDim < cols; j++) {
					if(i+d.h < rows && j+d.w < cols) {
						Slice sl = new Slice(j, j+d.w, i, i+d.h);
						if(sl.isFeasible() && !sol.conflicts(sl)) {
							sol.addSlice(sl);
							piecesToPut--;
							
							d = dimens.get(rand.nextInt(prefDims));
							smallerDim = Math.min(d.w, d.h);
							
						}
					}
					
					if(i+d.w < rows && j+d.h < cols) {
						Slice sl = new Slice(j, j+d.h, i, i+d.w);
						if(sl.isFeasible() && !sol.conflicts(sl)) {
							sol.addSlice(sl);
							piecesToPut--;
							
							d = dimens.get(rand.nextInt(prefDims));
							smallerDim = Math.min(d.w, d.h);
						}
					}
				}
			}
		}
		return sol;
	}
	
	void compPartialArea() { 
		partialArea = new int[rows][cols];
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(i == 0 && j == 0) {
					partialArea[i][j] = 0;
				} else if(i==0) {
					partialArea[i][j] = partialArea[i][j-1]; 
				} else if(j == 0) {
					partialArea[i][j] = partialArea[i-1][j];
				} else {
					partialArea[i][j] = partialArea[i][j-1] + partialArea[i-1][j] - partialArea[i-1][j-1];
				}
				
				if (pizza[i][j]) partialArea[i][j]++;
			}
		}
	}
	
	public void solve(InputReader in, PrintWriter out) {
		readInput(in);
		compPartialArea();
		//System.out.println(rows + " " + cols);
		
		dimens = getPossibleDimens();
		
		Solution bestSol = getRandomSolution2();
		System.out.println("area is " + bestSol.getArea());
		
		for(int i = 0; i < 10; i++) {
			System.out.println("now computing: " + i);
			Solution curr = getRandomSolution2();
			
			System.out.println("area is " + curr.getArea());
			
			if( curr.getArea() > bestSol.getArea()) {
				bestSol = curr;
			}
		}
		System.out.println("best area: " + bestSol.getArea());
		
		for(int i = 0; i < 30; i++) {
			for(int j = 0; j < 150; j++) {
				System.out.print( (bestSol.map[i][j]) ? ("X") : ( (pizza[i][j])?"~":" ") );
			}
			System.out.println();
		}
		bestSol.print(out);
    }
	
    public static void main(String[] args) throws FileNotFoundException {
    	File inf = new File("big.in");
    	InputStream inputStream = new FileInputStream(inf);
    	
//        InputStream inputStream = System.in;
    	
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
//        PrintWriter out = new PrintWriter(outputStream);
        
        File f = new File("output.txt");
        PrintWriter out = new PrintWriter(f);
        
        Practice solver = new Practice();
        solver.solve(in, out);
        out.close();
    }

    class Dimen implements Comparable<Dimen> {
    	public int w, h;

		public Dimen(int w, int h) {
			this.w = w;
			this.h = h;
		}

		@Override
		public int compareTo(Dimen o) {
			return Integer.compare(w+h, o.w + o.h);
		}
    }
    
    class Slice {
    	public int left, right, up, down;

		public Slice(int left, int right, int up, int down) {
			this.left = left;
			this.right = right;
			this.up = up;
			this.down = down;
		}
		
		public int area() {
			return (right-left+1)*(down-up+1);
		}
		
		public int getNumMush() { // TODO: faster
			int A, B, C;

			A = (left > 0) 
					? partialArea[down][left-1]
					: 0;
			
			B = (up > 0) 
					? partialArea[up-1][right]
					: 0;
				
			C = (left > 0 && up > 0) 
					? partialArea[up-1][left-1]
					: 0;
					
			return partialArea[down][right] - A - B + C;
		}
		
		public boolean isFeasible() {
			int area = area();
    		if(area > maxTotalCells || area < 2*minIngred) return false;
    		
    		int mush = getNumMush();
    		if(mush < minIngred) return false;
    		if((area-mush) < minIngred) return false;
    		
    		return true;
		}
		
		public boolean intersects(Slice o) {
			return left <= o.right && o.left <= right &&
					up <= o.down && o.up <= down;
		}
    }
    
    class Solution {
    	private List<Slice> slices = new ArrayList<>();
    	private int area = -1;
    	
    	boolean map[][] = new boolean[rows][cols];
    	
    	public void addSlice(Slice sl) {
    		slices.add(sl);
    		
    		for(int i = sl.up; i <= sl.down; i++) {
    			for(int j = sl.left; j <= sl.right; j++) {
    				map[i][j] = true;
    			}
    		}
    	}
    	public int getArea() {
    		if(area == -1) {
    			area = 0;
    			for(Slice sl : slices) {
    				area += sl.area();
    			}
    		}
    		
    		return area;
    	}
    	
    	public void print(PrintWriter out) {
    		out.println(slices.size());
    		for (Slice s : slices) {
    			out.println(s.up + " " + s.left + " " + s.down + " " + s.right);
    		}
    	}
    	
    	public boolean isFeasible(Solution sol) {
        	for(Slice s : sol.slices) {
        		if(!s.isFeasible()) return false;
        	}
        	return true;
        }
    	
    	public boolean conflicts(Slice sl) {

    		for(int i = sl.up; i <= sl.down; i++) {
    			for(int j = sl.left; j <= sl.right; j++) {
    				if(map[i][j]) return true;
    			}
    		}
    		return false;
//    		for(Slice sl : slices) {
//    			if(sl.intersects(s)) {
//    				return true;
//    			}
//    		}
//    		return false;
    	}
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
