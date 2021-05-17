package edu.learn.ib.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EqualAveragePartition {

	public static void main(String[] args) {
		System.out.println(avgset(new ArrayList<Integer>(Arrays.asList(41, 46, 9, 14, 19))));
	}

	public static ArrayList<ArrayList<Integer>> avgset(ArrayList<Integer> A) {
		Collections.sort(A);
		int N = A.size();
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum = sum + A.get(i);
		}
		System.out.println(sum);

		boolean shouldProceed = (sum % N == 0);
		if (!shouldProceed) {
			return new ArrayList<>();
		}

		int average = sum / N;
		Boolean[][][] dp = new Boolean[N / 2 + 1][N][sum + 1];

		for (int j = 0; j < N; j++) {
			dp[0][j][0] = true;
		}

		ArrayList<Integer> res1 = null;

		for (int i = 1; i <= N / 2 + 1; i++) {
			res1 = new ArrayList<>();
			int target = average * i;
			boolean isPossible = findP(A, target, 0, dp, i, res1);
			if (isPossible) {
				System.out.println(res1);
				break;
			}
		}

		ArrayList<Integer> res2 = new ArrayList<>();
		int ptr = 0, ptr1 = 0;
		while (ptr1 < res1.size()) {
			int curr = A.get(ptr);
			int curr1 = res1.get(ptr1);
			if (curr != curr1) {
				res2.add(curr);
				ptr++;
			} else {
				ptr++;
				ptr1++;
			}
		}

		while (ptr < A.size()) {
			res2.add(A.get(ptr));
			ptr++;
		}

		ArrayList<ArrayList<Integer>> response = new ArrayList<>();
		if (res1.size() <= res2.size()) {
			response.add(res1);
			response.add(res2);
		} else {

			response.add(res2);
			response.add(res1);

		}
		return response;

	}

	public static boolean findP(List<Integer> input, int target, int index, Boolean[][][] dp, int setSize,
			List<Integer> res1) {
		if (setSize < 0 || target < 0 || index >= input.size()) {
			return false;
		}
		if (dp[setSize][index][target] != null) {
			return dp[setSize][index][target];
		}

		int curr = input.get(index);

		// if curr == target
		if (curr == target) {
			if (setSize == 1) {
				res1.add(curr);
				dp[setSize][index][target] = true;

			} else {
				dp[setSize][index][target] = false;
			}
			return dp[setSize][index][target];
		}

		// not possible
		if (curr > target) {
			return false;
		}

		// possible
		if (curr < target) {
			res1.add(curr);
			boolean res = findP(input, target - curr, index + 1, dp, setSize - 1, res1);
			if (res) {
				dp[setSize][index][target] = true;
			} else {
				res1.remove(res1.size() - 1);
				res = findP(input, target, index + 1, dp, setSize, res1);
				dp[setSize][index][target] = res;
			}

		}

		return dp[setSize][index][target];
	}
}
