package edu.learn.ib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Greedy {

	public static void main(String[] args) {
		System.out.println(seats("x.xx..."));
	}

	public static int seats(String A) {
		if (A.length() <= 2) {
			return 0;
		}

		List<Integer> fiiledSeats = new ArrayList<>();
		for (int i = 0; i < A.length(); i++) {
			char curr = A.charAt(i);
			if (curr == 'x') {
				fiiledSeats.add(i);
			}
		}

		int totalGuys = fiiledSeats.size();
		if (totalGuys <= 1) {
			return 0;
		}
		int middleGuyPos = fiiledSeats.get(totalGuys / 2);
		int currPosToFill = middleGuyPos - 1;
		int ans = 0;
		for (int i = totalGuys / 2 - 1; i >= 0; i--) {
			int thisGuyPos = fiiledSeats.get(i);
			ans = ans + Math.abs(thisGuyPos - currPosToFill);
			ans = ans % 10000003;
			currPosToFill--;
		}

		currPosToFill = middleGuyPos + 1;
		for (int i = totalGuys / 2 + 1; i < totalGuys; i++) {
			int thisGuyPos = fiiledSeats.get(i);
			ans = ans + Math.abs(thisGuyPos - currPosToFill);
			ans = ans % 10000003;
			currPosToFill++;
		}

		return ans;
	}

	// -- gas station --
	public static int canCompleteCircuit(final List<Integer> A, final List<Integer> B) {
		int currStartingPos = 0;
		int petrolInCar = 0;
		int n = A.size();
		boolean[] parsed = new boolean[n];

		while (true) {

			if (parsed[currStartingPos]) {
				break;
			}

			petrolInCar = 0;
			parsed[currStartingPos] = true;
			int hops = 0;
			int i = currStartingPos;
			while (true) {
				if (i == currStartingPos && hops == n) {
					return currStartingPos;
				}
				int petrolAvailable = A.get(i);
				int petrolUsage = B.get(i);
				int totalPetrol = petrolInCar + petrolAvailable;
				if (totalPetrol >= petrolUsage) {
					petrolInCar = totalPetrol - petrolUsage;
					i = (i + 1) % n;
					parsed[i] = true;
					hops++;
				} else {
					currStartingPos = (i + 1) % n;
					break;
				}
			}
		}

		return -1;
	}

	public int solve(ArrayList<ArrayList<Integer>> A) {
		int entries[] = new int[A.size()];
		int exits[] = new int[A.size()];
		for (int i = 0; i < A.size(); i++) {
			entries[i] = A.get(i).get(0);
			exits[i] = A.get(i).get(1);
		}

		Arrays.sort(entries);
		Arrays.sort(exits);

		int maxRooms = 1;
		int free = 1;
		int i = 0, j = 0;
		while (i < A.size()) {
			int nextEntry = entries[i];
			int nextExit = exits[j];
			if (nextExit == nextEntry) {
				i++;
				j++;
			} else if (nextExit < nextEntry) {
				j++;
				free++;
			} else {
				if (free > 0) {
					free--;
					i++;
				} else {
					maxRooms++;
					i++;
				}
			}
		}

		return maxRooms;
	}

	// -- bulbs --
	public static int bulbs(ArrayList<Integer> arr) {
		int count = 0;
		int cos = 1;

		for (int i = 0; i < arr.size(); i++) {
			int curr = arr.get(i);
			if (curr == cos) {
				// NOP
			} else {
				count++;
				cos = cos == 1 ? 0 : 1;
			}
		}

		return count;
	}

	// -- swap largest --
	public static List<Integer> swap(List<Integer> input, int k) {
		int length = input.size();
		int[] indexes = new int[length];
		for (int i = 1; i <= length; i++) {
			int curr = input.get(i - 1);
			indexes[curr - 1] = i - 1;
		}

		int swapped = 0;
		int target = length;
		int targetPos = indexes[target - 1];
		int targetNewPos = length - target;

		while (swapped < k && target > 1) {
			targetPos = indexes[target - 1];
			targetNewPos = length - target;
			if (targetPos != targetNewPos) {
				// swap
				int numberToSwapWith = input.get(targetNewPos);
				input.set(targetNewPos, target);
				input.set(targetPos, numberToSwapWith);
				swapped++;
				indexes[numberToSwapWith - 1] = targetPos;
				indexes[target - 1] = targetNewPos;
				target--;
			} else {
				target--;
			}
		}

		// System.out.println(Arrays.asList(input));
		return input;
	}

	// -- mce to hole --
	public int mice(ArrayList<Integer> A, ArrayList<Integer> B) {
		Collections.sort(A);
		Collections.sort(B);

		int max = 0;
		for (int i = 0; i < A.size(); i++) {
			int time = Math.abs(A.get(i) - B.get(i));
			if (time > max) {
				max = time;
			}
		}
		return max;
	}

	public int maxp3(ArrayList<Integer> A) {
		int size = A.size();
		Collections.sort(A);
		int option1 = A.get(size - 1) * A.get(size - 2) * A.get(size - 3);
		int option2 = A.get(size - 1) * A.get(0) * A.get(1);

		return option1 > option2 ? option1 : option2;
	}
}
