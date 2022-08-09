package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11047 {
	
	private static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		String[] inputSplit = input.split(" ");
		
		int coinNum = Integer.parseInt(inputSplit[0]);
		int value   = Integer.parseInt(inputSplit[1]);
		int count   = 0;
		arr = new int[coinNum];
		
		for(int i = 0; i < coinNum; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = coinNum-1; i >= 0; i--) {
	
			if(arr[i] < value) {
				count += value/arr[i];
				value  = value%arr[i];
			}
		}
		System.out.println(count);
	}
}
