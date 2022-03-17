import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
public class Permutations {
	public static int  Factorial(int num) {
		int fact=1;
		for(int i=1;i<=num;i++)
			fact=fact*i;
		return fact;
	}
	public static void GeneratePermutations(String s) {
		HashMap<Character,Integer> s1=new HashMap<Character,Integer>();
		for(int i=0;i<s.length();i++) {
			if(s1.get(s.charAt(i))==null) {
				s1.put(s.charAt(i), 1);
			}
			else {
				s1.put(s.charAt(i), s1.get(s.charAt(i))+1);
			}
			}
		int result=1;
		for (Entry<Character, Integer> set :s1.entrySet()) {
			result=result*Factorial(set.getValue());
       }
		System.out.println(Factorial(s.length())/Factorial(result));
		}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String s=sc.next();
		 GeneratePermutations(s);
	}

}
