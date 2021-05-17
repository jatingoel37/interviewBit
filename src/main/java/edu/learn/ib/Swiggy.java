package edu.learn.ib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Swiggy {

	class SinglyLinkedListNode {
		int data;
		SinglyLinkedListNode next;
	}

	public static long getNumber(SinglyLinkedListNode binary) {
		SinglyLinkedListNode curr = binary;
		StringBuilder sb = new StringBuilder();

		while (curr != null) {
			sb.append(curr.data);
			curr = curr.next;
		}
		String bin = sb.toString();
		long number = 0;
		int size = bin.length();
		long base = 1;
		int i = size - 1;
		while (i >= 0) {
			int currDigit = bin.charAt(i) == '0' ? 0 : 1;
			number = number + base * currDigit;
			base = base * 2;
			i--;
		}

		return number;
	}

	

	public static long prison(int n, int m, List<Integer> h, List<Integer> v) {

		int[] vertical = new int[m + 2];

		for (Integer i : v) {
			vertical[i] = 1;
		}

		List<Integer> tempList2 = new ArrayList<Integer>();

		for (int i = 0; i < m + 2; i++) {
			int curr = vertical[i];
			if (curr == 0) {
				tempList2.add(i);
			}
		}

		long maxDiff2 = 1;
		for (int i = 0; i < tempList2.size() - 1; i++) {
			if (tempList2.get(i + 1) - tempList2.get(i) > maxDiff2) {
				maxDiff2 = tempList2.get(i + 1) - tempList2.get(i);
			}
		}

		int[] horizontal = new int[n + 2];

		for (Integer i : h) {
			horizontal[i] = 1;
		}

		List<Integer> tempList = new ArrayList<Integer>();

		for (int i = 0; i < n + 2; i++) {
			int curr = horizontal[i];
			if (curr == 0) {
				tempList.add(i);
			}
		}

		long maxDiff = 1;
		for (int i = 0; i < tempList.size() - 1; i++) {
			if (tempList.get(i + 1) - tempList.get(i) > maxDiff) {
				maxDiff = tempList.get(i + 1) - tempList.get(i);
			}
		}

		return maxDiff * maxDiff2;
	}
	
	public static void main(String[] args) {
		System.out.println(minOperationsInt(11));
	}

	public static long minOperationsInt(long n) {
		
		
		if (n == 0) {
			return 0;
		}

		if (n == 1) {
			return 1;
		}

		if (n == 3) {
			return 2;
		}

		if (n == 2) {
			return 3;
		}

		int bits = countBits(n);

		int number = (int) (Math.pow(2, bits) - 1);
		
		int temp = (int) (Math.pow(2, bits-1));
		
		return number - minOperationsInt(n - temp);

	}

	public static int countBits(long n) {

		return (int) (Math.log(n) / Math.log(2) + 1);
	}
	
	

}
