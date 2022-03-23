package IpValidation;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		IpValidation validator = new IpValidation();
		validator.setIpValidationString(sc.next());
		try {
		System.out.println(validator.validateIpAddress());
		}
		catch(NumberFormatException e) {
			System.out.println("Only Numbers range 0-9 is allowed");
		}
		catch(IpFormatInvalidException e) {
			System.out.println(e.getMessage());
		}

	}

}
