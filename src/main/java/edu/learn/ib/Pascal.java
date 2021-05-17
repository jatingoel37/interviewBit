package edu.learn.ib;

import java.util.ArrayList;
import java.util.Arrays;

public class Pascal {

	public static void main(String[] args) {
		System.out.println(solve(6));
	}

	public static ArrayList<ArrayList<Integer>> solve(int A) {

		ArrayList<ArrayList<Integer>> res = new ArrayList<>();

		if (A == 0) {
			return res;
		}
		ArrayList<Integer> first = new ArrayList<Integer>(1);
		first.add(1);
		res.add(first);

		if (A == 1) {
			return res;
		}

		ArrayList<Integer> sec = new ArrayList<Integer>(2);
		sec.add(1);
		sec.add(1);

		res.add(sec);
		if (A == 2) {
			return res;
		}
		ArrayList<Integer> last = sec;
		for (int i = 3; i <= A; i++) {
			last = generateNext(last, i);
			res.add(last);
		}

		return res;

	}

	public ArrayList<Integer> getRow(int A) {
		A++;
		ArrayList<Integer> first = new ArrayList<Integer>(1);
		first.add(1);

		if (A == 1) {
			return first;
		}

		ArrayList<Integer> sec = new ArrayList<Integer>(2);
		sec.add(1);
		sec.add(1);

		if (A == 2) {
			return sec;
		}

		int[] temp1 = new int[A]; // res
		int[] temp2 = new int[A];

		temp1[0] = 1;
		temp1[1] = 1;

		temp2[0] = 1;
		temp2[1] = 1;

		for (int i = 3; i <= A; i++) {
			generateNext(temp1, temp2, i);
			int[] temp3 = temp1;
			temp1 = temp2;
			temp2 = temp3;
		}

		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i : temp1) {
			res.add(i);
		}

		return res;
	}

	public static void generateNext(int[] last, int[] curr, int size) {
		curr[0] = 1;
		curr[size - 1] = 1;
		for (int i = 1; i <= size - 2; i++) {
			int e = last[i - 1] + last[i];
			curr[i] = e;
		}
	}

	public static void generateNext(ArrayList<Integer> last, ArrayList<Integer> curr, int size) {
		curr.set(0, 1);
		for (int i = 1; i <= size - 2; i++) {
			int e = last.get(i - 1) + last.get(i);
			curr.set(i, e);
		}
		curr.add(1);
	}

	public static ArrayList<Integer> generateNext(ArrayList<Integer> last, int size) {
		ArrayList<Integer> res = new ArrayList<Integer>(size);
		res.add(1);
		for (int i = 1; i <= size - 2; i++) {
			int curr = last.get(i - 1) + last.get(i);
			res.add(curr);
		}
		res.add(1);
		return res;
	}

}
