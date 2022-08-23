package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9012 {

	private static Stack<Character> q;
	private static String[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		q = new Stack<>();
		arr = new String[num];
		for(int i = 0; i < num; i++) {
			String input = br.readLine();
			q.clear();
			q.push('D');
			for(int j = 0; j < input.length(); j++) {
				if(input.charAt(j) == '(') {
					q.push('(');
					
				} else if(q.peek() == '(' && input.charAt(j) == ')') {
					q.pop();
					
				} else {
					q.push(input.charAt(j));
				}
			}
			if(q.size() == 1) {
				arr[i] = "YES";
			} else {
				arr[i] = "NO";
			}
		}
		
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
}
