package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11054 {

	private static int num;
	private static int[] arr;
	private static Integer[] leftDp;
	private static Integer[] rightDp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		num		= Integer.parseInt(br.readLine());
		
		arr 	= new int[num];
		leftDp  = new Integer[num];
		rightDp = new Integer[num];
		
		String input = br.readLine();
		String[] inputSplit = input.split(" ");
		
		for(int i = 0; i < num; i++) {
			arr[i] = Integer.parseInt(inputSplit[i]);
		}
		
		int max	= 0;
		
		for(int j =0; j < num; j++) {
			
			if(max < left(j) + right(j)) {
				max = left(j) + right(j);
			}
		}
		System.out.println(max -1);
	}
	public static int left(int n) {
		if(leftDp[n] == null) {
			leftDp[n] = 1;
			
			for(int i = n; i >= 0; i--) {
				if(arr[n] > arr[i]) {
					leftDp[n] = Math.max(leftDp[n], left(i) + 1);
				}
			}
		}
		return leftDp[n];
	}
	
	public static int right(int n) {
		if(rightDp[n] == null) {
			rightDp[n] = 1;
			
			for(int i = n; i < num; i++) {
				if(arr[i] < arr[n]) {
					rightDp[n] = Math.max(rightDp[n], right(i) + 1);
				}
			}
		}
		return rightDp[n];
	}
}
