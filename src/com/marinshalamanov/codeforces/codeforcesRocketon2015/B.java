package codeforcesRocketon2015;

import java.util.Scanner;

public class B {
	public static boolean nextPermutation(int[] p) {          
        int a = p.length - 2;          
        while (a >= 0 && p[a] >= p[a + 1]) {              
            a--;          
        }          
        if (a == -1) {              
            return false;          
        }
        
        int b = p.length - 1;          
        while (p[b] <= p[a]) {              
            b--;          
        }
        
        int t = p[a];          
        p[a] = p[b];          
        p[b] = t;          
        
        
        for (int i = a + 1, j = p.length - 1; i < j; i++, j--) {              
            t = p[i];              
            p[i] = p[j];
            p[j] = t;
        }
        return true;
    }
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int n2 = in.nextInt();
		
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = i+1;

		int max = n*(n+1)*(n+2)/6;
		int count = 1;
		do {
			int sum = 0;
			for (int i = 0; i < n; i++) {
				for (int j = i; j < n; j++) {
					int min = 10000;
					for (int k = i; k <= j; k++) {
						if (arr[k] < min)
							min = arr[k];
					}
					sum += min;
				}
			}
			if(sum == max) {
				if(count++ == n2) {
					for(int i = 0; i < n; i++) {
						System.out.print(arr[i] + " ");
					}
					System.out.println();
				}
			}
			//System.out.println(Arrays.toString(arr) + "\t" + sum);
		} while(nextPermutation(arr));
		
		
//		
//		for (int kk = 0; kk < 50; kk++) {
//			Random rand = new Random();
//			for (int i = n - 1; i > 0; i--) {
//				int _t = arr[i];
//				int idx = rand.nextInt(i + 1);
//				arr[i] = arr[idx];
//				arr[idx] = _t;
//			}
//
//			int sum = 0;
//			for (int i = 0; i < n; i++) {
//				for (int j = i; j < n; j++) {
//					int min = 10000;
//					for (int k = i; k <= j; k++) {
//						if (arr[k] < min)
//							min = arr[k];
//					}
//					sum += min;
//				}
//			}
//
//			System.out.println(Arrays.toString(arr) + "\t" + sum);
//			if(sum > max) { max = sum;}
//		}
		
//		System.out.println(max);

	}
}
