package edu.learn.ib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HashingPrac {
	public static void main(String[] args) {
		System.out.println(anagrams(Arrays.asList("b")));
	}

	public static ArrayList<Integer> twoSum(final List<Integer> A, int B) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < A.size(); i++) {
			int curr = A.get(i);
			int diff = B - curr;
			if (map.containsKey(diff)) {
				ArrayList<Integer> res = new ArrayList<Integer>(2);
				res.add(map.get(diff) + 1);
				res.add(i + 1);
				return res;
			} else {
				if (!map.containsKey(curr)) {
					map.put(curr, i);
				}

			}
		}

		return new ArrayList<Integer>(2);
	}

	public static ArrayList<ArrayList<Integer>> anagrams(final List<String> A) {

		Map<String, ArrayList<Integer>> map = new HashMap<>();

		for (int i = 0; i < A.size(); i++) {
			char[] charArray = A.get(i).toCharArray();
			Arrays.sort(charArray);
			String sorted = new String(charArray);

			if (!map.containsKey(sorted)) {
				ArrayList<Integer> val = new ArrayList<>();
				val.add(i + 1);
				map.put(sorted, val);
			} else {
				map.get(sorted).add(i + 1);
			}
		}

		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		for (ArrayList<Integer> e : map.values()) {
			result.add(e);
		}

		return result;
	}

	public int diffPossible(final List<Integer> A, int B) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < A.size(); i++) {
			int curr = A.get(i);
			int old = B + curr;
			int old2 = curr - B;
			if (map.containsKey(old) || map.containsKey(old2)) {
				return 1;
			} else {
				map.put(curr, i);

			}
		}

		return 0;
	}

	public static int colorful(int A) {

		Set<Integer> set = new HashSet<>();
		List<Integer> parts = new ArrayList<>();

		while (A > 0) {
			parts.add(A % 10);
			if (set.contains(A % 10)) {
				return 0;
			}

			set.add(A % 10);
			A = A / 10;

		}

		List<Integer> temp = new ArrayList<>(parts);
		int tempSize = temp.size();
		for (int csize = 2; csize <= parts.size(); csize++) {
			tempSize--;
			for (int i = 0; i < tempSize; i++) {
				int replacingNumber = temp.get(i) * parts.get(i + csize - 1);
				if (set.contains(replacingNumber)) {
					return 0;
				}

				temp.set(i, replacingNumber);
				set.add(replacingNumber);
			}

		}

		return 1;

	}

	public static ArrayList<Integer> equal(List<Integer> A) {
		Map<Integer, Pair> mapping = new HashMap<>();
		Pair pf = null, ps = null;
		int n = A.size();
		for (int i = 0; i < n; i++) {
			int first = A.get(i);
			for (int j = i + 1; j < n; j++) {
				int sec = A.get(j);
				int sum = first + sec;
				if (!mapping.containsKey(sum)) {
					mapping.put(sum, new Pair(i, j));
				} else {
					Pair oldPair = mapping.get(sum);
					if (oldPair.first == i || oldPair.second == i || oldPair.first == j || oldPair.second == j) {
						// Ignore
					} else {
						if (pf == null && ps == null) {
							pf = new Pair(oldPair.first, oldPair.second);
							ps = new Pair(i, j);
						} else {
							updatePair(pf, ps, oldPair.first, oldPair.second, i, j);
						}
					}
				}

			}
		}

		ArrayList<Integer> res = new ArrayList<Integer>();
		if (pf == null && ps == null) {
			return res;
		}
		res.addAll(Arrays.asList(pf.first, pf.second, ps.first, ps.second));

		return res;
	}

	public static void updatePair(Pair pf, Pair ps, int a, int b, int c, int d) {
		if (pf.first < a) {
			return;
		} else if (pf.first > a) {
			pf.first = a;
			pf.second = b;
			ps.first = c;
			ps.second = d;
			return;
		}

		if (pf.second < b) {
			return;
		} else if (pf.second > b) {
			pf.first = a;
			pf.second = b;
			ps.first = c;
			ps.second = d;
			return;
		}

		if (ps.first < c) {
			return;
		} else if (ps.first > c) {
			pf.first = a;
			pf.second = b;
			ps.first = c;
			ps.second = d;
			return;
		}

		if (ps.second < d) {
			return;
		} else if (ps.second > d) {
			pf.first = a;
			pf.second = b;
			ps.first = c;
			ps.second = d;
			return;
		}

	}

	public static class Pair {
		int first, second;

		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}

}
