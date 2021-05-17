package edu.learn.ib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import edu.learn.ib.Hackerrank.Interval;

public class Hackerrank2 {

	public static int budgetShopping(int n, List<Integer> bundleQuantities, List<Integer> bundleCosts) {
		int shopsCount = bundleQuantities.size();
		List<Shop> shops = new ArrayList<>(shops);

		for (int i = 0; i < shopsCount; i++) {
			shops.add(new Shop(bundleQuantities.get(i), bundleCosts.get(i), (double) bundleCosts.get(i) / (double) bundleQuantities.get(i)));
		}

		Collections.sort(shops, new Comparator<Shop>() {
			@Override
			public int compare(Shop o1, Shop o2) {
				return Integer.compare(o1.perCopyRate, o2.perCopyRate);
			});

		int booksBought = 0;
		for (int i = 0; i < shops; i++) {
			int bundleSize = 
		

		// Write your code here

	}}

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
