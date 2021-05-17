package edu.learn.ib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Heap {

	public static void main(String[] args) {
		System.out.println(heapify(new ArrayList<Integer>(Arrays.asList(4, 10, 3, 5, 1))));
	}

	public static List<Integer> heapify(List<Integer> A) {
		int n = A.size();
		int lnl = (n - 2) / 2;
		for (int i = lnl; i >= 0; i--) {
			heapify(A, i, n);
		}

		return A;
	}

	public static void heapify(List<Integer> A, int i, int n) {
		if (i >= n) {
			return;
		}
		int fi = 2 * i + 1;
		if (fi >= n) {
			return;
		}
		int f = A.get(fi);
		int si = fi + 1;
		int s = si >= n ? Integer.MAX_VALUE : A.get(si);
		int curr = A.get(i);
		int min = Math.min(curr, Math.min(f, s));
		if (min == curr) {
			return;
		}

		if (min == f) {
			A.set(fi, curr);
			A.set(i, f);
			heapify(A, fi, n);
		} else {
			A.set(si, curr);
			A.set(i, s);
			heapify(A, si, n);
		}

	}

}
