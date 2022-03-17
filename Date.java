
import java.util.*;

public class Date {
	public static boolean onlyDigit(String s) {
		return s.chars().allMatch(c -> Character.isDigit(c));
	}

	public static boolean isValidDate(String s) {
		//01-01-2022
		if (s.charAt(2) == '-' && s.charAt(5) == '-') {
			return onlyDigit(s.substring(0, 2)) && onlyDigit(s.substring(3, 5)) && onlyDigit(s.substring(6, 10));
		}
		return false;
	}

	public static void main(String[] args) {
		int start = 0;
		int end = 9;
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		if (input.length() < 9) {
			System.out.println("Cannot extract date from the input !!");
			return;
		}

		Set<Integer> arr = new HashSet<Integer>();

		while (end < input.length()) {
			if (isValidDate(input.substring(start, end + 1))) {
				arr.add(Integer.parseInt(input.substring(end - 3, end + 1)));
			}
			start += 1;
			end += 1;
		}
		System.out.println(arr.size());

	}

}

//01-01-2022

//
//Suppose you have a string in the form of a paragraph as input. And important dates in
//the para are provided in the format 
//"dd-mm-yyyy", calculate number of unique years present in the paragraph
//Sample input : Hello Earthlings , Today is 16-03-2022, you will be destroyed by 01-01-2025 01-01-2026
