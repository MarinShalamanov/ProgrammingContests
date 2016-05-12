package com.marinshalamanov.sdk;

import java.util.Arrays;

/**
 * Structure that keeps the best K elements inserted into it.
 * 
 * @author Marin Shalamanov
 *
 * @param <T> 
 */
public class BestK <T extends Comparable<T>> {
	
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
		} else if(el.compareTo(((T)arr[k-1])) > 0) {
			pos = k-1;
		} else {
			return;
		}

		arr[pos] = el;
		while(pos > 0 && ((T)arr[pos]).compareTo(((T)arr[pos-1])) > 0) {
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
