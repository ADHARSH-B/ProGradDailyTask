package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ProductPairs {
	public static int getProductCount(int arr[], int size, int product) {
		HashMap<Integer, Integer> arr1 = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> map1 = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> map2 = new HashMap<Integer, Integer>();

		int count = 0;

		for (int i = 0; i < size; i++) {
			if (arr1.get(arr[i]) == null)
				arr1.put(arr[i], 1);
		}
		for (int i = 0; i < size; i++) {
			if (map1.get(arr[i]) == null && map2.get(arr[i]) == null
					|| map1.get(product / arr[i]) == null && map2.get(product / arr[i]) == null) {
				if (product % arr[i] == 0 && arr1.get(product / arr[i]) != null) {
					map1.put(arr[i], 1);
					map2.put(product / arr[i], 1);
					count += 1;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = Integer.parseInt(sc.next());
		int arr[] = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = Integer.parseInt(sc.next());
		}
		int product = Integer.parseInt(sc.next());
		System.out.println(getProductCount(arr, size, product));

	}

}
