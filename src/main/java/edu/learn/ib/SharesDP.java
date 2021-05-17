package edu.learn.ib;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SharesDP {

	public static void main(String[] args) {
		System.out.println(maxProfit(Arrays.asList(1, 2)));
	}

	public static int maxProfit1(final List<Integer> A) {
		int profit = 0;
		int n = A.size();
		if (n <= 1) {
			return 0;
		}

		int maxTillNow = A.get(n - 1);
		for (int i = n - 2; i >= 0; i--) {
			int currValue = A.get(i);
			profit = Math.max(profit, maxTillNow - currValue);
			maxTillNow = Math.max(maxTillNow, currValue);
		}

		return profit;
	}

	/**
	 * Any number of txn
	 * 
	 * @param A
	 * @return
	 */
	public static int maxProfit2Approach2(final List<Integer> A) {

		int n = A.size();
		if (n <= 1) {
			return 0;
		}

		int profit = 0, boughtRate = A.get(0);

		for (int i = 1; i < n; i++) {
			int curr = A.get(i);
			if (curr > boughtRate) {
				profit = profit + curr - boughtRate;
				boughtRate = curr;
			} else if (curr < boughtRate) {
				boughtRate = curr;
			}
		}

		return profit;
	}

	public static int maxProfit(final List<Integer> A) {

		return maxProfit3(A, 2);
	}

	public static int maxProfit3(final List<Integer> prices, int maxTxns) {
		
		int days = prices.size();
		int[][] dp = new int[maxTxns + 1][days + 1];

		int prevDiff = Integer.MIN_VALUE;
		for (int t = 1; t <= maxTxns; t++) {
			for (int d = 2; d <= days; d++) {
				dp[t][d] = Math.max(dp[t][d - 1],
						prices.get(d - 1) + Math.max(prevDiff, dp[t - 1][d - 1] - prices.get(d - 2)));

				prevDiff = Math.max(prevDiff, dp[t - 1][d - 1] - prices.get(d - 2));
			}
		}

		return dp[maxTxns][days];

	}

}
