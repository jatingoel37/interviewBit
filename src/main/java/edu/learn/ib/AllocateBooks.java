package edu.learn.ib;

import java.util.ArrayList;

public class AllocateBooks {

	public int books(ArrayList<Integer> A, int B) {

		if (B > A.size()) {
			return -1;
		}

		int max = 0, sum = 0;
		int n = A.size();
		for (int i = 0; i < n; i++) {
			int book = A.get(i);
			sum = sum + book;
			if (book > max) {
				max = book;
			}
		}

		int res = sum;

		int lo = max, hi = sum;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			int studentsNeededForMid = getStudents(A, n, mid);
			if (studentsNeededForMid <= B) {
				hi = mid - 1;
				res = mid;
			} else {
				lo = mid + 1;
			}
		}

		return res;

	}

	public int getStudents(ArrayList<Integer> books, int n, int maxBooks) {
		int students = 1, total = 0, i = 0;
		while (i < n) {
			int book = books.get(i);
			if (total + book <= maxBooks) {
				total = total + book;
				i++;
			} else {
				students++;
				total = 0;
			}

		}

		return students;
	}
}
