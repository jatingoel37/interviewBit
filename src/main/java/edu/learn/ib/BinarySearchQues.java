package edu.learn.ib;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchQues {
	public static void main(String[] args) {
	}

	public static int search(final List<Integer> A, int B) {
		int lo = 0, hi = A.size() - 1;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			int middleNumber = A.get(mid);
			int low = A.get(lo);
			int high = A.get(hi);

			if (middleNumber == B) {
				return mid;
			} else if (middleNumber >= low) {
				if (B < middleNumber && B >= low) {
					hi = mid - 1;
				} else {
					lo = mid + 1;
				}
			} else if (middleNumber <= high) {
				if (B > middleNumber && B <= high) {
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			}

		}

		return -1;
	}

	public static int searchInsert(List<Integer> A, int B) {
		int lo = 0, hi = A.size() - 1;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			int middleNumber = A.get(mid);

			if (middleNumber == B) {
				return mid;
			} else if (B < middleNumber) {

				if (mid == 0) {
					return 0;
				}
				if (A.get(mid - 1) < B) {
					return mid;
				} else {
					hi = mid - 1;
				}

			} else {
				if (mid == A.size() - 1)
					return A.size();

				if (A.get(mid + 1) > B) {
					return mid + 1;
				} else {
					lo = mid + 1;
				}
			}

		}

		return -1;

	}

	public static ArrayList<Integer> searchRange(final List<Integer> A, int B) {
		return searchRange(A, B, 0, A.size() - 1);
	}

	public static ArrayList<Integer> searchRange(final List<Integer> A, int B, int lo, int hi) {
		if (lo > hi) {
			ArrayList<Integer> res = new ArrayList<Integer>(2);
			res.add(-1);
			res.add(-1);
			return res;
		}

		int low = A.get(lo);
		if (lo == hi) {
			ArrayList<Integer> res = new ArrayList<Integer>(2);
			if (low == B) {
				res.add(lo);
				res.add(lo);
			} else {
				res.add(-1);
				res.add(-1);
			}
			return res;

		}

		int mid = (lo + hi) / 2;
		int middle = A.get(mid);

		if (middle == B) {
			ArrayList<Integer> res = new ArrayList<Integer>(2);
			res.add(mid);
			res.add(mid);
			ArrayList<Integer> resl = searchRange(A, B, lo, mid - 1);
			ArrayList<Integer> resr = searchRange(A, B, mid + 1, hi);

			if (resl.get(0) != -1) {
				res.set(0, resl.get(0));
			}

			if (resr.get(1) != -1) {
				res.set(1, resr.get(1));
			}
			return res;

		} else if (middle < B) {
			return searchRange(A, B, mid + 1, hi);
		}

		return searchRange(A, B, lo, mid - 1);
	}

	public int pow(int x, int n, int d) {
		if (x < 0) {
			x = x + d;
		}
		return (int) powI(x, n, d);
	}

	public static long powI(long x, long n, long d) {


		if (x == 0) {
			return 0;
		}
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return x % d;
		}

		long a = x % d;
		if (n == 2) {
			return (a * a) % d;
		}

		long half = n / 2;
		long resh = powI(x, half, d);
		long rest = (resh * resh) % d;

		if (n % 2 == 1) {
			return (rest * a) % d;
		}

		return rest;
	}

}
