package edu.learn.ib;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Matrix {

	public static void main(String[] args) {
		ArrayList<Integer> a1 = new ArrayList<Integer>(asList(8, 6, 8));
		ArrayList<Integer> a2 = new ArrayList<Integer>(asList(8, 1, 0));
		ArrayList<Integer> a3 = new ArrayList<Integer>(asList(8, 6, 8));
		ArrayList<ArrayList<Integer>> i = new ArrayList<>();
		i.add(a1);
		i.add(a2);
		i.add(a3);
		diagonal(i);

	}

	public void setZeroes(ArrayList<ArrayList<Integer>> a) {
		int rows = a.size();
		int cols = a.get(0).size();

		boolean[] rowsRec = new boolean[rows];
		boolean[] colsRec = new boolean[cols];

		for (int i = 0; i < rows; i++) {
			ArrayList<Integer> cr = a.get(i);
			for (int j = 0; j < cols; j++) {
				int curr = cr.get(j);
				if (curr == 0) {
					rowsRec[i] = true;
					colsRec[j] = true;
				}
			}
		}

		for (int i = 0; i < rows; i++) {
			if (rowsRec[i]) {
				for (int j = 0; j < cols; j++) {
					a.get(i).set(j, 0);
				}
			}
		}

		for (int j = 0; j < cols; j++) {
			if (colsRec[j]) {
				for (int i = 0; i < rows; i++) {
					a.get(i).set(j, 0);
				}
			}
		}
	}

	public static ArrayList<ArrayList<Integer>> diagonal(ArrayList<ArrayList<Integer>> A) {
		int n = A.size();
		int maxSum = 2 * (n - 1);
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		for (int i = 0; i <= maxSum; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			for (int r = 0; r <= i; r++) {
				int c = i - r;
				if (c < n && r < n) {
					temp.add(A.get(r).get(c));
				}
			}

			res.add(temp);
		}

		return res;
	}

	public void rotate(ArrayList<ArrayList<Integer>> a) {
		int rows = a.size(), cols = a.get(0).size();
		for (int i = 0; i < rows / 2; i++) {
			ArrayList<Integer> l1 = a.get(i);
			ArrayList<Integer> l2 = a.get(rows - 1 - i);
			a.set(i, l2);
			a.set(rows - 1 - i, l1);
		}

		for (int i = 0; i < rows; i++) {
			for (int j = i + 1; j < cols; j++) {
				int first = a.get(i).get(j);
				int second = a.get(j).get(i);
				a.get(j).set(i, first);
				a.get(i).set(j, second);
			}
		}
	}

	public int findMedian(ArrayList<ArrayList<Integer>> A) {
		int rows = A.size(), cols = A.get(0).size();
		int target = (rows * cols) / 2; // how many numbers should be less than or equal to median

		int min = 1, max = Integer.MAX_VALUE;
		while (min < max) {
			int mid = (min + max) / 2;
			int smallerOrEqual = 0;
			for (int r = 0; r < rows; r++) {
				int index = Collections.binarySearch(A.get(r), mid);
				if (index < 0) {
					smallerOrEqual = smallerOrEqual + Math.abs(index) - 1;
				} else { // there is a match
					while (index < cols && A.get(r).get(index) == mid) {
						index++;
					}

					smallerOrEqual = smallerOrEqual + index;
				}
			}

			if (smallerOrEqual < target) {
				min = mid + 1;
			} else if (smallerOrEqual > target) {
				max = mid;
			} else {
				max = mid;
			}
		}

		return min;
	}

	public int searchMatrix(ArrayList<ArrayList<Integer>> A, int B) {
		int rows = A.size();

		List<Integer> tempArray = new ArrayList<>(rows);

		for (int i = 0; i < rows; i++) {
			tempArray.add(A.get(i).get(0));
		}

		int rowIndex = Collections.binarySearch(tempArray, B);
		if (rowIndex >= 0) {
			return 1;
		}

		int insertionIndex = Math.abs(rowIndex) - 1;
		int possibleRow = insertionIndex - 1;
		if (possibleRow < 0) {
			return 0;
		}

		return Collections.binarySearch(A.get(possibleRow), B) >= 0 ? 1 : 0;

	}

}
