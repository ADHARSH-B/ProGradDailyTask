package problems;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class BalancedParanthesis {
	static Map<Character, Character> map  = new HashMap<Character, Character>() {{
	    put('[', ']');
	    put('{', '}');
	    put('(', ')');
	    put(']', '[');
	    put('}', '{');
	    put(')', '(');
	}};
	
	public static String generateValidParanthesis(String s) {
		Stack<Character> stack = new Stack<Character>();
		StringBuilder ss = new StringBuilder();
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='[' || s.charAt(i)=='{' || s.charAt(i)=='(') {
				ss.append(s.charAt(i));
				stack.push(s.charAt(i));
			}
			else {
				if(!stack.empty() && stack.peek()==map.get(s.charAt(i))) {
					ss.append(s.charAt(i));
					stack.pop();
				}
				else if(!stack.empty() && stack.peek()!=map.get(s.charAt(i))) {
					char c=stack.pop();
					
					ss.append(map.get(c));
					i--;
				}
				else if(stack.empty()) {
					ss.append(map.get(s.charAt(i)));
					ss.append(s.charAt(i));
				}
				
			}
			
		}
	
		while(!stack.empty()) {
			char c=stack.pop();
			ss.append(map.get(c));
		}
		
		return ss.toString();
	}
	
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println(generateValidParanthesis(sc.next()));
	}
}
