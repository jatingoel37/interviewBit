package edu.learn.ib;

import java.util.Arrays;
import java.util.Comparator;

public class Trie {
	
	public static void main(String[] args) {
		String[] B = new String[] {"water_is_cool", "cold_ice_drink", "cool_wifi_speed"};
		solve("cool_ice_wifi", B);
	}

	private TrieNode head = new TrieNode();

	public void addString(String s) {
		TrieNode curr = head;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int index = c - 'a';
			if (curr.nodes[index] == null) {
				curr.nodes[index] = new TrieNode();
			}

			curr = curr.nodes[index];
			if (i == s.length() - 1) {
				curr.isTerminal = true;
			}

		}
	}

	public boolean find(String s) {
		TrieNode curr = head;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int index = c - 'a';
			if (curr.nodes[index] == null) {
				return false;
			}

			curr = curr.nodes[index];
			if (i == s.length() - 1) {
				return curr.isTerminal;
			}

		}

		return false;
	}

	public static class TrieNode {
		public TrieNode[] nodes = new TrieNode[26];
		public boolean isTerminal;
	}

	public static class Goodness {

		public Goodness(int goodness, int index) {
			this.goodness = goodness;
			this.index = index;
		}

		public int goodness, index;
	}

	public static int[] solve(String A, String[] B) {
		String[] goodwords = A.split("_");
		Trie trie = new Trie();
		for (String s : goodwords) {
			trie.addString(s);
		}

		int reviewsCount = B.length;
		Goodness[] goodness = new Goodness[reviewsCount];
		for (int i = 0; i < reviewsCount; i++) {
			String review = B[i];
			String[] splittedReview = review.split("_");

			int val = 0;
			for (String s : splittedReview) {
				if (trie.find(s)) {
					val++;
				}
			}

			goodness[i] = new Goodness(val, i);
		}

		Arrays.sort(goodness, new Comparator<Goodness>() {

			@Override
			public int compare(Goodness o1, Goodness o2) {
				if(o1.goodness!=o2.goodness) {
					return Integer.compare(o2.goodness, o1.goodness);
				}
				
				return Integer.compare(o1.index, o1.index);
			}
		});

		int[] response = new int[reviewsCount];
		for (int i = 0; i < reviewsCount; i++) {
			response[i] = goodness[i].index;
		}
		
		return response;
		
	}

}
