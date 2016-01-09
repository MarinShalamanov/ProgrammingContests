package com.marinshalamanov.usp;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Aura {

	Queue<Integer> W, M, R;
	Scanner in;

	void readNext(int k) {
		for (int i = 0; i < k; i++) {
			if (in.hasNext()) {
				String s = in.next();
				char type = s.charAt(0);
				int number = Integer.parseInt(s.substring(1, s.length()));
				switch (type) {
				case 'W':
					W.add(number);
					break;
				case 'M':
					M.add(number);
					break;
				case 'R':
					R.add(number);
					break;
				default:
					break;
				}
			}
		}
	}

	void run() {
		Scanner input = new Scanner(System.in);
		int nt = input.nextInt();

		while (nt-- > 0) {
			// int n = in.nextInt();
			int myNum = input.nextInt();

			W = new ArrayDeque<Integer>();
			M = new ArrayDeque<Integer>();
			R = new ArrayDeque<Integer>();
			input.nextLine();
			in = new Scanner(input.nextLine());

			int passed = 0;

			readNext(10);
			while (passed < 1000 && (!M.isEmpty() || !W.isEmpty() || !R.isEmpty())) {
				passed++;

				if (!M.isEmpty()) {
					if (M.poll() == myNum) {
						System.out.println(passed);
						break;
					}
				} else if (!W.isEmpty()) {
					if (W.poll() == myNum) {
						System.out.println(passed);
						break;
					}
				} else {
					if (R.poll() == myNum) {
						System.out.println(passed);
						break;
					}
				}

				readNext(3);
			}

		}
	}

	public static void main(String[] args) {
		new Aura().run();
	}
}
