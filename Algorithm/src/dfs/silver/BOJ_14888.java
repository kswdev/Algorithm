package dfs.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14888 {

	private static int[] cal = new int[4];
	private static int[] arr;
	
	private static int max = Integer.MIN_VALUE;
	private static int min = Integer.MAX_VALUE;
	
	
	private static int num;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		num = Integer.parseInt(br.readLine());
		
		arr = new int[num];
		
		String input 	= br.readLine();
		String inputCal = br.readLine();
		String[] inputSplit = input.split(" ");
		String[] inCalSplit = inputCal.split(" ");
		
		for(int i = 0; i < num; i++) {
			arr[i] = Integer.parseInt(inputSplit[i]);
		}
		
		for(int i = 0; i < 4; i++) {
			cal[i] = Integer.parseInt(inCalSplit[i]);
		}
		
		dfs(arr[0], 1);
		System.out.println(max);
		System.out.println(min);
	}
	
	public static void dfs(int n, int d) {
		if(d == num) {
			max = Math.max(max, n);
			min = Math.min(min, n);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(cal[i] > 0) {
				cal[i]--;
				if(i == 0) {					
					dfs(n + arr[d], d+1);
				} else if(i == 1) {
					dfs(n - arr[d], d+1);
				} else if(i == 2) {
					dfs(n * arr[d], d+1);
				} else if(i == 3) {
					dfs(n / arr[d], d+1);
				}
				cal[i]++;
			}
		}
	}
}
