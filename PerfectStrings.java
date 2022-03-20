package problems;

import java.util.Scanner;

public class PerfectStrings {
	public static boolean perfectString(String input) {
		if(input.charAt(0)==input.charAt(1))
			return false;
		char c1=input.charAt(0);
		char c2=input.charAt(1);
		for(int i=2;i<input.length();i+=2) {
			if(input.charAt(i)!=c1)
				return false;
		}
		for(int i=3;i<input.length();i+=2)
			if(input.charAt(i)!=c2)
				return false;
		return true;
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.next();

		if (perfectString(input))
			System.out.println("Perfect");
		else
			System.out.println("NotPerfect");

	}

}