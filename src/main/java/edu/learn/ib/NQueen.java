package edu.learn.ib;

import java.util.ArrayList;
import java.util.Arrays;

public class NQueen {

	public static void main(String[] args) {
		new NQueen().solveNQueens(4);
	}

	public ArrayList<ArrayList<String>> solveNQueens(int a) {
		// result
		ArrayList<ArrayList<String>> res = new ArrayList<>();
		int k = a - 1;
		
		// per solution
		int[] vertocal = new int[a];
		int[] l2r = new int[2 * k + 1];
		int[] r2l = new int[2 * k + 1];
		ArrayList<char[]> curr = createNewRes(a);
		
		for (int i = 0; i < a; i++) {
			put(curr, 0, i, vertocal, l2r, r2l);
			boolean further = tryPut(res, 1, vertocal, l2r, r2l, a, curr);
			if (further) {
				res.add(transform(curr));
				curr = createNewRes(a);
				Arrays.fill(vertocal, 0);
				Arrays.fill(l2r, 0);
				Arrays.fill(r2l, 0);
			} else {
				remove(curr, 0, i, vertocal, l2r, r2l);
			}
		}
		//System.out.println(res);
		return res;
	}

	boolean tryPut(ArrayList<ArrayList<String>> res, int row, int[] vertocal, int[] l2r, int[] r2l, int a,
			ArrayList<char[]> curr) {

		if (row >= a) {
			return true;
		}

		for (int i = 0; i < a; i++) {
			if (isSafe(a, row, i, vertocal, l2r, r2l)) {
				put(curr, row, i, vertocal, l2r, r2l);
				boolean further = tryPut(res, row + 1, vertocal, l2r, r2l, a, curr);
				if (further) {
					return true;

				} else {
					remove(curr, row, i, vertocal, l2r, r2l);
				}
			}
		}

		return false;

	}

	ArrayList<String> transform(ArrayList<char[]> input) {
		ArrayList<String> res = new ArrayList<String>();
		for (char[] arr : input) {
			res.add(new String(arr));
		}

		return res;
	}

	ArrayList<char[]> createNewRes(int a) {
		ArrayList<char[]> curr = new ArrayList<>(a);
		for (int i = 0; i < a; i++) {
			char[] sb = new char[a];
			Arrays.fill(sb, '.');
			curr.add(sb);

		}

		return curr;
	}

	void put(ArrayList<char[]> board, int row, int col, int[] vertocal, int[] l2r, int[] r2l) {
		int k = board.size() - 1;
		board.get(row)[col] = 'Q';
		vertocal[col]++;
		r2l[row + col]++;
		l2r[row - col + k]++;
	}

	void remove(ArrayList<char[]> board, int row, int col, int[] vertocal, int[] l2r, int[] r2l) {
		int k = board.size() - 1;
		board.get(row)[col] = '.';
		vertocal[col]--;
		r2l[row + col]--;
		l2r[row - col + k]--;
	}

	boolean isSafe(int a, int row, int col, int[] vertocal, int[] l2r, int[] r2l) {
		int k = a - 1;
		return (vertocal[col] == 0) && (r2l[row + col] == 0) && (l2r[row - col + k] == 0);
	}
}
