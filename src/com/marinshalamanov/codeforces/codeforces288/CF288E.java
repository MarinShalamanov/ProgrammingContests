package codeforces288;



import java.util.Scanner;


public class CF288E {
	public static void main(String[] args) {
	    new CF288E();
	}
	
	Scanner in = new Scanner(System.in);
	
	int n;
	int[][] w = new int [605][2];
	int[] str = new int[1205];
	
	
	public CF288E() {
	    n = in.nextInt();
	    for(int i = 0; i < n; i++) {
	    	w[i][0] = in.nextInt();
	    	w[i][1] = in.nextInt();
	    }
	    
	    solve(0, 0, -1);
	    
	    System.out.println("IMPOSSIBLE");
    }
	
	void solve(int i, int firstFree, int lastClosed) {
		if(i==n) {
			for(int k = 0; k < 2*n; k++) {
				System.out.print((str[k]==1)?("("):(")"));
			}
			System.out.println();
			System.exit(0);
		}
		while(str[firstFree] != 0) firstFree++;
		str[firstFree] = 1;
		
		for(int end = w[i][0]; end <= w[i][1]; end++) {
			if(firstFree + end < 2*n) {
				if(str[firstFree+end]!=0) continue;
				
				if(firstFree < lastClosed && lastClosed < firstFree+end) {
					continue;
				}
				
				boolean ok = true;
				for(int j = firstFree+1; j < firstFree + end; j++) {
					if(str[j]==-1) {
						ok = false;
						break;
					}
				}
				if(!ok) continue;
				
				str[firstFree+end] = -1;
				
				solve(i+1, firstFree, Math.max(lastClosed, firstFree+end));
				
				str[firstFree+end] = 0;
			} else {
				break;
			}
		}
		
		str[firstFree] = 0;
	}
	


	
}
