import java.util.*;

public class ReplaceAdjacentElements {
	static ArrayList<Integer> list = new ArrayList<Integer>();

	public static void reverseList() {
		if (list.size() == 0 || list.size() == 1)
			return;

		else if (list.size() % 2 == 0) {
			for (int i = 0; i < list.size() - 1; i += 2) {
				int temp = list.get(i + 1);
				list.set(i + 1, list.get(i));
				list.set(i, temp);
			}
		} else {
			for (int i = 0; i < list.size() - 2; i += 2) {
				int temp = list.get(i + 1);
				list.set(i + 1, list.get(i));
				list.set(i, temp);
			}
		}
	
	}

	public static void printList() {
		if(list.size()==0) {
			System.out.println("Array List is empty no elements to print !!");
			return;
		}
		System.out.println("The numbers are :");
		for (Integer s : list) {
			System.out.print(s);
			
			System.out.print("  ");
		}
	}

	public static void getInput() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the size of the ArrayList :");
		int size=Integer.parseInt(sc.next());
		System.out.println();
		
		for(int i=0;i<size;i++) {
			System.out.print("Enter the Number :");
			list.add(Integer.parseInt(sc.next()));
			System.out.println();
		}
//		while (true) {
//			Scanner sc = new Scanner(System.in);
//			String s = sc.next();
//			if (s.equals("break"))
//				break;
//			list.add(Integer.parseInt(s));
//
//		}
	}

	public static void main(String[] args) {
		getInput();
		reverseList();
		printList();
	}
}
