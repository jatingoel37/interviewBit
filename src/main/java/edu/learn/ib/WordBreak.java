package edu.learn.ib;

import java.util.ArrayList;
import java.util.Arrays;

public class WordBreak {

	public static void main(String[] args) {
		System.out.println(
				wordBreak("myinterviewtrainer", new ArrayList<String>(Arrays.asList("interview", "my", "trainer"))));
	}

	public static int wordBreak(String A, ArrayList<String> B) {
		return wordBreakI(A, B, 0, new Boolean[A.length()]) ? 1 : 0;
	}

	public static boolean wordBreakI(String A, ArrayList<String> B, int si, Boolean[] dp) {

		if (si >= A.length()) {
			return true;
		}

		if (dp[si] != null)
			return dp[si];

		int inputLength = A.length() - si;
		for (String dic : B) {
			int len = dic.length();
			if (len > inputLength) {
				continue;
			}

			if (dic.equals(A.substring(si, si + len))) {
				boolean subResult = wordBreakI(A, B, si + len, dp);
				if (subResult) {
					dp[si] = true;
					return true;
				}
			}
		}

		dp[si] = false;
		return false;

	}

}
