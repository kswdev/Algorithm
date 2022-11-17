package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11052 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n+1];
		int[] dp  = new int[n+1];
		
		String input = br.readLine();
		String[] inputSplit = input.split(" ");
		
		for(int i = 0; i < n; i++) {
			arr[i + 1] = Integer.parseInt(inputSplit[i]); 
		}
		
		for(int i = 1; i < n+1; i++) {
			for(int j = 1; j <= i; j++) {
				dp[i] = Math.max(dp[i], dp[i-j]+arr[j]);
			}
		}
		System.out.println(dp[n]);
	}
}
