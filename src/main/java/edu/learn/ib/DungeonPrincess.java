package edu.learn.ib;

import java.util.ArrayList;

public class DungeonPrincess {

	public int calculateMinimumHP(ArrayList<ArrayList<Integer>> A) {
		int rows = A.size();
		int cols = A.get(0).size();
		long[][] dp = new long[rows][cols];

		dp[rows - 1][cols - 1] = toSurvive(A.get(rows - 1).get(cols - 1));
		for (int i = cols - 2; i >= 0; i--) {
			int row = rows - 1;
			ArrayList<Integer> rowV = A.get(row);
			int powerAlloted = rowV.get(i);
			dp[row][i] = goNext(powerAlloted, dp[row][i + 1]);
		}

		for (int i = rows - 2; i >= 0; i--) {
			int col = cols - 1;
			int powerAlloted = A.get(i).get(col);
			dp[i][col] = goNext(powerAlloted, dp[i + 1][col]);
		}

		for (int i = rows - 2; i >= 0; i--) {
			ArrayList<Integer> rowV = A.get(i);
			for (int j = cols - 2; j >= 0; j--) {
				int powerAlloted = rowV.get(j);
				dp[i][j] = Math.min(goNext(powerAlloted, dp[i][j + 1]), goNext(powerAlloted, dp[i + 1][j]));
			}
		}

		return (int) dp[0][0];
	}

	private static long goNext(long y, long z) {
		if (z == 0) {
			z = 1;
		}
		long x = z - y;
		if (x > 0) {
			return x;
		}
		return 1;

	}

	private static long toSurvive(long power) {
		long x = 1L - power;
		if (x > 0) {
			return x;
		}

		return 1;
	}
}
