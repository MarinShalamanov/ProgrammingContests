package rosalind;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lcsm {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		List<String> list = new ArrayList<String>();

		String buffer;
		String last = null;
		while (true) {
			buffer = in.next();
			if (buffer.charAt(0) == '>') {
				if (last != null) {
					list.add(last);
				}
				last = new String();
				continue;
			} else if (buffer.charAt(0) == '<') {
				list.add(last);
				break;
			} else {
				last += buffer;
				//list.add(buffer);
			}
		}

		String first = list.get(0);
		String max = new String();
		outer: for (int i = 0; i < first.length(); i++) {
			//	sSystem.out.println(i);
			for (int j = i + 1; j < first.length(); j++) {
				boolean ok = true;
				String sub = first.substring(i, j);
				for (int k = 1; k < list.size(); k++) {
					if (!list.get(k).contains(sub)) {
						ok = false;
						break;
					}
				}
				if (ok) {
					if (max.length() < sub.length()) {
						max = sub;
					}
				} else {
					continue outer;
				}
			}
		}
		System.out.println(max);

	}
}
