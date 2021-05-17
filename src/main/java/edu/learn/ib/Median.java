package edu.learn.ib;

import static java.util.Arrays.asList;

import java.util.List;

public class Median {

	public static void main(String[] args) {
		findMedianSortedArrays(asList(-2), asList(-43, -25, -18, -15, -10, 9, 39, 40));

	}

	/**
	 * a is smaller
	 */

	public static double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {

		if (a.size() < b.size()) {
			double res = median(a, b);
			System.out.println(res);
			return res;
		}

		double res = median(b, a);
		System.out.println(res);
		return res;
	}

	public static double median(List<Integer> A, List<Integer> B) {

		int m = A.size(), n = B.size();

		if (m == 0) {
			if (n % 2 == 1) {
				return B.get(n / 2);
			}

			return ((double) B.get(n / 2) + (double) B.get(n / 2 - 1)) / 2;
		}
		int targetLeftCount = (m + n) / 2;
		int min = 0, max = m - 1;

		while (min <= max) {
			int fbi = (min + max) / 2;
			int fc = fbi + 1;
			int sc = targetLeftCount - fc;
			int sbi = sc - 1;

			int a = A.get(fbi);
			int b = fbi >= m - 1 ? Integer.MAX_VALUE : A.get(fbi + 1);
			int c = B.get(sbi);
			int d = sbi >= n - 1 ? Integer.MAX_VALUE : B.get(sbi + 1);

			if (a <= d && c <= b) {
				// we found answer
				if ((n + m) % 2 == 1) { // odd
					return Math.min(b, d);
				} else {
					return ((double) Math.max(a, c) + (double) Math.min(b, d)) / 2;
				}
			} else if (a > d) {
				max = fbi - 1;
			} else if (c > b) {
				min = fbi + 1;
			}

		}

		if (max < 0) {
			int a = Integer.MIN_VALUE;
			int b = A.get(0);
			int sbi = targetLeftCount - 1;
			int c = B.get(sbi);
			int d = sbi >= n - 1 ? Integer.MAX_VALUE : B.get(sbi + 1);
			if ((n + m) % 2 == 1) { // odd
				return Math.min(b, d);
			} else {
				return ((double) Math.max(a, c) + (double) Math.min(b, d)) / 2;
			}

		}

		int a = A.get(m - 1);
		int b = Integer.MAX_VALUE;
		int sc = targetLeftCount - m;
		int sbi = sc - 1;
		int c = sbi < 0 ? Integer.MIN_VALUE : B.get(sbi);
		int d = B.get(sbi + 1);

		if ((n + m) % 2 == 1) { // odd
			return Math.min(b, d);
		} else {
			return ((double) Math.max(a, c) + (double) Math.min(b, d)) / 2;
		}

	}
}
