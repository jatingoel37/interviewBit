package edu.learn.ib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class MergeInterval {

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

	public static void main(String[] args) {
		insert(Arrays.asList(new Interval(1, 2), new Interval(3, 6)), new Interval(8, 10));
	}

	public static ArrayList<Interval> insert(List<Interval> intervals, Interval newInterval) {
		if (intervals.isEmpty()) {
			ArrayList<Interval> res = new ArrayList<Interval>(1);
			res.add(newInterval);
			return res;
		}
		Stack<Interval> stack = new Stack<>();
		boolean newMerged = false;
		Interval curr = intervals.get(0).start < newInterval.start ? intervals.get(0) : newInterval;
		newMerged = curr == newInterval;
		stack.push(curr);
		int i = newMerged ? 0 : 1;
		while (i < intervals.size()) {
			if (newMerged) {
				curr = intervals.get(i);
				i++;
			} else {
				curr = intervals.get(i).start < newInterval.start ? intervals.get(i) : newInterval;
				newMerged = curr == newInterval;
				i = newMerged ? i : i + 1;
			}
			Interval last = stack.peek();
			if (curr.start > last.end) {
				stack.push(curr);
			} else {
				last.end = last.end > curr.end ? last.end : curr.end;
			}
		}

		if (!newMerged) {
			curr = newInterval;
			Interval last = stack.peek();
			if (curr.start > last.end) {
				stack.push(curr);
			} else {
				last.end = last.end > curr.end ? last.end : curr.end;
			}
		}

		ArrayList<Interval> res = new ArrayList<>(stack);
		return res;
	}

	public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
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
