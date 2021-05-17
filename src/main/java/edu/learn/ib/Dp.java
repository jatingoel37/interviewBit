package edu.learn.ib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dp {

	public static void main(String[] args) {
//		System.out.println(
//				wordBreak("myinterviewtrainer", new ArrayList<String>(Arrays.asList("interview", "my", "trainer"))));
		System.out.println(numDecodings("2611055971756562"));
	}

	public int solveTriangle(ArrayList<ArrayList<Integer>> A) {
		int n = A.size();
		int[] dp = new int[n];
		for (int i = 0; i < n; i++) {
			dp[i] = A.get(n - 1).get(i);
		}

		for (int i = n - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				int curr = A.get(i).get(j);
				dp[j] = curr + Math.max(dp[j], dp[j + 1]);
			}
		}

		return dp[0];
	}

	public int[][] kthManhattan(int A, int[][] B) {
		int rows = B.length;
		int cols = B[0].length;
		int[][][] dp = new int[A + 1][rows][cols];

		dp[0] = B;

		for (int k = 1; k <= A; k++) {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {

					int res = dp[k - 1][i][j];
					if (i - 1 >= 0) {
						res = Math.max(res, dp[k - 1][i - 1][j]);
					}

					if (i + 1 < rows) {
						res = Math.max(res, dp[k - 1][i + 1][j]);
					}

					if (j - 1 >= 0) {
						res = Math.max(res, dp[k - 1][i][j - 1]);
					}

					if (j + 1 < cols) {
						res = Math.max(res, dp[k - 1][i][j + 1]);
					}

					dp[k][i][j] = res;

				}
			}
		}
		return dp[A];

	}

	public static int numDecodings(String A) {
		int n = A.length();
		long[] dp = new long[n + 1];

		dp[0] = 1;
		if (A.charAt(0) == '0') {
			return 0;
		} else {
			dp[1] = 1;
		}
		for (int i = 2; i <= n; i++) {
			char curr = A.charAt(i - 1);
			char before = A.charAt(i - 2);
			int number = Integer.valueOf(before + "" + curr);

			if (number % 10 == 0 && number > 26) {
				return 0;
			}

			// combine before and curr
			if (before != '0' && number <= 26) {
				dp[i] = dp[i - 2];
			}

			// consider unique
			if (curr != '0') {
				dp[i] = dp[i] + dp[i - 1];
			}

			dp[i] = dp[i] % 1000_000_007;

		}

		return (int) (dp[n] % 1000_000_007);
	}

	public static int isInterleave(String A, String B, String C) {
		int m = A.length(), n = B.length();
		if (n + m != C.length()) {
			return 0;
		}

		boolean[][] dp = new boolean[m + 1][n + 1];
		dp[0][0] = true;

		// filling first row
		for (int i = 1; i <= n; i++) {
			if (dp[0][i - 1]) {
				dp[0][i] = B.charAt(i - 1) == C.charAt(i - 1);
			}
		}

		// filling first column
		for (int i = 1; i <= m; i++) {
			if (dp[i - 1][0]) {
				dp[i][0] = A.charAt(i - 1) == C.charAt(i - 1);
			}
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (C.charAt(i + j - 1) == A.charAt(i - 1)) {
					dp[i][j] = dp[i - 1][j];
				}

				if (!dp[i][j] && C.charAt(i + j - 1) == B.charAt(j - 1)) {
					dp[i][j] = dp[i][j - 1];
				}
			}
		}

		return dp[m][n] ? 1 : 0;
	}

	public static int wordBreak(String A, ArrayList<String> B) {
		List<Integer> matched = new ArrayList<>();
		int n = A.length();
		Set<String> set = new HashSet<String>(B);
		boolean[] dp = new boolean[n];
		for (int i = 0; i < n; i++) {
			String sunString = A.substring(0, i + 1);
			if (set.contains(sunString)) {
				dp[i] = true;
			} else {
				for (int j : matched) {
					String sub = A.substring(j + 1, i + 1);
					if (set.contains(sub)) {
						dp[i] = true;
						break;
					}

				}

			}

			if (dp[i]) {
				matched.add(i);
			}
		}

		return dp[n - 1] ? 1 : 0;
	}

	public static int numDistinct(String A, String B) {
		int m = A.length(), n = B.length();
		if (m < n) {
			return 0;
		}

		int[][] dp = new int[m + 1][n + 1];

		if (A.charAt(m - 1) == B.charAt(n - 1)) {
			dp[m - 1][n - 1] = 1;
		}

		for (int i = 0; i <= m; i++) {
			dp[i][n] = 1;
		}

		for (int i = m - 2; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				int remA = m - 1 - i + 1;
				int remB = n - 1 - j + 1;
				if (remA >= remB) {
					if (A.charAt(i) == B.charAt(j)) {
						dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
					} else {
						dp[i][j] = dp[i + 1][j];
					}
				}
			}
		}

		return dp[0][0];
	}

	public int arrange(String A, int B) {
		int n = A.length();
		int[][] dp = new int[n + 1][B + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= B; j++) {
				dp[i][j] = -1;
			}
		}

		// for column 2, ie when 1 stable only
		int blacks = 0, whites = 0;
		for (int i = 1; i <= n; i++) {
			char curr = A.charAt(i - 1);
			if (curr == 'B') {
				blacks++;
			} else {
				whites++;
			}

			dp[i][1] = blacks * whites;
		}

		// for other
		for (int j = 2; j <= B; j++) {
			for (int i = j; i <= n; i++) {
				int b = 0, w = 0;
				int res = 0;
				for (int k = 1; k <= i - j + 1; k++) {
					char curr = A.charAt(i - 1 - k + 1);
					if (curr == 'B') {
						b++;
					} else {
						w++;
					}
					res = (b * w) + dp[i - k][j - 1];
					if (dp[i][j] < 0) {
						dp[i][j] = res;
					} else {
						dp[i][j] = Math.min(dp[i][j], res);
					}
				}
			}
		}

		return dp[n][B];

	}

	public static int maxProduct(final List<Integer> A) {
		int n = A.size();
		int resTillNow = A.get(n - 1);
		int ptn = A.get(n - 1) > 0 ? A.get(n - 1) : 0;
		int ntn = A.get(n - 1) < 0 ? A.get(n - 1) : 0;

		for (int i = n - 2; i >= 0; i--) {
			int curr = A.get(i);
			if (curr == 0) {
				ptn = 0;
				ntn = 0;
			} else if (curr > 0) {
				ptn = Math.max(curr, ptn * curr);
				ntn = ntn * curr;
				resTillNow = Math.max(resTillNow, ptn);
			} else {
				int tempN = ntn;
				ntn = Math.min(curr, ptn * curr);
				ptn = tempN * curr;
				resTillNow = Math.max(resTillNow, ptn);
			}
		}

		return resTillNow;
	}

	public int minimumTotal(ArrayList<ArrayList<Integer>> a) {
		int rows = a.size();
		int[] dp = new int[a.get(rows - 1).size()];
		int[] dpTemp = new int[a.get(rows - 1).size()];
		for (int i = 0; i < a.get(rows - 1).size(); i++) {
			dp[i] = a.get(rows - 1).get(i);
		}

		for (int i = rows - 2; i >= 0; i--) {
			ArrayList<Integer> currRow = a.get(i);
			for (int j = 0; j < currRow.size(); j++) {
				dpTemp[j] = currRow.get(j) + Math.min(dp[j], dp[j + 1]);
			}
			int[] temp = dp;
			dp = dpTemp;
			dpTemp = temp;
		}

		return dp[0];
	}

	public int minPathSum(ArrayList<ArrayList<Integer>> A) {
		int rows = A.size();
		int cols = A.get(0).size();
		int[][] dp = new int[rows][cols];
		dp[rows - 1][cols - 1] = A.get(rows - 1).get(cols - 1);
		for (int i = cols - 2; i >= 0; i--) {
			dp[rows - 1][i] = dp[rows - 1][i + 1] + A.get(rows - 1).get(i);
		}

		for (int i = rows - 2; i >= 0; i--) {
			dp[i][cols - 1] = dp[i + 1][cols - 1] + A.get(i).get(cols - 1);
		}

		for (int i = rows - 2; i >= 0; i--) {
			for (int j = cols - 2; j >= 0; j--) {
				dp[i][j] = A.get(i).get(j) + Math.min(dp[i][j + 1], dp[i + 1][j]);
			}
		}

		return dp[0][0];
	}

	public static int chordCnt(int A) {
		if (A <= 1) {
			return 1;
		}

		if (A == 2) {
			return 2;
		}
		long[] dp = new long[A + 1];
		Arrays.fill(dp, -1);
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i <= A; i++) {
			chordCnt(i, dp);
		}
		return (int) dp[A];
	}

	public static long chordCnt(int A, long[] dp) {
		if (dp[A] > -1) {
			return dp[A];
		}

		long ways = 0;
		for (int i = 0; i <= A - 1; i++) {
			ways = ways + (chordCnt(i, dp) * chordCnt(A - i - 1, dp)) % 1000_000_007;
			ways = ways % 1000_000_007;
		}

		dp[A] = ways;
		return ways;

	}

	public static int subMetricsZeroSum(ArrayList<ArrayList<Integer>> A) {
		if (A == null || A.size() == 0 || A.get(0).size() == 0) {
			return 0;
		}

		int rows = A.size(), cols = A.get(0).size();

		int[][] preSum = new int[rows + 1][cols];

		for (int col = 0; col < cols; col++) {
			for (int row = 0; row < rows; row++) {
				preSum[row + 1][col] = preSum[row][col] + A.get(row).get(col);
			}
		}

		int res = 0;
		Map<Integer, Integer> record = new HashMap<>();

		for (int sr = 0; sr < rows; sr++) {
			for (int er = sr; er < rows; er++) {
				record.clear();
				int sumTillNow = 0;
				for (int col = 0; col < cols; col++) {
					sumTillNow = sumTillNow + preSum[er + 1][col] - preSum[sr][col];
					if (sumTillNow == 0) {
						res++;
					}
					int offset = sumTillNow - 0;
					if (record.containsKey(offset)) {
						res = res + record.get(offset);
					}

					if (record.containsKey(sumTillNow)) {
						record.put(sumTillNow, record.get(sumTillNow) + 1);
					} else {
						record.put(sumTillNow, 1);
					}

				}
			}
		}
		return res;
	}

	public int minDistance(String A, String B) {
		int m = A.length(), n = B.length();
		int[][] dp = new int[m + 1][n + 1];
		for (int col = 0; col <= n; col++) {
			dp[0][col] = col;
		}

		for (int row = 0; row <= m; row++) {
			dp[row][0] = row;
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				char curr1 = A.charAt(i - 1);
				char curr2 = B.charAt(j - 1);

				if (curr1 == curr2) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = 1 + Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
				}
			}
		}

		return dp[m][n];
	}

	// -- max binary 1s --
	public static int maximalRectangle(List<List<Integer>> A) {
		int rowsC = A.size();
		int colsC = A.get(0).size();

		int[][] rows = new int[rowsC + 1][colsC + 1];
		int[][] cols = new int[rowsC + 1][colsC + 1];

		for (int i = rowsC - 1; i >= 0; i--) {

			for (int j = colsC - 1; j >= 0; j--) {
				int curr = A.get(i).get(j);
				if (curr == 0) {
					rows[i][j] = 0;
					cols[i][j] = 0;
				} else {
					rows[i][j] = 1 + rows[i + 1][j];
					cols[i][j] = 1 + cols[i][j + 1];
				}
			}
		}

		int[][] dprows = new int[rowsC + 1][colsC + 1];
		int[][] dpcols = new int[rowsC + 1][colsC + 1];
		int mi = 0, mj = 0;
		int max = 0;
		for (int i = rowsC - 1; i >= 0; i--) {

			for (int j = colsC - 1; j >= 0; j--) {
				int curr = A.get(i).get(j);
				if (curr == 0) {
					// noop
				} else {
					int temp = 0;
					int option1 = rows[i][j];
					int option2 = cols[i][j];
					if (option1 > option2) {
						temp = option1;
						dprows[i][j] = rows[i][j];
						dpcols[i][j] = 1;
					} else if (option2 > option1) {
						temp = option2;
						dprows[i][j] = 1;
						dpcols[i][j] = cols[i][j];
					} else {
						temp = option2;
						dprows[i][j] = rows[i][j];
						dpcols[i][j] = cols[i][j];

					}

					int option3 = (1 + Math.min(dprows[i + 1][j], dprows[i + 1][j + 1]))
							* (1 + Math.min(dpcols[i][j + 1], dpcols[i + 1][j + 1]));

					if (option3 > temp) {
						temp = option3;
						dprows[i][j] = 1 + Math.min(dprows[i + 1][j], dprows[i + 1][j + 1]);
						dpcols[i][j] = 1 + Math.min(dpcols[i][j + 1], dpcols[i + 1][j + 1]);
					}

					if (temp > max) {

						max = temp;
						mi = i;
						mj = j;
					}
				}
			}
		}

		System.out.println("-------");
		System.out.println(mi);
		System.out.println(mj);
		System.out.println("-------");
		return max;
	}

	// -- egg break --

	public static int eggBreak(int eggs, int floors) {

		int[][] dp = new int[eggs + 1][floors + 1];
		return eggBreak(eggs, floors, dp);

	}

	public static int eggBreak(int eggs, int floors, int[][] dp) {

		if (eggs == 0) {
			dp[eggs][floors] = 0;
			return 0;
		}

		if (eggs == 1) {
			dp[eggs][floors] = floors;
			return floors;
		}

		if (floors == 0) {
			dp[eggs][floors] = 0;
			return 0;

		}

		if (dp[eggs][floors] > 0) {
			return dp[eggs][floors];
		}

		int minTillNow = floors;
		int temp = floors;
		for (int floor = 1; floor <= floors; floor++) {
			temp = 1 + Math.max(eggBreak(eggs - 1, floor - 1, dp), eggBreak(eggs, floors - floor, dp));
			if (temp < minTillNow) {
				minTillNow = temp;
			}
		}

		dp[eggs][floors] = minTillNow;
		return minTillNow;
	}

	// -- LIS --
	public static int lis(final List<Integer> A) {
		int size = A.size();
		int[] dp = new int[size];
		int max = 1;

		for (int i = size - 1; i >= 0; i--) {
			dp[i] = 1;
		}

		for (int i = size - 2; i >= 0; i--) {
			int curr = A.get(i);
			for (int j = i + 1; j < size; j++) {
				if (curr < A.get(j)) {
					if (1 + dp[j] > dp[i]) {
						dp[i] = 1 + dp[j];
					}
				}
			}

			if (dp[i] > max) {
				max = dp[i];
			}
		}

		return max;
	}

	// -- max subsize square matrix --

	public int maxSubsize(ArrayList<ArrayList<Integer>> A) {
		int rows = A.size();
		int cols = A.get(0).size();

		int[][] dp = new int[rows + 1][cols + 1];
		int res = 0;

		for (int row = rows - 1; row >= 0; row--) {
			for (int col = cols - 1; col >= 0; col--) {
				int val = A.get(row).get(col);
				if (val == 1) {
					dp[row][col] = 1 + Math.min(dp[row + 1][col], Math.min(dp[row][col + 1], dp[row + 1][col + 1]));
				} else {
					dp[row][col] = 0;
				}

				if (dp[row][col] > res) {
					res = dp[row][col];
				}
			}
		}

		return res * res;

	}

	// -- LCS --

	public int lcs(String A, String B) {
		int len1 = A.length();
		int len2 = B.length();
		int[][] dp = new int[len1 + 1][len2 + 1];

		for (int i = len1 - 1; i >= 0; i--) {
			char char1 = A.charAt(i);
			for (int j = len2 - 1; j >= 0; j--) {
				char char2 = B.charAt(j);
				if (char1 == char2) {
					dp[i][j] = 1 + dp[i + 1][j + 1];
				} else {
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
				}

			}
		}

		return dp[0][0];
	}

	public int lcs2(String A, String B) {
		int n = A.length(), m = B.length();
		int[][] dp = new int[n + 1][m + 1];

		for (int i = n - 1; i >= 0; i--) {
			char f = A.charAt(i);
			for (int j = m - 1; j >= 0; j--) {
				char s = A.charAt(j);
				if (f == s) {
					dp[i][j] = 1 + dp[i + 1][j + 1];
				} else {
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
				}
			}
		}

		return n + m - dp[n][m];
	}

	// --LPS --
	public int lps(String A) {
		int size = A.length();
		int[][] dp = new int[size][size];

		// length = 1
		for (int i = 0; i < size; i++) {
			dp[i][i] = 1;
		}

		// length = 2
		for (int i = 0; i < size; i++) {
			int j = i + 1;
			if (j < size) {
				dp[i][j] = A.charAt(i) == A.charAt(j) ? 2 : 1;
			}
		}

		for (int len = 3; len <= size; len++) {
			for (int i = 0; i < size; i++) {
				int j = i + len - 1;
				if (j < size) {
					dp[i][j] = A.charAt(i) == A.charAt(j) ? 2 + dp[i + 1][j - 1] : Math.max(dp[i + 1][j], dp[i][j - 1]);
				}
			}
		}

		return dp[0][size - 1];
	}

	// -- unique path --

	public int solve(ArrayList<ArrayList<Integer>> A) {
		int rows = A.size();
		int cols = A.get(0).size();

		int[][] dp = new int[rows + 1][cols + 1];
		int res = A.get(rows - 1).get(cols - 1);

		for (int i = rows - 1; i >= 0; i--) {
			for (int j = cols - 1; j >= 0; j--) {
				int curr = A.get(i).get(j);
				int potRes = curr + dp[i + 1][j] + dp[i][j + 1] - dp[i + 1][j + 1];
				if (potRes > res) {
					res = potRes;
				}
				dp[i][j] = potRes;
			}
		}

		return res;
	}

	public int uniquePathsWithObstacles(ArrayList<ArrayList<Integer>> A) {
		int rows = A.size();
		int cols = A.get(0).size();
		int[][] dp = new int[rows][cols];
		dp[rows - 1][cols - 1] = A.get(rows - 1).get(cols - 1) == 1 ? 0 : 1;

		// filling last row(row-1), col cols-2...0
		List<Integer> lastRow = A.get(rows - 1);
		for (int col = cols - 2; col >= 0; col--) {
			int obstacle = lastRow.get(col);
			if (obstacle == 1) {
				dp[rows - 1][col] = 0;
			} else {
				dp[rows - 1][col] = dp[rows - 1][col + 1];
			}
		}

		// fill last column(col-1), rows-> rows-2...0
		for (int row = rows - 2; row >= 0; row--) {
			int obstacle = A.get(row).get(cols - 1);
			if (obstacle == 1) {
				dp[row][cols - 1] = 0;
			} else {
				dp[row][cols - 1] = dp[row + 1][cols - 1];
			}
		}

		for (int row = rows - 2; row >= 0; row--) {
			for (int col = cols - 2; col >= 0; col--) {
				int obstacle = A.get(row).get(col);
				if (obstacle == 1) {
					dp[row][col] = 0;
				} else {
					dp[row][col] = dp[row + 1][col] + dp[row][col + 1];
				}
			}
		}

		return dp[0][0];

	}

	// -- can jump --
	public static int canJump(List<Integer> A) {
		int positions = A.size();
		int minPossiblePosition = positions - 1;

		for (int i = positions - 2; i >= 0; i--) {
			int currPossibleJumps = A.get(i);
			if (i + currPossibleJumps >= minPossiblePosition) {
				minPossiblePosition = i;
			}
		}

		return minPossiblePosition == 0 ? 1 : 0;
	}

	// -- make sum N --

	public static int coinchange2(List<Integer> A, int n) {
		int m = A.size();
		int[][] dp = new int[n + 1][m];

		// filling 1st column
		for (int i = 1; i <= n; i++) {
			int coinValue = A.get(0);
			if (i % coinValue == 0) {
				dp[i][0] = 1;
			}
		}

		for (int target = 2; target <= n; target++) {
			int ways = 0;
			for (int coini = 1; coini < m; coini++) {
				int coinValue = A.get(coini);

				// when coini-th is there
				if (coinValue <= target) {
					ways = (ways + dp[target - coinValue][coini]) % 1000007;
				}

				// if coini-th not present
				ways = (ways + dp[target][coini - 1]) % 1000007;

				dp[target][coini] = ways;

			}
		}

		return (dp[n][m - 1]) % 1000007;

	}

	// -- climb stairs --
	public int climbStairs(int A) {

		if (A == 1) {
			return 1;
		}

		if (A == 2) {
			return 2;
		}

		int[] ways = new int[A];
		ways[A - 1] = 1;
		ways[A - 2] = 2;

		for (int i = A - 3; i >= 0; i--) {
			ways[i] = ways[i + 1] + ways[i + 2];
		}

		return ways[0];
	}

	// -- minimum paint ---
	public int minPaintCost(ArrayList<ArrayList<Integer>> A) {
		int totalHouses = A.size();
		int[] redMinCost = new int[totalHouses];
		redMinCost[totalHouses - 1] = A.get(totalHouses - 1).get(0);
		int[] blueMinCost = new int[totalHouses];
		blueMinCost[totalHouses - 1] = A.get(totalHouses - 1).get(1);
		int[] greenMinCost = new int[totalHouses];
		greenMinCost[totalHouses - 1] = A.get(totalHouses - 1).get(2);

		for (int i = totalHouses - 2; i >= 0; i--) {
			ArrayList<Integer> paintCost = A.get(i);
			redMinCost[i] = paintCost.get(0) + Math.min(blueMinCost[i + 1], greenMinCost[i + 1]);
			blueMinCost[i] = paintCost.get(1) + Math.min(redMinCost[i + 1], greenMinCost[i + 1]);
			greenMinCost[i] = paintCost.get(2) + Math.min(blueMinCost[i + 1], redMinCost[i + 1]);
		}

		return Math.min(redMinCost[0], Math.min(blueMinCost[0], greenMinCost[0]));

	}

	// -- merge minimum cost --
	public static int mergeMinimum(List<Integer> A) {
		int size = A.size();
		int[] costs = new int[size];
		costs[size - 1] = 0;
		costs[size - 2] = A.get(size - 1) + A.get(size - 2);
		int sumFromI = costs[size - 2];
		for (int i = size - 3; i >= 0; i--) {
			int curr = A.get(i);
			costs[i] = Math.min(costs[i + 1] + curr + sumFromI, costs[i + 2] + curr + A.get(i + 1) + sumFromI + curr);

			sumFromI = sumFromI + curr;
		}

		int[] costsE = new int[size];
		costsE[0] = 0;
		costsE[1] = A.get(0) + A.get(1);
		int sumTillI = costsE[1];

		for (int i = 2; i < size; i++) {
			int curr = A.get(i);
			costsE[i] = Math.min(costsE[i - 1] + curr + sumTillI,
					costsE[i - 2] + curr + A.get(i - 1) + sumTillI + curr);
			sumTillI = sumTillI + curr;
		}

		int min = Math.min(costs[0], costsE[size - 1]);
		for (int i = 0; i < size - 1; i++) {
			int currCost = costsE[i] + costs[i + 1] + sumFromI;
			if (currCost < min) {
				min = currCost;
			}
		}
		return min;
	}

	// -- max without adjacent
	public int adjacent(ArrayList<ArrayList<Integer>> A) {
		ArrayList<Integer> array1 = A.get(0);
		ArrayList<Integer> array2 = A.get(1);
		int size = array1.size();

		int[] inclusive = new int[size];
		int[] exclusive = new int[size];

		inclusive[size - 1] = Math.max(array1.get(size - 1), array2.get(size - 1));

		for (int i = size - 2; i >= 0; i--) {
			inclusive[i] = Math.max(array1.get(i), array2.get(i)) + exclusive[i + 1];
			exclusive[i] = Math.max(exclusive[i + 1], inclusive[i + 1]);
		}

		return Math.max(inclusive[0], exclusive[0]);
	}

	// -- eval to true --

	public static int cnttrue(String A) {
		int operandCount = A.length() / 2 + 1;
		char[] operands = new char[operandCount];
		char[] operators = new char[A.length() / 2];
		int operandI = 0, operatorI = 0;
		for (int i = 0; i < A.length(); i++) {
			char curr = A.charAt(i);
			if (i % 2 == 0) {
				operands[operandI] = curr;
				operandI++;
			} else {
				operators[operatorI] = curr;
				operatorI++;
			}
		}

		int[][] dp = new int[operandCount][operandCount];
		int[][] dpF = new int[operandCount][operandCount];
		for (int i = 0; i < operandCount; i++) {
			dp[i][i] = operands[i] == 'T' ? 1 : 0;
			dpF[i][i] = operands[i] == 'T' ? 0 : 1;
		}

		for (int len = 2; len <= operandCount; len++) {
			for (int i = 0; i < operandCount; i++) {
				int j = i + len - 1;
				int countT = 0;
				int countF = 0;
				if (j >= operandCount) {
					break;
				}

				// operator will go from i to j-1
				for (int oi = i; oi <= j - 1; oi++) {
					char currentOperator = operators[oi];
					if (currentOperator == '&') {
						countT = countT + (dp[i][oi] * dp[oi + 1][j]) % 1003;
						countF = countF + (dpF[i][oi] * dp[oi + 1][j]) % 1003 + (dpF[i][oi] * dpF[oi + 1][j]) % 1003
								+ (dp[i][oi] * dpF[oi + 1][j]) % 1003;
					} else if (currentOperator == '|') {
						countT = countT + (dp[i][oi] * dp[oi + 1][j]) % 1003 + (dpF[i][oi] * dp[oi + 1][j]) % 1003
								+ (dp[i][oi] * dpF[oi + 1][j]) % 1003;
						countF = countF + (dpF[i][oi] * dpF[oi + 1][j]) % 1003;
					} else {
						countT = countT + (dp[i][oi] * dpF[oi + 1][j]) % 1003 + (dpF[i][oi] * dp[oi + 1][j]) % 1003;
						countF = countF + (dpF[i][oi] * dpF[oi + 1][j]) % 1003 + (dp[i][oi] * dp[oi + 1][j]) % 1003;
					}
				}
				dp[i][j] = countT;
				dpF[i][j] = countF;
			}
		}

		return (int) (dp[0][operandCount - 1] % 1003);
	}

	// -- coins in a line--
	public static int maxcoin(List<Integer> A) {
		int count = A.size();
		int[][] dp = new int[count][count];

		// when 2 coins only
		for (int sci = 0; sci < count; sci++) {
			int eci = sci + 1;
			if (eci >= count) {
				continue;
			}

			dp[sci][eci] = Math.max(A.get(sci), A.get(eci));
		}

		// for 4 to count
		int cc = 4;
		for (int sci = 0; sci < count; sci++) {
			if (cc > count) {
				break;
			}
			int eci = sci + cc - 1;
			if (eci >= count) {
				cc = cc + 2;
				sci = -1;
				continue;
			}
			int sc = A.get(sci);
			int ec = A.get(eci);
			int poss1 = ec + Math.min(dp[sci][eci - 2], dp[sci + 1][eci - 1]);
			int poss2 = sc + Math.min(dp[sci + 2][eci], dp[sci + 1][eci - 1]);
			dp[sci][eci] = Math.max(poss1, poss2);
		}

		return dp[0][count - 1];

	}

	// --- regex matching ---

	public static int isMatch(final String A, final String B) {
		int[][] dp = new int[A.length() + 1][B.length() + 1];

		// fill when both reach end
		dp[A.length()][B.length()] = 1;

		for (int row = 0; row < A.length(); row++) {
			dp[row][B.length()] = -1;
		}

		for (int col = B.length() - 1; col >= 0; col--) {
			char patternChar = B.charAt(col);
			if (patternChar == '*') {
				dp[A.length()][col] = dp[A.length()][col + 1];
			} else {
				dp[A.length()][col] = -1;
			}
		}

		for (int i = A.length() - 1; i >= 0; i--) {
			for (int j = B.length() - 1; j >= 0; j--) {
				isMatch(A, i, B, j, dp);
			}
		}
		return dp[0][0] == 1 ? 1 : 0;
	}

	public static boolean isMatch(final String text, int ti, final String pattern, int pi, int[][] dp) {

		if (dp[ti][pi] != 0) {
			return dp[ti][pi] == 1 ? true : false;
		}

		boolean matched = false;

		char pc = pattern.charAt(pi);
		if (pc == '*') {
			matched = isMatch(text, ti + 1, pattern, pi, dp) || isMatch(text, ti, pattern, pi + 1, dp);
		} else if (pc == '?') {
			matched = isMatch(text, ti + 1, pattern, pi + 1, dp);
		} else {
			char tc = text.charAt(ti);
			if (tc != pc) {
				matched = false;
			} else {
				matched = isMatch(text, ti + 1, pattern, pi + 1, dp);
			}
		}

		if (matched) {
			dp[ti][pi] = 1;
		} else {
			dp[ti][pi] = -1;
		}
		return dp[ti][pi] == 1 ? true : false;
	}

}
