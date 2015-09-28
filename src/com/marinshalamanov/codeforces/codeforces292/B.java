package codeforces292;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class B {
	public static void main(String[] args) throws IOException {
		new B();
	}

	int n, m;
	char[][] arr;
	int [][] pow;
	boolean [][]used;

	int getNumN(int i, int j) {
		int num = 0;
		
		if(i>0 && arr[i-1][j]=='.') num++;
		if(j>0 && arr[i][j-1]=='.') num++;
		if(i+1<n && arr[i+1][j]=='.') num++;
		if(j+1<m && arr[i][j+1]=='.') num++;
		
		return num;
	}
	
	void match(int i, int j) {
		if(i>0 && arr[i-1][j]=='.') {
			arr[i-1][j] = '^';
			arr[i][j] = 'v';
		}
		if(j>0 && arr[i][j-1]=='.') {
			arr[i][j-1] = '<';
			arr[i][j] = '>';
		}
		if(i+1<n && arr[i+1][j]=='.') {
			arr[i+1][j] = 'v';
			arr[i][j] = '^';
		}
		if(j+1<m && arr[i][j+1]=='.') {
			arr[i][j+1] = '>';
			arr[i][j]= '<';
		}
	}
	
	void print() {
		for(int i = 0; i < n; i++) {
			System.out.println(arr[i]);
		}
	}
	
	boolean isReady () {
		for (int j, i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				if(arr[i][j] == '.') {
					return false;
				}
			}
		}
		return true;
	}
	
	boolean noMoreComponents = false;
	
	boolean checkNextComponent() {
		int[][] queue = new int[n*m][2];
		int end = 0;
		
		int white = 0;
		int black = 0;
		
		boolean found = false;
		for(int j, i = 0; i < n; i++) {
			for(j = 0; j < m; j++) {
				if(arr[i][j] == '.' && !used[i][j]) {
					queue[end][0] = i;
					queue[end][1] = j;
					end++;
					used[i][j] = true;
					found = true;
				}
			}
		}
		
		if(!found) {
			noMoreComponents = true;
			return true;
		}
		
		int i,j;
		for(int x = 0; x < end; x++) {
			i = queue[x][0];
			j = queue[x][1];
			if((i%2==0 && j%2==0) || (i%2==1 && j%2==1)) {
				white++;
			} else {
				black++;
			}
			
			if(i>0 && arr[i-1][j]=='.' && !used[i-1][j]) {
				queue[end][0] = i-1;
				queue[end][1] = j;
				end++;
			}
			if(j>0 && arr[i][j-1]=='.' && !used[i][j-1]) {
				queue[end][0] = i;
				queue[end][1] = j-1;
				end++;
			}
			if(i+1<n && arr[i+1][j]=='.' && !used[i+1][j]) {
				queue[end][0] = i+1;
				queue[end][1] = j;
				end++;
			}
			if(j+1<m && arr[i][j+1]=='.' && !used[i][j+1]) {
				queue[end][0] = i;
				queue[end][1] = j+1;
				end++;
			}
			
		}
		
		return white==black;
	}
	
	int numFree = 0;
	
	public B() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner in = new Scanner(br.readLine());
		n = in.nextInt();
		m = in.nextInt();

		arr = new char[n][m];
		
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			arr[i] = line.toCharArray();
		}
		
		pow = new int[n][m];

		int savedJ = 0;
		out: 
		for (int j, i = 0; i < n; i++) {
			for (j = savedJ; j < m; j++) {
				if(arr[i][j] == '.') {
					
					//System.out.println(i + " " + j);
					pow[i][j] = getNumN(i, j);
					if(pow[i][j] == 1) {
						match(i, j);
						i = Math.max(-1, i-3);
						savedJ = Math.max(0, j-2);
						continue out;
					}
				}
			}
			savedJ = 0;
		}
		
		if (isReady()) {
			print();
			System.exit(0);
		} else {
			System.out.println("Not unique");
		}
		
		/*
		while(noMoreComponents){
			if(!checkNextComponent()) {
				System.out.println("Not unique");
			}
		}
			*/
		

	}

}
