package edu.learn.ib;

public class ShortestUniquePrefix {

	public static void main(String[] args) {
		String[] B = new String[] { "zebra", "dog", "duck", "dove" };
		prefix(B);
	}

	private TrieNode head = new TrieNode();

	public void addString(String s) {
		TrieNode curr = head;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int index = c - 'a';
			if (curr.nodes[index] == null) {
				curr.nodes[index] = new TrieNode();
				curr.nodes[index].isUnique = true;
			} else {
				curr.nodes[index].isUnique = false;
			}

			curr = curr.nodes[index];
			if (i == s.length() - 1) {
				curr.isTerminal = true;
			}

		}
	}

	public String findSUP(String s) {
		StringBuilder res = new StringBuilder();
		TrieNode curr = head;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			res.append(c);

			int index = c - 'a';
			
			curr = curr.nodes[index];
			if (curr.isUnique) {
				return res.toString();
			}

		}

		return res.toString();
	}

	public static class TrieNode {
		public TrieNode[] nodes = new TrieNode[26];
		public boolean isTerminal;
		public boolean isUnique;
	}

	public static String[] prefix(String[] A) {
		ShortestUniquePrefix trie = new ShortestUniquePrefix();

		for (String s : A) {
			trie.addString(s);
		}
		String[] res = new String[A.length];

		for (int i = 0; i < A.length; i++) {
			res[i] = trie.findSUP(A[i]);
		}

		return res;

	}

}
