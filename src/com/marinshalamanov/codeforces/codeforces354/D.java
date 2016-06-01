package com.marinshalamanov.codeforces.codeforces354;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class D {
	
	class Cell {
		public int i, j, k;
		public int time;

		public Cell(int i, int j, int k) {
			super();
			this.i = i;
			this.j = j;
			this.k = k;
			time = 0;
		}
		
		public Cell(int i, int j, int k, int time) {
			super();
			this.i = i;
			this.j = j;
			this.k = k;
			this.time = time;
		}

		@Override
		public int hashCode() {
			final int prime = 1097;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + i;
			result = prime * result + j;
			result = prime * result + k;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cell other = (Cell) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (i != other.i)
				return false;
			if (j != other.j)
				return false;
			if (k != other.k)
				return false;
			return true;
		}

		private D getOuterType() {
			return D.this;
		}

		@Override
		public String toString() {
			return "Cell [i=" + i + ", j=" + j + ", k=" + k + ", time=" + time + "]";
		}
		
		
	}
	
	char rotate(char c) {
		switch (c) {
		case '+': return '+';
		case '-': return '|';
		case '|': return '-';
		case '^': return '>';
		case '>': return 'v';
		case 'v': return '<';
		case '<': return '^';
		case 'L': return 'U';
		case 'U': return 'R';
		case 'R': return 'D';
		case 'D': return 'L';
		case '*': return '*';
		default: return c;
		}
	}
	
	char arr[][][];
int n, m;
		 
	boolean hasLeft(Cell c) {
		return hasLeft(c.i, c.j, c.k);
	}
	boolean hasUp(Cell c) {
		return hasUp(c.i, c.j, c.k);
	}
	boolean hasRight(Cell c) {
		return hasRight(c.i, c.j, c.k);
	}
	boolean hasDown(Cell c) {
		return hasDown(c.i, c.j, c.k);
	}

	boolean hasLeft(int i, int j, int k) {
		String left = "+-<URD";
		return j > 0 && left.contains("" + arr[i][j][k]);
	}
	
	boolean hasRight(int i, int j, int k) {
		String left = "+->ULD";
		return j+1 < m && left.contains("" + arr[i][j][k]);
	}
	
	boolean hasUp(int i, int j, int k) {
		String left = "+|^LRD";
		return i > 0 && left.contains("" + arr[i][j][k]);
	}
	
	boolean hasDown(int i, int j, int k) {
		String left = "+|vURL";
		return i+1 < n && left.contains("" + arr[i][j][k]);
	}
	
	Cell getLeft(Cell c) {
		return new Cell(c.i, c.j-1, c.k, c.time+1);
	}
	Cell getRight(Cell c) {
		return new Cell(c.i, c.j+1, c.k, c.time+1);
	}
	Cell getUp(Cell c) {
		return new Cell(c.i-1, c.j, c.k, c.time+1);
	}
	Cell getDown(Cell c) {
		return new Cell(c.i+1, c.j, c.k, c.time+1);
	}
	
	int xt, yt, xm, ym;
	
	boolean isTarget(Cell c) {
		return c.i == xm && c.j == ym;
	}
	
	public void solve(InputReader in, PrintWriter out) {
       
        
        n = in.nextInt();
        m = in.nextInt();
        
        arr = new char[n][m][4];
		for(int i = 0; i < n; i++) {
			String s = in.next();
			for(int j = 0; j < s.length(); j++) {
				arr[i][j][0] = s.charAt(j);
			}
		}
		
		xt = in.nextInt()-1;
		yt = in.nextInt()-1;
		xm = in.nextInt()-1;
		ym = in.nextInt()-1;
		
		
		for(int k = 1; k < 4; k++) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					arr[i][j][k] = rotate(arr[i][j][k-1]);
				}
			}
			
//			System.out.println("lay " + k );
//			for(int i = 0; i < n; i++) {
//				for(int j = 0; j < m; j++) {
//					System.out.print(arr[i][j][k]);
//				}
//				System.out.println();
//			}
		}
		
		
		
		Cell start = new Cell(xt, yt, 0);
		ArrayList<Cell> queue = new ArrayList<>();
//		Cell[] queue = new Cell[n*m*4*10 + 1];
		int queueEnd = 0;
		queue.add(start);
		
		HashSet<Cell> visited = new HashSet<>();
		//visited.add(start);
		for(int idx = 0; idx < queue.size(); idx++) {
			Cell curr = queue.get(idx);
//			System.out.println("curr " + curr);
			
			if(isTarget(curr)) {
				System.out.println(curr.time);
				return;
			}
			visited.add(curr);
			
			Cell left = getLeft(curr);
			if(hasLeft(curr.i, curr.j, curr.k) &&  hasRight(left) && !visited.contains(left) ) {
				queue.add(left);
				visited.add(left);
			}
			
			Cell right = getRight(curr);
			if(hasRight(curr.i, curr.j, curr.k) && hasLeft(right) && !visited.contains(right)) {
				queue.add(right);
				visited.add(right);
			}
			
			Cell up = getUp(curr);
			if(hasUp(curr.i, curr.j, curr.k) && hasDown(up) &&  !visited.contains(up)) {
				queue.add(up);
				visited.add(up);
			}
			
			Cell down = getDown(curr);
			if(hasDown(curr.i, curr.j, curr.k) && hasUp(down)  && !visited.contains(down)) {
				queue.add(down);
				visited.add(down);
			}
			
			if(!visited.contains(new Cell(curr.i, curr.j, (curr.k+1)%4 ) ) ) {
				Cell over = new Cell(curr.i, curr.j, (curr.k+1)%4, curr.time+1 );
				queue.add(over);
				visited.add(over);
			}
			
		}
		System.out.println(-1);
		
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
