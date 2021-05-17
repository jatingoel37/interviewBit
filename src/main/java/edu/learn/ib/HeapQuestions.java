package edu.learn.ib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class HeapQuestions {

	static class ListNode {
		public int val;
		public ListNode next;

		public ListNode(int x) {
			val = x;
			next = null;
		}

		ListNode(int x, ListNode nxt) {
			val = x;
			next = nxt;
		}
	}

	public static ListNode mergeKLists(ArrayList<ListNode> a) {
		PriorityQueue<ListNode> q = new PriorityQueue<>(new Comparator<ListNode>() {

			@Override
			public int compare(ListNode o1, ListNode o2) {
				return Integer.compare(o1.val, o2.val);
			}
		});
		for (ListNode ln : a) {
			q.add(ln);
		}
		ListNode res = null, tail = null;
		while (!q.isEmpty()) {
			ListNode next = q.remove();
			if (res == null) {
				res = next;
				tail = next;
				if (tail.next != null) {
					q.add(tail.next);
				}

				tail.next = null;
			} else {
				tail.next = next;
				if (next.next != null) {
					q.add(next.next);
				}
				tail = tail.next;
				tail.next = null;
			}
		}

		return res;
	}

	public int nchoc(int A, ArrayList<Integer> B) {
		PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return -1 * Integer.compare(o1, o2);
			}
		});

		for (Integer i : B) {
			q.add(i);
		}
		int res = 0;

		for (int i = 0; i < A; i++) {
			int choc = q.remove();
			res = (res + choc % 1000_000_007) % 1000_000_007;
			q.add(choc / 2);
		}

		return res;
	}

	public static void main(String[] args) {
		System.out.println(dNums(new ArrayList<Integer>(Arrays.asList(80, 18, 80, 80, 80, 80, 80, 80, 94, 18)), 8));
	}

	public static ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<Integer> B) {
		Collections.sort(A);
		Collections.sort(B);
		int n = A.size();

		HashSet<SumPair> set = new HashSet<>();
		PriorityQueue<SumPair> q = new PriorityQueue<>(new Comparator<SumPair>() {

			@Override
			public int compare(SumPair o1, SumPair o2) {
				return -1 * Integer.compare(o1.sum, o2.sum);
			}
		});

		ArrayList<Integer> res = new ArrayList<>();
		q.add(new SumPair(A.get(n - 1) + B.get(n - 1), n - 1, n - 1));

		for (int i = 0; i < n; i++) {
			SumPair fetched = q.remove();
			res.add(fetched.sum);
			int ci = fetched.i;
			int cj = fetched.j;
			if (ci - 1 >= 0) {
				SumPair s1 = new SumPair(A.get(ci - 1) + B.get(cj), ci - 1, cj);
				if (!set.contains(s1)) {
					q.add(s1);
					set.add(s1);
				}

			}

			if (cj - 1 >= 0) {
				SumPair s2 = new SumPair(A.get(ci) + B.get(cj - 1), ci, cj - 1);
				if (!set.contains(s2)) {
					q.add(s2);
					set.add(s2);
				}
			}
		}

		return res;

	}

	static class SumPair {
		public int sum, i, j;

		public SumPair(int sum, int i, int j) {
			this.sum = sum;
			this.i = i;
			this.j = j;
		}

		@Override
		public boolean equals(Object obj) {
			SumPair other = (SumPair) obj;
			return other.i == i && other.j == j;
		}

		@Override
		public int hashCode() {
			return Objects.hash(i, j);
		}

	}

	public static ArrayList<Integer> dNums(ArrayList<Integer> A, int B) {
		int n = A.size();
		if (B > n) {
			return new ArrayList<Integer>(0);
		}
		ArrayList<Integer> result = new ArrayList<>();
		Map<Integer, Integer> rec = new HashMap<>();
		for (int i = 0; i < B; i++) {
			int curr = A.get(i);
			if (!rec.containsKey(curr)) {
				rec.put(curr, 1);
			} else {
				rec.put(curr, rec.get(curr) + 1);
			}
		}
		result.add(rec.size());
		for (int i = B; i < n; i++) {
			int old = A.get(i - B);
			int curr = A.get(i);
			if (rec.get(old) == 1) {
				rec.remove(old);
			} else {
				rec.put(old, rec.get(old) - 1);
			}

			if (!rec.containsKey(curr)) {
				rec.put(curr, 1);
			} else {
				rec.put(curr, rec.get(curr) + 1);
			}

			result.add(rec.size());
		}

		return result;
	}

}
