package edu.learn.ib;

public class PainterPartition {

	public static void main(String[] args) {
		System.out.println(getTime(3, 10, new int[] { 640, 435, 647, 352, 8, 90, 960, 329, 859 }));
	}

	public static int getTime(int B, int A, int[] arr) {
		int sum = 0, max = 0;
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			int curr = arr[i];
			sum = sum + curr;
			if (curr > max) {
				max = curr;
			}
		}

		long lo = (long) max * (long) A, hi = (long) sum * (long) A;
		long res = hi;
		while (lo <= hi) {
			long mid = (lo + hi) / 2;

			int possiblePainters = getPainters(arr, mid, A);
			if (possiblePainters <= B) {
				res = mid;
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}

		return (int) (res % 10000003);
	}

	public static int getPainters(int[] arr, long timeAllocated, int mul) {
		int painters = 1, n = arr.length;
		long total = 0;
		int i = 0;
		while (i < n) {
			long curr = (long) arr[i] * mul;
			total = total + curr;
			if (total <= timeAllocated) {
				i++;
			} else {
				painters++;
				total = 0;
			}
		}

		return painters;
	}
}
