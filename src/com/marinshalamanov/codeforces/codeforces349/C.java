package com.marinshalamanov.codeforces.codeforces349;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class C {

	String sufixes[][];
	String s;
	
	void addSuf(int start, String suf) {
		if(sufixes[start][0]==null) {
			sufixes[start][0] = suf;
		} else {
			sufixes[start][1] = suf;
		}
	}
	
	boolean isOkSuf(int start, String suf) {
		int nextStart = start + suf.length();
		if(nextStart >= s.length()) {
			return true;
		}
		if(sufixes[nextStart][0] == null && sufixes[nextStart][1] == null) {
			return false;
		} else if (sufixes[nextStart][0] != null && sufixes[nextStart][1] == null) {
			return !suf.equals(sufixes[nextStart][0]);
		} else if (sufixes[nextStart][0] == null && sufixes[nextStart][1] != null) {
			return !suf.equals(sufixes[nextStart][1]);
		} else {
			return true;
		}
	}
	
	public void solve(InputReader in, PrintWriter out) {

		Trie t = new Trie();
		s = in.next();
		sufixes = new String[s.length()][2];

		for (int end = s.length() - 1; end > 0; end--) {
			if (end == s.length() - 2)
				continue;

			int start = end - 1;
			if (start > 4) {
				String suf = s.substring(start, end + 1);
				if(isOkSuf(start, suf)) {
					addSuf(start, suf);
					t.insert(suf);
				}
			}

			start = end - 2;
			if (start > 4) {
				String suf = s.substring(start, end + 1);
				if(isOkSuf(start, suf)) {
					addSuf(start, suf);
					t.insert(suf);
				}
			}
		}

		System.out.println(t.size());

		Stack<TrieNode> stack = new Stack<>();
		stack.add(t.root);

		Stack<TrieNode> currWord = new Stack<>();

		while (!stack.isEmpty()) {
			TrieNode top = stack.pop();
			if (top.par != null) {
				while (currWord.peek() != top.par) {
					currWord.pop();
				}
			}
			currWord.add(top);

			if (top.isEnd) {
				String word = "";
				for (int i = 1; i < currWord.size(); i++) {
					word += currWord.get(i).letter;
				}
				System.out.println(word);
			}

			for (int i = top.arr.length - 1; i >= 0; i--) {
				if (top.arr[i] != null) {
					top.arr[i].par = top;
					stack.add(top.arr[i]);
				}
			}
		}
	}

	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		C solver = new C();
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

	public class TrieNode {
		TrieNode[] arr;
		boolean isEnd;
		char letter = 'x';
		TrieNode par;

		// Initialize your data structure here.
		public TrieNode() {
			this.arr = new TrieNode[26];
		}

	}

	public class Trie {
		private TrieNode root;
		private int size = 0;

		public Trie() {
			root = new TrieNode();
		}

		public int size() {
			return size;
		}

		// Inserts a word into the trie.
		public void insert(String word) {
			// System.out.println("ins " + word);
			TrieNode p = root;
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				int index = c - 'a';
				if (p.arr[index] == null) {
					TrieNode temp = new TrieNode();
					temp.letter = c;
					p.arr[index] = temp;
					p = temp;
				} else {
					p = p.arr[index];
				}
			}
			if (!p.isEnd) {
				p.isEnd = true;
				size++;
			}
		}

		// Returns if the word is in the trie.
		public boolean search(String word) {
			TrieNode p = searchNode(word);
			if (p == null) {
				return false;
			} else {
				if (p.isEnd)
					return true;
			}

			return false;
		}

		// Returns if there is any word in the trie
		// that starts with the given prefix.
		public boolean startsWith(String prefix) {
			TrieNode p = searchNode(prefix);
			if (p == null) {
				return false;
			} else {
				return true;
			}
		}

		public TrieNode searchNode(String s) {
			TrieNode p = root;
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				int index = c - 'a';
				if (p.arr[index] != null) {
					p = p.arr[index];
				} else {
					return null;
				}
			}

			if (p == root)
				return null;

			return p;
		}
	}

}
