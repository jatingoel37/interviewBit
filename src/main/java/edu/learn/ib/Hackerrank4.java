package edu.learn.ib;

import java.util.List;

public class Hackerrank4 {

	public static int budgetShopping(int n, List<Integer> bundleQuantities, List<Integer> bundleCosts) {
		int[] tArray = new int[n + 1];

		int shops = bundleQuantities.size(); // len
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < shops; j++) {
				if (bundleCosts.get(j) <= i) {
					tArray[i] = Math.max(tArray[i - bundleCosts.get(j)] + bundleQuantities.get(j), tArray[i]);
				}
			}
		}

		return tArray[n];
	}

	public static int budgetShopping2(int n, List<Integer> bundleQuantities, List<Integer> bundleCosts) {

		int[] res = new int[n + 1];

		// i rupees
		for (int i = 0; i <= n; i++) {
			for (int shop = 0; shop < bundleQuantities.size(); shop++) {
				if (bundleCosts.get(shop) <= i) {
					res[i] = Math.max(res[i], bundleQuantities.get(shop) + res[i - bundleCosts.get(shop)]);
				}

			}
		}

		return res[n];

	}
}
