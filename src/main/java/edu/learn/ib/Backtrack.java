package edu.learn.ib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Backtrack {

	public static void main(String[] args) {
		System.out.println(letterCombinations("10"));
	}

	public static ArrayList<String> letterCombinations(String A) {
		Map<Character, char[]> mapping = new HashMap<>();
		mapping.put('0', new char[] { '0' });
		mapping.put('1', new char[] { '1' });
		mapping.put('2', new char[] { 'a', 'b', 'c' });
		mapping.put('3', new char[] { 'd', 'e', 'f' });
		mapping.put('4', new char[] { 'g', 'h', 'i' });
		mapping.put('5', new char[] { 'j', 'k', 'l' });
		mapping.put('6', new char[] { 'm', 'n', 'o' });
		mapping.put('7', new char[] { 'p', 'q', 'r', 's' });
		mapping.put('8', new char[] { 't', 'u', 'v' });
		mapping.put('9', new char[] { 'w', 'x', 'y', 'z' });

		return letterCombinations(A, 0, A.length(), mapping);

	}

	public static ArrayList<String> letterCombinations(String A, int i, int n, Map<Character, char[]> mapping) {
		ArrayList<String> res;
		if (i == n - 1) {
			res = new ArrayList<String>();
			char[] currMapping = mapping.get(A.charAt(i));
			for (int j = 0; j < currMapping.length; j++) {
				res.add(currMapping[j] + "");
			}
			return res;
		}
		res = new ArrayList<String>();
		ArrayList<String> tempres = letterCombinations(A, i + 1, n, mapping);
		char[] currMapping = mapping.get(A.charAt(i));

		for (int j = 0; j < currMapping.length; j++) {
			char append = currMapping[j];
			for (String s : tempres) {
				res.add(append + s);
			}
		}

		return res;

	}

}
