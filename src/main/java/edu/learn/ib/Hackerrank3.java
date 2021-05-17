package edu.learn.ib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class Hackerrank3 {

	public static void main(String[] args) {
		System.out.println(budgetShopping(50, Arrays.asList(20, 19), Arrays.asList(24, 20)));
	}

	public static int budgetShopping(int n, List<Integer> bundleQuantities, List<Integer> bundleCosts) {
		int shopsCount = bundleQuantities.size();
		List<Shop> shops = new ArrayList<>(shopsCount);

		for (int i = 0; i < shopsCount; i++) {
			shops.add(new Shop(bundleQuantities.get(i), bundleCosts.get(i),
					(double) bundleCosts.get(i) / (double) bundleQuantities.get(i)));
		}

		Collections.sort(shops, new Comparator<Shop>() {

			@Override
			public int compare(Shop o1, Shop o2) {
				return Double.compare(o1.perCopyRate, o2.perCopyRate);
			}
		});

		int booksBought = 0;
		int moneyLeft = n;
		for (int i = 0; i < shopsCount; i++) {
			Shop curr = shops.get(i);
			if (moneyLeft >= curr.bundleRate) {
				int bundleCanBeBougt = moneyLeft / curr.bundleRate;
				moneyLeft = moneyLeft - bundleCanBeBougt * curr.bundleRate;
				booksBought = booksBought + bundleCanBeBougt * curr.bundleSize;
			}
		}

		return booksBought;

	}

	public static class Shop {
		public int bundleSize;
		public int bundleRate;
		public double perCopyRate;

		public Shop(int bundleSize, int bundleRate, double perCopyRate) {
			this.bundleSize = bundleSize;
			this.bundleRate = bundleRate;
			this.perCopyRate = perCopyRate;
		}

	}
}
