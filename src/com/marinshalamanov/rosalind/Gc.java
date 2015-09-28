package rosalind;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gc {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		List<String> ids = new ArrayList<String>();
		List<String> list = new ArrayList<String>();

		String buffer;
		String last = null;
		while (true) {
			buffer = in.next();
			if (buffer.charAt(0) == '>') {
				ids.add(buffer.substring(1));
				
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

		double max = -1.;
		String maxLabel = null;
		for(int i = 0; i < list.size(); i++) {
			String str = list.get(i);
			int count = 0;
			for(int k = 0; k < str.length(); k++) {
				if(str.charAt(k) == 'C' || str.charAt(k) == 'G') {
					count++;
				}
			}
			double curr = (1.*count)/str.length();
			System.out.println(curr);
			if(max < curr) {
				max = curr;
				maxLabel = ids.get(i);
			}
			
		}
		
		System.out.println();
		System.out.println(maxLabel);
		System.out.println(max*100);

	}
}
