package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1541 {

	private static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int input = Integer.parseInt(br.readLine());
		
		arr = new int[input];
		
		String inputNum   = br.readLine();
		String[] numSplit = inputNum.split(" ");
		
		for(int i = 0; i < numSplit.length; i++) {
			arr[i] = Integer.parseInt(numSplit[i]);
		}
		Arrays.sort(arr);
		
		int sum = arr[0];
		for(int i = 1; i < arr.length; i++) {
			arr[i] += arr[i-1];
			sum += arr[i];
		}
		System.out.println(sum);
	}
}
