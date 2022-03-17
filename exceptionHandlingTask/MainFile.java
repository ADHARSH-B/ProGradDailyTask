package ExceptionHandlingTask;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

	public static String check(String value, boolean isoperator) {
		Scanner inputScanner = new Scanner(System.in);

		try {
			if (!isoperator) {
				if (isvalidOperand(value))
					return value;
				else
					throw new NumberFormatException("Only number are allowed !!");
			} else {
				if (isValidOperator(value)) {
					return value;
				} else
					throw new InvalidOperator(value + " is Not a valid operator please try again!!");
			}

		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			System.out.print("Enter the operand value again:");
			return check(inputScanner.next(), false);
		} catch (InvalidOperator e) {

			System.out.println(e.getMessage());
			System.out.print("Enter the operator value again :");
			return check(inputScanner.next(), true);
		}

	}

	public static boolean isvalidOperand(String number) {
		if (!number.chars().allMatch(c -> Character.isDigit(c)) || number == "")
			return false;
		return true;
	}

	public static boolean isValidOperator(String operator) {

		if (operator.equals("+") || operator.equals("-") || operator.equals("/") || operator.equals("*"))
			return true;
		else
			return false;
	}

	public static int getResult(int num1, String operator, int num2) throws ArithmeticException {
		if (operator.equals("+"))
			return num1 + num2;
		else if (operator.equals("-"))
			return num1 - num2;
		else if (operator.equals("*"))
			return num1 * num2;
		else
			return num1 / num2;

	}

	public static void performArithmenticCalculations() throws ArithmeticException {
		Scanner inputScanner = new Scanner(System.in);
		System.out.print("Enter the operand 1 :");
		int operand1 = Integer.parseInt(check(inputScanner.next(), false));
		System.out.println("Success operand 1 fetched !!");
		System.out.print("Enter the operator  :");
		String operator = check(inputScanner.next(), true);
		System.out.println("Success operator  fetched !!");
		System.out.print("Enter the operand 2  :");
		int operand2 = Integer.parseInt(check(inputScanner.next(), false));
		System.out.println("Success operand 2 fetched !!");
		System.out.println("Result is :" + getResult(operand1, operator, operand2));
	}

	public static void writeFile(Student s[]) {
		File file = new File("Student.txt");

		try {
			file.createNewFile();

			if (file.canWrite()) {

				ObjectOutputStream oStream = new ObjectOutputStream(new FileOutputStream("Student.txt"));

				for (int i = 0; i < s.length; i++) {
					oStream.writeObject(s[i]);
				}
				oStream.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public static List<Student> filterStudentsByCollege(String name) {
//
//		try {
//			return readFile().stream().filter((Student s) -> s.getCollegeName().equals(name))
//					.collect(Collectors.toList());
//		} catch (FileNotFoundException e) {
//			System.out.println("No file found to read!!");
//		}
//		return null;
//	}

	public static void displayAllStudents() {
		try {
			readFile().forEach((Student s) -> {
				System.out.println("---------------------------------------------------");
				System.out.println("Name :" + s.getName());
				System.out.println("College :" + s.getCollegeName());
				System.out.println("Department :" + s.getDepartmentName());
				System.out.println("YearOfStudy :" + s.getYearOfStudy());
				System.out.println("---------------------------------------------------");
			});
		} catch (FileNotFoundException e) {
			System.out.println("Create file to read the file no file found to read!!");
		}
	}

	public static ArrayList<Student> readFile() throws FileNotFoundException {
		File file = new File("Student.txt");
		ArrayList<Student> stu = new ArrayList<Student>();

		if (file.exists()) {
			FileInputStream inputStream;
			try {
				ObjectInputStream iStream = new ObjectInputStream(new FileInputStream("Student.txt"));
				boolean cont = true;
				while (cont) {
					Student Objec = (Student) iStream.readObject();
					if (Objec != null) {
						Objec = (Student) Objec;
						stu.add(Objec);

					} else
						cont = false;
				}
				iStream.close();

			} catch (ClassNotFoundException e) {
				System.out.println("Class Not Found !!");
			} catch (FileNotFoundException e) {
				System.out.println("File Not Found!!");

			} catch (EOFException e) {
				System.out.println("End of File");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return stu;
		} else {
			throw new FileNotFoundException("File not Found !!");
		}

	}

	public static Student[] getStudentData() {
		Student s1 = new Student(1, "adharsh", "BIT", "EEE", "4th");
		Student s2 = new Student(2, "akash", "BIT", "CSE", "3RD");
		Student s3 = new Student(3, "MUKESH", "CIT", "EIE", "2ND");
		Student s4 = new Student(4, "VIRAT", "VIT", "ECE", "1ST");
		Student s5 = new Student(5, "DHONI", "NIT", "IT", "4TH");
		Student s6 = new Student(5, "SACHIN", "IIT", "IT", "3RD");

		Student s[] = { s1, s2, s3, s4, s5, s6 };
		return s;
	}

	public static void fileHandling() {

		writeFile(getStudentData());
		displayAllStudents();
		
		
		
//		System.out.println("hey");
//		filterStudentsByCollege("BIT").forEach((Student s) -> {
//			System.out.println("---------------------------------------------------");
//			System.out.println("Name :" + s.getName());
//			System.out.println("College :" + s.getCollegeName());
//			System.out.println("Department :" + s.getDepartmentName());
//			System.out.println("YearOfStudy :" + s.getYearOfStudy());
//			System.out.println("---------------------------------------------------");
//		});
	}

	public static void main(String[] args) {
		// fileHandling();

		try {
			performArithmenticCalculations();
		}

		catch (ArithmeticException e) {
			System.out.println("Division by zero is Not allowed !!");
		}
	}

}
