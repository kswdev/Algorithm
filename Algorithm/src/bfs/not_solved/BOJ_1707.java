package bfs.not_solved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1707 {
	
	private static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < num; i++) {
			String input = br.readLine();
			String[] inputSplit = input.split(" ");
			
			int a = Integer.parseInt(inputSplit[0]);
			int b = Integer.parseInt(inputSplit[1]);
			
			arr = new int[a][a];
			
			for(int j = 0; j < b; j++) {
				String input2 = br.readLine();
				String[] input2Split = input2.split(" ");
				
				int n = Integer.parseInt(input2Split[0]);
				int m = Integer.parseInt(input2Split[1]);
				
				arr[n][m] = 1;
				arr[m][n] = 1;
			}
			bfs();
		}
	}
	public static void bfs() {
		
	}
}