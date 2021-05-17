package edu.learn.ib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class Hackerrank5 {

	public static void main(String[] args) {
		int n = 3;
		LastCount lc = new LastCount(0, 1);

		for (int i = 1; i < n; i++) {
			lc = getCount(true, lc.lastBlack, lc.lastWhite, i == n - 1);
		}

		LastCount lc2 = new LastCount(1, 0);

		for (int i = 1; i < n; i++) {
			lc2 = getCount(false, lc2.lastBlack, lc2.lastWhite, i == n - 1);
		}

		System.out.println(lc.total() + lc2.total());
	}

	public static int sol(int n) {
		LastCount lc = new LastCount(0, 1);

		for (int i = 1; i < n; i++) {
			lc = getCount(true, lc.lastBlack, lc.lastWhite, i == n - 1);
		}

		LastCount lc2 = new LastCount(1, 0);

		for (int i = 1; i < n; i++) {
			lc2 = getCount(false, lc2.lastBlack, lc2.lastWhite, i == n - 1);
		}

		return (int) ((lc.total() + lc2.total()) % 1_000_000_007);
	}

	public static LastCount getCount(boolean whiteSeries, long lastBlack, long lastWhite, boolean last) {
		if (!last) {

			return new LastCount(lastWhite, lastBlack + lastWhite);

		}

		// last

		if (whiteSeries) {
			return new LastCount(lastWhite, lastBlack + lastWhite);
		}

		return new LastCount(0, lastBlack + lastWhite);

	}

	public static class LastCount {
		public long lastBlack;
		public long lastWhite;

		public LastCount(long lastBlack, long lastWhite) {
			this.lastBlack = lastBlack;
			this.lastWhite = lastWhite;
		}

		public long total() {
			return (lastBlack + lastWhite) % 1_000_000_007;
		}

	}
}
