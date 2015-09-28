package codeforces290;

import java.util.Scanner;

public class CF290B {

	public static void main(String[] args) {
		new CF290B();
	}
	
	
	int n, m;
	
	char[][] arr = new char[55][55];
	int[][] visited = new int[55][55];
	int[][][] prev = new int[55][55][2];
	
	void read() {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		m = in.nextInt();
		
		in.nextLine();
		for(int i = 0 ; i < n; i++) {
			arr[i] = in.nextLine().toCharArray();
		}
	}
	
	void printVisited() {
		for(int i = 0; i < n; i++) {
			for(int j = 0 ; j < m; j++) {
				System.out.print(visited[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	

	
	void bfs(int x, int y) {
		int[][] queue = new int[n*m][2];
		int end = 0;
		
		queue[end][0] = x;
		queue[end][1] = y;
		visited[x][y] = 1;
		prev[x][y][0] = -1;
		prev[x][y][1] = -1;
		
		end++;
			
		
		
		for(int i = 0; i < end; i++) {
			x = queue[i][0];
			y = queue[i][1];
			if(x+1 < n && arr[x+1][y] == arr[x][y]) {
				if(visited[x+1][y] == 0) {
					queue[end][0] = x+1;
					queue[end][1] = y;
					visited[x+1][y] = 1;
					prev[x+1][y][0] = x;
					prev[x+1][y][1] = y;
					end++;
				} else {
					if (!(prev[x][y][0] == x+1 && prev[x][y][1] == y)) {
						System.out.println("Yes");
						System.exit(0);
					}
				}
			}
			
			if(x-1 >= 0 && arr[x-1][y] == arr[x][y]) {
				if(visited[x-1][y] == 0) {
					queue[end][0] = x-1;
					queue[end][1] = y;
					visited[x-1][y] = 1;
					prev[x-1][y][0] = x;
					prev[x-1][y][1] = y;
					end++;
				} else {
					if (!(prev[x][y][0] == x-1 && prev[x][y][1] == y)) {
						System.out.println("Yes");
						System.exit(0);
					}
				}
			}
			
			if(y+1 < m && arr[x][y+1] == arr[x][y]) {
				if(visited[x][y+1] == 0) {
					queue[end][0] = x;
					queue[end][1] = y+1;
					visited[x][y+1] = 1;
					prev[x][y+1][0] = x;
					prev[x][y+1][1] = y;
					end++;
				} else {
					if (!(prev[x][y][0] == x && prev[x][y][1] == y+1)) {
						System.out.println("Yes");
						System.exit(0);
					}
				}
			}
			
			if(y-1 >= 0 && arr[x][y-1] == arr[x][y]) {
				if(visited[x][y-1] == 0) {
					queue[end][0] = x;
					queue[end][1] = y-1;
					visited[x][y-1] = 1;
					prev[x][y-1][0] = x;
					prev[x][y-1][1] = y;
					end++;
				}else {
					if (!(prev[x][y][0] == x && prev[x][y][1] == y-1)) {
						System.out.println("Yes");
						System.exit(0);
					}
				}
			}
		}
		
	}
	
	public CF290B () {
		read();
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(visited[i][j] == 0) {
					bfs(i, j);
				}
			}
		}
		
		System.out.println("No");
	}
}
