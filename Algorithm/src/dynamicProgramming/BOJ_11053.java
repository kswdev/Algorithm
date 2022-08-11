package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11053 {

	private static Integer[] dp;
	private static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		dp  = new Integer[num];
		arr = new int[num];
		String input   		= br.readLine();
		String[] inputSplit = input.split(" ");
		
		for(int i = 0; i < inputSplit.length; i++) {
			arr[i] = Integer.parseInt(inputSplit[i]);
		}
		for(int i = 0; i < num; i++) {
			LIS(i);
		}
		
		int max = dp[0];
		
		for(int i = 1; i < num; i++) {
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
	public static int LIS(int n) {
		
		if(dp[n] == null) {
			dp[n] = 1;
			
			for(int i = n-1; i >= 0; i--) {
				
				if(arr[i] < arr[n]) {
					dp[n] = Math.max(dp[n], LIS(i) + 1);
				}
			}
		}
		
		return dp[n];
	}
}
