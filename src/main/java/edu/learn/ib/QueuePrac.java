package edu.learn.ib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class QueuePrac {

	public static void main(String[] args) {
		System.out.println(slidingMaximum(Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1), 1));
	}

	public static ArrayList<Integer> slidingMaximum(final List<Integer> A, int B) {
		LinkedList<Integer> queue = new LinkedList<>();
		int n = A.size();
		ArrayList<Integer> res = new ArrayList<>();
		for (int i = 0; i < B; i++) {
			int curr = A.get(i);
			while (!queue.isEmpty() && A.get(queue.getLast()) <= curr) {
				queue.removeLast();
			}

			queue.add(i);
		}

		res.add(A.get(queue.getFirst()));

		for (int i = B; i < n; i++) {
			int curr = A.get(i);
			int minIndexPossible = i - B + 1;
			while (!queue.isEmpty() && queue.getFirst() < minIndexPossible) {
				queue.removeFirst();
			}
			while (!queue.isEmpty() && A.get(queue.getLast()) <= curr) {
				queue.removeLast();
			}
			queue.add(i);
			res.add(A.get(queue.getFirst()));
		}

		return res;
	}
}
