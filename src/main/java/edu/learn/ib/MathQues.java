package edu.learn.ib;

import java.util.List;

public class MathQues {
	public static void main(String[] args) {
		System.out.println(trailingZeroes(10));
	}

	public static int trailingZeroes(int A) {
		int divisor = 5;
		int res = 0;
		while (true) {
			int add = A / divisor;
			if (add <= 0) {
				break;
			} else {
				res = res + add;
				divisor = divisor * 5;
			}

		}

		return res;
	}

	public static void arrange(List<Integer> a) {
		int n = a.size();
		for (int i = 0; i < n; i++) {
			int curr = a.get(i) % n;
			int tbr = a.get(curr) % n;
			a.set(i, n * tbr + curr);
		}

		for (int i = 0; i < n; i++) {
			int curr = a.get(i);
			a.set(i, curr / n);
		}

		// System.out.println(a);

	}

	public int gcd(int A, int B) {

		if (A < B) {
			return gcd(B, A);
		}

		if (B == 0) {
			return A;
		}

		if (B == 1) {
			return 1;
		}

		return gcd(B, A % B);
	}

	public static int isPalindrome(int A) {
		int cache = A;
		boolean negative = A < 0;
		if (negative) {
			return 0;
		}

		long temp = 0;

		while (A > 0) {
			temp = temp * 10;
			int digit = A % 10;
			A = A / 10;
			temp = temp + (long) digit;

		}

		if (temp < Integer.MIN_VALUE || temp > Integer.MAX_VALUE) {
			return 0;
		}
		return temp == cache ? 1 : 0;

	}

	public static int reverse(int A) {
		boolean negative = A < 0;
		A = Math.abs(A);

		long temp = 0;

		while (A > 0) {
			temp = temp * 10;
			int digit = A % 10;
			A = A / 10;
			temp = temp + (long) digit;

		}

		if (temp < Integer.MIN_VALUE || temp > Integer.MAX_VALUE) {
			return 0;
		}
		return (int) (negative ? -1 * temp : temp);
	}

	public int titleToNumber(String A) {
		int res = 0;
		int mul = 1;
		int n = A.length();
		for (int i = n - 1; i >= 0; i--) {
			char curr = A.charAt(i);
			int value = curr - 'A' + 1;
			res = res + value * mul;
			mul = mul * 26;
		}

		return res;
	}

	public static String convertToTitle(int A) {
		StringBuilder sb = new StringBuilder();
		while (A > 0) {
			int rem = A % 26;
			A = (A - 1) / 26;
			if (rem == 0) {
				sb.append('Z');
			} else {
				sb.append((char) ('A' + rem - 1));
			}

		}

		sb.reverse();
		return sb.toString();
	}

	public int uniquePaths(int A, int B) {
		int[][] dp = new int[A][B];

		for (int c = 0; c < B; c++) {
			dp[A - 1][c] = 1;

		}

		for (int r = 0; r < A; r++) {
			dp[r][B - 1] = 1;

		}

		for (int i = A - 2; i >= 0; i--) {
			for (int j = B - 2; j >= 0; j--) {
				dp[i][j] = dp[i + 1][j] + dp[i][j + 1];
			}
		}

		return dp[0][0];
	}

}
