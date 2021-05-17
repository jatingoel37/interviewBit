package edu.learn.ib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayPractice {

	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 5));
		// ArrayList<Integer> b = new ArrayList<Integer>(Arrays.asList(12, 8, 6));

		System.out.println(repeatedNumber3(a));
	}

	public ArrayList<Integer> repeatedNumber22(final List<Integer> A) {

		int n = A.size();
		long diff = 0, squareDiff = 0;

		for (int i = 0; i < n; i++) {
			diff = diff + A.get(i) - i - 1;
			squareDiff = squareDiff + (long) A.get(i) * (long) A.get(i) - (long) (i + 1) * (long) (i + 1);
		}

		long plus = squareDiff / diff;
		int x = (int) ((diff + plus) / 2);
		int y = (int) (plus - x);

		ArrayList<Integer> res = new ArrayList<>(2);
		res.add(x);
		res.add(y);
		return res;

	}

	public int noble(ArrayList<Integer> A) {
		Collections.sort(A);
		int i = 0, n = A.size();
		while (i <= n - 2) {
			int curr = A.get(i);
			if (curr == A.get(i + 1)) {
				i++;
			} else {
				int bigger = n - (i + 1);
				if (bigger == curr) {
					return 1;
				} else {
					i++;
				}
			}
		}

		if (A.get(n - 1) == 0) {
			return 1;
		}

		return -1;

	}

	public static int repeatedNumber3(final List<Integer> a) {
		int n = a.size();
		int sqrt = (int) Math.sqrt(n);
		int range = n / sqrt + 1;
		int[] count = new int[range];
		for (int i = 0; i < n; i++) {
			count[(a.get(i) - 1) / sqrt]++;
		}
		int selected_block = range - 1;
		for (int i = 0; i < range - 1; i++) {
			if (count[i] > sqrt) {
				selected_block = i;
				break;
			}
		}
		HashMap<Integer, Integer> hm = new HashMap<>();
		for (int i = 0; i < n; i++) {
			if ((selected_block) * sqrt < a.get(i) && a.get(i) <= (selected_block + 1) * sqrt) {
				if (!hm.containsKey(a.get(i)))
					hm.put(a.get(i), 1);
				else
					return a.get(i);
			}
		}
		return -1;
	}

	public static int repeatedNumber2(final List<Integer> A) {
		int n = A.size() - 1;
		int k = (int) Math.ceil(Math.sqrt(n));
		int[] buckets = new int[k];
		for (Integer a : A) {
			buckets[(a - 1) / k]++;
		}

		for (int i = n + 1; i <= k * k; i++) {
			buckets[(i - 1) / k]++;
		}

		int probBucket = 0;
		for (int i = 0; i < k; i++) {
			if (buckets[i] > k) {
				probBucket = i;
			}
		}

		int l = probBucket * k + 1;
		int r = (probBucket + 1) * k;
		int[] count = new int[r - l + 1];
		for (Integer a : A) {
			if (a >= l && a <= r) {
				count[a - l]++;
				if (count[a - l] > 1) {
					return a;
				}
			}

		}

		return -1;

	}

	public static int jump(ArrayList<Integer> A) {

		if (A.get(0) == 0) {
			return -1;
		}

		if (A.size() == 1) {
			return 0;
		}

		int jumps = 1;
		int i = 0;
		int currentLadderMax = A.get(0);
		int nextladderMax = 0;
		while (true) {
			while (i <= currentLadderMax) {

				nextladderMax = Math.max(nextladderMax, i + A.get(i));
				i++;
				if (i == A.size() - 1) {
					return jumps;
				}

			}

			if (nextladderMax == i) {
				return -1;
			}
			jumps++;
			currentLadderMax = nextladderMax;
			nextladderMax = 0;

		}
	}

	public ArrayList<Integer> lszero(ArrayList<Integer> A) {

		Map<Integer, Integer> sumTillNow = new HashMap<>();
		sumTillNow.put(0, -1);
		int len = 0;
		int sum = 0, start = 0;
		for (int i = 0; i < A.size(); i++) {
			sum = sum + A.get(i);
			if (!sumTillNow.containsKey(sum)) {
				sumTillNow.put(sum, i);
			} else {
				int index = sumTillNow.get(sum);
				int tempLen = i - index;
				if (tempLen > len) {
					len = tempLen;
					start = index + 1;
				}
			}
		}

		ArrayList<Integer> res = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			res.add(A.get(start + i));
		}

		return res;
	}

	public static ArrayList<Integer> findPerm(final String A, int B) {
		int dCount = 0;
		for (char c : A.toCharArray()) {
			if (c == 'D') {
				dCount++;
			}
		}
		ArrayList<Integer> res = new ArrayList<>();
		int firstNumber = dCount + 1;
		int l = dCount, r = dCount + 2;
		res.add(firstNumber);
		for (char c : A.toCharArray()) {
			if (c == 'D') {
				res.add(l);
				l--;
			} else {
				res.add(r);
				r++;
			}
		}

		return res;
	}

	public static ArrayList<Integer> nextPermutation(ArrayList<Integer> A) {
		int n = A.size();
		int i = n - 1, max = Integer.MIN_VALUE;
		while (i >= 0) {
			int curr = A.get(i);
			if (curr >= max) {
				max = curr;
				i--;
			} else {
				break;
			}
		}

		if (i < 0) {
			Collections.sort(A);
			return A;
		}

		// swap i and guy just greater than ith from end
		int l = n - 1;
		while (A.get(l) < A.get(i)) {
			l--;
		}
		int temp1 = A.get(i);
		int temp2 = A.get(l);
		A.set(i, temp2);
		A.set(l, temp1);
		int j = i + 1;
		int k = n - 1;
		while (j < k) {
			temp1 = A.get(j);
			temp2 = A.get(k);
			A.set(j, temp2);
			A.set(k, temp1);
			j++;
			k--;
		}

		return A;
	}

	public static boolean hotel(ArrayList<Integer> arrive, ArrayList<Integer> depart, int K) {
		Collections.sort(arrive);
		Collections.sort(depart);
		int i = 0, j = 0;
		int n = arrive.size();
		while (i < n && j < n) {
			int nextA = arrive.get(i);
			int nextD = depart.get(j);
			if (nextD < nextA) {
				K++;
				j++;
			} else if (nextD > nextA) {
				if (K == 0) {
					return false;
				} else {
					K--;
					i++;
				}
			} else {
				i++;
				j++;
			}
		}

		return true;
	}

	// -- max unsorted --

	public static ArrayList<Integer> maxUnsorted2(List<Integer> A) {
		int n = A.size();
		int s1 = -1, e1 = -1, i = 0, max = -1;
		while (i < n) {
			int curr = A.get(i);
			if (curr >= max) {
				max = curr;
				i++;
			} else {
				if (s1 == -1) {
					s1 = i;
					e1 = i;
				} else {
					e1 = i;
				}
				i++;
			}
		}

		if (s1 == -1) {
			ArrayList<Integer> res = new ArrayList<Integer>();
			res.add(-1);
			return res;
		}

		int s2 = -1, e2 = -1, min = Integer.MAX_VALUE;
		i = n - 1;
		while (i >= 0) {
			int curr = A.get(i);
			if (curr <= min) {
				min = curr;
				i--;
			} else {
				if (s2 == -1) {
					s2 = i;
					e2 = i;
				} else {
					s2 = i;
				}
				i--;
			}
		}

		ArrayList<Integer> res = new ArrayList<Integer>();
		res.add(Math.min(s1, s2));
		res.add(Math.max(e1, e2));
		return res;

	}

	public static int minimize(final List<Integer> A, final List<Integer> B, final List<Integer> C) {
		int res = Integer.MAX_VALUE;
		int ai = 0, bi = 0, ci = 0;

		while (true) {
			if (ai == A.size() || bi == B.size() || ci == C.size()) {
				return res;
			}

			int a = A.get(ai);
			int b = B.get(bi);
			int c = C.get(ci);

			int max = Math.max(Math.max(a, b), c);
			int min = Math.min(Math.min(a, b), c);
			int tempDiff = Math.abs(max - min);
			if (tempDiff < res) {
				res = tempDiff;
			}

			if (a == min) {
				ai++;
			}

			if (b == min) {
				bi++;
			}

			if (c == min) {
				ci++;
			}
		}
	}

	public static ArrayList<Integer> maxone(ArrayList<Integer> A, int B) {
		int rsi = -1, rc = 0, cc = 0, cs = 0, ce = 0, rf = B;
		while (ce < A.size()) {
			int curr = A.get(ce);
			if (curr == 1) {
				cc++;
				ce++;
			} else {
				if (rf > 0) {
					rf--;
					cc++;
					ce++;
				} else {
					while (A.get(cs) == 1) {
						cs++;
						cc--;
					}
					cs++;
					ce++;
				}
			}

			if (cc > rc) {
				rc = cc;
				rsi = cs;
			}
		}
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < rc; i++) {
			res.add(rsi + i);
		}

		return res;

	}

	public int candy(ArrayList<Integer> A) {
		int n = A.size();
		int[] candies = new int[n];

		for (int i = 0; i < n; i++) {
			candies[i] = 1;
		}

		// L2R
		for (int i = 1; i < n; i++) {
			int leftRating = A.get(i - 1);
			int currRating = A.get(i);

			if (leftRating < currRating) {
				candies[i] = candies[i - 1] + 1;
			}
		}

		// R2L
		for (int i = n - 2; i >= 0; i--) {

			int rightRating = A.get(i + 1);
			int currRating = A.get(i);

			if (rightRating < currRating && candies[i] <= candies[i + 1]) {
				candies[i] = candies[i + 1] + 1;
			}

		}

		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += candies[i];
		}
		return sum;
	}

	public int maxArea(ArrayList<Integer> A) {
		int n = A.size();
		int l = 0, r = n - 1;
		int max = 0;
		while (l < r) {
			int left = A.get(l);
			int right = A.get(r);
			int area = Math.min(left, right) * (r - l);
			if (area > max) {
				max = area;
			}

			if (left < right) {
				l++;
			} else {
				r--;
			}
		}
		return max;
	}

	// -- wave array --
	public ArrayList<Integer> wave(ArrayList<Integer> A) {
		Collections.sort(A);

		int i = 0;
		while (i <= A.size() - 2) {
			int f = A.get(i);
			int s = A.get(i + 1);
			A.set(i, s);
			A.set(i + 1, f);
			i = i + 2;
		}

		return A;
	}

	// -- missing and repeating --
	public static ArrayList<Integer> missingAndRepeating(ArrayList<Integer> A) {
		int n = A.size();
		long ymx = 0, symx = 0;
		for (long i = 0; i < n; i++) {
			ymx = ymx + (i + 1 - A.get((int) i));
			symx = symx + ((i + 1) * (i + 1) - (long) (A.get((int) i) * A.get((int) i)));
		}

		long ypx = symx / ymx;
		long y = ymx / 2 + ypx / 2;
		long x = ypx - y;

		ArrayList<Integer> res = new ArrayList<>(2);
		res.add((int) x);
		res.add((int) y);
		return res;
	}

	// -- n triangle --

	public static int nTriang2(ArrayList<Integer> A) {
		Collections.sort(A);
		long sum = 0;
		int n = A.size();
		for (int i = 0; i <= n - 3; i++) {
			int f = A.get(i);
			int k = i + 2;
			for (int j = i + 1; j <= n - 2; j++) {
				int s = A.get(j);
				sum = (sum + k - j - 1) % 1000_000_007;
				while (k < n && f + s > A.get(k)) {
					sum = (sum + 1) % 1000_000_007;
					k++;
				}

			}
		}

		return (int) sum;
	}

	public static int nTriang(ArrayList<Integer> A) {
		Collections.sort(A);
		int sum = 0;
		int n = A.size();
		int i = 0;

		while (i <= n - 3) {
			int f = A.get(i);
			int j = i + 1, k = i + 2;
			while (j <= n - 2 && k <= n - 1) {
				int s = A.get(j);
				int t = A.get(k);
				if (f + s > t) {
					sum++;
					k++;
				} else {
					j++;
					if (j == k) {
						k++;
					}
					sum = sum + k - j - 1;
				}
			}
			i++;
		}
		return sum;
	}

	public ArrayList<ArrayList<Integer>> threeSum(ArrayList<Integer> A) {
		Collections.sort(A);
		int n = A.size();
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		int i = 0;

		while (i <= n - 3) {
			int f = A.get(i);
			int j = i + 1, k = n - 1;
			while (j < k) {
				int s = A.get(j), t = A.get(k);
				long sum = (long) f + (long) s + (long) t;
				if (sum == 0) {
					res.add(new ArrayList<Integer>(Arrays.asList(f, s, t)));
					while (j < n && A.get(j) == s) {
						j++;
					}

					while (k < j && A.get(k) == t) {
						k--;
					}
				} else if (sum < 0) {
					j++;
				} else {
					k--;
				}
			}

			while (i < n && A.get(i) == f) {
				i++;
			}

		}

		return res;
	}

	// -- 3 sum closest --
	public static int threeSumClosest(ArrayList<Integer> A, int B) {
		Collections.sort(A);
		int n = A.size();
		int i = 0;
		int optR = Integer.MAX_VALUE;
		int res = -1;
		while (i <= n - 3) {
			int f = A.get(i);
			int j = i + 1, k = n - 1;
			while (j < k) {
				int s = A.get(j), t = A.get(k);
				int sum = f + s + t;
				if (sum == B) {
					return B;
				}
				int diff = Math.abs(sum - B);
				if (diff < optR) {
					optR = diff;
					res = sum;
				}

				if (sum > B) {
					k--;
				} else if (sum < B) {
					j++;
				}
			}
			i++;
		}

		return res;
	}

	public static int firstMissingPositive(ArrayList<Integer> arr) {
		int n = arr.size();
		int i = 0;

		while (i < n) {
			int curr = arr.get(i);
			if (curr - 1 == i) {
				i++;
			} else if (curr >= 1 && curr <= n) {
				int temp = arr.get(curr - 1);
				if (temp == curr) {
					i++;
				} else {
					arr.set(i, temp);
					arr.set(curr - 1, curr);
				}

			} else {
				i++;
			}
		}

		i = 0;
		while (i < n) {
			int curr = arr.get(i);
			if (curr != i + 1) {
				return i + 1;
			} else {
				i++;
			}
		}

		return n + 1;

	}

	public int diffPossible(ArrayList<Integer> A, int B) {
		int i = 0;
		int j = A.size() - 1;

		while (j > i) {
			int diff = A.get(j) - A.get(i);
			if (diff == B) {
				return 1;
			} else if (diff > B) {
				i++;
			} else {
				j--;
			}

		}

		return 0;
	}

	public static int diffK(ArrayList<Integer> A, int B) {
		int n = A.size();
		int i = 0;
		int j = i + 1;

		while (i <= n - 2 && j <= n - 1) {
			if (i == j) {
				j++;
			}
			int f = A.get(i);
			int s = A.get(j);
			if (s - f == B) {
				return 1;
			} else if (s - f > B) {
				i++;
			} else {
				j++;
			}
		}

		return 0;
	}

	public void merge(ArrayList<Integer> a, ArrayList<Integer> b) {
		int i = a.size() - 1;
		int j = b.size() - 1;
		a.addAll(b);
		int p = a.size() - 1;

		while (i >= 0 && j >= 0) {
			int f = a.get(i);
			int s = b.get(j);
			if (f == s) {
				a.set(p, f);
				p--;
				i--;
				a.set(p, f);
				p--;
				j--;
			} else if (f > s) {
				a.set(p, f);
				p--;
				i--;
			} else {
				a.set(p, s);
				p--;
				j--;
			}
		}

		while (i >= 0) {
			a.set(p, a.get(i));
			p--;
			i--;
		}

		while (j >= 0) {
			a.set(p, b.get(j));
			p--;
			j--;
		}

	}

	// -- intersection of 2 sorted array --
	public ArrayList<Integer> intersect(final List<Integer> A, final List<Integer> B) {
		int n = A.size();
		int m = B.size();
		ArrayList<Integer> res = new ArrayList<>();
		int i = 0, j = 0;
		while (i < n && j < m) {
			int a = A.get(i);
			int b = B.get(j);

			if (a == b) {
				res.add(a);
				i++;
				j++;
			} else if (a > b) {
				j++;
			} else {
				i++;
			}

		}

		return res;
	}

	// -- sort012 --
	public static void sort012(ArrayList<Integer> arr) {
		int c0 = 0, c1 = 0, c2 = 0;
		for (int i = 0; i < arr.size(); i++) {
			int curr = arr.get(i);
			if (curr == 0) {
				c0++;
			} else if (curr == 1) {
				c1++;
			} else {
				c2++;
			}
		}

		for (int i = 0; i < c0; i++) {
			arr.set(i, 0);
		}

		for (int i = 0; i < c1; i++) {
			arr.set(c0 + i, 0);
		}

		for (int i = 0; i < c2; i++) {
			arr.set(c0 + c1 + i, 0);
		}

	}

	public static int removeElement(List<Integer> a, int b) {

		int npi = 0;
		int count = 0;
		for (int i = 0; i < a.size(); i++) {
			int curr = a.get(i);
			if (curr == b) {
				// No - OP
			} else {
				a.set(npi, curr);
				npi++;
				count++;
			}
		}

		return count;
	}

	// -- count unique --
	public static int removeDuplicates2(List<Integer> a) {
		int n = a.size();
		if (n <= 2) {
			return n;
		}

		int res = 0;
		int i = 0;
		while (true) {

			if (i >= n) {
				break;
			}

			if (i == n - 1) {
				a.set(res, a.get(i));
				res++;
				break;
			}
			int currCount = 0;
			int curr = a.get(i);

			while (i < n && a.get(i) == curr) {
				currCount++;
				i++;
			}

			if (currCount == 1) {
				a.set(res, curr);
				res++;
			} else {
				a.set(res, curr);
				res++;
				a.set(res, curr);
				res++;
			}
		}

		return res;
	}

	// -- count unique --
	public static int removeDuplicates(List<Integer> a) {

		int res = 0;
		int i = 0;
		int n = a.size();
		while (true) {
			if (i >= n) {
				break;
			}

			if (i == n - 1) {
				a.set(res, a.get(n - 1));
				res++;
				break;
			}

			if (a.get(i).equals(a.get(i + 1))) {
				a.set(res, a.get(i));
				res++;
				i++;
				int curr = a.get(i);
				while (i < n && a.get(i) == curr) {
					i++;
				}

			} else {
				a.set(res, a.get(i));
				res++;
				i++;
			}
		}

		return res;
	}

	// -- majority element --
	public static int majority(List<Integer> A) {
		int elem = A.get(0);
		int count = 1;

		for (int i = 1; i < A.size(); i++) {
			int curr = A.get(i);
			if (count > 0) {
				if (elem == curr) {
					count++;
				} else {
					count--;
				}
			} else {
				count++;
				elem = curr;
			}
		}

		if (count > 0) {
			return elem;
		}

		return -1;
	}

	// -- sorted square --
	public static ArrayList<Integer> ss(List<Integer> A) {
		int size = A.size();
		ArrayList<Integer> res = new ArrayList<Integer>(size);
		int pi = size, ni = -1;
		for (int i = 0; i < size; i++) {
			int curr = A.get(i);
			if (curr < 0) {
				ni = i;
			} else {
				if (pi == size) {
					pi = i;
				}
			}
		}

		while (pi < size && ni >= 0) {
			int pos = A.get(pi);
			int neg = A.get(ni);

			if (pos * pos < neg * neg) {
				res.add(pos * pos);
				pi++;
			} else {
				res.add(neg * neg);
				ni--;
			}
		}

		if (pi < size) {
			while (pi < size) {
				int pos = A.get(pi);
				res.add(pos * pos);
				pi++;
			}
		}

		if (ni >= 0) {
			while (ni >= 0) {
				int neg = A.get(ni);
				res.add(neg * neg);
				ni--;
			}

		}

		return res;
	}

	public String largestNumber(final List<Integer> A) {
		Collections.sort(A, (n1, n2) -> {
			if (n1 == n2) {
				return 0;
			}
			String num1 = n1.toString();
			String num2 = n2.toString();

			return (num1 + num2).compareTo(num2 + num1);

		});

		StringBuilder sb = new StringBuilder();
		boolean numFound = false;
		for (Integer i : A) {
			if (i == 0 && !numFound) {
				// NO Op
			} else {
				numFound = true;
				sb.append(i);
			}

		}
		String res = sb.toString();
		return "".equals(res) ? "0" : res;

	}

	public int maxMinSum(ArrayList<Integer> A) {
		int size = A.size();
		int max = A.get(0), min = A.get(0);

		for (int i = 1; i < size; i++) {
			int curr = A.get(i);
			if (curr > max) {
				max = curr;
			}

			if (curr < min) {
				min = curr;
			}
		}

		return max + min;
	}

	// -- duplicate in array --
	public int repeatedNumber(final List<Integer> A) {
		int size = A.size();
		for (int i = 0; i < size; i++) {
			int curr = Math.abs(A.get(i));
			int noAtIndexCurr = A.get(curr - 1);
			if (noAtIndexCurr < 0) {
				return curr;
			} else {
				A.set(curr - 1, -1 * noAtIndexCurr);
			}
		}

		return -1;
	}

	// -- peak --

	public int perfectPeak(ArrayList<Integer> A) {
		int size = A.size();

		int[] isLargestFromStarting = new int[size];
		int largestTillNow = A.get(0);

		for (int i = 1; i < size; i++) {
			int curr = A.get(i);
			if (curr > largestTillNow) {
				largestTillNow = curr;
				isLargestFromStarting[i] = 1;
			}
		}

		int[] isSmallestFromEnd = new int[size];
		int smallestFromEnd = A.get(size - 1);

		for (int i = size - 2; i >= 0; i--) {
			int curr = A.get(i);
			if (curr < smallestFromEnd) {
				smallestFromEnd = curr;
				isSmallestFromEnd[i] = 1;
			}
		}

		for (int i = 1; i < size - 1; i++) {
			if (isLargestFromStarting[i] + isSmallestFromEnd[i] == 2) {
				return 1;
			}
		}

		return 0;
	}

	// --- flip--

	public static int[] flip2(String s) {
		int size = s.length();
		int cashTillNow = 0;
		int maxCash = 0;
		int l = -1, r = -1;

		int start = 0;

		for (int i = start; i < size; i++) {
			char curr = s.charAt(i);
			if (curr == '0') {
				cashTillNow++;
			} else {
				cashTillNow--;
			}

			if (cashTillNow > maxCash) {
				l = start;
				r = i;
				maxCash = cashTillNow;
			} else if (cashTillNow < 0) {
				cashTillNow = 0;
				start = i + 1;
			}
		}

		if (l == -1) {
			return new int[0];
		}

		return new int[] { l + 1, r + 1 };

	}

	public static int[] flip(String s) {
		int size = s.length();
		int cashTillNow = 0;
		int maxCash = 0;
		int l = 0, r = 0;

		for (int i = 0; i < size; i++) {
			char curr = s.charAt(i);
			if (curr == '0') {
				cashTillNow++;
			} else {
				cashTillNow--;
			}

			if (cashTillNow > maxCash) {
				maxCash = cashTillNow;
				r = i;
				l = r - maxCash + 1;
			}

			if (cashTillNow <= 0) {
				cashTillNow = 0;
			}

		}

		if (maxCash == 0) {
			return new int[0];
		}

		return new int[] { l + 1, r + 1 };

	}

	// --- max contiguous subarray ---
	public static int maxSubArray(final int[] arr) {

		int size = arr.length;

		if (size == 1) {
			return arr[0];
		}

		int res = Integer.MIN_VALUE, sumTillNow = 0, lowestTillNow = 0;

		for (int i = 0; i < size; i++) {
			int currNo = arr[i];
			sumTillNow = sumTillNow + currNo;
			int possAnswer = sumTillNow - lowestTillNow;
			if (possAnswer > res) {
				res = possAnswer;
			}

			if (sumTillNow < lowestTillNow) {
				lowestTillNow = sumTillNow;
			}

		}

		return res;
	}

	// -- add one to number --

	public static int[] addOneToNumber(int[] arr) {
		int size = arr.length;
		List<Integer> res = new ArrayList<Integer>(size + 1);
		int carry = 1;
		for (int i = size - 1; i >= 0; i--) {
			int currDigit = arr[i];
			if (carry == 0) {
				res.add(currDigit);
			} else {
				if (currDigit == 9) {
					res.add(0);
					carry = 1;
				} else {
					res.add(currDigit + 1);
					carry = 0;
				}
			}

		}

		if (carry == 1) {
			res.add(1);
		}

		Collections.reverse(res);

		List<Integer> tempres = new ArrayList<Integer>(size + 1);
		int j = 0;
		for (j = 0; j < res.size(); j++) {
			if (res.get(j) != 0) {
				break;
			}
		}

		for (; j < res.size(); j++) {
			tempres.add(res.get(j));
		}

		System.out.println(tempres);
		int[] response = new int[tempres.size()];
		for (int i = 0; i < tempres.size(); i++) {
			response[i] = tempres.get(i);

		}

		return response;
	}

	// --------- max sum ------
	public static int solve(int[] A, int B) {
		int size = A.length;
		if (size == 0 || B <= 0) {
			return 0;
		}

		List<Integer> reverseSum = reverseContinuousSum(A);

		int maxSum = reverseSum.get(size - B);
		int currSum = 0;
		for (int i = 1; i <= B; i++) {
			currSum = currSum + A[i - 1];
			int tempSum = currSum;
			if (i != B) {
				tempSum = tempSum + reverseSum.get(size - B + i);
			}
			if (tempSum > maxSum) {
				maxSum = tempSum;
			}
		}

		return maxSum;
	}

	private static List<Integer> reverseContinuousSum(int[] arr) {
		List<Integer> res = new ArrayList<Integer>(arr.length);
		int sum = 0;
		for (int i = arr.length - 1; i >= 0; i--) {
			sum = sum + arr[i];
			res.add(sum);

		}

		Collections.reverse(res);
		return res;
	}

	// ----- cover point ---

	public static int coverPoints(int[] A, int[] B) {
		if (A.length == 0 || B.length == 0) {
			return 0;
		}

		int res = 0;

		for (int i = 0; i <= A.length - 2; i++) {
			res = res + Math.max(Math.abs(A[i] - A[i + 1]), Math.abs(B[i] - B[i + 1]));
		}

		return res;

	}

	// --- minimum light ---

	public static int minLight2(int[] A, int B) {
		int size = A.length;
		if (size == 1) {
			return A[0] == 1 ? 1 : -1;
		}

		int res = 0;
		int curr = 0;
		while (curr < size) {
			int bestBulb = Math.min(curr + B - 1, size - 1);
			boolean found = false;
			for (int i = bestBulb; i >= 0 && i >= curr - B + 1; i--) {
				if (A[i] == 1) {
					res++;
					curr = i + B;
					found = true;
					break;
				}
			}

			if (!found) {
				return -1;
			}

		}

		return res;
	}

	// --- max triplet ---

	public static int maxTriplet(int[] arr) {
		int size = arr.length;
		if (size <= 2) {
			return 0;
		}

		int[] maxInRight = maxInRight(arr);
		int maxsum = Integer.MIN_VALUE;
		for (int i = 0; i <= size - 3; i++) {
			int left = arr[i];
			for (int j = i + 1; j <= size - 2; j++) {
				int mid = arr[j];
				if (mid > left) {
					int right = maxInRight[j];
					if (left + mid + right > maxsum) {
						maxsum = left + mid + right;
					}
				}
			}
		}

		return maxsum;
	}

	public static int[] maxInRight(int[] arr) {
		int[] res = new int[arr.length];

		int maxTillNow = arr[arr.length - 1];
		res[arr.length - 1] = maxTillNow;
		for (int i = arr.length - 2; i >= 0; i--) {
			int curr = arr[i];
			if (curr > maxTillNow) { // =
				res[i] = Integer.MIN_VALUE;
				maxTillNow = curr;
			} else {
				res[i] = maxTillNow;
			}
		}

		return res;
	}
}
