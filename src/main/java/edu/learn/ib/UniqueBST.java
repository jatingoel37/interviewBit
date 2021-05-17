package edu.learn.ib;

import java.util.Arrays;

public class UniqueBST {
	
	public static void main(String[] args) {
		System.out.println(numTrees(3));
	}

	public static int numTrees(int A) {

		if (A == 1 || A == 2) {
			return A;
		}

		int[] dp = new int[A + 1];
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i <= A; i++) {
			fillDp(dp, i);
		}
		return dp[A];

	}

	public static void fillDp(int[] dp, int A) {
		int res = 0;
		for (int i = 0; i < A; i++) {
			int j = A - i - 1;
			res = res + dp[i] * dp[j];
		}

		dp[A] = res;
	}
}
