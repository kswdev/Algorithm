package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_6603 {

	private static int[] arr;
	private static boolean[] visited;
	private static int num;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		while(true) {
			String input = br.readLine();
			String[] inputSplit = input.split(" ");
			int n = Integer.parseInt(inputSplit[0]);
			if(n == 0) {
				break;
			}
			arr = new int[n];
			num = n;
			visited = new boolean[n];
			for(int i = 1; i <= n; i++) {
				arr[i-1] = Integer.parseInt(inputSplit[i]);
			}
			dfs(0, 0);
			System.out.println();
		}
	}
	
	public static void dfs(int n, int d) {
		if(d == 6) {
			for(int i = 0; i < num; i++) {
				if(visited[i]) {
					System.out.print(arr[i] + " ");
				}
			}
			System.out.println();
		}
		
		
		
		for(int i = n; i < num; i++) {
			visited[i] = true;
			dfs(i + 1, d + 1);
			visited[i] = false;
		}
	}
}
