package problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Palindrome {
	public static boolean isPalindrome(String s) {
		String str = s, reverseStr = "";

		int strLength = str.length();

		for (int i = (strLength - 1); i >= 0; --i) {
			reverseStr = reverseStr + str.charAt(i);
		}

		return str.equals(reverseStr);
	}

	public static String makePalindrome(String s) {
		StringBuilder res = new StringBuilder(s);

		int index = -1;
		//abb
		for (int i = 0; i < s.length(); i++) {
			if (isPalindrome(s.substring(i))) {
				index = i - 1;
				break;
			}
		}
		if (index == -1)
			return s;
		for (int i = index; i >= 0; i--)
			res.append(s.charAt(i));
		return res.toString();
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		System.out.println(makePalindrome(input));

	}

}
