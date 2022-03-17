import java.util.Scanner;

public class PerfectStrings {
	public static boolean perfectString(String input) {
		int p1 = 0;
		int p2 = input.length() - 1;
		while (p1 < p2) {
			if (input.charAt(p2) == 'P' || input.charAt(p1) == 'R') {
				if (input.charAt(p1) == input.charAt(p2))
					return false;
				p1 += 1;
				p2 -= 1;
			}
			else
				return false;
		}
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
