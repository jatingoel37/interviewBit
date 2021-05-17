package edu.learn.ib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class StrigQues {

	public static void main(String[] args) {
		System.out.println(minWindow("ADOBECODEBANC", "ABC"));
	}

	public static String minWindow(String A, String B) {
		int rsi = 0, rl = A.length();
		boolean solFound = false;
		int m = A.length(), s = 0, e = 0;
		int charToMatch = B.length();
		Map<Character, Integer> mapping = new HashMap<>();
		for (char c : B.toCharArray()) {
			if (mapping.containsKey(c)) {
				mapping.put(c, mapping.get(c) + 1);
			} else {
				mapping.put(c, 1);
			}
		}

		while (e < m) {
			char curr = A.charAt(e);

			// not needed char
			if (!mapping.containsKey(curr)) {
				e++;
			} else { // needed char
				if (mapping.get(curr) > 0) {
					charToMatch--;
					mapping.put(curr, mapping.get(curr) - 1);
				} else {
					mapping.put(curr, mapping.get(curr) - 1);
				}
				e++;
			}

			if (charToMatch == 0) {
				solFound = true;
				while (!mapping.containsKey(A.charAt(s)) || mapping.get(A.charAt(s)) < 0) {
					if (mapping.containsKey(A.charAt(s)) && mapping.get(A.charAt(s)) < 0) {
						mapping.put(A.charAt(s), mapping.get(A.charAt(s)) + 1);
					}
					s++;
				}

				if (rl > e - s) {
					rl = e - s;
					rsi = s;
				}

				charToMatch++;
				mapping.put(A.charAt(s), mapping.get(A.charAt(s)) + 1);
				s++;
			}
		}

		if (!solFound) {
			return "";
		}

		return A.substring(rsi, rl + rsi);
	}

	public String firstNonRepCharacter(String A) {
		int[] firstOccurence = new int[26];
		StringBuilder sb = new StringBuilder();
		Arrays.fill(firstOccurence, -1);
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int i = 0; i < A.length(); i++) {
			char curr = A.charAt(i);
			int charIndex = curr - 'a';
			if (firstOccurence[charIndex] == -1) {
				pq.add(i);
				firstOccurence[charIndex] = i;
			} else if (firstOccurence[charIndex] == -2) {
				// already handled
			} else {
				pq.remove(firstOccurence[charIndex]);
				firstOccurence[charIndex] = -2;
			}

			if (pq.isEmpty()) {
				sb.append("#");
			} else {
				sb.append(A.charAt(pq.peek()));
			}
		}

		return sb.toString();
	}

	public static int longestValidParentheses(String A) {
		int n = A.length(), res = 0;
		int[] longest = new int[n + 1];
		for (int i = 1; i < n; i++) {
			char curr = A.charAt(i);
			if (curr == ')') {
				if (A.charAt(i - 1) == '(') {
					longest[i + 1] = longest[i - 1] + 2;
				} else {
					int lastLongest = longest[i];
					int possibleOpen = i - 1 - lastLongest;
					if (possibleOpen >= 0 && A.charAt(possibleOpen) == '(') {
						longest[i + 1] = longest[i] + 2 + longest[possibleOpen];
					}
				}
			}

			if (longest[i + 1] > res) {
				res = longest[i + 1];
			}
		}

		return res;
	}

	public static int solve(String A) {
		return solve(A, 0, A.length() - 1);
	}

	public static int lengthOfLastWord(final String A) {
		int len = 0, lastLen = 0;

		int i = 0, n = A.length();
		while (i < n) {
			char curr = A.charAt(i);
			if (curr == ' ') {
				lastLen = len > 0 ? len : lastLen;
				len = 0;
			} else {
				len++;
			}
			i++;
		}
		return len == 0 ? lastLen : len;
	}

	public static int solve(String A, int start, int end) {
		if (start >= end) {
			return 0;
		}

		if (A.charAt(start) == A.charAt(end)) {
			return solve(A, start + 1, end - 1);
		}

		return 1 + solve(A, start, end - 1);
	}

	public static String intToRoman(int A) {
		TreeMap<Integer, String> mapping = new TreeMap<>();
		mapping.put(1, "I");
		mapping.put(5, "V");
		mapping.put(10, "X");
		mapping.put(50, "L");
		mapping.put(100, "C");
		mapping.put(500, "D");
		mapping.put(1000, "M");
		mapping.put(4, "IV");
		mapping.put(9, "IX");
		mapping.put(40, "XL");
		mapping.put(90, "XC");
		mapping.put(400, "CD");
		mapping.put(900, "CM");

		return intToRoman(A, mapping);

	}

	private static String intToRoman(int a, TreeMap<Integer, String> mapping) {
		if (mapping.containsKey(a)) {
			return mapping.get(a);
		}
		int fkey = mapping.floorKey(a);
		return mapping.get(fkey) + intToRoman(a - fkey, mapping);
	}

	public static int romanToInt(String A) {
		int sum = 0, i = 0, n = A.length();
		while (i <= n - 2) {
			int curr = charForRoman(A.charAt(i));
			int next = charForRoman(A.charAt(i + 1));
			if (curr >= next) {
				sum = sum + curr;
				i++;
			} else {
				sum = sum - curr + next;
				i = i + 2;
			}
		}

		if (i == n - 1) {
			sum = sum + charForRoman(A.charAt(i));
		}

		return sum;
	}

	public static int charForRoman(char c) {
		switch (c) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;

		default:
			return 0;
		}
	}

	public static String longestPalindrome(String A) {
		int n = A.length();
		boolean[][] dp = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			dp[i][i] = true;
		}

		int count = 1;
		int startIndex = 0;

		for (int i = 0; i <= n - 2; i++) {
			if (A.charAt(i) == A.charAt(i + 1)) {
				if (count == 1) {
					count = 2;
					startIndex = i;
				}
				dp[i][i + 1] = true;
			}
		}

		for (int l = 3; l <= n; l++) {
			for (int i = 0; i <= n - l; i++) {
				char first = A.charAt(i);
				char last = A.charAt(i + l - 1);
				if (first == last) {
					dp[i][i + l - 1] = dp[i + 1][i + l - 1 - 1];
					if (dp[i][i + l - 1] && count < l) {
						count = l;
						startIndex = i;
					}
				}
			}
		}

		return A.substring(startIndex, startIndex + count);
	}

	public static String longestCommonPrefix(ArrayList<String> A) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < A.get(0).length(); i++) {
			char curr = A.get(0).charAt(i);
			boolean matched = true;
			for (int j = 1; j < A.size(); j++) {
				String currString = A.get(j);
				if (i >= currString.length() || curr != currString.charAt(i)) {
					matched = false;
					break;
				}
			}

			if (!matched) {
				break;
			} else {
				sb.append(curr);
			}
		}

		return sb.toString();
	}

	// --ranking --

	public static String rankTeams(String[] votes) {

		// if one candidate or one voter votes[0] return
		int candidates = votes[0].length();

		Map<Character, int[]> voteRecord = new HashMap<>(candidates);
		for (char candidate : votes[0].toCharArray()) {
			voteRecord.put(candidate, new int[candidates]);
		}

		for (String vote : votes) {
			// each vote by 1 voter
			char[] votesCasted = vote.toCharArray();
			for (int i = 0; i < votesCasted.length; i++) {
				char currVote = votesCasted[i];
				voteRecord.get(currVote)[i]++;
			}
		}

		List<Map.Entry<Character, int[]>> list = new ArrayList<>(voteRecord.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Character, int[]>>() {

			@Override
			public int compare(Entry<Character, int[]> candidate1, Entry<Character, int[]> candidate2) {
				for (int i = 0; i < candidates; i++) {
					int currentVoteComparison = Integer.compare(candidate1.getValue()[i], candidate2.getValue()[i]);
					if (currentVoteComparison != 0) {
						return currentVoteComparison;
					}
				}
				return -1 * Character.compare(candidate1.getKey(), candidate2.getKey());
			}
		});

		StringBuilder sb = new StringBuilder();

		for (Map.Entry<Character, int[]> entry : list) {
			sb.append(entry.getKey());
		}

		return sb.reverse().toString();
	}

	// -- anagram --
	public static List<Integer> anagrams(String s, String p) {
		int[] hashr = new int[26];
		for (int i = 0; i < 26; i++) {
			hashr[i] = -1;
		}

		for (int i = 0; i < p.length(); i++) {
			if (hashr[p.charAt(i) - 'a'] == -1) {
				hashr[p.charAt(i) - 'a']++;
			}
			hashr[p.charAt(i) - 'a']++;
		}

		List<Integer> res = new ArrayList<>();
		int si = 0, ci = 0, matched = 0;
		while (true) {

			if (si == s.length() || ci == s.length()) {
				break;
			}

			char curr = s.charAt(ci);
			int currHash = hashr[curr - 'a'];

			if (currHash > 0) {
				hashr[curr - 'a']--;
				matched++;
				if (matched == p.length()) {
					res.add(si);
					hashr[s.charAt(si) - 'a']++;
					si++;
					matched--;
				}
				ci++;
			} else if (currHash == 0) {
				hashr[s.charAt(si) - 'a']++;
				si++;
				matched--;
			} else {
				while (si < ci) {
					hashr[s.charAt(si) - 'a']++;
					si++;
					matched--;
				}

				si++;
				ci++;
			}
		}

		return res;
	}

	public static int lengthOfLongestSubstring(String A) {
		int maxLen = 1, tempLen = 1, si = 0;
		Map<Character, Integer> hashR = new HashMap<>();

		hashR.put(A.charAt(0), 0);

		for (int i = 1; i < A.length(); i++) {
			char curr = A.charAt(i);
			if (hashR.containsKey(curr)) {
				int oldIndex = hashR.get(curr);
				while (si <= oldIndex) {
					hashR.remove(A.charAt(si));
					si++;
					tempLen--;
				}
				tempLen++;
				hashR.put(curr, i);

			} else {
				tempLen++;
				hashR.put(curr, i);
			}

			if (tempLen > maxLen) {
				maxLen = tempLen;
			}
		}

		return maxLen;
	}

}
