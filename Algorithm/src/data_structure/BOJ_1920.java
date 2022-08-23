package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1920 {
	private static int[] arr;
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		String input = br.readLine();
		String[] inputSplit = input.split(" ");
		arr = new int[n];
		for(int i = 0; i < n; i++) {
			
			arr[i] = Integer.parseInt(inputSplit[i]);
		}
		
		Arrays.sort(arr);
		int m = Integer.parseInt(br.readLine());
		
		String input2 = br.readLine();
		String[] input2Split = input2.split(" ");
		
		for(int j = 0; j < m; j++) {
			if(Arrays.binarySearch(arr, Integer.parseInt(input2Split[j])) >= 0) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}
		
		
	}
}
