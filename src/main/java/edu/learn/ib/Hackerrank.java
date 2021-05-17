package edu.learn.ib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class Hackerrank {

	public static void main(String[] args) {
		System.out.println(widestGap(10, Arrays.asList(1, 2, 6, 6), Arrays.asList(4, 4, 10, 8)));
	}

	public static int widestGap(int n, List<Integer> start, List<Integer> finish) {

		int cars = start.size();

		ArrayList<Interval> intervals = new ArrayList<>(cars);
		for (int i = 0; i < cars; i++) {
			int s = start.get(i);
			int e = finish.get(i);

			intervals.add(new Interval(s, e));
		}

		intervals = merge(intervals);
		Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return Integer.compare(o1.start, o2.start);
			}
		});
		int intervalsCount = intervals.size();

		int tempRes = intervals.get(0).start - 1;
		int last = n - intervals.get(intervalsCount - 1).end;

		for (int i = 0; i <= intervalsCount - 2; i++) {
			Interval curr = intervals.get(i);
			Interval next = intervals.get(i + 1);

			if (next.start - curr.end - 1 > tempRes) {
				tempRes = next.start - curr.end - 1;
			}
		}

		return Math.max(tempRes, last);

	}

	public static class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;
		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}
	}

	public static ArrayList<Interval> merge(ArrayList<Interval> intervals) {
		Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return Integer.compare(o1.start, o2.start);
			}
		});

		Stack<Interval> stack = new Stack<>();
		stack.push(intervals.get(0));

		int i = 1;
		while (i < intervals.size()) {
			Interval curr = intervals.get(i);
			Interval last = stack.peek();
			if (curr.start > last.end) {
				stack.push(curr);
			} else {
				last.end = last.end > curr.end ? last.end : curr.end;
			}
			i++;
		}

		ArrayList<Interval> res = new ArrayList<>(stack);
		return res;
	}

}
