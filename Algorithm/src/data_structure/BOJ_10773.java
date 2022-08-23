package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_10773 {
	
	private static Stack<Integer> s;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		int sum = 0;
		
		s = new Stack<>();
		
		for(int i = 0; i < num; i++) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0) {
				s.pop();
			}
			else {
				s.add(n);
			}
		}
		
		while(!s.isEmpty()) {			
			sum += s.pop();
		}
		System.out.println(sum);
	}
}
