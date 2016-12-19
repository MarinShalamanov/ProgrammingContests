package com.marinshalamanov.hackerrank.nbu;

import java.util.Arrays;
import java.util.Scanner;

public class AndXor {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int arr[] = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}
		
		int max = -1;
		for (int i = 1; i < n; i++) {
			max = Math.max(max, arr[i-1]^arr[i]);
		}
		
		
		for(int k = 3; k < 5; k++) {
			for (int i = k-1; i < n; i++) {
				BestK<Integer> b = new BestK<>(2);
				for(int j = 0; j < k; j++) {
					b.add(arr[i-j]);
				}
				
				max = Math.max(max, b.get(0)^b.get(1));
			}
		}
		
		
		System.out.println(max);
		
	}
	
	static class BestK <T extends Comparable<T>> {
		
		private int k;
		private Object arr[];
		private int arrEnd;
			
		public BestK (int k) {
			this.k = k;
			arr = new Object[k];
			arrEnd = 0;
		}
		
		@SuppressWarnings("unchecked")
		public void add(T el) {
			int pos;
			if (arrEnd < k) {
				pos = arrEnd++;
			} else if(el.compareTo(((T)arr[k-1])) < 0) {
				pos = k-1;
			} else {
				return;
			}

			arr[pos] = el;
			while(pos > 0 && ((T)arr[pos]).compareTo(((T)arr[pos-1])) < 0) {
				Object t = arr[pos];
				arr[pos] = arr[pos-1];
				arr[pos-1] = t;
				pos--;
			}
		}
		
		@SuppressWarnings("unchecked")
		public T get(int i) {
			return (T) arr[i];
		}
		
		public int size() {
			return k;
		}
		
		public String toString() {
			return Arrays.toString(arr);
		}
	}

}
