package edu.learn.ib;

import java.util.Arrays;

public class CatalanNumber {

	private int ncr(int n, int k) {
		if (n - k < k) {
			k = n - k;
		}

		int res = 1;
		for (int i = 0; i < k; i++) {
			res = res * (n - k + 1 + i);
			res = res / (i + 1);
		}
		return res;
	}

	private int catalanNumber(int n) {

		if (n == 0) {
			return 1;
		}

		return ncr(2 * n, n) / (n + 1);
	}

	public int numTrees(int A) {

		int[] dp = new int[A];
		Arrays.fill(dp, -1);
		int res = 0;
		for (int i = 0; i < A; i++) {
			int j = A - 1 - i;
			if (dp[i] == -1) {
				dp[i] = catalanNumber(i);
			}

			if (dp[j] == -1) {
				dp[j] = catalanNumber(j);
			}
			res = res + dp[i] * dp[j];
		}

		return res;
	}
}
