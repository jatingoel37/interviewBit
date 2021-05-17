package edu.learn.ib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BitQues {

	public static int setBits(int A) {
		int count = 0;
		while (A > 0) {
			A = A & (A - 1);
			count++;
		}

		return count;
	}

	public int findMinXor(ArrayList<Integer> A) {
		Collections.sort(A);
		int min = Integer.MAX_VALUE;
		for (int i = 0; i <= A.size() - 2; i++) {
			int temp = A.get(i) ^ A.get(i + 1);
			if (temp < min) {
				min = temp;
			}
		}

		return min;
	}

	public int singleNumber(final List<Integer> A) {

		long res = 0;
		for (int i = 0; i < A.size(); i++) {
			res = res ^ A.get(i);
		}

		return (int) res;
	}

	public long reverse(long a) {
		long res = 0;
		long multiplier = (long) Math.pow(2, 31);

		while (a > 0) {
			long rem = a % 2;
			a = a / 2;

			res = res + multiplier * rem;
			multiplier = multiplier / 2;
		}

		return res;
	}

	public int cntBits(ArrayList<Integer> A) {
		long sum = 0;
		int n = A.size();
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				sum = sum + setBits(A.get(i) ^ A.get(j));
				sum = sum % 1_000_000_007;
			}
		}

		sum = (2 * sum) % 1_000_000_007;
		return (int) sum;
	}

	public int cntBits2(ArrayList<Integer> A) {
		long[] zeroes = new long[32];
		long[] ones = new long[32];
		long sum = 0;

		for (int i = 0; i < 32; i++) {
			for (int n : A) {
				if ((n & 1 << i) == 0) {
					zeroes[i]++;
				} else {
					ones[i]++;
				}
			}

			sum = sum + 2 * zeroes[i] * ones[i];
			sum = sum % 1_000_000_007;
		}

		return (int) sum;
	}

	public int solve(int A) {
		A++;
		long ones = 0;
		long groupSize = 1;
		while (true) {
			long onesInGroup = groupSize;
			groupSize = groupSize * 2;
			long groups = A / groupSize;
			long left = A % groupSize;
			if (groups == 0) {
				if (left > onesInGroup) {
					ones = ones + left - onesInGroup;
				}
				return (int) (ones % 1_000_000_007);
			} else {
				ones = ones + groups * onesInGroup;
				if (left > onesInGroup) {
					ones = ones + left - onesInGroup;
				}
			}

		}
	}

}
